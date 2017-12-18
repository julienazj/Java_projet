package GUI;

import Bol.BolInt;
import Bol.action;
import Evenements.PButtonBorderEvent;
import Evenements.PointRepaintEvent;
import Joueur.Joueur;
import Joueur.JoueurVirtuel;
import Joueur.JoueurPhysique;
import Partie.Partie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by siyuan on 2016/12/21.
 */
public class PButton extends JButton implements Observer {
    private Joueur joueur;
    public BolInt bolInt;
    public static ArrayList<PButton> PbuttonChoisi=new ArrayList<PButton>();


    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public PButton(Joueur joueur, BolInt bolInt) {

        this.bolInt = bolInt;
        for (int i = 0; i < Partie.PartieEnCours.getJoueur().size(); i++) {
            Joueur j = Partie.PartieEnCours.getJoueur().get(i);
            j.addObserver(this);
        }

        this.joueur = joueur;
        this.joueur.setButton(this);
        this.setSize(100, 100);

        int id = joueur.getCarteDevinite().getId();
        System.out.println(joueur.getCarteDevinite());
        ImageIcon icon1 = new ImageIcon(this.getClass().getResource("/pics/DV" + id + ".png"));
        Image temp = icon1.getImage().getScaledInstance(this.getWidth(),
                this.getHeight(), icon1.getImage().SCALE_SMOOTH);
        icon1 = new ImageIcon(temp);

        this.setIcon(icon1);
        this.setForeground(Color.BLUE);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setOpaque(false);

        this.addActionListener(new action(this) {

            public void run() {

                int index=Partie.PartieEnCours.getJoueur().indexOf(joueur);

                if(JoueurPhysique.ChoixListJoueur==false) {
                    try {
                        bolInt.deposerInt(index);

                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else {
                    PButton pButton= (PButton) this.getComponent();
                    pButton.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), null));
                    PButton.PbuttonChoisi.add(pButton);
                    Partie.bolString.number+=String.valueOf(index)+" ";

                }
            }

        });
        this.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
            	PButton testB = (PButton) e.getSource();
            	testB.setSize(200, 200);
            	ImageIcon icon1 = new ImageIcon(this.getClass().getResource("/pics/DV" + id + ".png"));


                Image temp = icon1.getImage().getScaledInstance(testB.getWidth(),
                		testB.getHeight(), icon1.getImage().SCALE_SMOOTH);
                icon1 = new ImageIcon(temp);

                testB.setIcon(icon1);
                
                /*/label
                PPoint pp = (PPoint) e.getSource();
                pp.setVisible(true);*/
            	super.mouseEntered(e);
            }

            public void mouseExited(MouseEvent e){
            	PButton testB = (PButton) e.getSource();
            	testB.setSize(100, 100);
            	ImageIcon icon1 = new ImageIcon(this.getClass().getResource("/pics/DV" + id + ".png"));


                Image temp = icon1.getImage().getScaledInstance(testB.getWidth(),
                		testB.getHeight(), icon1.getImage().SCALE_SMOOTH);
                icon1 = new ImageIcon(temp);

                testB.setIcon(icon1);
                /*/label
                PPoint pp =(PPoint) e.getSource();
                pp.setVisible(false);*/
            	super.mouseExited(e);
            }
        });


    }


    public PButton() {
        this.setSize(100, 100);
        ImageIcon icon1 = new ImageIcon(this.getClass().getResource("/pics/Back.png"));


        Image temp = icon1.getImage().getScaledInstance(this.getWidth(),
                this.getHeight(), icon1.getImage().SCALE_SMOOTH);
        icon1 = new ImageIcon(temp);

        this.setIcon(icon1);
        this.setForeground(Color.BLUE);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setOpaque(false);
        
        this.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
            	PButton testB = (PButton) e.getSource();
            	testB.setSize(200, 200);
                ImageIcon icon1 = new ImageIcon(this.getClass().getResource("/pics/Back.png"));


                Image temp = icon1.getImage().getScaledInstance(testB.getWidth(),
                        testB.getHeight(), icon1.getImage().SCALE_SMOOTH);
                icon1 = new ImageIcon(temp);

                testB.setIcon(icon1);

            	super.mouseEntered(e);
            }
            public void mouseExited(MouseEvent e){
            	PButton testB = (PButton) e.getSource();
            	testB.setSize(100, 100);
                ImageIcon icon1 = new ImageIcon(this.getClass().getResource("/pics/Back.png"));


                Image temp = icon1.getImage().getScaledInstance(testB.getWidth(),
                        testB.getHeight(), icon1.getImage().SCALE_SMOOTH);
                icon1 = new ImageIcon(temp);

                testB.setIcon(icon1);

            	super.mouseExited(e);
            }
        });
    }

    @Override
    public void update(Observable arg0, Object arg1) {

        if (arg1 instanceof PButtonBorderEvent) {
            if (this.joueur instanceof JoueurVirtuel) {
                this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.green), null));
            }
        }/*else if(arg1 instanceof PointRepaintEvent){
            String s1="PointAction Jour : "+joueur.getPointActionJour()+"    PointAction Nuit :"+joueur.getPointActionNuit()+"  PointAction Neant : "+joueur.getPointActionNeant();
            String s2="PeutSacrifierGuide: "+joueur.isPeutSacrifierGuideSpirituel()+"  PeutSacrifierCroyant : "+joueur.getPeutScrifierCroyant()+ "  CapacitéUtilisé : "+joueur.isCapaciteUtilise();
            this.setText("<html>"+s1+"<br>"+s2+"</html>");
        }*/

    }

}

