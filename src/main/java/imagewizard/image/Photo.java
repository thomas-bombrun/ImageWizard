/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagewizard.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Iterator;

/**
 *
 * @author bombrunt
 */
public class Photo {
    
    public BufferedImage image;
    public int height;
    public int width;
    public Pixel[][] pixels;

    public Photo(BufferedImage image) {
        this.image = image;
        this.height = image.getHeight();
        this.width = image.getWidth();
        this.pixels = new Pixel[width][height];
    }
    
    public Pixel getPixel(int x, int y){
        if(0<=x && x<width && 0<=y && y<height) {
            if(pixels[x][y] == null){
                pixels[x][y] = new Pixel(x,y,null);
            }
            return pixels[x][y];
        } else {
            return null;
        }
    }
    
    public Pixel getFreePixel(){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Pixel pix = getPixel(i, j);
                if(pix.blob == null) {
                    return pix;
                }
            }
        }
        return null;
    }
    
    public void getInBW(float threshold) {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j));
                //System.out.println((float)(color.getBlue() + color.getRed() + color.getGreen()) / 3.0);
                Color newColor = ((float)(color.getBlue() + color.getRed() + color.getGreen()) / 3.0 < threshold) ? Color.WHITE : Color.BLACK;
                image.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public void getInShadesOfGrey(float alpha) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = new Color(image.getRGB(i, j));
                int mean = (int)((float)(color.getGreen()+color.getRed()+color.getBlue())/3.0);
                try {
                image.setRGB(i, j, new Color(mean,mean,mean).getRGB());
                } catch (java.lang.IllegalArgumentException e){
                    System.out.println("error "+mean+" "+color);
                }
            }
        }
    }
    
    public void fillWithBlobs() {
        final Iterator<Color> colorIterator = RandomColor.iterator();
        
        Pixel currentPixel = getFreePixel();
        do {
            Blob blob = new Blob(colorIterator.next());
            blob.populate(currentPixel);
            colorIterator.remove();
            currentPixel = getFreePixel();
        } while (currentPixel!=null);
    }
    
}
