package GUI;

import Bol.action;
import Carte.CarteAction;
import Carte.CarteCroyant;
import Carte.GuideSpirituel;
import Evenements.ChampRepaintEvent;
import Joueur.Joueur;
import Joueur.JoueurPhysique;
import Partie.Partie;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by siyuan on 2016/12/25.
 */
public class ChampPanel extends JPanel implements Observer {
    private Joueur joueur;

    public ChampPanel(Joueur joueur) {
        joueur.addObserver(this);
        this.setLayout(new FlowLayout());
        this.joueur = joueur;
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
    }

    public void repaintChamp() {
        Component[] components = this.getComponents();
        for (int i = 0; i < components.length; i++) {
            this.remove(components[i]);
        }
        this.add(new JLabel("Champ"));
        ArrayList<CarteAction> list = new ArrayList<CarteAction>();
        Iterator<GuideSpirituel> iterator = joueur.getAssociationGC().keySet().iterator();
        while (iterator.hasNext()) {
            GuideSpirituel guideSpirituel = iterator.next();
            list.add(guideSpirituel);
            Iterator<CarteCroyant> iterator1 = guideSpirituel.getCroyantRattache().iterator();
            while (iterator1.hasNext()) {
                CarteCroyant croyant = iterator1.next();
                list.add(croyant);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            CButton cb = new CButton(list.get(i));
            this.add(new CButton(list.get(i)));
            int finalI = i;
            cb.addActionListener(new action(cb){
                public void run() {

                    if( JoueurPhysique.ChoixParmiList==null) {

                        try {
                            Partie.PartieEnCours.bol.deposerInt(finalI);

                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }else {
                        int index=JoueurPhysique.ChoixParmiList.indexOf(cb.getCa());
                        try {
                            Partie.PartieEnCours.bol.deposerInt(index);

                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }

                }
            });
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ChampRepaintEvent) {
            this.repaintChamp();
        }
    }
}
