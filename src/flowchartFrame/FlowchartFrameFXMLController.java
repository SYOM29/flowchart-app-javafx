/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flowchartFrame;

import dataStructure.Joint;
import dataStructure.Surec;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class FlowchartFrameFXMLController implements Initializable
{

    private double A4PaneWidth;
    private double A4PaneHeight;
    private int selected = -1;
    private Surec p1 = new Surec();

    @FXML
    private AnchorPane pane = new AnchorPane();

    private Scene scene;
    private Line verticalLineUp;
    private Line verticalLineDown;
    private Line horizontalLineLeft;
    private Line horizontalLineRight;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        pane.getChildren().add(p1.getIndexedComponent(0).getLabel());
        addToPane();
        //setComponentEditable((p1.getIndexedComponent(p1.getObjectID())));
    }

    //-------------------------FXML methods--------------------------------------
    @FXML
    public void drawWork()
    {
        p1.setComponentTypeWork(0, 0);
        pane.getChildren().add(p1.getIndexedComponent(p1.getObjectID()).getLabel());
        addToPane();
        //setComponentEditable((p1.getIndexedComponent(p1.getObjectID())));
    }

    @FXML
    public void drawDecision()
    {
        p1.setComponentTypeDecision(0, 0);
        pane.getChildren().add(p1.getIndexedComponent(p1.getObjectID()).getLabel());
        addToPane();
        //setComponentEditable((p1.getIndexedComponent(p1.getObjectID())));
    }

    @FXML
    public void drawDocument()
    {
        p1.setComponentTypeDocument(0, 0);
        pane.getChildren().add(p1.getIndexedComponent(p1.getObjectID()).getLabel());
        addToPane();
        //setComponentEditable((p1.getIndexedComponent(p1.getObjectID())));
    }

    @FXML
    public void drawNarrowedProcess()
    {
        p1.setComponentTypeNarrowedProcess(0, 0);
        pane.getChildren().add(p1.getIndexedComponent(p1.getObjectID()).getLabel());
        addToPane();
        //setComponentEditable((p1.getIndexedComponent(p1.getObjectID())));
    }

    @FXML
    public void drawOffPageRef()
    {
        p1.setComponentTypeOffPageRef(0, 0);
        pane.getChildren().add(p1.getIndexedComponent(p1.getObjectID()).getLabel());
        addToPane();
        //setComponentEditable((p1.getIndexedComponent(p1.getObjectID())));
    }

    @FXML
    public void drawFinishEvent()
    {
        p1.setComponentTypeFinishEvent(0, 0);
        pane.getChildren().add(p1.getIndexedComponent(p1.getObjectID()).getLabel());
        addToPane();
        //setComponentEditable((p1.getIndexedComponent(p1.getObjectID())));
    }

    @FXML
    public void drawArrow()
    {
        p1.setArrowMode(!p1.getArrowMode());
        p1.setDeleteMode(false);
        if (p1.getArrowMode())
        {
            setArrowMouseClick();
            alignCursor();
            System.out.println("p1.getArrowMode()" + p1.getArrowMode());
        }
        else
        {
            setDefaultMouseClick();
            System.out.println("p1.getArrowMode()" + p1.getArrowMode());
        }
    }

    @FXML
    public void deleteComponentAction()
    {

        pane.setOnMouseEntered((event)
                ->
        {
            verticalLineUp = new Line();
            verticalLineDown = new Line();
            horizontalLineLeft = new Line();
            horizontalLineRight = new Line();
            redrawDeleteCursor(event.getX(), event.getY(), 5);

            pane.getChildren().add(verticalLineDown);
            pane.getChildren().add(horizontalLineLeft);
            pane.getChildren().add(verticalLineUp);
            pane.getChildren().add(horizontalLineRight);
            scene.setCursor(Cursor.HAND);
        });

        pane.setOnMouseMoved((MouseEvent event)
                ->
        {
            if (event.getX() % 5 == 0 || event.getY() % 5 == 0)
            {
                redrawDeleteCursor(event.getX(), event.getY(), 5);
            }
        });

        pane.setOnMouseExited((event)
                ->
        {
            removeDrawnCursor();

            scene.setCursor(Cursor.DEFAULT);
        });

        pane.setOnMouseDragged((event)
                ->
        {
            if (event.getX() % 5 == 0 || event.getY() % 5 == 0)
            {
                redrawDeleteCursor(event.getX(), event.getY(), 5);
            }
        });

        p1.setDeleteMode(!p1.isDeleteMode());
        p1.setArrowMode(false);
        if (p1.isDeleteMode())
        {
            setDeleteMouseClick();
        }
        else
        {
            setDefaultMouseClick();
        }
        p1.deleteAllSelectedComponentFromDS();
        p1.deleteAllSelectedArrowsFromDS();

    }

    @FXML
    public void close()
    {
        System.exit(0);
    }

    //-------------------------Arrow methods--------------------------------------
    public void addToPane()
    {
        int a = p1.getIndexedComponent(p1.getProcessComponents().size() - 1).getNumberOfJoints();
        for (int j = 0; j < a; j++)
        {
            pane.getChildren().add(p1.getIndexedComponent(p1.getObjectID()).getAllJoints().get(j));
        }
    }

    public void addArrowsToPane() {
        pane.getChildren().add(p1.getArrows().get(p1.getArrows().size() - 1).getAllArrowFragments().get(p1.getArrows().get(p1.getArrows().size() - 1).getAllArrowFragments().size() - 1));
        if(p1.getArrows().get(p1.getArrows().size() - 1).isConnected()){
            pane.getChildren().add(p1.getArrows().get(p1.getArrows().size() - 1).getAllArrowFragments().get(p1.getArrows().get(p1.getArrows().size() - 1).getAllArrowFragments().size() - 2));
            pane.getChildren().add(p1.getArrows().get(p1.getArrows().size() - 1).getAllArrowFragments().get(p1.getArrows().get(p1.getArrows().size() - 1).getAllArrowFragments().size() - 3));
        }
    }

    //-------------------------editable methods--------------------------------------
//    public void setComponentEditable(Component component)
//    {
//        component.getLabel().setOnMouseClicked((event)
//                ->
//        {
//            if (p1.getArrowMode())
//            {
//                p1.setArrowMode(!p1.getArrowMode());
//                p1.setDeleteMode(false);
//                setArrowMouseClick();
//            }
//            else if (p1.isDeleteMode())
//            {
//                p1.setArrowMode(false);
//                p1.setDeleteMode(!p1.isDeleteMode());
//                setDeleteMouseClick();
//            }
//            else
//            {
//                if (event.getClickCount() == 2)
//                {
//                    TextInputDialog dialog = new TextInputDialog("");
//                    dialog.setTitle("Change name:");
//                    dialog.setHeaderText(null);
//                    dialog.setContentText(null);
//
//                    Optional<String> result = dialog.showAndWait();
//                    if (result.isPresent())
//                    {
//                        component.setText(result.get());
//                        if (pane.getChildren().contains((component).getInformationLabel()))
//                        {
//                            (component).setTextCentered();
//                        }
//                        else
//                        {
//                            pane.getChildren().add((component).getInformationLabel());
//                            (component).setTextCentered();
//                        }
//                    }
//                }
//            }
//        });
//
//        component.getInformationLabel().setOnMouseDragged((event)
//                ->
//        {
//            (component).setTextCentered();
//        });
//    }

    //-------------------------Cursor methods--------------------------------------
    private void alignCursor()
    {

        A4PaneWidth = pane.getWidth();
        A4PaneHeight = pane.getHeight();
        pane.setOnMouseEntered((event)
                ->
        {
            verticalLineUp = new Line();
            verticalLineDown = new Line();
            horizontalLineLeft = new Line();
            horizontalLineRight = new Line();
            pane.getChildren().add(verticalLineDown);
            pane.getChildren().add(horizontalLineLeft);
            pane.getChildren().add(verticalLineUp);
            pane.getChildren().add(horizontalLineRight);

            redrawAllignCursor(event.getX(), event.getY(), 5);

            scene.setCursor(Cursor.HAND);
        });
        pane.setOnMouseMoved((MouseEvent event)
                ->
        {
            if (event.getX() % 5 == 0 || event.getY() % 5 == 0)
            {
                redrawAllignCursor(event.getX(), event.getY(), 5);
            }
        });
        pane.setOnMouseDragged((event)
                ->
        {
            if (event.getX() % 5 == 0 || event.getY() % 5 == 0)
            {
                redrawAllignCursor(event.getX(), event.getY(), 5);
            }
        });
        pane.setOnMouseExited((event)
                ->
        {
            removeDrawnCursor();
            scene.setCursor(Cursor.DEFAULT);
        });
        pane.setOnMouseDragExited((event)
                ->
        {
            removeDrawnCursor();
            scene.setCursor(Cursor.DEFAULT);
        });
    }

    public void removeDrawnCursor()
    {
        pane.getChildren().remove(verticalLineDown);
        pane.getChildren().remove(horizontalLineLeft);
        pane.getChildren().remove(verticalLineUp);
        pane.getChildren().remove(horizontalLineRight);
    }

    public void redrawAllignCursor(double eventX, double eventY, double off)
    {
        verticalLineUp.setStartX(eventX);
        verticalLineUp.setStartY(eventY - off);
        verticalLineUp.setEndX(eventX);
        verticalLineUp.setEndY(0);

        verticalLineDown.setStartX(eventX);
        verticalLineDown.setStartY(eventY + off);
        verticalLineDown.setEndX(eventX);
        verticalLineDown.setEndY(A4PaneHeight);

        horizontalLineLeft.setStartX(eventX - off);
        horizontalLineLeft.setStartY(eventY);
        horizontalLineLeft.setEndX(0);
        horizontalLineLeft.setEndY(eventY);

        horizontalLineRight.setStartX(eventX + off);
        horizontalLineRight.setStartY(eventY);
        horizontalLineRight.setEndX(A4PaneWidth);
        horizontalLineRight.setEndY(eventY);
    }

    public void redrawDeleteCursor(double eventX, double eventY, double off)
    {
        verticalLineUp.setStartX(eventX - off);
        verticalLineUp.setStartY(eventY - off);
        verticalLineUp.setEndX(eventX + off);
        verticalLineUp.setEndY(eventY - off);

        verticalLineDown.setStartX(eventX - off);
        verticalLineDown.setStartY(eventY + off);
        verticalLineDown.setEndX(eventX + off);
        verticalLineDown.setEndY(eventY + off);

        horizontalLineLeft.setStartX(eventX - off);
        horizontalLineLeft.setStartY(eventY - off);
        horizontalLineLeft.setEndX(eventX - off);
        horizontalLineLeft.setEndY(eventY + off);

        horizontalLineRight.setStartX(eventX + off);
        horizontalLineRight.setStartY(eventY - off);
        horizontalLineRight.setEndX(eventX + off);
        horizontalLineRight.setEndY(eventY + off);
    }

    //-------------------------Mouse clickSetter methods---------------------------
    public void setDefaultMouseClick()
    {
        System.out.println("setDefaultMouseClick()");
        pane.setOnMouseClicked(null);
        pane.setOnMouseMoved(null);
        pane.setOnMouseDragged(null);
        pane.setOnMouseEntered(null);
        removeDrawnCursor();
        scene.setCursor(Cursor.DEFAULT);
    }

    public void setDeleteMouseClick()
    {
        pane.setOnMouseClicked((MouseEvent event)
                ->
        {
            Rectangle rekt = new Rectangle(event.getX() - 5, event.getY() - 5, 10, 10);
            for (int i = 0; i < p1.getArrows().size(); i++)
            {
                p1.getArrows().get(i).sdelete(rekt);
            }
            ArrayList<Integer> a = new ArrayList();
            a = p1.deleteAllSelectedArrows();

            for (int i = 0; i < a.size(); i++)
            {
                for (int j = 0; j < p1.getArrows().get(a.get(i)).getAllArrowFragments().size(); j++)
                {
                    pane.getChildren().remove(p1.getArrows().get(a.get(i)).getAllArrowFragments().get(j));
                }

            }

            ArrayList<Integer> b = new ArrayList();
            b = p1.deleteAllSelectedComponents();
            for (int i = 0; i < b.size(); i++)
            {
                pane.getChildren().remove(p1.getIndexedComponent(b.get(i)).getLabel());
                for (int j = 0; j < p1.getIndexedComponent(b.get(i)).getAllJoints().size(); j++)
                {
                    pane.getChildren().remove(p1.getIndexedComponent(b.get(i)).getAllJoints().get(j));
                }
            }
        });
    }

    public void setArrowMouseClick()
    {
        pane.setOnMouseClicked((MouseEvent event)
                ->
        {
            if (Joint.jointbool)
            {
                Joint.jointbool = false;
                if (Joint.jointClickCount == 1)
                {
                }
                else
                {
                    addArrowsToPane();
                }

            }
            else
            {
                if (Joint.jointClickCount == 1)
                {
                    p1.drawArrows(event.getX(), event.getY());
                    addArrowsToPane();
                }
            }
        });
    }

    //-------------------------Setter methods--------------------------------------
    public void setScene(Scene scene)
    {
        this.scene = scene;
    }
}
