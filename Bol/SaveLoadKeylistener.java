package Bol;

import Partie.Partie;
import SaveLoad.SaveLoadGui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Administrator on 2017/1/6.
 */
public class SaveLoadKeylistener implements Runnable, KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.run();
        }
    }

    @Override
    public void run() {
            SaveLoadGui saveLoadGui=new SaveLoadGui(Partie.PartieEnCours.getSaver(),Partie.PartieEnCours.getLoader());
    }
}
