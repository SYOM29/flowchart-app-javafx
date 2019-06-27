package dataStructure;
/**
 * @(#)ImageTaker.java
 *
 *
 * @author
 * @version 1.00 2018/12/1
 */
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageTaker {

    private ImageView imageView;
    private Image image;
    public ImageTaker(String url){
    	File file = new File(url);
        try {
            image = new Image(file.toURI().toURL().toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(ImageTaker.class.getName()).log(Level.SEVERE, null, ex);
        }
    	imageView = new ImageView(image);
    }
    public ImageView getImageView(){
    	return imageView;
    }
    public double getImageWidth(){
    	return image.getWidth();
    }
    public double getImageHeight(){
    	return image.getHeight();
    }

}
