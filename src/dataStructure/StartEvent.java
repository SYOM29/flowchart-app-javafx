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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class StartEvent implements Component
{

    private information info;
    private Surec ownerProcess;
    private double x, y, w, h;
    private int objID;
    private final String imageRespath = "src\\images\\StartEvent.png";
    private boolean isSelected;
    private String s;
    private Label StartEventLabel;
    private Joint[] joints;
    private boolean readyToDelete;

    public StartEvent(String s, double x, double y, int objID, Surec ownerProcess)
    {
        readyToDelete = false;
        this.ownerProcess = ownerProcess;
        this.s = s;
        this.objID = objID;
        ImageTaker take = new ImageTaker(imageRespath);
        StartEventLabel = new Label("", take.getImageView());
        StartEventLabel.setTranslateX(x);
        StartEventLabel.setTranslateY(y);
        x = StartEventLabel.getTranslateX();
        y = StartEventLabel.getTranslateY();
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
        joints[0] = new Joint(this, x + w / 2, y + h, 0);
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
        return StartEventLabel;
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
        StartEventLabel.setTranslateX(x);
        StartEventLabel.setTranslateY(y);
        moveJoints(x, y);
    }

    private void initializeMouseEvents()
    {
        StartEventLabel.setOnMouseDragged((MouseEvent event)
                ->
        {
            if (ownerProcess.getArrowMode())
            {

            }
            else
            {
                moveWork(event.getX() + StartEventLabel.getTranslateX() - w / 2, event.getY() + StartEventLabel.getTranslateY() - h / 2);
                setTextCentered();
            }
        });
        StartEventLabel.setOnMouseClicked((MouseEvent event)
                ->
        {
            if (ownerProcess.getArrowMode())
            {

            }
            else
            {
                for (int i = 0; i < ownerProcess.getProcessComponents().size(); i++)
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
        return false;
    }

    @Override
    public void decrementObjectID()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String toString()
    {
        String s = "Start Event number : " + objID;
        return s;
    }

    public Label getInformationLabel()
    {
        return info.getLabel();
    }

    public void setTextCentered()
    {
        info.setTranslateX(x + StartEventLabel.getWidth() / 2 - info.getLabel().getWidth() / 2);
        info.setTranslateY(y + StartEventLabel.getHeight() / 2 - info.getLabel().getHeight() / 2);
                
        info.getLabel().setOnMouseDragged(getLabel().getOnMouseDragged());
    }
}
