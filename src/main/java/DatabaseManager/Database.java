/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseManager;

/**
 *
 * @author Faizan Hussain S
 */



import BeanClasses.AttendanceBean;
import BeanClasses.DepartmentBean;
import BeanClasses.LoginBean;
import BeanClasses.StudentBean;
import java.io.FileWriter;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;


public class Database {
    
    private static Connection con;
      private static Statement st;
      private static ResultSet rs;
    
    static{
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("Driver Connected...");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/facedetectiondatabase","root","");
            System.out.println("Database Connected Successfully...");
        }
        catch(Exception e){
            e.printStackTrace();
        }    
    }
    
    
    
public static void generateCSV(int deptId, String date) throws Exception {
    String query = "SELECT s.std_name,s.father_name,s.surname,s.roll_no,a.attendance_date FROM students s JOIN department d ON (d.dept_id = s.dept_id) JOIN attendance a ON (a.std_id = s.std_id) where d.dept_id=" + deptId + " AND a.attendance_date like '" + date + "'";
    System.out.println(query);

    Statement st = null;
    ResultSet result = null;
    FileWriter writer = null;

    try {
        st = con.createStatement();
        result = st.executeQuery(query);

        // Define CSV file path
        String csvFilePath = "attendance.csv";
        writer = new FileWriter(csvFilePath);

        // Write CSV header
        writer.append("Student Name");
        writer.append(",");
        writer.append("Father Name");
        writer.append(",");
        writer.append("Surname");
        writer.append(",");
        writer.append("Roll No");
        writer.append(",");
        writer.append("Attendance Date");
        writer.append("\n");

        // Write data to CSV
        while (result.next()) {
            String stdName = result.getString("std_name");
            String fatherName = result.getString("father_name");
            String surname = result.getString("surname");
            String rollNo = result.getString("roll_no");
            String attendanceDate = result.getString("attendance_date");

            writer.append(stdName);
            writer.append(",");
            writer.append(fatherName);
            writer.append(",");
            writer.append(surname);
            writer.append(",");
            writer.append(rollNo);
            writer.append(",");
            writer.append(attendanceDate);
            writer.append("\n");
        }

        System.out.println("CSV file created successfully!");
        javax.swing.JOptionPane.showMessageDialog(null,"CSV file created successfully!");
    } finally {
        if (st != null) st.close();
        if (result != null) result.close();
        if (writer != null) writer.close();
    }
}
    
    
    
    
    
    public static Vector getStudent(String name)throws Exception{// start getStudent()
        String query="SELECT * FROM students WHERE std_name='"+name+"'";
         System.out.println(query);
        
        Statement st=null;
        ResultSet result=null;

        try{
            st=con.createStatement();
            result=st.executeQuery(query);
            Vector v=new Vector();
            
            while(result.next()){
                StudentBean bean=new StudentBean();
              
                bean.setStdId(result.getInt("std_id"));
                bean.setStdName(result.getString("std_name"));
                bean.setRemarks(result.getString("remarks"));
               
                v.addElement(bean);
            }
            
            return v;
        }finally{
            if(st!=null)st.close();
            if(result!=null)result.close();
        }
        
       
    }// End getStudent()
    
    public static Vector getAttendance(int stdId,String attendanceDate)throws Exception{// start getAttendance
        String query="SELECT * FROM `attendance` WHERE  std_id="+stdId+" and attendance_date='"+attendanceDate+"'";
          System.out.println(query);
        
        Statement st=null;
        ResultSet result=null;

        try{
            st=con.createStatement();
            result=st.executeQuery(query);
            Vector v=new Vector();
            
            while(result.next()){
                AttendanceBean bean=new AttendanceBean();
                
                bean.setStdId(result.getInt("std_id"));
                bean.setAttendanceId(result.getInt("attendance_id"));
                bean.setAttendanceDate(result.getString("attendance_date"));
                bean.setRemarks(result.getString("remarks"));
                
                v.addElement(bean);
            }
            
            return v;
        
        }finally{
            if(st!=null)st.close();
            if(result!=null)result.close();
        }
    }// End getAttendance
    
    public static int addAttendance(int stdId,String attendanceDate)throws Exception{
        String query="INSERT INTO `attendance`(`std_id`,`attendance_date`,remarks) VALUES ("+stdId+",'"+attendanceDate+"','good')";
          System.out.println(query);
        Statement st=null;
        try{
            st=con.createStatement();
            int rows=st.executeUpdate(query);
            return rows;
        }
        finally{
         if(st!=null)st.close();
        }   
    }
    
    
    public static Vector getDepartment() throws Exception{
    
        String query="SELECT * FROM department";
        System.out.println(query);  
        try{
             st = con.createStatement();
             rs = st.executeQuery(query);
             Vector v = new Vector();
           while(rs.next()){
             DepartmentBean bean=new DepartmentBean();
            // bean.setFacId(rs.getInt("FAC_ID"));
             bean.setDeptId(rs.getInt("DEPT_ID"));
             bean.setDeptName(rs.getString("DEPT_NAME"));
             bean.setRemarks(rs.getString("REMARKS"));
            
             v.addElement(bean);
           }//end of while
            return v; 
        }finally{
        
            if(rs!=null)
                rs.close();
           if(st!=null)st.close();
        }/// end of finally
}//end of vector getDepartment
    
    
    
    
    
    public static Vector  getStudents()throws Exception{
    
    String query="SELECT * FROM students";
        System.out.println(query);
        try{
             st = con.createStatement();
             rs = st.executeQuery(query);
             Vector v = new Vector();
           while(rs.next()){
             StudentBean bean=new StudentBean();
             
             //bean.setDeptIdId(rs.getInt("DEPT_ID"));
             bean.setStdId(rs.getInt("STD_ID"));
             bean.setDeptId(rs.getInt("DEPT_ID"));
             bean.setStdName(rs.getString("STD_NAME"));
             bean.setfName(rs.getString("FATHER_NAME"));
             bean.setSurName(rs.getString("SURNAME"));
             bean.setRollNo(rs.getString("ROLL_NO"));
             bean.setRemarks(rs.getString("REMARKS"));
             
             v.addElement(bean);
           }//end of while
            return v; 
        }finally{
        
            if(rs!=null)
                rs.close();
           if(st!=null)st.close();
        }/// end of finally
}// end of Vector getStudent
    
     public static Vector  getStudents(int deptId)throws Exception{
    
    String query="SELECT * FROM students where DEPT_ID="+deptId;
        System.out.println(query);
        try{
             st = con.createStatement();
             rs = st.executeQuery(query);
             Vector v = new Vector();
           while(rs.next()){
             StudentBean bean=new StudentBean();
             
             //bean.setDeptIdId(rs.getInt("DEPT_ID"));
             bean.setStdId(rs.getInt("STD_ID"));
             bean.setDeptId(rs.getInt("DEPT_ID"));
             bean.setStdName(rs.getString("STD_NAME"));
             bean.setfName(rs.getString("FATHER_NAME"));
             bean.setSurName(rs.getString("SURNAME"));
             bean.setRollNo(rs.getString("ROLL_NO"));
             bean.setRemarks(rs.getString("REMARKS"));
             
             v.addElement(bean);
           }//end of while
            return v; 
        }finally{
        
            if(rs!=null)
                rs.close();
           if(st!=null)st.close();
        }/// end of finally
}// end of Vector getStudent

    public static int userLoginFrom(String userName, String pass) throws Exception {
    String sql = "SELECT * FROM Login WHERE USER_NAME=? AND USER_PASS=?";
    System.out.println(sql);
    PreparedStatement pst = null;
    ResultSet rs = null;
    try {
        pst = con.prepareStatement(sql);
        pst.setString(1, userName);
        pst.setString(2, pass);
        rs = pst.executeQuery();

        if (rs.next()) {
            // If the query returns at least one row, the username and password are correct
            return 1;
        } else {
            // If the query returns no rows, the username and password do not match
            return 0;
        }
    } finally {
        // Close resources in the finally block to ensure proper cleanup
        if (rs != null) rs.close();
        if (pst != null) pst.close();
    }
}
            
            
     public static int addRegsterFrom(String userName,String pass, String email)throws Exception{
        String query="insert into Login (USER_NAME,USER_PASS,EMAIL) values ('"+userName+"','"+pass+"','"+email+"')";
            System.out.println(query);
            Statement st=null;
            try{
                st=con.createStatement();
                int rows=st.executeUpdate(query);
                return rows;
            }finally{
                if(st!=null)st.close();
            }
        }//end add customer

     

     
   public static int addStudent(int deptId, String studName, String remarks,String father_name,String surname,String rollNo,String shift,String droupDes) throws Exception {
    String query = "INSERT INTO students (dept_id, std_name, remarks,father_name,surname,roll_no,shift,groupDes) VALUES (?, ?, ?,?,?,?,?,?)";
    System.out.println(query);
    PreparedStatement st = null;
    try {
        st = con.prepareStatement(query);
        st.setInt(1, deptId);
        st.setString(2, studName);
        st.setString(3, remarks);
        st.setString(4, father_name);
        st.setString(5, surname);
        st.setString(6, rollNo);
        st.setString(7, shift);
        st.setString(8, droupDes);
        
        int rows = st.executeUpdate();
        return rows;
    } finally {
        if (st != null) st.close();
    }
}
   
   public static int updateStudent(int studentId, int deptId, String studName, String remarks, String father_name, String surname, String rollNo, String shift, String droupDes) throws Exception {
    String query = "UPDATE students SET dept_id = ?, std_name = ?, remarks = ?, father_name = ?, surname = ?, roll_no = ?, shift = ?, groupDes = ? WHERE std_id = ?";
    System.out.println(query);
    PreparedStatement st = null;
    try {
        st = con.prepareStatement(query);
        st.setInt(1, deptId);
        st.setString(2, studName);
        st.setString(3, remarks);
        st.setString(4, father_name);
        st.setString(5, surname);
        st.setString(6, rollNo);
        st.setString(7, shift);
        st.setString(8, droupDes);
        st.setInt(9, studentId); // Assuming studentId is the unique identifier
        
        int rows = st.executeUpdate();
        return rows;
    } finally {
        if (st != null) st.close();
    }
}
   
   public static int deleteStudent(int studentId) throws Exception {
    String query = "DELETE FROM students WHERE std_id = ?";
    PreparedStatement st = null;
    try {
        st = con.prepareStatement(query);
        st.setInt(1, studentId);
        
        int rows = st.executeUpdate();
        return rows;
    } finally {
        if (st != null) st.close();
    }
}

   public static int deleteDepartment(int deptId)throws Exception{
        String query="delete from department where DEPT_ID="+deptId;
         System.out.println(query);
         Statement st=null;
        try{
             st=con.createStatement();
             int rows=st.executeUpdate(query);
             return rows;
             
        }finally{
             if(st!=null){
                 st.close();
             }
        }//end finally
    }// end DeleteDepartment
   
   
     public static int updateDepartment(int deptId, String deptName, String remarks)throws Exception{         
        String query = "update department set DEPT_NAME='"+deptName+"', REMARKS='"+remarks+"'where DEPT_ID="+deptId;
        System.out.println(query);
        Statement st=null;
        try{
            
            st=con.createStatement();
            int rows=st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null){
              st.close();
            }
        }
    }// end update Department
     
     
       public static int addDepartment(String deptName, String remarks)throws Exception{
        String query="insert into department(DEPT_NAME,REMARKS) values ('"+deptName+"','"+remarks+"')";
            System.out.println(query);
            Statement st=null;
            try{
                st=con.createStatement();
                int rows=st.executeUpdate(query);
                return rows;
            }finally{
                if(st!=null)st.close();
            }
        }// end add Department
       




     
     
     
     
     
     
     
    public static void main(String arg[]){
        Database ob=new Database();
    }
}
