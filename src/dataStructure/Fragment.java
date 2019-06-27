package dataStructure;
/**
 * @(#)Fragment.java
 *
 *
 * @author
 * @version 1.00 2018/12/1
 */

import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public interface Fragment {
	public Shape getFragmentShape();
	public void TranslateFragmentLoc(double x, double y);
        public void contains(Rectangle rekt);


}