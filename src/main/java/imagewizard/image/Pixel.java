package imagewizard.image;

import java.awt.Color;

/**
 *
 * @author bombrunt
 */
public class Pixel {
    
    public int x;
    
    public int y;
    
    public Blob blob;
    
    public static Photo photo ;

    public Pixel(int x, int y,Blob blob) {
        this.x = x;
        this.y = y;
        this.blob = blob;
    }
    
    public static Photo getPhoto() {
        return Pixel.photo;
    }
    
    public Color getColor(){
        return new Color(photo.image.getRGB(x, y));
    }
    
    public void copyPixel(Pixel pixel) {
        this.x = pixel.x;
        this.y = pixel.y;
    }

    @Override
    public boolean equals(Object o) {
        return ((Pixel)(o)).x==x && ((Pixel)(o)).y==y; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public String toString() {
        return "("+x+","+y+")";
    }
}
