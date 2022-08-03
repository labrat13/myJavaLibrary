package Part1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class ScreenShot
{
    
    private File file;
    
    public void init() throws Exception
    {
        
        Robot robot = new Robot();
        String savePath = System.getProperty("java.io.tmpdir") + new Random().nextInt(50000) + ".png";
        Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage image = robot.createScreenCapture(capture);
        file = new File(savePath);
        ImageIO.write(image, "png", file);
        file.deleteOnExit();
    }
    
    public File[] getFiles()
    {
        return new File[] { file };
    }
}
