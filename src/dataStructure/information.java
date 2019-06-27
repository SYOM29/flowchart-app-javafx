package dataStructure;

/**
 * @(#)information.java
 *
 *
 * @author
 * @version 1.00 2018/12/2
 */
import javafx.scene.control.Label;

public class information
{

    private Label textLabel;
    private Component owner;

    public information()
    {
        owner = null;
        textLabel = new Label();
    }

    public void setInformationText(String s)
    {
        textLabel.setText(s);
    }

    public void setOwner(Component owner)
    {
        this.owner = owner;
    }

    public Component getOwner()
    {
        return owner;
    }

    public void setTranslateX(double x)
    {
        textLabel.setTranslateX(x);
    }

    public void setTranslateY(double y)
    {
        textLabel.setTranslateY(y);
    }

    public double getTranslateX()
    {
        return textLabel.getTranslateX();
    }

    public double getTranslateY()
    {
        return textLabel.getTranslateY();
    }

    public Label getLabel()
    {
        return textLabel;
    }
    
    public String getText()
    {
        return textLabel.getText();
    }
}
