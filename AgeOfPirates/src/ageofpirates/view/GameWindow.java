
package ageofpirates.view;

import ageofpirates.model.SeaCell;
import interfaces.iWindow;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameWindow extends javax.swing.JFrame implements iWindow{

    
    public static final int SEA_SIZE = 20; // matriz cuadrada de 20x20
    public static final int CELL_SIZE = 25; // cada celda sera de 25x25
    
    private SeaCell[][] playerSea; // matriz de labels que reprensentan el oceano
    private SeaCell[][] enemySea;

    public GameWindow() {
        initComponents();
        initSea();
    }
    
    private void initSea(){
        int x = 0, y = 0;
        this.playerSea = new SeaCell[SEA_SIZE][SEA_SIZE];
        for(int i = 0; i < SEA_SIZE; i++){
            for(int j = 0; j < SEA_SIZE; j++){
                this.playerSea[i][j] = new SeaCell(i, j, false);
                this.playerSea[i][j].setBounds(x , y, CELL_SIZE, CELL_SIZE);
                this.pnlSea.add(this.playerSea[i][j]);
                
                x += CELL_SIZE;
            }
            x = 0;
            y += CELL_SIZE;
        }
    }

    private void initSeas(){
        int x = 0, y = 0;
        this.playerSea = new SeaCell[SEA_SIZE][SEA_SIZE];
        for(int i = 0; i < SEA_SIZE; i++){
            for(int j = 0; j < SEA_SIZE; j++){
                this.playerSea[i][j] = new SeaCell(i, j, false);
                this.playerSea[i][j].setBounds(x , y, CELL_SIZE, CELL_SIZE);
                this.pnlSea.add(this.playerSea[i][j]);
                
                x += CELL_SIZE;
            }
            x = 0;
            y += CELL_SIZE;
        }
        x =y =0;
        this.enemySea = new SeaCell[SEA_SIZE][SEA_SIZE];
        for(int i = 0; i < SEA_SIZE; i++){
            for(int j = 0; j < SEA_SIZE; j++){
                this.enemySea[i][j] = new SeaCell(i, j, true);
                this.enemySea[i][j].setBounds(x , y, CELL_SIZE, CELL_SIZE);
                this.pnlEnemySea.add(this.enemySea[i][j]);
                
                x += CELL_SIZE;
            }
            x = 0;
            y += CELL_SIZE;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblPlayerTurn = new javax.swing.JLabel();
        pnlPlayersList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        txtfMessage = new javax.swing.JTextField();
        btnSendMessage = new javax.swing.JButton();
        pnlSea = new javax.swing.JPanel();
        pnlEnemySea = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaChat = new javax.swing.JTextArea();
        pnlItems = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblComodin = new javax.swing.JLabel();
        btnGhostShip = new javax.swing.JButton();
        btnComodin = new javax.swing.JButton();
        btnRBCannon = new javax.swing.JButton();
        btnBomb = new javax.swing.JButton();
        btnMultipleCannon = new javax.swing.JButton();
        btnCannon = new javax.swing.JButton();
        lblSelectedVertex = new javax.swing.JLabel();
        lblSelectedVertex1 = new javax.swing.JLabel();
        lblSteel = new javax.swing.JLabel();
        lblSelectedVertex3 = new javax.swing.JLabel();
        lblMoney = new javax.swing.JLabel();
        btnOpen = new javax.swing.JButton();
        btnConfig = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblWeaponSelected = new javax.swing.JLabel();
        btnAttack = new javax.swing.JButton();

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

        lblPlayerTurn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayerTurn.setText("Tu turno");
        getContentPane().add(lblPlayerTurn, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 500, 20));

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

        getContentPane().add(pnlPlayersList, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 20, 60, 500));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 560, 500, 120));
        getContentPane().add(txtfMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 650, 420, 30));

        btnSendMessage.setText("Enviar");
        getContentPane().add(btnSendMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 650, -1, 30));

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

        getContentPane().add(pnlSea, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 500, 500));

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

        getContentPane().add(pnlEnemySea, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 500, 500));

        txtaChat.setEditable(false);
        txtaChat.setColumns(20);
        txtaChat.setRows(5);
        jScrollPane2.setViewportView(txtaChat);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 500, 110));

        jLabel2.setText("Cañon");

        jLabel4.setText("Cañon Multiple");

        jLabel6.setText("Bomba");

        jLabel8.setText("Cañon Barba Roja");

        jLabel10.setText("Barco Fantasma");

        lblComodin.setText("Comodin");

        btnGhostShip.setText("0");

        btnComodin.setText("Usar");

        btnRBCannon.setText("0");

        btnBomb.setText("0");

        btnMultipleCannon.setText("0");

        btnCannon.setText("0");

        lblSelectedVertex.setText("No seleccionado");

        lblSelectedVertex1.setText("Acero");

        lblSteel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSteel.setText("000000");

        lblSelectedVertex3.setText("Dinero");

        lblMoney.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMoney.setText("000000");

        btnOpen.setText("Abrir");

        btnConfig.setText("Config");

        javax.swing.GroupLayout pnlItemsLayout = new javax.swing.GroupLayout(pnlItems);
        pnlItems.setLayout(pnlItemsLayout);
        pnlItemsLayout.setHorizontalGroup(
            pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblComodin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSelectedVertex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSelectedVertex1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSteel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSelectedVertex3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMoney, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlItemsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGhostShip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRBCannon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBomb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMultipleCannon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCannon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnComodin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlItemsLayout.setVerticalGroup(
            pnlItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlItemsLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCannon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMultipleCannon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBomb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRBCannon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGhostShip)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblComodin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnComodin)
                .addGap(18, 18, 18)
                .addComponent(lblSelectedVertex1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSteel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSelectedVertex3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMoney)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSelectedVertex)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOpen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConfig)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        getContentPane().add(pnlItems, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 130, 540));

        lblWeaponSelected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWeaponSelected.setText("Selecciona un arma");

        btnAttack.setText("Atacar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(btnAttack, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(lblWeaponSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblWeaponSelected, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addComponent(btnAttack))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 520, 500, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAttack;
    private javax.swing.JButton btnBomb;
    private javax.swing.JButton btnCannon;
    private javax.swing.JButton btnComodin;
    private javax.swing.JButton btnConfig;
    private javax.swing.JButton btnGhostShip;
    private javax.swing.JButton btnMultipleCannon;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnRBCannon;
    private javax.swing.JButton btnSendMessage;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblComodin;
    private javax.swing.JLabel lblMoney;
    private javax.swing.JLabel lblPlayerTurn;
    private javax.swing.JLabel lblSelectedVertex;
    private javax.swing.JLabel lblSelectedVertex1;
    private javax.swing.JLabel lblSelectedVertex3;
    private javax.swing.JLabel lblSteel;
    private javax.swing.JLabel lblWeaponSelected;
    private javax.swing.JPanel pnlEnemySea;
    private javax.swing.JPanel pnlItems;
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

    public JButton getBtnBomb() {
        return btnBomb;
    }

    public JButton getBtnCannon() {
        return btnCannon;
    }

    public JButton getBtnComodin() {
        return btnComodin;
    }

    public JButton getBtnGhostShip() {
        return btnGhostShip;
    }

    public JButton getBtnMultipleCannon() {
        return btnMultipleCannon;
    }

    public JButton getBtnOpen() {
        return btnOpen;
    }

    public JButton getBtnRBCannon() {
        return btnRBCannon;
    }

    public JLabel getLblPlayerTurn() {
        return lblPlayerTurn;
    }

    public JPanel getPnlEnemySea() {
        return pnlEnemySea;
    }

    public JPanel getPnlSea() {
        return pnlSea;
    }

    public SeaCell[][] getPlayerSea() {
        return playerSea;
    }

    public JLabel getLblSelectedVertex() {
        return lblSelectedVertex;
    }

    public JButton getBtnConfig() {
        return btnConfig;
    }

    public JLabel getLblComodin() {
        return lblComodin;
    }

    public JLabel getLblMoney() {
        return lblMoney;
    }

    public JLabel getLblSteel() {
        return lblSteel;
    }

    public JButton getBtnAttack() {
        return btnAttack;
    }

    public JLabel getLblWeaponSelected() {
        return lblWeaponSelected;
    }

}
