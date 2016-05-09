package system;

import entities.ships.Ship;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public class MathHelper {
    public static Point.Double getVector(double angle, double value){
        return new Point.Double(value * Math.cos(angle), value * Math.sin(angle));
    }

    public static Point.Double normalize(Point.Double point){
        double x = point.x;
        double y = point.y;

        double hyp = Math.sqrt(x*x + y*y);

        return new Point.Double(x/hyp, y/hyp);
    }

    public static Point.Double multiply(Point.Double point, double by){
        return new Point.Double(point.x * by, point.y * by);
    }

    public static Point.Double rotate(double angle, double oldAngle, Point.Double point){

        double [][] matrix = {
                {Math.cos(angle - oldAngle), -Math.sin(angle - oldAngle)},
                {Math.sin(angle - oldAngle),  Math.cos(angle - oldAngle)}
        };

        return matrixMultiply(matrix, point);
    }

    public static Point.Double matrixMultiply(double [][] matrix, Point.Double point){

        double x = matrix[0][0] * point.x + matrix[0][1] * point.y;
        double y = matrix[1][0] * point.x + matrix[1][1] * point.y;
        return new Point.Double(
                x,
                y
        );
    }

    public static Shape rotate(double angle, Shape shape){
        shape = AffineTransform.getRotateInstance(angle).createTransformedShape(shape);
        return shape;
    }


    public static Ellipse2D getEllipse(double c_x, double c_y, double width, double height){
        return new Ellipse2D.Double(c_x - width/2, c_y - height/2, width, height);
    }

    public static int generateLevel(int level){
        double chance = Math.random();

        if(chance < 0.01 * level){
            return 12;
        }if(chance < 0.01 * level){
            return 11;
        }if(chance < 0.02 * level){
            return 10;
        }if(chance < 0.03 * level){
            return 9;
        }if(chance < 0.05 * level){
            return 8;
        }if(chance < 0.08 * level){
            return 7;
        }if(chance < 0.13 * level){
            return 6;
        }if(chance < 0.21 * level){
            return 5;
        }if(chance < 0.34 * level){
            return 4;
        }if(chance < 0.55 * level){
            return 3;
        }if(chance < 0.89 * level){
            return 2;
        }if(chance < 1.00 * level){
            return 1;
        }

        throw new IllegalStateException();
    }

    public static boolean inRange(Point.Double origin, Point.Double other, double range){
        if(origin.distance(other) > range){
            return false;
        }
        return true;
    }

    public static double getAngle(Point.Double origin, Point.Double other){
        double dx = other.getX() - origin.getX();
        double dy = other.getY() - origin.getY();

        return Math.atan2(dy, dx);
    }

    public static Ship getTarget(boolean ally, Point.Double location, int range){
        ArrayList<Ship> ships = ally ? GameState.getArena().getEnemies() : GameState.getArena().getAllies();

        for(Ship ship: ships){
            if(MathHelper.inRange(location, ship.getLocation(), range)){
                return ship;
            }
        }

        return null;
    }

    public static int shortSide(Ship ship, Ship other){
        double angle = ship.getAngle();

        double targetAngle = getAngle(ship.getLocation(), other.getLocation());

        angle += (Math.PI * 2);
        angle = angle % (Math.PI * 2);

        targetAngle += (Math.PI * 2);
        targetAngle = targetAngle % (Math.PI * 2);



        return -1;
    }
}
