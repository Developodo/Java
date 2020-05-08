package com.carlosserrano.basicojfx.controller;

import com.carlosserrano.basicojfx.App;
import com.carlosserrano.basicojfx.model.Connection;
import com.carlosserrano.basicojfx.model.Item;
import com.carlosserrano.basicojfx.model.ItemDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {

    @FXML
    private TableView<Item> table;
    @FXML
    private TableColumn<Item, String> titleColumn;
    @FXML
    private TableColumn<Item, String> descriptionColumn;

    private ObservableList<Item> data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.data = FXCollections.observableArrayList();

        //se ejecuta automáticamente cuando un controlador (es decir, una escena) es cargada
        List<Item> misItems = ItemDAO.selectAll();
        data.addAll(misItems);

        this.titleColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getTitle());
        });
        this.descriptionColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getDescription());
        });

        //Editables
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        titleColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Item, String> t) {

                Item selected = (Item) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                selected.setTitle(t.getNewValue());  //<<- update lista en vista

                ItemDAO dao = new ItemDAO(selected); //update en mysql
                dao.save();
            }
        }
        );
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Item, String> t) {

                Item selected = (Item) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                selected.setDescription(t.getNewValue());  //<<- update lista en vista

                ItemDAO dao = new ItemDAO(selected); //update en mysql
                dao.save();
            }
        }
        );
        table.setEditable(true);
        //Indico que info tiene que renderizar la tabla
        table.setItems(data);

    }

    @FXML
    public void deleteItem() {
        Item selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (!showConfirm(selected.getTitle())) {
                return;
            }

            //BORRAR DE LA INTERFAZ
            data.remove(selected);
            //BORRAR DE LA BBDD
            ItemDAO dao = new ItemDAO(selected);
            dao.remove();
        } else {
            //LISTO
            showWarning("¡Ojo!", "Ningún Item a borrar", "Seleccione un item antes de presionar delete");
        }
    }

    public void showWarning(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }

    //https://code.makery.ch/blog/javafx-dialogs-official/
    public boolean showConfirm(String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("A punto de eliminar");
        alert.setContentText("Desea borrar al elemento " + title);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }

    }

    public void openNewItem() {
        //ABRIR EL MODAL -> edit.fxml
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("edit.fxml"));
        Parent modal;
        try {
            modal = fxmlLoader.load();
        
        Stage modalStage = new Stage();
        modalStage.setTitle("New Item");
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(App.rootstage); //ojo no existía, hay que crearlo

        Scene modalScene = new Scene(modal);
        modalStage.setScene(modalScene);
        
        EditController modalController = fxmlLoader.getController();
        if(modalController!=null){
            //Para que se pueda autocerrar
            modalController.setStage(modalStage);
            // Comeicación hijo -> padre
            modalController.setParent(this);
            // Comunicación padre ->hijo
            modalController.setParams(null);
            
        }
        
        modalStage.showAndWait();
        
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void doOnModalClosed(Object response){
        if(response!=null){
            //gauardar el item
            Item newItem=(Item)response;
            //actualizo vista <- 
            data.add(newItem);
            //actualizo mysql
            ItemDAO dao=new ItemDAO(newItem);
            int newId=dao.save();
           
            //corrijo
            newItem.setId(newId);
        }
    }

}
