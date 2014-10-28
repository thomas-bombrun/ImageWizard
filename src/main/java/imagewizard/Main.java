package imagewizard;

import imagewizard.image.Photo;
import imagewizard.image.Pixel;
import imagewizard.gui.Viewer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
    
    public static void main(java.lang.String[] args) throws IOException, InterruptedException {        
        BufferedImage image = ImageIO.read(new File("img/lenna.png"));
        Photo photo = new Photo(image);
        Pixel.photo = photo;
        
        Viewer viewer = new Viewer(photo);
    }
    
}