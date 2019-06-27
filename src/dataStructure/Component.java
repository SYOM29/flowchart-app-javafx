package dataStructure;
/**
 * @(#)Component.java
 *
 *
 * @author
 * @version 1.00 2018/12/1
 */

import javafx.scene.control.Label;
import javafx.scene.shape.Shape;
import java.util.ArrayList;

public interface Component {
    public int getNumberOfJoints();
    public String getText();
    public void setText(String s);
    public Joint getJoint(int index);
    public ArrayList<Shape> getAllJoints();
    public Label getLabel();
    public int getObjectID();
    public void setJointVisibility(boolean bool);
    public boolean isSelected();
    public Surec getCurrentProcess();
    public void setSelected(boolean bool);
    public void deleteComponent();
    public boolean getReadyToDelete();
    public void decrementObjectID();
    public String toString();
    public Label getInformationLabel();
    public void setTextCentered();
}