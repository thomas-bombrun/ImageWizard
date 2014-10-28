package imagewizard.image;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author bombrunt
 */
public class Blob {
    private final ArrayList<Pixel> pixels;
    private Color color;
    private final Color replacementColor;

    public Blob() {
        this.pixels = new ArrayList<>();
        this.color = null;
        this.replacementColor = null;
    }
    
    public Blob(Color color) {
        this.pixels = new ArrayList<>();
        this.color = null;
        this.replacementColor = color;
    }

    public ArrayList<Pixel> getPixels() {
        return pixels;
    }
    
    private boolean isAddable(Pixel pixel) {
        if(pixel==null) {
            //System.out.println(pixel+" doesn't exist");
            return false;
        }
        if(!(pixel.getColor().equals(color))){
            //System.out.println(pixel+" is not addable because of the color");
            return false;
        }
        if(pixel.blob!=null){
            return false;
        }
        if(!(0 <= pixel.x && pixel.x < Pixel.photo.width)){
            //System.out.println(pixel+" is not addable because of width");
            return false;
        }
        if(!(0 <= pixel.y && pixel.y < Pixel.photo.height)) {
            //System.out.println(pixel+" is not addable because of height");
            return false;
        }
        return true;
    }
    
    private void addPixel(Pixel pixel){
        if(replacementColor != null) {
            Pixel.getPhoto().image.setRGB(pixel.x, pixel.y, replacementColor.getRGB());
        }
        pixel.blob=this;
        pixels.add(pixel);
    }
    
    public void populate(Pixel pixel) {
        if(pixel==null) {
            System.err.println("All pixels are in a blob.");
            return;
        }
        color = pixel.getColor();
        LinkedList<Pixel> list = new LinkedList<>();
        list.addLast(pixel);
        Pixel current;
        while(!list.isEmpty()){
            current  = list.removeFirst();
            if(isAddable(current)) {
                addPixel(current);
                list.addLast(Pixel.getPhoto().getPixel(current.x-1,current.y));
                list.addLast(Pixel.getPhoto().getPixel(current.x+1,current.y));
                list.addLast(Pixel.getPhoto().getPixel(current.x,current.y-1));
                list.addLast(Pixel.getPhoto().getPixel(current.x,current.y+1));
            }
        }
    }
    
    @Override
    public String toString(){
        String res = "";
        if(color!=null){
            res+=color.toString();
        } else {
            res+="colorless";
        }
        if(replacementColor!=null) {
            res+= " (replaced by "+replacementColor+")";
        }
        res += " blob containing :\n";
        for(Pixel pixel : pixels){
            res+=pixel+"\n";
        }
        return res;
    }
    
}
