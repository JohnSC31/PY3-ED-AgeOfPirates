
package ageofpirates.view;

import ageofpirates.controller.MainController;
import ageofpirates.model.SeaCell;
import interfaces.iWindow;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ConfigWindow extends javax.swing.JFrame implements iWindow{
    
    public static final int SEA_SIZE = 20; // matriz cuadrada de 20x20
    public static final int CELL_SIZE = 25; // cada celda sera de 25x25
    
    private SeaCell[][] playerSea; // matriz de labels que reprensentan el oceano
        
    public ConfigWindow() {
        initComponents();
        initSea();
        
        // icono de la brujula
        try {
            ImageIcon icon = new ImageIcon(new File("./src/media/compass.png").getCanonicalPath());
            this.lblCompass.setIcon(MainController.resizeIcon(icon, this.lblCompass.getWidth(), this.lblCompass.getHeight()));
            
        } catch (IOException ex) {
            Logger.getLogger(ConfigWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // se inicializa el oceano y sus labels
    private void initSea(){
        int x = 0, y = 0;
        this.playerSea = new SeaCell[SEA_SIZE][SEA_SIZE];
        for(int i = 0; i < SEA_SIZE; i++){
            for(int j = 0; j < SEA_SIZE; j++){
                this.playerSea[i][j] = new SeaCell(i, j);
                this.playerSea[i][j].setBounds(x , y, CELL_SIZE, CELL_SIZE);
                this.pnlSea.add(this.playerSea[i][j]);
                
                x += CELL_SIZE;
            }
            x = 0;
            y += CELL_SIZE;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pnlSea = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblSelectedElement = new javax.swing.JLabel();
        btnMoveNorth = new javax.swing.JButton();
        btnMoveSouth = new javax.swing.JButton();
        btnMoveEaste = new javax.swing.JButton();
        btnMoveWest = new javax.swing.JButton();
        lblCompass = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnElementAction = new javax.swing.JButton();
        btnStartGame = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Configuracion del Mar");

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSelectedElement.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSelectedElement.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSelectedElement.setText("Selecciona un elemento");
        jPanel2.add(lblSelectedElement, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 160, -1));

        btnMoveNorth.setText("N");
        jPanel2.add(btnMoveNorth, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        btnMoveSouth.setText("S");
        jPanel2.add(btnMoveSouth, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        btnMoveEaste.setText("E");
        jPanel2.add(btnMoveEaste, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 100, -1, -1));

        btnMoveWest.setText("O");
        jPanel2.add(btnMoveWest, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        lblCompass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(lblCompass, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 72, 72, 72));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Acciones");

        btnElementAction.setText("jButton1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnElementAction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnElementAction)
                .addContainerGap(222, Short.MAX_VALUE))
        );

        btnStartGame.setText("Jugar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnStartGame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlSea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStartGame, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlSea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnElementAction;
    private javax.swing.JButton btnMoveEaste;
    private javax.swing.JButton btnMoveNorth;
    private javax.swing.JButton btnMoveSouth;
    private javax.swing.JButton btnMoveWest;
    private javax.swing.JButton btnStartGame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblCompass;
    private javax.swing.JLabel lblSelectedElement;
    private javax.swing.JPanel pnlSea;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setVisibility(boolean visible) {
        this.setVisible(visible);
    }
    
    // -------------------------------------------- GETTERS AND SETTERS ----------------------------------------------------

    public JPanel getPnlSea() {
        return pnlSea;
    }

    public JButton getBtnMoveEaste() {
        return btnMoveEaste;
    }

    public JButton getBtnMoveNorth() {
        return btnMoveNorth;
    }

    public JButton getBtnMoveSouth() {
        return btnMoveSouth;
    }

    public JButton getBtnMoveWest() {
        return btnMoveWest;
    }

    public JLabel getLblSelectedElement() {
        return lblSelectedElement;
    }

    public JButton getBtnStartGame() {
        return btnStartGame;
    }
    
    public SeaCell[][] getPlayerSea(){
        return playerSea;
    }
    
    
    
}
