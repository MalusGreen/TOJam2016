package system;

import java.awt.*;

/**
 * Created by Kevin Zheng on 2016-05-07.
 */
public class Camera {
    private static Camera camera;

    public static Camera getCamera(){
        if(camera == null){
            camera = new Camera();
        }
        return camera;
    }

    public double x;
    public double y;

    public double tx;
    public double ty;

    private Camera(){

    }

    public void setTarget(Point.Double location){
        this.tx = location.x - 500;
        this.ty = location.y - 500;

        double dx = tx - x;
        double dy = ty - y;

        x += dx/50 + 1;
        y += dy/50 + 1;
    }

    public void panCamera(Graphics g){
        g.translate((int)-x,(int) -y);
    }
    public void unpanCamera(Graphics g){
        g.translate((int)x,(int) y);
    }
}
