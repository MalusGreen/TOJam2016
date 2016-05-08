package system;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Kevin Zheng on 2016-05-06.
 */
public class ArtHelper {
    public static BufferedImage getImage(String path){
        try{
            return ImageIO.read(new File(path));
        }
        catch(IOException e){
            return new BufferedImage(25, 25, BufferedImage.TYPE_3BYTE_BGR);
        }
    }



    public static BufferedImage getEmptyImage(int width, int height){
        return new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
    }
    public static BufferedImage getBlankImage(int width, int height){
        return new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
    }

    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}
