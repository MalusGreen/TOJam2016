package entities.ships;

import interfaces.Drawable;
import system.MathHelper;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public class ShipComponent implements Drawable {
    private BufferedImage image;

    double x;
    double y;
    double relative_x;
    double relative_y;
    double pivot_x;
    double pivot_y;
    double angle;

    public ShipComponent(BufferedImage image, int x, int y){
        this.relative_x = x;
        this.relative_y = y;
        this.image = image;
    }

    public void setXY(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void setPivot(int x, int y){
        this.pivot_x = x;
        this.pivot_y = y;
    }

    public Rectangle getRect(){
        return new Rectangle(image.getMinX(), image.getMinY(), image.getWidth(), image.getHeight());
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.rotate(angle);

        drawImage(g);

        g2d.rotate(-angle);
    }

    @Override
    public void drawImage(Graphics g) {
        g.drawImage(this.image, (int)-pivot_x, (int)-pivot_y, null);
    }

    @Override
    public void setAngle(double angle) {
        double oldAngle = this.angle;
        this.angle = angle;

        Point.Double newPoint = MathHelper.rotate(angle, oldAngle, new Point.Double(relative_x, relative_y));

        this.relative_x = newPoint.x;
        this.relative_y = newPoint.y;
    }

    public double getX(){
        return x + relative_x;
    }

    public double getY(){
        return y + relative_y;
    }

    private double circleSize = 0.8;

    public Ellipse2D getEllipse(){
        return MathHelper.getEllipse(this.getX(), this.getY(), image.getWidth() * circleSize, image.getHeight() * circleSize);
    }
}
