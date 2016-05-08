package driver;

/**
 * Created by Kevin Zheng on 2016-05-03.
 */
public class Driver {
    public static boolean DEBUG = true;
    private static DriverFrame driverFrame;

    public static void main(String args[]){
        driverFrame = new DriverFrame("Moonlight Showers: The Roguelike");
    }

    public static DriverFrame getMainFrame(){
        return driverFrame;
    }

    private Driver(){

    }
}
