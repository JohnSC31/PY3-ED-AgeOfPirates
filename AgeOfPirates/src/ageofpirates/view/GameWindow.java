
package ageofpirates.view;

import interfaces.iWindow;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameWindow extends javax.swing.JFrame implements iWindow{


    public GameWindow() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlPlayersList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        txtfMessage = new javax.swing.JTextField();
        btnSendMessage = new javax.swing.JButton();
        pnlSea = new javax.swing.JPanel();
        pnlEnemySea = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaChat = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, -1, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tu turno");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 0, 500, -1));

        pnlPlayersList.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout pnlPlayersListLayout = new javax.swing.GroupLayout(pnlPlayersList);
        pnlPlayersList.setLayout(pnlPlayersListLayout);
        pnlPlayersListLayout.setHorizontalGroup(
            pnlPlayersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        pnlPlayersListLayout.setVerticalGroup(
            pnlPlayersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        getContentPane().add(pnlPlayersList, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 20, 60, 500));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 530, 500, 120));
        getContentPane().add(txtfMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 620, 420, 30));

        btnSendMessage.setText("Enviar");
        getContentPane().add(btnSendMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 620, -1, 30));

        pnlSea.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlSeaLayout = new javax.swing.GroupLayout(pnlSea);
        pnlSea.setLayout(pnlSeaLayout);
        pnlSeaLayout.setHorizontalGroup(
            pnlSeaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        pnlSeaLayout.setVerticalGroup(
            pnlSeaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        getContentPane().add(pnlSea, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 500, 500));

        pnlEnemySea.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlEnemySeaLayout = new javax.swing.GroupLayout(pnlEnemySea);
        pnlEnemySea.setLayout(pnlEnemySeaLayout);
        pnlEnemySeaLayout.setHorizontalGroup(
            pnlEnemySeaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        pnlEnemySeaLayout.setVerticalGroup(
            pnlEnemySeaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        getContentPane().add(pnlEnemySea, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 500, 500));

        txtaChat.setEditable(false);
        txtaChat.setColumns(20);
        txtaChat.setRows(5);
        jScrollPane2.setViewportView(txtaChat);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 500, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSendMessage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel pnlEnemySea;
    private javax.swing.JPanel pnlPlayersList;
    private javax.swing.JPanel pnlSea;
    private javax.swing.JTextArea txtaChat;
    private javax.swing.JTextField txtfMessage;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setVisibility(boolean visible) {
        this.setVisible(visible);
    }
    
    // ----------------------------------------------------------------------

    public JButton getBtnSendMessage() {
        return btnSendMessage;
    }

    public JTextArea getTxtaChat() {
        return txtaChat;
    }

    public JTextField getTxtfMessage() {
        return txtfMessage;
    }
    
}
