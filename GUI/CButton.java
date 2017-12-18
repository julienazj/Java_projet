package GUI;

import Bol.BolInt;
import Bol.action;
import Carte.*;
import Joueur.JoueurPhysique;
import Partie.Partie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.StringJoiner;

/**
 * Created by siyuan on 2016/12/22.
 */
public class CButton extends JButton implements Observer {
    private CarteAction ca;
    private BolInt bolInt;
    public static ArrayList<CButton> cButtonsChoisi=new ArrayList<CButton>();


    public CButton(CarteAction ca) {

        ca.setButton(this);
        this.ca=ca;
        this.setSize(100, 100);

        String chemin = null;

        if(ca instanceof CarteCroyant){
            chemin="CR"+((CarteCroyant) ca).getId();
        }else if (ca instanceof GuideSpirituel){
            chemin="GS"+((GuideSpirituel) ca).getId();
        }else if(ca instanceof DeusEX){
            chemin="DS"+((DeusEX) ca).getId();
        }else if (ca instanceof Apocalypse){
            chemin="AP"+((Apocalypse) ca).getId();
        }

        ImageIcon icon1=new ImageIcon(this.getClass().getResource("/pics/"+chemin+".png"));


        Image temp = icon1.getImage().getScaledInstance(this.getWidth(),
                this.getHeight(), icon1.getImage().SCALE_SMOOTH);
        icon1 = new ImageIcon(temp);

        this.setIcon(icon1);
        this.setForeground(Color.BLUE);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setOpaque(false);
        
        this.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
            	CButton testB = (CButton) e.getSource();
            	testB.setSize(200, 200);
                testB.ca=ca;

                String chemin = null;

                if(ca instanceof CarteCroyant){
                    chemin="CR"+((CarteCroyant) ca).getId();
                }else if (ca instanceof GuideSpirituel){
                    chemin="GS"+((GuideSpirituel) ca).getId();
                }else if(ca instanceof DeusEX){
                    chemin="DS"+((DeusEX) ca).getId();
                }else if (ca instanceof Apocalypse){
                    chemin="AP"+((Apocalypse) ca).getId();
                }
                ImageIcon icon1=new ImageIcon(this.getClass().getResource("/pics/"+chemin+".png"));


                Image temp = icon1.getImage().getScaledInstance(testB.getWidth(),
                        testB.getHeight(), icon1.getImage().SCALE_SMOOTH);
                icon1 = new ImageIcon(temp);

                testB.setIcon(icon1);
            	super.mouseEntered(e);
            }
            public void mouseExited(MouseEvent e){
            	CButton testB = (CButton) e.getSource();
            	testB.setSize(100, 100);
                testB.ca=ca;

                String chemin = null;

                if(ca instanceof CarteCroyant){
                    chemin="CR"+((CarteCroyant) ca).getId();
                }else if (ca instanceof GuideSpirituel){
                    chemin="GS"+((GuideSpirituel) ca).getId();
                }else if(ca instanceof DeusEX){
                    chemin="DS"+((DeusEX) ca).getId();
                }else if (ca instanceof Apocalypse){
                    chemin="AP"+((Apocalypse) ca).getId();
                }
                ImageIcon icon1=new ImageIcon(this.getClass().getResource("/pics/"+chemin+".png"));


                Image temp = icon1.getImage().getScaledInstance(testB.getWidth(),
                        testB.getHeight(), icon1.getImage().SCALE_SMOOTH);
                icon1 = new ImageIcon(temp);

                testB.setIcon(icon1);
            	super.mouseEntered(e);
            	super.mouseExited(e);
            }
        });

        

    }


    /**
     * Methode pour creer le carte en cours
     */
    public CButton() {
        this.setSize(200, 200);

        ImageIcon icon1 = new ImageIcon(this.getClass().getResource("/pics/Back.png"));


        Image temp = icon1.getImage().getScaledInstance(this.getWidth(),
                this.getHeight(), icon1.getImage().SCALE_SMOOTH);
        icon1 = new ImageIcon(temp);

        this.setIcon(icon1);
        this.setForeground(Color.BLUE);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setOpaque(false);
    }

    /**
     * Setter l'image de button selon la carte action
     * @param ca La carte action
     */
    public void setImage(CarteAction ca){
        String chemin = null;
        if(ca instanceof CarteCroyant){
            chemin="CR"+((CarteCroyant) ca).getId();
        }else if (ca instanceof GuideSpirituel){
            chemin="GS"+((GuideSpirituel) ca).getId();
        }else if(ca instanceof DeusEX){
            chemin="DS"+((DeusEX) ca).getId();
        }else if (ca instanceof Apocalypse){
            chemin="AP"+((Apocalypse) ca).getId();
        }

        ImageIcon icon1 = new ImageIcon(this.getClass().getResource("/pics/"+chemin+".png"));


        Image temp = icon1.getImage().getScaledInstance(this.getWidth(),
                this.getHeight(), icon1.getImage().SCALE_SMOOTH);
        icon1 = new ImageIcon(temp);

        this.setIcon(icon1);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public CarteAction getCa() {
        return ca;
    }

    public void setCa(CarteAction ca) {
        this.ca = ca;
    }
}
