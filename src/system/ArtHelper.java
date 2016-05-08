package system;

import javax.imageio.ImageIO;
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
}
