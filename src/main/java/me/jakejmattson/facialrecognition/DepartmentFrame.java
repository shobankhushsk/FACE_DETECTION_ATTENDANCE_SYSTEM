/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package me.jakejmattson.facialrecognition;


import BeanClasses.*;
import DatabaseManager.Database;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class DepartmentFrame extends javax.swing.JFrame {

    public DepartmentFrame() {
        initComponents();
       
        getDepartment();
        //getFaculty();
        
        java.awt.Toolkit kit=this.getToolkit();
        java.awt.Dimension d=kit.getScreenSize();
        this.setBounds(0,0,d.width,d.height);
    }
    
    
  
    
      private void getDepartment(){
     try {
         
            Vector v = Database.getDepartment();
            departmentNameList.setListData(v);
        } catch (Exception ex) {
            Logger.getLogger(DepartmentFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
     }  
    
    
    private void addRecord(){
    
     //FacultyBean selectedBean = (FacultyBean) this.facultyComboBox.getSelectedItem();
        
        //int facId = selectedBean.getFacId();
    
        String deptName = departmentNameTextField.getText();
        String remarks = remarksTextArea.getText();
                  
        try {
            int row = Database.addDepartment(deptName, remarks);
            
            if(row>0){
                JOptionPane.showMessageDialog(null,"RECORD ADDED SUCCESSFULLY");
            getDepartment();
            clear();
            }else{
                JOptionPane.showMessageDialog(null,"Something went wrong");
            }
            
        } catch (Exception ex) {
            Logger.getLogger(DepartmentFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
     private void deleteRecord(){
      
        DepartmentBean  bean = (DepartmentBean) departmentNameList.getSelectedValue();  
       if(bean==null)return;
        try {
            
            int row = Database.deleteDepartment(bean.getDeptId());
            if(row>0){
                JOptionPane.showMessageDialog(null,"RECORD DELETED SUCCESSFULLY");
            getDepartment();
            clear();
            }else{
                JOptionPane.showMessageDialog(null,"Something went wrong");
            }
            
        } catch (Exception ex) {
            Logger.getLogger(DepartmentFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
   }  
     
     private void clear(){
    departmentIDTextField.setText("");    
    departmentNameTextField.setText("");
    remarksTextArea.setText("");
    
    } 
     
     private void updateRecord(){
      
        DepartmentBean  bean = (DepartmentBean) departmentNameList.getSelectedValue();  
       
        if(bean==null)return;
        
        try {
             //int row = DBManager.updateFaculty(bean.getFacId());
            int row = Database.updateDepartment(bean.getDeptId(),departmentNameTextField.getText(),remarksTextArea.getText());
            if(row>0){
                JOptionPane.showMessageDialog(null,"RECORD UPDATE SUCCESSFULLY");
               clear();
               getDepartment();
            }else{
                JOptionPane.showMessageDialog(null,"Something went wrong");
            }
            
        } catch (Exception ex) {
            Logger.getLogger(DepartmentFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }  
     

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        departmentLabel = new javax.swing.JLabel();
        departmentIdLabel = new javax.swing.JLabel();
        departmentIDTextField = new javax.swing.JTextField();
        departmentNameLabel = new javax.swing.JLabel();
        departmentNameTextField = new javax.swing.JTextField();
        remarksLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        clearButton = new javax.swing.JToggleButton();
        backButton = new javax.swing.JToggleButton();
        departmentNamesLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        departmentNameList = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        departmentLabel.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        departmentLabel.setText("DEPARTMENT");
        getContentPane().add(departmentLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 260, 38));

        departmentIdLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        departmentIdLabel.setText("DEPARTMENT ID");
        getContentPane().add(departmentIdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 160, 30));

        departmentIDTextField.setEditable(false);
        departmentIDTextField.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        getContentPane().add(departmentIDTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 110, 30));

        departmentNameLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        departmentNameLabel.setText("DEPARTMENT NAME");
        getContentPane().add(departmentNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 190, 30));

        departmentNameTextField.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        getContentPane().add(departmentNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, 710, 30));

        remarksLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        remarksLabel.setText("REMARKS");
        getContentPane().add(remarksLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 120, 30));

        remarksTextArea.setColumns(20);
        remarksTextArea.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        remarksTextArea.setRows(5);
        jScrollPane1.setViewportView(remarksTextArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 710, 260));

        addButton.setBackground(new java.awt.Color(242, 242, 242));
        addButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        addButton.setText("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 560, 100, 40));

        deleteButton.setBackground(new java.awt.Color(242, 242, 242));
        deleteButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        getContentPane().add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 560, 110, 40));

        updateButton.setBackground(new java.awt.Color(242, 242, 242));
        updateButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        updateButton.setText("UPDATE");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        getContentPane().add(updateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 560, 120, 40));

        clearButton.setBackground(new java.awt.Color(242, 242, 242));
        clearButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        clearButton.setText("CLEAR");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        getContentPane().add(clearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 560, 120, 40));

        backButton.setBackground(new java.awt.Color(242, 242, 242));
        backButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 560, 180, 40));

        departmentNamesLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        departmentNamesLabel.setText("DEPARTMENT NAMES");
        getContentPane().add(departmentNamesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 80, 210, 30));

        departmentNameList.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        departmentNameList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                departmentNameListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(departmentNameList);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 130, 330, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        updateRecord();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
       
        this.hide();
        
    }//GEN-LAST:event_backButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        addRecord();    
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
         deleteRecord();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void departmentNameListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_departmentNameListValueChanged
        // TODO add your handling code here:
          DepartmentBean  bean = (DepartmentBean) departmentNameList.getSelectedValue();
          if(bean==null)return; 
         
          this.departmentNameTextField.setText(bean.getDeptName());
          this.departmentIDTextField.setText(""+bean.getDeptId());
          this.remarksTextArea.setText(bean.getRemarks());
        


         
        
    }//GEN-LAST:event_departmentNameListValueChanged

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_clearButtonActionPerformed

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
            java.util.logging.Logger.getLogger(DepartmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DepartmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DepartmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DepartmentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DepartmentFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JToggleButton backButton;
    private javax.swing.JToggleButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField departmentIDTextField;
    private javax.swing.JLabel departmentIdLabel;
    private javax.swing.JLabel departmentLabel;
    private javax.swing.JLabel departmentNameLabel;
    private javax.swing.JList departmentNameList;
    private javax.swing.JTextField departmentNameTextField;
    private javax.swing.JLabel departmentNamesLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel remarksLabel;
    private javax.swing.JTextArea remarksTextArea;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
