package GUI;

import Bol.action;
import Carte.CarteCroyant;
import Evenements.CentreRepaintEvent;
import Joueur.JoueurPhysique;
import Partie.Partie;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by siyuan on 2016/12/25.
 */
public class CentreTablePanel extends JPanel implements Observer{
    public CentreTablePanel(){
        this.setSize(300 ,300);
        Partie.PartieEnCours.addObserver(this);
        this.setLayout(new FlowLayout());
    }
    public void repaintCentre(){
        Component[] components = this.getComponents();
        for(int i=0;i<components.length;i++){
            this.remove(components[i]);
        }
        for(int i=0;i<Partie.PartieEnCours.getCentreCommun().getListCroyant().size();i++)
        {
            CarteCroyant croyant = Partie.PartieEnCours.getCentreCommun().getListCroyant().get(i);
            CButton cButton = new CButton(croyant);
            this.add(cButton);
            int finalI = i;
           /* cButton.addActionListener(new action(){
                public void run() {
                        try {
                            Partie.bol.deposerInt(finalI);

                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
            });*/
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof CentreRepaintEvent){
            this.repaintCentre();
        }
    }
}