package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2017/1/8.
 */
public class ImagePanel extends JComponent{
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
