package GUI;

import Bol.action;
import Partie.Partie;

import javax.swing.*;
import java.awt.*;

/**
 * Created by siyuan on 2016/12/23.
 */
public class ViewChoisirOperation extends JPanel {
    private JButton Bdefausser;
    private JButton Bcompleter;
    private JButton BUtiliser;

    public ViewChoisirOperation() {
        this.setLayout(new FlowLayout());
        Bdefausser=new JButton();
        Bcompleter=new JButton();
        BUtiliser=new JButton();

        Bdefausser.setText("1   Defausser les cartes en main");
        Bcompleter.setText("2   Completer votre main jusqu'a 7 cartes");
        BUtiliser.setText("3   Utiliser une carte Action et Sacrifier un Croyant ou Guide Spirituel");

        Bdefausser.addActionListener(new action(){

            @Override
            public void run() {

                try {
                    Partie.bol.deposerInt(1);
                    CardLayout cardLayout= (CardLayout) ViewJouer.ViewChoix.getLayout();
                    cardLayout.show(ViewJouer.ViewChoix, "ViewNull");

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });

        Bcompleter.addActionListener(new action(){

            @Override
            public void run() {

                try {
                    Partie.bol.deposerInt(2);
                    CardLayout cardLayout= (CardLayout) ViewJouer.ViewChoix.getLayout();
                    cardLayout.show(ViewJouer.ViewChoix, "ViewNull");

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });

        BUtiliser.addActionListener(new action(){

            @Override
            public void run() {

                try {
                    Partie.bol.deposerInt(3);
                    CardLayout cardLayout= (CardLayout) ViewJouer.ViewChoix.getLayout();
                    cardLayout.show(ViewJouer.ViewChoix, "ViewNull");

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });

        this.add(Bdefausser);
        this.add(Bcompleter);
        this.add(BUtiliser);
    }

}
