package imagewizard.utils;

import java.util.Date;

/**
 *
 * @author bombrunt
 */
public class Chrono {
    private static Date startTime;
    
    public static void start() {
        startTime = new Date();
    }
    
    public static float getTime() {
        return (new Date().getTime()-startTime.getTime());
    }
}
