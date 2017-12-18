package GUI;

import Bol.BolInt;
import Bol.action;
import Joueur.JoueurPhysique;
import Partie.Partie;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Le panel qui contiens tous les cartes de joueur physique
 * Created by siyuan on 2016/12/23.
 *
 */
public class PPanel extends JPanel implements Observer {
    private JoueurPhysique joueurPhysique;
    public BolInt bolInt;

    public void repaintCA( ){
        //Remove tous les ancients elements
        joueurPhysique.addObserver(this);
        Component[] components = this.getComponents();
        for(int i=0;i<components.length;i++){
            if(components[i] instanceof CButton){
                this.remove(components[i]);
            }
        }
        //Ajoute new elements
        for (int i=0;i<joueurPhysique.getCarte().size();i++){
            CButton cb=new CButton(joueurPhysique.getCarte().get(i));
            this.add(cb);
            cb.addActionListener(new action(cb){
                public void run() {

                    if(JoueurPhysique.ChoixListCarte==false && JoueurPhysique.ChoixParmiList==null) {
                        int index=Partie.PartieEnCours.getJoueurPhysique().getCarte().indexOf(cb.getCa());
                        try {
                            bolInt.deposerInt(index);

                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }else if(JoueurPhysique.ChoixListCarte==false && JoueurPhysique.ChoixParmiList!=null){
                        int index=JoueurPhysique.ChoixParmiList.indexOf(cb.getCa());
                        try {
                            bolInt.deposerInt(index);

                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                    else if (JoueurPhysique.ChoixListCarte==true){
                        int index=Partie.PartieEnCours.getJoueurPhysique().getCarte().indexOf(cb.getCa());
                        CButton cButton= (CButton) this.getComponent();
                        cButton.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), null));
                        CButton.cButtonsChoisi.add(cButton);
                        Partie.bolString.number+=String.valueOf(index)+" ";
                    }
                }
            });
        }
        this.repaint();
    }

    public PPanel(JoueurPhysique joueurPhysique, PButton DJoueur, BolInt bolInt) {
        this.joueurPhysique = joueurPhysique;
        this.bolInt=bolInt;
        this.add(DJoueur);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaintCA();
    }
}
