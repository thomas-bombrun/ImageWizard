package imagewizard.image;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author bombrunt
 */
public class RandomColor {

    public static Iterator iterator() {
        return new Iterator() {
            
            ArrayList<Color> bannedColors = new ArrayList<>();
            Color current;
            
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Object next() {
                current = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
                while(bannedColors.contains(current)){
                    current = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
                }
                return current;
            }

            @Override
            public void remove() {
                bannedColors.add(current);
            }
        };
    }
    
}
