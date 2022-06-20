package BadApp.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class BaseForm extends JFrame {
    public static Image Icon=null;
    static {
        try {
            Icon = ImageIO.read(Objects.requireNonNull(BaseForm.class.getClassLoader().getResource("icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BaseForm(int width, int height){
        setTitle("Good");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(width, height));
        setLocation(
                Toolkit.getDefaultToolkit().getScreenSize().width/2-width/2,
                Toolkit.getDefaultToolkit().getScreenSize().height/2-height/2
        );
        if (Icon!=null){
            setIconImage(Icon);
        }
    }
}
