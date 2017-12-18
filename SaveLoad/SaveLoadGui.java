package SaveLoad;

import Evenements.CentreRepaintEvent;
import Evenements.ChampRepaintEvent;
import GUI.ViewJouer;
import Joueur.Joueur;
import Partie.Partie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * La classe SaveLoadGui represent realisation de frame save/load dans GUI
 * Created by siyuan on 2016/12/14.
 */
public class SaveLoadGui implements Runnable {
    private JButton saveButton;
    private JPanel panel1;
    private JButton loadButton;
    private Saver saver;
    private Loader loader;

    public void saveLoad() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

    }

    public SaveLoadGui(Saver saver, Loader loader) {
        this.saver = saver;
        this.loader = loader;

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saver.save();
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Partie partie = loader.load();
                Partie.PartieEnCours=partie;
                ViewJouer viewJouer=new ViewJouer(partie,partie.bol);
                ViewJouer.ViewJouerEnCours=viewJouer;
             new Thread(new Runnable(){
					 public void run(){
						 try {

                             for (int i=0; i<partie.getJoueur().size();i++){
                                 Joueur joueur=partie.getJoueur().get(i);
                                 joueur.notifyElement(new ChampRepaintEvent());
                             }
                             partie.notifyElement(new CentreRepaintEvent());
							partie.tour();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					 }}).start();
				//partie.tour();
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });


    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        saveButton = new JButton();
        saveButton.setText("Save");
        panel1.add(saveButton, BorderLayout.NORTH);
        loadButton = new JButton();
        loadButton.setText("Load");
        panel1.add(loadButton, BorderLayout.SOUTH);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
