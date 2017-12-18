package GUI;

import Bol.action;
import Joueur.JoueurPhysique;
import Partie.Partie;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 * Created by siyuan on 2016/12/25.
 * Deux Boutons pour choix de list de cartes ou joueurs
 */
public class ViewValiderAnnuler extends JPanel {
    private JButton valider;
    private JButton annuler;

    public ViewValiderAnnuler() {
        this.setLayout(new FlowLayout());
        valider=new JButton();
        annuler=new JButton();

        valider.setText("Valider");
        annuler.setText("Annuler");

        valider.addActionListener(new action() {

            public void run() {


                try {
                    Partie.bolString.deposerString(Partie.bolString.number);
                    CardLayout cardLayout= (CardLayout) ViewJouer.ViewChoix.getLayout();
                    cardLayout.show(ViewJouer.ViewChoix, "ViewNull");
                    ViewJouer.LabelJoueur.setText(null);

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                JoueurPhysique.ChoixListCarte=false;
                JoueurPhysique.ChoixListJoueur=false;
            }

        });

        annuler.addActionListener(new action() {

            public void run() {

                Partie.bolString.number="";
                Iterator<CButton>iterator=CButton.cButtonsChoisi.iterator();
                while (iterator.hasNext()){
                    CButton cButton=iterator.next();
                    cButton.setBorder(BorderFactory.createEmptyBorder());
                }
                CButton.cButtonsChoisi.removeAll(CButton.cButtonsChoisi);

                Iterator<PButton>iterator1=PButton.PbuttonChoisi.iterator();
                while (iterator1.hasNext()){
                    PButton pButton=iterator1.next();
                    pButton.setBorder(BorderFactory.createEmptyBorder());
                }
                PButton.PbuttonChoisi.removeAll(PButton.PbuttonChoisi);

            }

        });

        this.add(valider);
        this.add(annuler);
    }
}
