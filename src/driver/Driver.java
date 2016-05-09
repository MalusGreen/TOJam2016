package driver;

import java.applet.Applet;

/**
 * Created by Kevin Zheng on 2016-05-03.
 */
public class Driver extends Applet {
    public static boolean DEBUG = false;
    private static DriverFrame driverFrame;

    public static void main(String args[]){
        driverFrame = new DriverFrame("Moonlight Showers: The Roguelike");
    }

    public void start(){
        this.main(new String[0]);
    }

    public static DriverFrame getMainFrame(){
        return driverFrame;
    }

    private Driver(){

    }
}
