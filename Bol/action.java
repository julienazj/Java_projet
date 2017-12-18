package Bol;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class action implements Runnable, ActionListener {

    private Component component;
    @Override
    public void actionPerformed(ActionEvent e) {
        Thread t=new Thread (this);
        t.start();
        // TODO Auto-generated method stub

    }

    @Override
    public void run() {

    }

    public action(Component component) {
        this.component = component;
    }
    public action(){

    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}