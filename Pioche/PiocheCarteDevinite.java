package Pioche;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;

import Carte.CarteAction;
import Carte.Devinite;
/**
 * La classe PiocheCarteDevinite represente la pioche de carte devinite.
 * @author AZJ
 *
 */
public class PiocheCarteDevinite implements Serializable {
private LinkedList<Devinite> contenue=new LinkedList<Devinite>(); 
	
	public void melanger(){
		Collections.shuffle(this.contenue);
	}
	
	public Devinite trierCarteDessus(){
		return contenue.pop();
	}
	
	public LinkedList<Devinite> getContenue() {
		return contenue;
	}

	public void setContenue(LinkedList<Devinite> contenue) {
		this.contenue = contenue;
	}
	
	public PiocheCarteDevinite(){
		for(int i=0;i<9;i++){
			Devinite d=new Devinite(i);
			this.getContenue().add(d);
		}
		this.melanger();
	}
	
}
