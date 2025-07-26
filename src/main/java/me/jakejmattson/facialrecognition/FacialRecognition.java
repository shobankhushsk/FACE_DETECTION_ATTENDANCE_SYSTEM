package me.jakejmattson.facialrecognition;

import BeanClasses.StudentBean;
import DatabaseManager.Database;
import java.applet.Applet;
import java.applet.AudioClip;
import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.features2d.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import javax.swing.JOptionPane;
import java.awt.Font;
import java.io.File;
import java.net.URL;
import java.util.Objects;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Demo (main) class to run functions.
 *
 * @author JakeJMattson
 */
public class FacialRecognition{
    /**
     * Directory on the disk containing faces
     */
    // Testing Attendace start part 1
    
    private static Map<String, Boolean> attendanceMap = new HashMap<>();
    
    // Testing Attendace end part 1
    private static final File DATABASE = new File("Database");
    
    private static VideoCapture camera;
    private static CascadeClassifier faceDetector;
    private static ImageFrame frame;
    
    
    // Add these variables to the FacialRecognition class
    private static int consecutiveMatches = 0;
    private static String currentFaceID = null;

    public static void main(String[] args){
        OpenCV.loadLocally();
        capture();
        System.exit(0);
    }
    

     private static void capture(){
        camera = new VideoCapture();
        camera.open(0);
        File classifier = new File("lbpcascade_frontalface_improved.xml");

        if (!classifier.exists()){
            displayFatalError("Unable to find classifier!");
            return;
        }

        faceDetector = new CascadeClassifier(classifier.toString());
       
        if (!camera.isOpened()){
            displayFatalError("No camera detected!");
            return;
        }

        if (!DATABASE.exists())
            DATABASE.mkdir();

           frame = new ImageFrame();
           
     
        while (frame.isOpen() && camera.isOpened()){
            Mat rawImage = new Mat();
            camera.read(rawImage);
            Mat newImage = detectFaces(rawImage, faceDetector, frame);
            frame.showImage(newImage);
            
        }

        camera.release();
       
    }
     
    private static Mat detectFaces(Mat image, CascadeClassifier faceDetector, ImageFrame frame){
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
        Rect[] faces = faceDetections.toArray();
        boolean shouldSave = frame.shouldSave();
        String name = frame.getFileName();
        Scalar color = frame.getTextColor();
        
        
      

        for (Rect face : faces){
//            Thread Treadtwo=new Thread(()-> {
            
            
          
            Mat croppedImage = new Mat(image, face);
            
            // Testing Attendace start part 2
            String faceID = identifyFace(croppedImage);
            
            // Check if the detected face is the same as the current face
            if (currentFaceID == null || currentFaceID.equals(faceID)) {
                consecutiveMatches++;
                currentFaceID = faceID;
            } else {
                // Different face detected, reset the counter and update the current face
                consecutiveMatches = 1;
                currentFaceID = faceID;
            }
            
            // After 10 consecutive matches, mark attendance
            if (consecutiveMatches >= 10) {
            
                // Check if the face has already been marked for attendance
                if (!attendanceMap.containsKey(faceID) || !attendanceMap.get(faceID)) {
                     // Face not marked for attendance, mark it and print attendance
                    attendanceMap.put(faceID, true);
                    System.out.println("detect for face ID: " + faceID);
                    try{
                        Vector v=Database.getStudent(faceID);
                        for(int i=0; i<v.size(); i++){
                            StudentBean bean=(StudentBean)v.elementAt(i);
                            String date=""+java.time.LocalDate.now();// date
                            Vector v2=Database.getAttendance(bean.getStdId(), date);
                            if(v2.size()==0){
                              int rows=Database.addAttendance(bean.getStdId(), date);
                                if(rows>=1){
                                    javax.swing.JOptionPane.showMessageDialog(null,"Attendance recorded for face ID: " + faceID+" Date: "+date);
                                 //  A_Sound();
                                }
                            }
                        }
                    }catch(Exception e){
                                    e.printStackTrace();
                    }
                }
            //Testing Attendace end part 2
            
            consecutiveMatches = 0;  // Reset the counter after marking attendance
        }
            
            if (shouldSave)
                saveImage(croppedImage, name);
                
            
            Imgproc.putText(image, "ID: " +faceID , face.tl(), Font.BOLD, 1.5, color);
            Imgproc.rectangle(image, face.tl(), face.br(), color);
                        
        }

        int faceCount = faces.length;
        String message = faceCount + " face" + (faceCount == 1 ? "" : "s") + " detected!";
     
        Imgproc.putText(image, message, new Point(3, 25), Font.BOLD, 2, color);
        
        return image;
    }// close detectFaces
    
    private static String identifyFace(Mat image){//
        
        int errorThreshold = 3;
        int mostSimilar = -1;
        File mostSimilarFile = null;
        
        
       
         
        for (File capture : Objects.requireNonNull(DATABASE.listFiles())){
            
          
          
            int similarities = compareFaces(image, capture.getAbsolutePath());

            if (similarities > mostSimilar){
                mostSimilar = similarities;
                mostSimilarFile = capture;
            }
            
        }
      
        if (mostSimilarFile != null && mostSimilar > errorThreshold){
            
            String faceID = mostSimilarFile.getName();
            String delimiter = faceID.contains(" (") ? "(" : ".";
            
            return faceID.substring(0, faceID.indexOf(delimiter)).trim();
            
        }
        else
            return "???";
        
      
    }

    private static int compareFaces(Mat currentImage, String fileName){
       
        Mat compareImage = Imgcodecs.imread(fileName);
        ORB orb = ORB.create();
        int similarity = 0;

        MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
        MatOfKeyPoint keypoints2 = new MatOfKeyPoint();
        orb.detect(currentImage, keypoints1);
        orb.detect(compareImage, keypoints2);

        Mat descriptors1 = new Mat();
        Mat descriptors2 = new Mat();
        orb.compute(currentImage, keypoints1, descriptors1);
        orb.compute(compareImage, keypoints2, descriptors2);

        if (descriptors1.cols() == descriptors2.cols()){
            MatOfDMatch matchMatrix = new MatOfDMatch();
            DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING).match(descriptors1, descriptors2, matchMatrix);

            for (DMatch match : matchMatrix.toList())
                if (match.distance <= 50)
                    similarity++;
        }
        return similarity;
        
    }
    
    private static void saveImage(Mat image, String name)
    {
        File destination;
        String extension = ".png";
        String baseName = DATABASE + File.separator + name;
        File basic = new File(baseName + extension);

        if (!basic.exists())
            destination = basic;
        else
        {
            int index = 0;

            do
                destination = new File(baseName + " (" + index++ + ")" + extension);
            while (destination.exists());
        }

        Imgcodecs.imwrite(destination.toString(), image);
    }

    private static void displayFatalError(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Fatal Error", JOptionPane.ERROR_MESSAGE);
    }

    void show() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    public static void A_Sound(){	
        try{
	      URL url=new URL("file:e:\\wa\\A for Apple.wav");
	      AudioClip clip=Applet.newAudioClip(url);
		clip.play();
	  }
	  catch(Exception e){
		e.printStackTrace();
	  }
 }
    
    
}