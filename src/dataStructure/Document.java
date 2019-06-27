package dataStructure;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Document implements Component
{

    private boolean readyToDelete;
    private information info;
    private Surec ownerProcess;
    private double x, y, w, h;
    private int objID;
    private final String imageRespath = "src\\images\\Document.png";
    private boolean isSelected;
    private String s;
    private Label documentLabel;
    private Joint[] joints;

    public Document(String s, double x, double y, int objID, Surec ownerProcess)
    {
        readyToDelete = false;
        this.ownerProcess = ownerProcess;
        this.s = s;
        this.objID = objID;
        ImageTaker take = new ImageTaker(imageRespath);
        documentLabel = new Label("", take.getImageView());
        documentLabel.setTranslateX(x);
        documentLabel.setTranslateY(y);
        x = documentLabel.getTranslateX();
        y = documentLabel.getTranslateY();
        w = 130;
        h = 80;
        joints = new Joint[4];
        setJointLocations();
        info = new information();
        info.setOwner((Component) this);
        info.setInformationText(s);
        info.setTranslateX(x + w / 2);
        info.setTranslateY(y + h / 2);
        isSelected = false;
        setJointVisibility(isSelected);
        initializeMouseEvents();
    }

    private void setJointLocations()
    {
        joints[0] = new Joint(this, x + w / 2, y, 0);
        joints[1] = new Joint(this, x + w, y + h / 2, 1);
        joints[2] = new Joint(this, x + w / 2, y + h - 10, 2);
        joints[3] = new Joint(this, x, y + h / 2, 3);
    }

    private void moveJoints(double x, double y)
    {
        for (int i = 0; i < 4; i++)
        {
            joints[i].moveJoint(x, y);
        }
    }

    public Joint getJoint(int index)
    {
        return joints[index];
    }

    public void setJoint(int index, Joint j)
    {
        joints[index] = j;
    }

    public int getObjectID()
    {
        return objID;
    }

    public boolean isSelected()
    {
        return isSelected;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getX()
    {
        return this.x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getY()
    {
        return this.y;
    }

    public Label getLabel()
    {
        return documentLabel;
    }

    public double getWidth()
    {
        return w;
    }

    public double getHeight()
    {
        return h;
    }

    public ArrayList<Shape> getAllJoints()
    {
        ArrayList<Shape> js = new ArrayList();
        for (int i = 0; i < 4; i++)
        {
            //if(joints[i].getJoint().isVisible()){
            js.add(joints[i].getJoint());
            //}
        }
        return js;
    }

    public boolean DoesIndexedJointContains(double x, double y, int index)
    {
        return joints[index].Contains(x, y);
    }

    public void setText(String s)
    {
        info.setInformationText(s);
        this.s = s;
    }

    public String getText()
    {
        return info.getText();
    }

    public int getNumberOfJoints()
    {
        return 4;
    }

    public void setJointVisibility(boolean vis)
    {
        for (int i = 0; i < 4; i++)
        {
            joints[i].getJoint().setVisible(vis);
        }
    }

    private void moveDocument(double x, double y)
    {
        this.x = x;
        this.y = y;
        documentLabel.setTranslateX(x);
        documentLabel.setTranslateY(y);
        moveJoints(x, y);
    }

    private void initializeMouseEvents()
    {
        documentLabel.setOnMouseDragged((MouseEvent event)
                ->
        {

            if (ownerProcess.getArrowMode())
            {

            }
            else
            {
                moveDocument(event.getX() + documentLabel.getTranslateX() - w / 2, event.getY() + documentLabel.getTranslateY() - h / 2);
                setTextCentered();
            }
        });
        documentLabel.setOnMouseClicked((MouseEvent event)
                ->
        {
            if (ownerProcess.isDeleteMode())
            {
                deleteComponent();
            }
            if (ownerProcess.getArrowMode())
            {

            }
            else
            {
                for (int i = 1; i < ownerProcess.getProcessComponents().size(); i++)
                {
                    if (event.getSource().hashCode() == ownerProcess.getIndexedComponent(i).getLabel().hashCode())
                    {
                        ownerProcess.setCurrentSelected(i);
                        (ownerProcess.getIndexedComponent(i)).setSelected(true);
                        ownerProcess.setIndexedComponentJointVisibilty(i, true);
                    }
                    else
                    {
                        ownerProcess.setIndexedComponentJointVisibilty(i, false);
                        (ownerProcess.getIndexedComponent(i)).setSelected(false);
                    }
                }
            }

        });
    }

    public void setSelected(boolean bool)
    {
        isSelected = bool;
    }

    public void updateSelectionFalse()
    {
        isSelected = false;
        setJointVisibility(isSelected);
    }

    public Surec getCurrentProcess()
    {
        return ownerProcess;
    }

    public void deleteComponent()
    {
        readyToDelete = true;
    }

    public boolean getReadyToDelete()
    {
        return readyToDelete;
    }

    public void decrementObjectID()
    {
        objID = objID - 1;
    }

    public String toString()
    {
        String s = "document number : " + objID;
        return s;

    }

    public Label getInformationLabel()
    {
        return info.getLabel();
    }

    public void setTextCentered()
    {
        info.setTranslateX(x + documentLabel.getWidth() / 2 - info.getLabel().getWidth() / 2);
        info.setTranslateY(y + documentLabel.getHeight() / 2 - info.getLabel().getHeight() / 2);
                
        info.getLabel().setOnMouseDragged(getLabel().getOnMouseDragged());
    }
}
