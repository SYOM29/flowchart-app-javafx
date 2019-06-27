package dataStructure;

/**
 * @(#)Work.java
 *
 *
 * @author
 * @version 1.00 2018/11/26
 */
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class OffPageRef implements Component
{

    private information info;
    private Surec ownerProcess;
    private double x, y, w, h;
    private int objID;
    private final String imageRespath = "src\\images\\OffPageReference.png";
    private boolean isSelected;
    private String s;
    private Label OffPageRefLabel;
    private Joint[] joints;
    private boolean readyToDelete;

    public OffPageRef(String s, double x, double y, int objID, Surec ownerProcess)
    {
        readyToDelete = false;
        this.ownerProcess = ownerProcess;
        this.s = s;
        this.objID = objID;
        ImageTaker take = new ImageTaker(imageRespath);
        OffPageRefLabel = new Label("", take.getImageView());
        OffPageRefLabel.setTranslateX(x);
        OffPageRefLabel.setTranslateY(y);
        x = OffPageRefLabel.getTranslateX();
        y = OffPageRefLabel.getTranslateY();
        w = 70;
        h = 70;
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
    }

    private void moveJoints(double x, double y)
    {
        joints[0].moveJoint(x, y);
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
        return OffPageRefLabel;
    }

    public ArrayList<Shape> getAllJoints()
    {
        ArrayList<Shape> js = new ArrayList();
        js.add(joints[0].getJoint());
        return js;
    }

    public double getWidth()
    {
        return w;
    }

    public double getHeight()
    {
        return h;
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
        return 1;
    }

    @Override
    public void setJointVisibility(boolean vis)
    {
        joints[0].getJoint().setVisible(vis);
    }

    private void moveWork(double x, double y)
    {
        this.x = x;
        this.y = y;
        OffPageRefLabel.setTranslateX(x);
        OffPageRefLabel.setTranslateY(y);
        moveJoints(x, y);
    }

    private void initializeMouseEvents()
    {
        OffPageRefLabel.setOnMouseDragged((MouseEvent event)
                ->
        {

            if (ownerProcess.getArrowMode())
            {

            }
            else
            {
                moveWork(event.getX() + OffPageRefLabel.getTranslateX() - w / 2, event.getY() + OffPageRefLabel.getTranslateY() - h / 2);
                setTextCentered();
            }
        });
        OffPageRefLabel.setOnMouseClicked((MouseEvent event)
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
        String s = "Off-Page Reference number : " + objID;
        return s;
    }

    public Label getInformationLabel()
    {
        return info.getLabel();
    }

    public void setTextCentered()
    {
        info.setTranslateX(x + OffPageRefLabel.getWidth() / 2 - info.getLabel().getWidth() / 2);
        info.setTranslateY(y + OffPageRefLabel.getHeight() / 2 - info.getLabel().getHeight() / 2);
                
        info.getLabel().setOnMouseDragged(getLabel().getOnMouseDragged());
    }
}
