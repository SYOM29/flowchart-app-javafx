/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flowchartFrame.dataBundle;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import dataStructure.DataBundle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import tableFrame.TableFXMLController;
import xml.SurecModel;

/**
 * FXML Controller class
 *
 * @author Siyovush Kadyrov <siyovush.kadyrov@gmail.com>
 */
public class DataBundleFXMLController implements Initializable
{

    @FXML
    private HBox countHBox;
    @FXML
    private TableView table = new TableView();
    @FXML
    private TableColumn<SurecModel, String> standartNosColumn;
    @FXML
    private TableColumn<SurecModel, String> surecIsimlerColumn;

    private final Spinner<Integer> spinner = new Spinner<>();
    private SpinnerValueFactory<Integer> valueFactory;

    private ObservableList data;
    private DataBundle dataBundle;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        configureColumns();
        table.getColumns().addAll(standartNosColumn, surecIsimlerColumn);
        setColumnFactory();
        columnsUndragable();
        configureSpinner();
    }

    private void configureColumns()
    {
        data = TableFXMLController.getData();
        standartNosColumn = new TableColumn<>("Standart Numaralar");
        standartNosColumn.setMinWidth(200);
        standartNosColumn.setCellValueFactory(
                new PropertyValueFactory<>("stdNos"));

        surecIsimlerColumn = new TableColumn<>("Sureç Isimler");
        surecIsimlerColumn.setMinWidth(200);
        surecIsimlerColumn.setCellValueFactory(
                new PropertyValueFactory<>("surecIsimler"));
    }

    private void setColumnFactory()
    {
        standartNosColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        standartNosColumn.setOnEditCommit((TableColumn.CellEditEvent<SurecModel, String> event) ->
        {
            ((SurecModel) (table.getItems().get(event.getTablePosition().getRow()))).setStdNos(event.getNewValue());
        });

        surecIsimlerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        surecIsimlerColumn.setOnEditCommit((TableColumn.CellEditEvent<SurecModel, String> event) ->
        {
            ((SurecModel) (table.getItems().get(event.getTablePosition().getRow()))).setSurecIsimler(event.getNewValue());
        });

        table.setEditable(true);
    }

    private void columnsUndragable()
    {
        table.widthProperty().addListener((ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) ->
        {
            TableHeaderRow header = (TableHeaderRow) table.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
            {
                header.setReordering(false);
            });
        });
    }

    private void configureSpinner()
    {
        //configure spinner
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, table.getItems().size());
        spinner.setValueFactory(valueFactory);
        spinner.setMaxWidth(120);
        spinner.setEditable(true);
        spinner.valueProperty().addListener((ObservableValue<? extends Integer> obs, Integer oldValue, Integer newValue) ->
        {
            if (newValue > table.getItems().size())
            {
                for (int i = newValue; i >= table.getItems().size(); i--)
                {
                    table.getItems().add(new SurecModel("", ""));
                }
            }
        }
        );
        countHBox.getChildren().add(spinner);
    }

    public ObservableList getData()
    {
        return table.getItems();
    }

    public void setData(ObservableList data)
    {
        table.setItems(data);
    }

    @FXML
    public void insertButtonAction()
    {
        SurecModel surec = new SurecModel("", "");
        table.getItems().add(surec);
    }

    @FXML
    public void deleteButtonAction()
    {
        Object selectedItem = table.getSelectionModel().getSelectedItem();
        table.getItems().remove(selectedItem);
    }

    @FXML
    public void moveUpButtonAction()
    {
        int index = table.getSelectionModel().getSelectedIndex();
        if (index > 0)
        {
            Object objToBeMoved = table.getSelectionModel().getSelectedItem();
            table.getItems().set(index, table.getItems().get(index - 1));
            table.getItems().set(index - 1, objToBeMoved);
        }
        table.getSelectionModel().select(index - 1);
    }

    @FXML
    public void moveDownButtonAction()
    {
        int index = table.getSelectionModel().getSelectedIndex();
        if (index < table.getItems().size() - 1)
        {
            Object objToBeMoved = table.getSelectionModel().getSelectedItem();
            table.getItems().set(index, table.getItems().get(index + 1));
            table.getItems().set(index + 1, objToBeMoved);
        }
        table.getSelectionModel().select(index + 1);
    }
    
    public void setDataBundle(DataBundle dataBundle)
    {
        this.dataBundle = dataBundle;
    }
}
