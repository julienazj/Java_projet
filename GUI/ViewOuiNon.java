package GUI;

import Bol.BolInt;
import Bol.action;
import Partie.Partie;

import javax.swing.*;
import java.awt.*;

/**
 * Created by siyuan on 2016/12/25.
 */
public class ViewOuiNon extends JPanel {
    private JButton oui;
    private JButton non;

    public ViewOuiNon() {
        this.setLayout(new FlowLayout());
        oui=new JButton();
        non=new JButton();

        oui.setText("Oui");
        non.setText("Non");

        oui.addActionListener(new action(){

            @Override
            public void run() {

                try {
                    Partie.bol.deposerInt(1);
                   CardLayout cardLayout= (CardLayout) ViewJouer.ViewChoix.getLayout();
                    cardLayout.show(ViewJouer.ViewChoix, "ViewNull");
                    ViewJouer.LabelJoueur.setText(null);

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });

        non.addActionListener(new action(){

            @Override
            public void run() {

                try {
                    Partie.bol.deposerInt(0);
                    CardLayout cardLayout= (CardLayout) ViewJouer.ViewChoix.getLayout();
                    cardLayout.show(ViewJouer.ViewChoix, "ViewNull");
                    ViewJouer.LabelJoueur.setText(null);

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });

        this.add(oui);
        this.add(non);
    }
}
