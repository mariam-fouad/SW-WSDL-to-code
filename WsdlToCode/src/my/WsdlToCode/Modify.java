/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.WsdlToCode;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Jasmin
 */
public class Modify extends javax.swing.JFrame {
    ArrayList<String> CheckBoxesNames = WsdlParser.GetAllNames();
    ArrayList<String> CheckedBoxesNames = new ArrayList();
    JCheckBox cb[] = new JCheckBox[CheckBoxesNames.size()];
    /**
     * Creates new form Modify
     */
    public Modify() {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("WSDL2JavaCodeModify");
        setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ModifyTxtArea = new javax.swing.JTextArea();
        btnChoose = new javax.swing.JButton();
        btnShow = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        PanCheckBoxes = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(645, 430));
        getContentPane().setLayout(null);

        ModifyTxtArea.setColumns(20);
        ModifyTxtArea.setRows(5);
        jScrollPane1.setViewportView(ModifyTxtArea);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(18, 54, 288, 324);

        btnChoose.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnChoose.setText("Choose");
        btnChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseActionPerformed(evt);
            }
        });
        getContentPane().add(btnChoose);
        btnChoose.setBounds(337, 278, 126, 36);

        btnShow.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnShow.setText("Show Classes");
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });
        getContentPane().add(btnShow);
        btnShow.setBounds(470, 278, 126, 36);

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave);
        btnSave.setBounds(337, 314, 126, 36);

        javax.swing.GroupLayout PanCheckBoxesLayout = new javax.swing.GroupLayout(PanCheckBoxes);
        PanCheckBoxes.setLayout(PanCheckBoxesLayout);
        PanCheckBoxesLayout.setHorizontalGroup(
            PanCheckBoxesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );
        PanCheckBoxesLayout.setVerticalGroup(
            PanCheckBoxesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
        );

        getContentPane().add(PanCheckBoxes);
        PanCheckBoxes.setBounds(324, 54, 288, 216);

        jLabel1.setFont(new java.awt.Font("Cambria Math", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Modify");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(288, 0, 108, 54);

        btnExit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit);
        btnExit.setBounds(470, 314, 126, 36);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my/WsdlToCode/1d129676d0fa0fabfca9a8fd344268d21.jpg"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(730, 400));
        jLabel2.setMinimumSize(new java.awt.Dimension(730, 400));
        jLabel2.setPreferredSize(new java.awt.Dimension(730, 400));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 648, 414);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseActionPerformed
        for(int i = 0 ; i < CheckBoxesNames.size() ; i++) 
        {
            if(cb[i].isSelected() == true)
            {
                CheckedBoxesNames.add(cb[i].getText());
            }
        }
        for(int i = 0 ; i < CheckedBoxesNames.size() ; i++) 
        {
            System.out.println(CheckedBoxesNames.get(i));
        }
        
        File Folder=new File(FileHelper.GetOutputFolderPath());
        File[] list = Folder.listFiles();
        for(int i = 0 ; i < list.length ; i++)
        {
            String FileName = list[i].getName().replace(".java", "");
            if(!CheckedBoxesNames.contains(FileName) && list[i].isFile())
            {
                list[i].delete();
            }
        }
        InputStream file = null;
        try 
        {
            String filePath = FileHelper.GetOutputFolderPath() + "/" + CheckedBoxesNames.get(0) + ".java";
            file = new FileInputStream(filePath);
            if (file != null)
            {
                String Text = FileHelper.getContents(file);
                ModifyTxtArea.setText(Text);
                file.close();
            }
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(Modify.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Modify.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally 
        {
            try 
            {
                file.close();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(Modify.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnChooseActionPerformed

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        PanCheckBoxes.setLayout(new GridLayout(CheckBoxesNames.size(),1));
        //PanCheckBoxes.setLayout(new FlowLayout());
        for(int i = 0 ; i < CheckBoxesNames.size() ; i++) 
        {
            cb[i]=new JCheckBox(CheckBoxesNames.get(i));
            cb[i].setVisible(false);
        }
        
        add(PanCheckBoxes);
        for(int i = 0 ; i < CheckBoxesNames.size() ; i++)
        {
            PanCheckBoxes.add(cb[i]);
        }
        for(int i = 0 ; i < CheckBoxesNames.size() ; i++) 
        {
            cb[i].setVisible(true);
        }
    }//GEN-LAST:event_btnShowActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String filePath = FileHelper.GetOutputFolderPath() + "/" + CheckedBoxesNames.get(0) + ".java";
        FileHelper.WriteClassTextToFile(filePath, ModifyTxtArea.getText());
        CheckedBoxesNames.remove(0);
        if(CheckedBoxesNames.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Your Modification is Finished", "Finish Message", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        InputStream file = null;
        try 
        {
            String file_Path = FileHelper.GetOutputFolderPath() + "/" + CheckedBoxesNames.get(0) + ".java";
            file = new FileInputStream(file_Path);
            if (file != null)
            {
                String Text = FileHelper.getContents(file);
                ModifyTxtArea.setText(Text);
                file.close();
            }
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(Modify.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Modify.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally 
        {
            try 
            {
                file.close();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(Modify.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_btnSaveActionPerformed

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
            java.util.logging.Logger.getLogger(Modify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WSDL2CodeApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ModifyTxtArea;
    private javax.swing.JPanel PanCheckBoxes;
    private javax.swing.JButton btnChoose;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnShow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
