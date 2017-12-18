package Pioche;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;

import Carte.Apocalypse;
import Carte.CarteAction;
import Carte.CarteCroyant;
import Carte.DeusEX;
import Carte.GuideSpirituel;

/**
 * La classe PiocheCarteAction represente la pioche pour mettre les carte d'action
 * @author Siyuan
 * @author Siyuan
 *
 */
public class PiocheCarteAction implements Serializable {
private LinkedList<CarteAction> contenue=new LinkedList<CarteAction>(); 
	/**
	 * Methode pour melanger les cartes dans la pioche.
	 */
	public void melanger(){
		Collections.shuffle(this.contenue);
	}
	/**
	 * Methode pour obtenir les cartes d'action.
	 * @return
	 */
	public CarteAction trierCarteDessus(){
		if(this.getContenue().size()==0){
			this.addCarte();
			this.melanger();
		}
		return contenue.pop();
	}
	
	public PiocheCarteAction(){
	    this.addCarte();
	    this.melanger();
	}
	
	public void addCarte(){
		for(int i=0;i<20;i++){
	    	this.getContenue().add(new GuideSpirituel(i));
	    }
	    for(int i=0;i<36;i++){
	    	this.getContenue().add(new CarteCroyant(i));
	    }
	    for(int i=0;i<18;i++){
	    	this.getContenue().add(new DeusEX(i));
	    }
	    for(int i=0;i<5;i++){
	    	this.getContenue().add(new Apocalypse(i));
	    }
	}

	public LinkedList<CarteAction> getContenue() {
		return contenue;
	}

	public void setContenue(LinkedList<CarteAction> contenue) {
		this.contenue = contenue;
	}
	
	

}
