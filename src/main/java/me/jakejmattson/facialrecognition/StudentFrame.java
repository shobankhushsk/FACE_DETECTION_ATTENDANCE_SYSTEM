/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package me.jakejmattson.facialrecognition;

import BeanClasses.*;
import DatabaseManager.Database;
//import DatabaseManager.DatabaseManager;
import java.util.Vector;
//import EncoderDecoder.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class StudentFrame extends javax.swing.JFrame {

    /**
     * Creates new form Student
     */
    public StudentFrame() {
        initComponents();
        
        //getFaculty();
        getDepartment();
        getStudents();
        java.awt.Toolkit kit=this.getToolkit();
        java.awt.Dimension d=kit.getScreenSize();
        this.setBounds(0,0,d.width,d.height);
    }
        
    
       private void  getDepartment(){
     try {
            Vector v = Database.getDepartment();   
            
            for(int i = 0; i<v.size(); i++){
                this.departmentComboBox.addItem(v.elementAt(i));
            }
        } catch (Exception ex) {
            Logger.getLogger(StudentFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }  
       private void getStudents(){ 
      try {
            Vector v = Database.getStudents();   
            this.stdNameList.setListData(v);
        } catch (Exception ex) {
            Logger.getLogger(StudentFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
     private void addRecord(){
    
     DepartmentBean selectedBean = (DepartmentBean) departmentComboBox.getSelectedItem();
    if(selectedBean==null)return;    
       int deptId = selectedBean.getDeptId();
       
       String  name = nameTextField.getText();
       String  fName = fatherNameTextField.getText();
       String surName=surNameTextField.getText();
       String rollNo=rollNoTextField.getText();
       String remarks = remarksTextArea.getText();
       String shift = (String)shiftComboBox.getSelectedItem();
       String groupDec = (String)groupComboBox.getSelectedItem();
        
        try {
            int row = Database.addStudent(deptId,name,remarks,fName,surName,rollNo,shift,groupDec);
            
            if(row>0){
                JOptionPane.showMessageDialog(null,"RECORD ADDED SUCCESSFULLY");
              getStudents();
              clear();
            }else{
                JOptionPane.showMessageDialog(null,"Something went wrong");
            }
            
        } catch (Exception ex) {
            Logger.getLogger(StudentFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
     
     private void updateRecord() {
    DepartmentBean selectedBean = (DepartmentBean) departmentComboBox.getSelectedItem();
    if (selectedBean == null) return;    
    int deptId = selectedBean.getDeptId();
       
    // Get the unique identifier for the record to be updated
    int studentId = Integer.parseInt(studentIdTextField.getText()); // Assuming studentIdTextField contains the unique identifier
    
    String name = nameTextField.getText();
    String fName = fatherNameTextField.getText();
    String surName = surNameTextField.getText();
    String rollNo = rollNoTextField.getText();
    String remarks = remarksTextArea.getText();
    String shift = (String) shiftComboBox.getSelectedItem();
    String groupDec = (String) groupComboBox.getSelectedItem();
        
    try {
        int row = Database.updateStudent(studentId, deptId, name, remarks, fName, surName, rollNo, shift, groupDec);
            
        if (row > 0) {
            JOptionPane.showMessageDialog(null, "RECORD UPDATED SUCCESSFULLY");
            getStudents();
            clear();
        } else {
            JOptionPane.showMessageDialog(null, "No record updated");
        }
    } catch (Exception ex) {
        Logger.getLogger(StudentFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
}

private void deleteRecord() {
    // Assuming you have a text field where the user enters the student ID to be deleted
    int studentId = Integer.parseInt(studentIdTextField.getText());
    
    try {
        int row = Database.deleteStudent(studentId);
        
        if (row > 0) {
            JOptionPane.showMessageDialog(null, "RECORD DELETED SUCCESSFULLY");
            getStudents();
            clear();
        } else {
            JOptionPane.showMessageDialog(null, "No record deleted");
        }
    } catch (Exception ex) {
        Logger.getLogger(StudentFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
}
   
      private void clear(){
       studentIdTextField.setText("");
       nameTextField.setText("");
       fatherNameTextField.setText("");
       surNameTextField.setText("");
       rollNoTextField.setText("");
       remarksTextArea.setText("");
      
      }
//    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        departmentComboBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        studentIdTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        fatherNameTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        surNameTextField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        rollNoTextField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        stdNameList = new javax.swing.JList();
        jLabel14 = new javax.swing.JLabel();
        addButton1 = new javax.swing.JButton();
        updateButton1 = new javax.swing.JButton();
        deleteButton1 = new javax.swing.JToggleButton();
        clearButton1 = new javax.swing.JToggleButton();
        backButton1 = new javax.swing.JToggleButton();
        shiftComboBox = new javax.swing.JComboBox();
        groupComboBox = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("STUDENT REGISTRATION");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 460, 40));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel3.setText("DEPARTMENT");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 170, 40));

        departmentComboBox.setFont(new java.awt.Font("Arial Narrow", 0, 24)); // NOI18N
        departmentComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                departmentComboBoxItemStateChanged(evt);
            }
        });
        departmentComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departmentComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(departmentComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 810, 40));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel6.setText("SHIFT");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 70, 20));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("GROUP");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, 100, 20));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel8.setText("STUDENT ID");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 150, 40));

        studentIdTextField.setEditable(false);
        studentIdTextField.setFont(new java.awt.Font("Arial Narrow", 0, 24)); // NOI18N
        studentIdTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentIdTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(studentIdTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 200, 40));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel9.setText("NAME");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 80, 40));

        nameTextField.setFont(new java.awt.Font("Arial Narrow", 0, 24)); // NOI18N
        nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(nameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 640, 40));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel10.setText("FATHER's NAME");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 200, 40));

        fatherNameTextField.setFont(new java.awt.Font("Arial Narrow", 0, 24)); // NOI18N
        getContentPane().add(fatherNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 360, 640, 40));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel11.setText("SURNAME");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, 130, 40));

        surNameTextField.setFont(new java.awt.Font("Arial Narrow", 0, 24)); // NOI18N
        getContentPane().add(surNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 410, 640, 40));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel12.setText("ROLL-NO");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 460, 120, 40));

        rollNoTextField.setFont(new java.awt.Font("Arial Narrow", 0, 24)); // NOI18N
        rollNoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rollNoTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(rollNoTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 460, 640, 40));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel13.setText("REMARKS");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 510, 120, 20));

        remarksTextArea.setColumns(20);
        remarksTextArea.setFont(new java.awt.Font("Arial Narrow", 0, 24)); // NOI18N
        remarksTextArea.setRows(5);
        jScrollPane1.setViewportView(remarksTextArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 540, 810, 140));

        stdNameList.setFont(new java.awt.Font("Arial Narrow", 0, 24)); // NOI18N
        stdNameList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                stdNameListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(stdNameList);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 120, 320, 560));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 51, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("STD NAME");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 60, 320, 80));

        addButton1.setBackground(new java.awt.Color(242, 242, 242));
        addButton1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        addButton1.setText("ADD");
        addButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(addButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 710, 140, 50));

        updateButton1.setBackground(new java.awt.Color(242, 242, 242));
        updateButton1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        updateButton1.setText("UPDATE");
        updateButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(updateButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 710, 150, 50));

        deleteButton1.setBackground(new java.awt.Color(242, 242, 242));
        deleteButton1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        deleteButton1.setText("DELETE");
        deleteButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(deleteButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 710, 140, 50));

        clearButton1.setBackground(new java.awt.Color(242, 242, 242));
        clearButton1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        clearButton1.setText("CLEAR");
        clearButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(clearButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 710, 140, 50));

        backButton1.setBackground(new java.awt.Color(242, 242, 242));
        backButton1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        backButton1.setText("BACK");
        backButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(backButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 700, 320, 60));

        shiftComboBox.setFont(new java.awt.Font("Arial Narrow", 0, 24)); // NOI18N
        shiftComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MORNING", "EVENING" }));
        shiftComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(shiftComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 200, 40));

        groupComboBox.setFont(new java.awt.Font("Arial Narrow", 0, 24)); // NOI18N
        groupComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MEDICAL ", "ENGINEERING", "COMMERCE ", "GENERAL" }));
        getContentPane().add(groupComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, 250, 40));

        jLabel15.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dell\\Desktop\\FacialRecognition-master\\src\\main\\java\\Image\\2logo-usindh.png")); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 340, 100));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dell\\Desktop\\FacialRecognition-master\\src\\main\\java\\Image\\purple-background-registration.png")); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rollNoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rollNoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rollNoTextFieldActionPerformed

    private void updateButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButton1ActionPerformed
        // TODO add your handling code here:
       updateRecord();
    }//GEN-LAST:event_updateButton1ActionPerformed

    private void clearButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButton1ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_clearButton1ActionPerformed

    private void backButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButton1ActionPerformed
        // TODO add your handling code here:
        this.hide();
    }//GEN-LAST:event_backButton1ActionPerformed

    private void studentIdTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentIdTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentIdTextFieldActionPerformed

    private void deleteButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButton1ActionPerformed
        // TODO add your handling code here:
       deleteRecord();
    }//GEN-LAST:event_deleteButton1ActionPerformed

    private void addButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButton1ActionPerformed
        // TODO add your handling code here:
        addRecord();
    }//GEN-LAST:event_addButton1ActionPerformed

    private void stdNameListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_stdNameListValueChanged
        // TODO add your handling code here:
       
       StudentBean  bean = (StudentBean) stdNameList.getSelectedValue(); 
       if(bean==null)return;
       
       
       studentIdTextField.setText(""+bean.getStdId());
       nameTextField.setText(bean.getStdName());
       fatherNameTextField.setText(bean.getfName());
       surNameTextField.setText(bean.getSurName());
       rollNoTextField.setText(bean.getRollNo());
       remarksTextArea.setText(bean.getRemarks());
      
       departmentComboBox.setSelectedItem(bean.getDeptId());
     //shiftComboBox.setSelectedItem(bean.getShift());
      //groupComboBox.setSelectedItem(bean.getGroup());        
     //BatchBean beans = (BatchBean) batchYearComboBox.getSelectedItem();
       
     // shiftComboBox.setSelectedItem(EncoderDecoder.Decoder.shiftDecode(bean.getShift()));
     // groupComboBox.setSelectedItem(EncoderDecoder.Decoder.groupDecode(bean.getGroup()));
       

       
    }//GEN-LAST:event_stdNameListValueChanged

    private void shiftComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftComboBoxActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_shiftComboBoxActionPerformed

    private void departmentComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departmentComboBoxActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_departmentComboBoxActionPerformed

    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextFieldActionPerformed

    private void departmentComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_departmentComboBoxItemStateChanged
        // TODO add your handling code here:
           DepartmentBean selectedBean = (DepartmentBean) this.departmentComboBox.getSelectedItem();        
        try {
            Vector vec = Database.getStudents(selectedBean.getDeptId());
                 this.stdNameList.setListData(vec);
        } catch (Exception ex) {
            Logger.getLogger(DepartmentFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_departmentComboBoxItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton1;
    private javax.swing.JToggleButton backButton1;
    private javax.swing.JToggleButton clearButton1;
    private javax.swing.JToggleButton deleteButton1;
    private javax.swing.JComboBox departmentComboBox;
    private javax.swing.JTextField fatherNameTextField;
    private javax.swing.JComboBox<String> groupComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextArea remarksTextArea;
    private javax.swing.JTextField rollNoTextField;
    private javax.swing.JComboBox shiftComboBox;
    private javax.swing.JList stdNameList;
    private javax.swing.JTextField studentIdTextField;
    private javax.swing.JTextField surNameTextField;
    private javax.swing.JButton updateButton1;
    // End of variables declaration//GEN-END:variables
}
