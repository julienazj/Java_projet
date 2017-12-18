package GUI;

import Evenements.PointRepaintEvent;
import Joueur.Joueur;
import Partie.Partie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Le panel pour print le points de joueurs et leurs Etats
 * Created by siyuan on 2016/12/28.
 */
public class PPoint extends JPanel implements Observer {
    private Joueur joueur;

    private JLabel pointNuit;
    private JLabel pointAct;
    private JLabel pointCroyant;
    private JLabel PeutSacrifierCroyant;
    private JLabel PeutSacrifierGuide;
    private JLabel CapaciteUtilise;

	private int count = 1;


    public PPoint(Joueur joueur) {
        this.joueur=joueur;

        for (int i = 0; i < Partie.PartieEnCours.getJoueur().size(); i++) {
            Joueur j = Partie.PartieEnCours.getJoueur().get(i);
            j.addObserver(this);
        }
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        this.pointAct=new JLabel();
        this.pointNuit=new JLabel();
        this.pointCroyant=new JLabel();
        this.PeutSacrifierCroyant=new JLabel();
        this.PeutSacrifierGuide=new JLabel();
        this.CapaciteUtilise=new JLabel();

        c.fill=GridBagConstraints.VERTICAL;
        c.gridx=0;
        c.gridy=0;
        this.add(pointAct,c);

        c.gridx=0;
        c.gridy=1;
        this.add(pointNuit,c);

        c.gridx=0;
        c.gridy=2;
        this.add(pointCroyant,c);

        c.gridx=1;
        c.gridy=0;
        this.add(PeutSacrifierCroyant,c);

        c.gridx=1;
        c.gridy=1;
        this.add(PeutSacrifierGuide,c);

        c.gridx=1;
        c.gridy=2;
        this.add(CapaciteUtilise,c);
        
        //
        int i;
		 for(i=0;i<6;i++){
			 this.getComponent(i).setVisible(false);
		 }
        
		//
		/* JButton info = new JButton("Info");
	        info.addMouseListener(new MouseAdapter(){
	        	 public void MouseEntered(MouseEvent e){
	        		 JButton info = (JButton) e.getSource();
	        		 int i;
	        		 for(i=0;i<6;i++){
	        			 info.getParent().getComponent(i).setVisible(true);
	        		 }
	        		 super.mouseEntered(e);

		                
	        	 }}
	        		);
	        
	        c.gridx=2;
	        c.gridy=2;
	        this.add(info,c);
		 */
        JButton info = new JButton("Info");
        

        info.addActionListener(new ActionListener(){
        	int count =1;
        	 public void actionPerformed(ActionEvent e){
        		 int i;
        		 this.count++;
        		 if (count%2==0){

        		 for(i=0;i<6;i++){
        			 info.getParent().getComponent(i).setVisible(true);
        		 }
        		 }
        		 else{
        			 for(i=0;i<6;i++){
            			 info.getParent().getComponent(i).setVisible(false);
            		 }
        		 }
	                
        	 }
        	 }
 
        		);
        
        c.gridx=2;
        c.gridy=1;
        this.add(info,c);


    }

    public void repaintPoint(){
        pointAct.setText("PointAction Jour/Nuit/Néant : "+joueur.getPointActionJour()+"/"+joueur.getPointActionNuit()+"/"+joueur.getPointActionNeant());
        pointNuit.setText("                        ");
        pointCroyant.setText("NombreCroyant : "+joueur.getNombreCroyant());
        PeutSacrifierCroyant.setText("PeutSacrifierGuide: "+joueur.isPeutSacrifierGuideSpirituel());
        PeutSacrifierGuide.setText("PeutSacrifierCroyant : "+joueur.getPeutScrifierCroyant());
        CapaciteUtilise.setText("CapacitéUtilisé : "+joueur.isCapaciteUtilise());
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof PointRepaintEvent){
            this.repaintPoint();
        }
    }
}
