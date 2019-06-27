package dataStructure;

/**
 * @(#)Joint.java
 *
 *
 * @author
 * @version 1.00 2018/11/26
 */
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class Joint {

    public static int jointClickCount = 0;
    public static boolean jointbool;
    private Ellipse joint;
    private Component owner;
    private double centerx, centery;
    private boolean isVis;
    private int jointNumber;

    public Joint(Component owner, double x, double y, int jointNumber) {
        isVis = false;
        this.jointNumber = jointNumber;
        this.centerx = x;
        this.centery = y;
        joint = new Ellipse(x, y, 10, 10);
        joint.setFill(Color.BLACK);
        this.owner = owner;
        initializeJointMouseEvents();
    }

    public double getCenterX() {
        return centerx;
    }

    public double getCenterY() {
        return centery;
    }

    public void setCenterX(double x) {
        centerx = x;
    }

    public void setCenterY(double y) {
        centery = y;
    }

    public Component getOwner() {
        return owner;
    }

    public int getJointNumber() {
        return jointNumber;
    }

    public boolean Contains(double x, double y) {
        return joint.contains(x, y);
    }

    public Shape getJoint() {
        return joint;
    }

    public void moveJoint(double x, double y) {
        joint.setTranslateX(x);
        joint.setTranslateY(y);
    }

    private void initializeJointMouseEvents() {
        joint.setOnMouseClicked((MouseEvent event) -> {
            jointbool = true;
            jointClickCount++;
            if (owner.getCurrentProcess().getArrowMode()) {
                if (jointClickCount == 1) {
                    owner.getCurrentProcess().createArrow(centerx + joint.getTranslateX(), centery + joint.getTranslateY());
                    owner.getCurrentProcess().drawArrows(centerx + joint.getTranslateX(), centery + joint.getTranslateY());
                    owner.getCurrentProcess().getArrows().get(owner.getCurrentProcess().getArrows().size() - 1).setConnected(false);
                    owner.getCurrentProcess().getArrows().get(owner.getCurrentProcess().getArrows().size() - 1).setEdgeBegin(this);
                } else {
                    
                    owner.getCurrentProcess().getArrows().get(owner.getCurrentProcess().getArrows().size() - 1).setEdgeEnd(this);
                    owner.getCurrentProcess().getArrows().get(owner.getCurrentProcess().getArrows().size() - 1).setConnected(true);
                    owner.getCurrentProcess().drawArrows(centerx + joint.getTranslateX(), centery + joint.getTranslateY());
                    jointClickCount = 0;
                }
            }
        });
    }

    public String toString() {
        String s = "owner of this joint is : " + owner.toString() + "joint number : " + jointNumber;
        return s;
    }

}
