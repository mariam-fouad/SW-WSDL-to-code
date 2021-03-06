package my.WsdlToCode;

import static my.WsdlToCode.WSDL2CodeApp.convertUrlToJavaPackageName;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.xml.sax.SAXException;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Jasmin
 */
public class WSDL2CodeApp extends javax.swing.JFrame {
	WsdlManager wsdlManager = new WsdlManager ();
    static public String convertUrlToJavaPackageName(String url)
    {
        String packageName;
        //firstly removed http://
        packageName = url.replaceFirst("http://", "");
        //the strip the last / if it has one
        if (packageName.endsWith("/"))
        {
            //yes - so remove it
            packageName = packageName.substring(0, packageName.length()-1);
        }
        //now split the packagename
        String[] parts = packageName.split("\\.");
        packageName = "";
        //now flip reverse to java standards
        //loop through in reverse order, as is the standard for Java packagenames
        for (int loop = parts.length - 1; loop > -1; loop--)
        {
            //path string
            packageName = packageName + "." + parts[loop];
        }
        //remove . from begining
        packageName = packageName.substring(1);
        return packageName;
    }
    /**
     * Creates new form WSDL2CodeApp
     */
    public WSDL2CodeApp() {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("WSDL2JavaCodeApp");
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

        ChooseFile = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        edtUrl = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        edtOutput = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        edtPackage = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        btnProcess = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        ChooseFile.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        ChooseFile.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        ChooseFile.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ChooseFile.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        ChooseFile.setPreferredSize(new java.awt.Dimension(800, 500));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(544, 260));
        setMinimumSize(new java.awt.Dimension(544, 260));
        setPreferredSize(new java.awt.Dimension(544, 260));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Url");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(21, 20, 42, 23);
        getContentPane().add(edtUrl);
        edtUrl.setBounds(21, 49, 389, 28);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Output Folder");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(21, 83, 126, 23);
        getContentPane().add(edtOutput);
        edtOutput.setBounds(21, 112, 389, 28);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Package Name");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(21, 146, 144, 23);
        getContentPane().add(edtPackage);
        edtPackage.setBounds(21, 175, 389, 28);

        btnBrowse.setText("Browse");
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });
        getContentPane().add(btnBrowse);
        btnBrowse.setBounds(416, 112, 97, 28);

        btnProcess.setText("Process");
        btnProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					btnProcessActionPerformed(evt);
				} catch (SAXException | IOException
						| ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        getContentPane().add(btnProcess);
        btnProcess.setBounds(416, 175, 97, 28);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my/WsdlToCode/294aa2667920e31644b7c556c6d455471.jpg"))); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(539, 233));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 540, 234);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProcessActionPerformed(java.awt.event.ActionEvent evt) throws SAXException, IOException, ParserConfigurationException {//GEN-FIRST:event_btnProcessActionPerformed
    	String URL = edtUrl.getText();
		String FilePath = edtOutput.getText();
		String packageName = edtPackage.getText();
		AppConfig App = new AppConfig();
		App.URL = URL;
		App.FilePath = FilePath;
		App.PackageName = packageName;
		if (packageName.length() == 0)
		{
		    packageName = convertUrlToJavaPackageName(WsdlParser.Namespace);
		    App.PackageName = packageName;
		}
		wsdlManager.appConfig = App;
		wsdlManager.processRequest (1,"","");
		if (FileHelper.createFolderStructure(App.FilePath, App.PackageName))
		{
		    try
		    {
		        wsdlManager.Generate();
		    }
		    catch (Exception ex)
		    {
		        System.out.print(ex.getMessage());
		    }
		}
        new Modify(App).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnProcessActionPerformed

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        try { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            ChooseFile.updateUI();
            ChooseFile.showOpenDialog(this);
            edtOutput.setText(ChooseFile.getSelectedFile().getAbsolutePath());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WSDL2CodeApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(WSDL2CodeApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(WSDL2CodeApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(WSDL2CodeApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnBrowseActionPerformed
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
            java.util.logging.Logger.getLogger(WSDL2CodeApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WSDL2CodeApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WSDL2CodeApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WSDL2CodeApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JFileChooser ChooseFile;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnProcess;
    private javax.swing.JTextField edtOutput;
    private javax.swing.JTextField edtPackage;
    private javax.swing.JTextField edtUrl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
