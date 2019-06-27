/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flowchartdesign;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import processing.javafx.PSurfaceFX;
import tableFrame.TableFXMLController;

/**
 *
 * @author SYOM
 */
public class flowchart extends Application
{

    public static PSurfaceFX surface;

    @Override
    public void start(Stage stage) throws Exception
    {
        //initialize
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tableFrame/TableFXML.fxml"));
        Parent root = loader.load();
        TableFXMLController tableController = loader.getController();
        tableController.setMainStage(stage);
        
        //set save file before close
        stage.setOnCloseRequest((event) ->
        {
            tableController.closeMenuItemAction();
        });

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/flowchartFrame/flowchartFrameFXML.fxml"));
//        Parent root = loader.load();
//        FlowchartFrameFXMLController frameController = loader.getController();
//
        Scene scene = new Scene(root);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
//        frameController.setScene(stage.getScene());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
