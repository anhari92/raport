/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rapsis_online;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Aal
 */
public class input_matpel extends javax.swing.JInternalFrame {
public String [] dataPopup = {"","",""};
String [] kolom = new String[3];
String [] isi = new String[3];
    /**
     * Creates new form input_matpel
     */
    public input_matpel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_kode = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_kkm = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_cari = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(0, 102, 0));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("DATA MATA PELAJARAN");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(240, 240, 240));
        jLabel3.setText("Kode Matpel");

        jLabel1.setIcon(new javax.swing.ImageIcon("F:\\Gambar\\RAPSIS\\alfalah0.jpg")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(240, 240, 240));
        jLabel4.setText("Nama Matpel");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(240, 240, 240));
        jLabel5.setText("KKM");

        btn_simpan.setBackground(new java.awt.Color(204, 204, 0));
        btn_simpan.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_update.setBackground(new java.awt.Color(204, 204, 0));
        btn_update.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(204, 204, 0));
        btn_hapus.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_cari.setBackground(new java.awt.Color(204, 204, 0));
        btn_cari.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_simpan)
                                .addGap(43, 43, 43)
                                .addComponent(btn_update)
                                .addGap(58, 58, 58)
                                .addComponent(btn_hapus))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_kkm, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(34, 34, 34)
                                        .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(btn_cari)))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_kkm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
         if(txt_kode.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Kode Matpel Masih Kosong", "Perhatian",JOptionPane.WARNING_MESSAGE);
            txt_kode.requestFocus();
        }else if(txt_nama.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Nama Matpel  Masih Kosong", "Perhatian",JOptionPane.WARNING_MESSAGE);
            txt_nama.requestFocus();
        }else{
           try{
               java.sql.Connection conn = (java.sql.Connection)rapsis_online.koneksi.konekDb();
                String sql = "select kd_matpel from tbl_mat_pel where kd_matpel ='"+txt_kode.getText()+"'";
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet rs = stm.executeQuery(sql);
                if(rs.next()){
                    if(txt_kode.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Kode Matpel Sudah Ada", "Error", JOptionPane.PLAIN_MESSAGE);
                    }
                }else{
                    Object [] options = {"Simpan","Lihat Kembali"};
                    int n = JOptionPane.showOptionDialog(null,
                            "<html>Yakin<b><i>"+txt_kode.getText()+" "+"</i></b>akan disimpan?</html>", 
                            "Konfirmasi",JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if(n == javax.swing.JOptionPane.YES_OPTION){
                        try{
                            java.sql.Connection con = (java.sql.Connection)rapsis_online.koneksi.konekDb();
                            String sql1 = "insert into tbl_mat_pel values('"+txt_kode.getText()+"',"
                                    + "'"+txt_nama.getText()+"',"
                                    + "'"+txt_kkm.getText()+"')";
                            java.sql.PreparedStatement pst = conn.prepareStatement(sql1);
                pst.execute();
                hapus();
                JOptionPane.showMessageDialog(null, "Data berhasil Disimpan");
                        }catch(SQLException e){
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                }
            }catch(Exception e){
                   System.err.println(e.getMessage());
           }
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
                popup_matpel dialog = new popup_matpel(new javax.swing.JFrame(),true);
                dialog.setVisible(true);
                if(dialog.getData().toString().equals("")){
                }else{
                    txt_kode.setText(dialog.getData()[0]);
                    txt_nama.setText(dialog.getData()[1]);
                    txt_kkm.setText(dialog.getData()[2]);
                    btn_simpan.setEnabled(true);
                    btn_update.setEnabled(true);
                    btn_hapus.setEnabled(true);
 
                }
            }
        });
    }//GEN-LAST:event_btn_cariActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        try{
            java.sql.Connection conn = (java.sql.Connection)rapsis_online.koneksi.konekDb();
            String sql ="select kd_matpel from tbl_mat_pel where kd_matpel ='"+txt_kode.getText()+"'";
            java.sql.Statement stm = conn.createStatement();
           
            
            Object [] options = {"ubah","Lihat Kembali"};
            int n = JOptionPane.showOptionDialog(null, "<html>Yakin<b><i> "+txt_kode.getText()+" "+"</i><b> Akan Diubah?</html>","Konfirmasi",JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
            if(n == javax.swing.JOptionPane.YES_OPTION){
                java.sql.Connection con =(java.sql.Connection)rapsis_online.koneksi.konekDb();
                String sql1 = "update tbl_mat_pel set kd_matpel = '"+txt_kode.getText()+"',"
                        + "nm_matpel ='"+txt_nama.getText()+"',"
                        + "kkm ='"+txt_kkm.getText()+"'"                      
                        + "where kd_matpel ='"+txt_kode.getText()+"'";
                java.sql.PreparedStatement pst = conn.prepareStatement(sql1);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Data Telah Diupdate!!!!");
                hapus();
            }
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error"+e);
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
         Object [] options = {"Delete","Lihat Kembali"};
        int n = JOptionPane.showOptionDialog(null, "<html>Yakin <b><i>"+txt_kode.getText()+""+"</i></b>akan Dihapus?</html>",
                "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if(n == JOptionPane.YES_OPTION){
            try{
                java.sql.Connection conn = (java.sql.Connection)rapsis_online.koneksi.konekDb();
            String sql = "delete from tbl_mat_pel where kd_matpel='"+txt_kode.getText()+"'";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Terhapus!!!!");
            hapus();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error"+e);
            }
        }
        
        
    }//GEN-LAST:event_btn_hapusActionPerformed

public void hapus(){
    txt_kode.setText("");
    txt_nama.setText("");
    txt_kkm.setText("");
}
public void setDataPopup(String [] dataPopup){
    this.dataPopup = dataPopup;
}
public String [] getDataPopup(){
    return dataPopup;
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txt_kkm;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_nama;
    // End of variables declaration//GEN-END:variables
}
