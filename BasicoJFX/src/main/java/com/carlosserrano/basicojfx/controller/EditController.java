package com.carlosserrano.basicojfx.controller;


import com.carlosserrano.basicojfx.model.Item;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController implements Initializable {
    
    @FXML
    private TextField title;
    @FXML
    private TextField description;
    
    //comunicar info a mi padre
    private PrimaryController parent;
    //recibir info de mi padre
    private Object params;
    //poder autocerrarme
    private Stage myStage;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setStage(Stage myStage){
        this.myStage=myStage;
    }
    public void setParent(PrimaryController p){
        this.parent=p;
    }
    public void setParams(Object p){
        params=p;
    }
    
    @FXML
    private void add(){
        String title=this.title.getText();
        String descripction=this.description.getText();
        
        if(title.trim().length()>0 && descripction.trim().length()>0){
            
            Item newItem =new Item(-1,title,descripction);
            if(parent!=null){
                parent.doOnModalClosed(newItem);
            }
            if (this.myStage != null) {
                this.myStage.close();
            }
        }else{
            if(parent!=null){
               parent.showWarning("Error validación", "Corrija los errores", "El titulo y la descripción debe contener información");
           }
        }
        
        
        
    }
    @FXML
    private void cancel(){
        if (parent!= null) {
            parent.doOnModalClosed(null);
        }
        if (this.myStage != null) {
            this.myStage.close();
        }
    }
    
}
