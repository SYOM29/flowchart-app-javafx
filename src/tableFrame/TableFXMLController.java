/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableFrame;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import flowchartFrame.FlowchartFrameFXMLController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import xml.SurecTableModel;
import xml.SurecModel;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javax.xml.bind.JAXBException;
import tableFrame.dialog.TableDialogFXMLController;
import xml.XMLMarshaller;
import xml.XMLUnmarshaller;

/**
 *
 * @author SYOM
 */
public class TableFXMLController implements Initializable
{

    @FXML
    private TableView table = new TableView();
    @FXML
    private TableColumn<SurecModel, String> standartNosColumn;
    @FXML
    private TableColumn<SurecModel, String> surecIsimlerColumn;
    @FXML
    private AnchorPane mainTablePane;

    private Stage mainStage;
    private FileChooser fileChooser = new FileChooser();
    private Alert alert;
    private boolean fileSaved;

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
        columnsUndragable();
        fileSaved = true;

        table.setOnMouseClicked((event) ->
        {
            TablePosition focusedCell = table.getFocusModel().getFocusedCell();
            System.out.println(focusedCell.getColumn());
            System.out.println(focusedCell.getRow());
            System.out.println("Yo " + event.getClickCount());
            if (event.getButton().equals(MouseButton.PRIMARY))
            {
                if (event.getClickCount() == 2)
                {
                    //open file
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/flowchartFrame/flowchartFrameFXML.fxml"));
                    Parent root;
                    try
                    {
                        root = (Parent) fxmlLoader.load();
                        FlowchartFrameFXMLController controller = fxmlLoader.getController();

                        ObservableList passData = FXCollections.observableArrayList();
                        passData.setAll(table.getItems());

                        //configure stge
                        Stage stage = new Stage();
                        stage.initStyle(StageStyle.DECORATED);
                        stage.setTitle("Edit Table");
                        stage.setScene(new Scene(root));
                        stage.setMaximized(true);
                        stage.setResizable(false);
                        stage.show();
                    }
                    catch (IOException ex)
                    {
                        Logger.getLogger(TableFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    private void configureColumns()
    {
        standartNosColumn = new TableColumn<>("Standart Numaralar");
        standartNosColumn.setMinWidth(400);
        standartNosColumn.setCellValueFactory(
                new PropertyValueFactory<>("stdNos"));

        surecIsimlerColumn = new TableColumn<>("Sureç Isimler");
        surecIsimlerColumn.setMinWidth(400);
        surecIsimlerColumn.setCellValueFactory(
                new PropertyValueFactory<>("surecIsimler"));
        table.setItems(getData());
        table.getColumns().addAll(standartNosColumn, surecIsimlerColumn);
        table.setEditable(false);
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

    public static ObservableList<SurecModel> getData()
    {
//        ObservableList<SurecModel> data = FXCollections.observableArrayList(
//                new SurecModel("Jacob", "Smith"),
//                new SurecModel("Isabella", "Johnson"),
//                new SurecModel("Ethan", "Williams"),
//                new SurecModel("Emma", "Jones"),
//                new SurecModel("Michael", "Brown")
//        );
        ObservableList<SurecModel> data = FXCollections.observableArrayList(
                new SurecModel("", "")
        );
        return data;
    }

    public void save(SurecTableModel surecler, String fileName) throws JAXBException
    {
        XMLMarshaller m = new XMLMarshaller();
        m.resave(fileName, surecler);
    }

    public void setMainStage(Stage mainStage)
    {
        this.mainStage = mainStage;
    }

    private Alert getErrorAlert()
    {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText("Error, please try again later.");
        return alert;
    }

    @FXML
    public void openEditTableDialog() throws IOException
    {
        //open file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tableFrame/dialog/TableDialogFXML.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        TableDialogFXMLController controller = fxmlLoader.getController();

        ObservableList passData = FXCollections.observableArrayList();
        passData.setAll(table.getItems());
        controller.setData(passData);

        //configure stge
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Edit Table");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

        //get data from edit table dialog
        stage.setOnCloseRequest((WindowEvent we) ->
        {
            table.setItems(controller.getData());
        });

        fileSaved = false;
    }

    @FXML
    public void newFileMenuItemAction()
    {
        if (fileSaved == false)
        {
            saveFileMenuItemAction();

        }
        table.setItems(FXCollections.observableArrayList(
                new SurecModel("", "")
        ));
    }

    @FXML
    public void openFileMenuItemAction()
    {
        XMLUnmarshaller un = new XMLUnmarshaller();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("XML Files", "*.xml"),
                new ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(mainStage);
        if (selectedFile != null && mainStage != null)
        {
            try
            {
                table.setItems(un.readFile(selectedFile.getPath()).getSurecler());
            }
            catch (FileNotFoundException | JAXBException ex)
            {
                getErrorAlert().showAndWait();
            }
        }
        else
        {
            getErrorAlert().showAndWait();
        }
    }

    @FXML
    private void saveFileMenuItemAction()
    {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Save File");
        dialog.setHeaderText(null);
        dialog.setContentText("Please enter file name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent())
        {
            SurecTableModel surecTable = new SurecTableModel();
            surecTable.setSurecler(table.getItems());
            try
            {
                save(surecTable, result.get());
            }
            catch (JAXBException ex)
            {
                getErrorAlert().showAndWait();
            }
        }
        fileSaved = true;
    }

    @FXML
    public void closeMenuItemAction()
    {
        if (fileSaved == false)
        {
            saveFileMenuItemAction();
        }
        System.exit(0);
    }
}
