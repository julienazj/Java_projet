package GUI;

import Bol.BolInt;
import Bol.action;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by siyuan on 2016/12/22.
 */
public class GUISelection {
    private ImagePanel panel;
    private JFrame frame;
    private JLabel title;
    private BolInt bol;

    public GUISelection(BolInt bol){

        this.bol=bol;
        this.title=new JLabel();
        frame = new JFrame();


        frame.setVisible(true);
        Image img = Toolkit.getDefaultToolkit().createImage("/pics/startImage.jpg");
        panel=new ImagePanel(img);
        frame.setContentPane(panel);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400,400);
    }

    public void init(){
        panel.setLayout(new BorderLayout(0, 0));
        panel.add(title,  BorderLayout.NORTH);
        title.setText("Pandoncreon Devinité");
        JPanel panelChoix=new JPanel(new BorderLayout(0, 0));
        panel.add(panelChoix,BorderLayout.SOUTH);
        JPanel panel0 = new JPanel();
        JPanel panel1=new JPanel();
        panelChoix.add(panel0,BorderLayout.SOUTH);
        panelChoix.add(panel1,BorderLayout.NORTH);
        panel1.setLayout(new FlowLayout());
        panel0.setLayout(new FlowLayout());
        JButton button1=new JButton();
        button1.setText("Facile");
        JButton button2=new JButton();
        button2.setText("Difficile");
        panel0.add(button1);
        panel0.add(button2);
        button1.addActionListener(new action(){

            public void run() {

                try {
                    bol.deposerInt(0);
                    //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    frame.setVisible(false);

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });

        button2.addActionListener(new action(){

            public void run() {

                try {
                    bol.deposerInt(1);
                    //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    frame.setVisible(false);

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });
        for (int i=1;i<7;i++){
            JButton button=new JButton();
            button.setText(String.valueOf(i));
            int finalI = i;
            button.addActionListener(new action(){

                public void run() {

                    try {
                        bol.deposerInt(finalI);

                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            });
           panel1.add(button);
        }
        panel1.repaint();
    }

}
