/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.WsdlToCode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import static my.WsdlToCode.WSDL2CodeApp.convertUrlToJavaPackageName;
import org.xml.sax.SAXException;
import javax.swing.JCheckBox;

/**
 *
 * @author Jasmin
 */
public class Modify extends javax.swing.JFrame {
    public static String Url, File_Path, Package_Name;
    public static ArrayList<JCheckBox> MethodsBox = new ArrayList();
    /**
     * Creates new form Modify
     */
    public Modify(String URL, String FilePath, String packageName) {
        Url = URL;
        File_Path = FilePath;
        Package_Name = packageName;
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
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        btnModify = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        MethodsCheckBoxes = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(645, 430));
        getContentPane().setLayout(null);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(18, 72, 306, 306);

        jLabel1.setFont(new java.awt.Font("Cambria Math", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Modify");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(288, 18, 108, 36);

        btnModify.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnModify.setText("Modify");
        btnModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyActionPerformed(evt);
            }
        });
        getContentPane().add(btnModify);
        btnModify.setBounds(355, 314, 126, 54);

        btnExit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit);
        btnExit.setBounds(488, 314, 126, 54);

        MethodsCheckBoxes.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        MethodsCheckBoxes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout MethodsCheckBoxesLayout = new javax.swing.GroupLayout(MethodsCheckBoxes);
        MethodsCheckBoxes.setLayout(MethodsCheckBoxesLayout);
        MethodsCheckBoxesLayout.setHorizontalGroup(
            MethodsCheckBoxesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );
        MethodsCheckBoxesLayout.setVerticalGroup(
            MethodsCheckBoxesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 229, Short.MAX_VALUE)
        );

        getContentPane().add(MethodsCheckBoxes);
        MethodsCheckBoxes.setBounds(355, 72, 252, 234);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my/WsdlToCode/1d129676d0fa0fabfca9a8fd344268d21.jpg"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(730, 400));
        jLabel2.setMinimumSize(new java.awt.Dimension(730, 400));
        jLabel2.setPreferredSize(new java.awt.Dimension(730, 400));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 720, 414);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyActionPerformed
        try 
        {
            AppConfig App = new AppConfig();
            App.URL = Url;
            App.FilePath = File_Path;
            App.PackageName = Package_Name;
            WsdlParser.processWSDL(App.URL);
            ArrayList<String> c = WsdlParser.GetAllNames();
            for(int i = 0 ; i < c.size() ; i++)
            {
                MethodsBox.add(new javax.swing.JCheckBox());
                MethodsBox.get(i).setText(c.get(i));
                MethodsCheckBoxes.add(MethodsBox.get(i));
            }
            MethodsCheckBoxes.setVisible(true);
            
            if (Package_Name.length() == 0)
            {
                Package_Name = convertUrlToJavaPackageName(WsdlParser.Namespace);
                App.PackageName = Package_Name;
            }

            if (FileHelper.createFolderStructure(App.FilePath, App.PackageName))
            {
                try
                {
                    //folder structure created  continue processing
                    // create service file
                    Generator.CreateServiceClass(App.PackageName);
                    //prepare baseobject
                    Generator.CreateBaseObjectFile(App.PackageName);
                    //prepare array object
                    Generator.CreateLiteralVectorArrayFile(App.PackageName);
                    //create classes
                    Generator.CreateClasess(App.PackageName);
                    //create paramter and return class
                    Generator.CreateMethodClasses(App.PackageName);
                }
                catch (Exception ex)
                {
                    System.out.print(ex.getMessage());
                }
            }
        } 
        catch (SAXException ex) 
        {
            Logger.getLogger(Modify.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Modify.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ParserConfigurationException ex) 
        {
            Logger.getLogger(Modify.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnModifyActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

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
    private javax.swing.JPanel MethodsCheckBoxes;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnModify;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
