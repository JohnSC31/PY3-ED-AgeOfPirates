
package ageofpirates.controller;

import ageofpirates.model.Game;
import ageofpirates.model.Game.ItemType;
import ageofpirates.model.*;
import ageofpirates.model.Vertex;
import ageofpirates.view.MarketWindow;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MarketController extends Controller{
    
    private MarketWindow view;
    private boolean marketAvailable;

    public MarketController(MarketWindow view, Game game, MainController mainController) {
        super(game, mainController);
        this.view = view;
        this.marketAvailable = game.getGraph().isIsland("Mercado");
        _init_();
    }

    @Override
    public void _init_() {
        // listeners
        // mi inventario
        view.getBtnSellBRCannon().addActionListener(this);
        view.getBtnSellBRCannon().setEnabled(marketAvailable);
        view.getBtnSellBomb().addActionListener(this);
        view.getBtnSellBomb().setEnabled(marketAvailable);
        view.getBtnSellCannon().addActionListener(this);
        view.getBtnSellCannon().setEnabled(marketAvailable);
        view.getBtnSellMultipleCannon().addActionListener(this);
        view.getBtnSellMultipleCannon().setEnabled(marketAvailable);
        view.getBtnSellSteel().addActionListener(this);
        view.getBtnSellSteel().setEnabled(marketAvailable);
        // compra islas
        view.getBtnBuyArmoryBRCannon().addActionListener(this);
        view.getBtnBuyArmoryBRCannon().setEnabled(marketAvailable);
        view.getBtnBuyArmoryBomb().addActionListener(this);
        view.getBtnBuyArmoryBomb().setEnabled(marketAvailable);
        view.getBtnBuyArmoryCannon().addActionListener(this);
        view.getBtnBuyArmoryCannon().setEnabled(marketAvailable);
        view.getBtnBuyArmoryMultipleCannon().addActionListener(this);
        view.getBtnBuyArmoryMultipleCannon().setEnabled(marketAvailable);
        view.getBtnBuyConnector().addActionListener(this);
        view.getBtnBuyConnector().setEnabled(marketAvailable);
        view.getBtnBuyGhostShip().addActionListener(this);
        view.getBtnBuyGhostShip().setEnabled(marketAvailable);
        view.getBtnBuyMarket().addActionListener(this);
        view.getBtnBuyMine().addActionListener(this);
        view.getBtnBuyMine().setEnabled(marketAvailable);
        view.getBtnBuySourcePower().addActionListener(this);
        view.getBtnBuySourcePower().setEnabled(marketAvailable);
        view.getBtnBuyTemple().addActionListener(this);
        view.getBtnBuyTemple().setEnabled(marketAvailable);
        // compra trueques
        view.getBtnBuyRBCannon().addActionListener(this);
        view.getBtnBuyRBCannon().setEnabled(marketAvailable);
        view.getBtnBuyMultipleCannon().addActionListener(this);
        view.getBtnBuyMultipleCannon().setEnabled(marketAvailable);
        view.getBtnBuyCannon().addActionListener(this);
        view.getBtnBuyCannon().setEnabled(marketAvailable);
        view.getBtnBuyBomb().addActionListener(this);
        view.getBtnBuyBomb().setEnabled(marketAvailable);
        view.getBtnBuySteel().addActionListener(this);
        view.getBtnBuySteel().setEnabled(marketAvailable);
        
        // boton de salir
        view.getBtnClose().addActionListener(this);
        
        // seteo de los valores iniciales
        setBudget();
        setPlayerInventory();
        setMarketInventory();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // vender del inventario
        if(e.getSource().equals(view.getBtnSellBRCannon())){
            sellToMarket(ItemType.RED_BEARD_CANNON);
        }
        
        if(e.getSource().equals(view.getBtnSellBomb())){
            sellToMarket(ItemType.BOMB);
        }
        
        if(e.getSource().equals(view.getBtnSellCannon())){
            sellToMarket(ItemType.CANNON);
        }
        
        if(e.getSource().equals(view.getBtnSellMultipleCannon())){
            sellToMarket(ItemType.MULTIPLE_CANNON);
        }
        
        if(e.getSource().equals(view.getBtnSellSteel())){
            sellToMarket(ItemType.STEEL);
        }
        
        // compra de armamento y materiales
        if(e.getSource().equals(view.getBtnBuyRBCannon())){
            buyToMarket(ItemType.RED_BEARD_CANNON);
        }
        
        if(e.getSource().equals(view.getBtnBuyMultipleCannon())){
            buyToMarket(ItemType.MULTIPLE_CANNON);
        }
        
        if(e.getSource().equals(view.getBtnBuyCannon())){
            buyToMarket(ItemType.CANNON);
        }
        
        if(e.getSource().equals(view.getBtnBuyBomb())){
            buyToMarket(ItemType.BOMB);
        }
        
        if(e.getSource().equals(view.getBtnBuySteel())){
            buyToMarket(ItemType.STEEL);
        }
        
        // compra de islas
        if(e.getSource().equals(view.getBtnBuySourcePower())){
            
            if(game.getBudget() - Integer.parseInt(view.getBtnBuySourcePower().getText()) >= 0){
                Vertex vertex = game.createVertex(new PowerSource(-1,-1));
                game.setIslandRandomPosition(mainController.getConfigView().getPlayerSea(), vertex);
                game.setBudget(game.getBudget() - Integer.parseInt(view.getBtnBuySourcePower().getText()));
                setBudget();
            }

        }
        if(e.getSource().equals(view.getBtnBuyConnector())){
            
            if(game.getBudget() - Integer.parseInt(view.getBtnBuyConnector().getText()) >= 0){
                Vertex vertex = game.createVertex(new Connector(-1,-1));
                game.setIslandRandomPosition(mainController.getConfigView().getPlayerSea(), vertex);
                game.setBudget(game.getBudget() - Integer.parseInt(view.getBtnBuyConnector().getText()));
                setBudget();
            }
            
        }
        if(e.getSource().equals(view.getBtnBuyMarket())){
            
            if(game.getBudget() - Integer.parseInt(view.getBtnBuyMarket().getText()) >= 0){
                Vertex vertex = game.createVertex(new Market(-1,-1));
                game.setIslandRandomPosition(mainController.getConfigView().getPlayerSea(), vertex);
                game.setBudget(game.getBudget() - Integer.parseInt(view.getBtnBuyMarket().getText()));
                setBudget();
            }
            
        }
        if(e.getSource().equals(view.getBtnBuyMine())){
            
            if(game.getBudget() - Integer.parseInt(view.getBtnBuyMine().getText()) >= 0){
                Vertex vertex = game.createVertex(new Mine(-1,-1));
                game.setIslandRandomPosition(mainController.getConfigView().getPlayerSea(), vertex);
                game.setBudget(game.getBudget() - Integer.parseInt(view.getBtnBuyMine().getText()));
                setBudget();
            }
            
        }
        if(e.getSource().equals(view.getBtnBuyTemple())){
            
            if(game.getBudget() - Integer.parseInt(view.getBtnBuyTemple().getText()) >= 0){
                Vertex vertex = game.createVertex(new Temple(-1,-1));
                game.setIslandRandomPosition(mainController.getConfigView().getPlayerSea(), vertex);
                game.setBudget(game.getBudget() - Integer.parseInt(view.getBtnBuyTemple().getText()));
                setBudget();
            }
            
        }
        if(e.getSource().equals(view.getBtnBuyArmoryCannon())){
            
            if(game.getBudget() - Integer.parseInt(view.getBtnBuyArmoryCannon().getText()) >= 0){
                Vertex vertex = game.createVertex(new Armory(-1,-1, ItemType.CANNON));
                game.setIslandRandomPosition(mainController.getConfigView().getPlayerSea(), vertex);
                game.setBudget(game.getBudget() - Integer.parseInt(view.getBtnBuyArmoryCannon().getText()));
                setBudget();
            }
            
        }
        if(e.getSource().equals(view.getBtnBuyArmoryMultipleCannon())){
            
            if(game.getBudget() - Integer.parseInt(view.getBtnBuyArmoryMultipleCannon().getText()) >= 0){
                Vertex vertex = game.createVertex(new Armory(-1,-1, ItemType.MULTIPLE_CANNON));
                game.setIslandRandomPosition(mainController.getConfigView().getPlayerSea(), vertex);
                game.setBudget(game.getBudget() - Integer.parseInt(view.getBtnBuyArmoryMultipleCannon().getText()));
                setBudget();
            }
            
        }
        if(e.getSource().equals(view.getBtnBuyArmoryBomb())){
            
            if(game.getBudget() - Integer.parseInt(view.getBtnBuyArmoryBomb().getText()) >= 0){
                Vertex vertex = game.createVertex(new Armory(-1,-1, ItemType.BOMB));
                game.setIslandRandomPosition(mainController.getConfigView().getPlayerSea(), vertex);
                game.setBudget(game.getBudget() - Integer.parseInt(view.getBtnBuyArmoryBomb().getText()));
                setBudget();
            }
            
        }
        if(e.getSource().equals(view.getBtnBuyArmoryBRCannon())){
            
            if(game.getBudget() - Integer.parseInt(view.getBtnBuyArmoryBRCannon().getText()) >= 0){
                Vertex vertex = game.createVertex(new Armory(-1,-1, ItemType.RED_BEARD_CANNON));
                game.setIslandRandomPosition(mainController.getConfigView().getPlayerSea(), vertex);
                game.setBudget(game.getBudget() - Integer.parseInt(view.getBtnBuyArmoryBRCannon().getText()));
                setBudget();
            }
            
        }
        
        if(e.getSource().equals(view.getBtnBuyGhostShip())){
            //agregar la nave el inventario
        }

        
        if(e.getSource().equals(view.getBtnClose())){
            mainController.closeWindow(view);
        }
        
    }
    
    // ---------------------------------------------------------- METODOS -------------------------------------------------------------
    // imprime el inventario en los labels correspondientes
    public void setPlayerInventory(){
        view.getLblInventorySteel().setText(game.getPlayerInventory().getItemAmount(Game.ItemType.STEEL) + "");
        view.getLblInventoryCannon().setText(game.getPlayerInventory().getItemAmount(Game.ItemType.CANNON) + "");
        view.getLblInvetoryMultipleCannon().setText(game.getPlayerInventory().getItemAmount(Game.ItemType.MULTIPLE_CANNON) + "");
        view.getLblInventoryBomb().setText(game.getPlayerInventory().getItemAmount(Game.ItemType.BOMB) + "");
        view.getLblInventoryRBCannon().setText(game.getPlayerInventory().getItemAmount(Game.ItemType.RED_BEARD_CANNON) + "");
    }
    
    // imprime el inventario del mercado en pantalla
    public void setMarketInventory(){
        view.getLblSteel().setText(game.getMarketInventory().getItemAmount(Game.ItemType.STEEL) + "");
        view.getLblCannon().setText(game.getMarketInventory().getItemAmount(Game.ItemType.CANNON) + "");
        view.getLblMultipleCannon().setText(game.getMarketInventory().getItemAmount(Game.ItemType.MULTIPLE_CANNON) + "");
        view.getLblBomb().setText(game.getMarketInventory().getItemAmount(Game.ItemType.BOMB) + "");
        view.getLblRBCannon().setText(game.getMarketInventory().getItemAmount(Game.ItemType.RED_BEARD_CANNON) + "");
    }
    
    public void setBudget(){
        view.getLblBudget().setText("$" + game.getBudget());
    }
    
    // vende un objeto en el mercado
    public void sellToMarket(ItemType type){
        if(game.getPlayerInventory().getItemAmount(type) > 0){
            game.getPlayerInventory().updateItemAmount(type, -1); // lo quitamos del inventario del jugador
            game.getMarketInventory().updateItemAmount(type, 1); // se le suma al inventario del mercado
            game.setBudget(game.getBudget() + type.getCost());
            
            updateAllMarkets(); // actualiza todos menos el de este jugador
            setMarketInventory();
            setPlayerInventory();
            setBudget();
        }
    }
    
    public void buyToMarket(ItemType type){
        if(game.getMarketInventory().getItemAmount(type) > 0 && game.getBudget() - type.getCost() >= 0){
            game.getMarketInventory().updateItemAmount(type, -1);
            game.getPlayerInventory().updateItemAmount(type, 1);
            game.setBudget(game.getBudget() - type.getCost());
                   
            updateAllMarkets();
            
            setMarketInventory();
            setPlayerInventory();
            setBudget();
        }
    }
    
    public void updateAllMarkets(){
        try {
            outputStream.writeInt(4); // opcion del config sea
            outputStream.writeInt(1); // subopcion de config sea
            game.getPlayer().getObjOutputStream().writeObject(game.getMarketInventory());
            
        } catch (IOException ex) {
                    Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
