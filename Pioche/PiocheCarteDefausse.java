package Pioche;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;

import Carte.CarteAction;
/**
 * La classe PiocheCarteDefausse represente les carte defausses.
 * @author Zijie
 * @author Siyuan
 *
 */
public class PiocheCarteDefausse implements Serializable {
	private LinkedList<CarteAction> contenue=new LinkedList<CarteAction>(); 
	
	public void melanger(){
		Collections.shuffle(this.contenue);
	}
	
	public CarteAction trierCarteDessus(){
		return contenue.pop();
	}

	public LinkedList<CarteAction> getContenue() {
		return contenue;
	}

	public void setContenue(LinkedList<CarteAction> contenue) {
		this.contenue = contenue;
	}
	
}
