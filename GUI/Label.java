package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by siyuan on 2016/12/25.
 */
public class Label extends JLabel {
    public Label(){
        this.setFont(new java.awt.Font("Dialog", 1, 40));
        this.setForeground(Color.red);


    }


    public void setTexte(String s){
        this.setText(s);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }
}
