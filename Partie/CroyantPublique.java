package Partie;

import java.io.Serializable;

import java.util.ArrayList;

import Carte.CarteCroyant;
/**
 * La classe CroyantPublique represente les croyant dans le table.
 * @author Zijie
 * @author Siyuan
 *
 */
public class CroyantPublique implements Serializable{
	
	private ArrayList<CarteCroyant> listCroyant; 
	
	public CroyantPublique() {
		this.listCroyant=new ArrayList<CarteCroyant>();
	}

	public void addCroyant(CarteCroyant croyant){
		this.listCroyant.add(croyant);
		
	}
	
    public void removeCroyant(CarteCroyant croyant){
    	this.listCroyant.remove(croyant);		
	}

	public ArrayList<CarteCroyant> getListCroyant() {
		return listCroyant;
	}

	public void setListCroyant(ArrayList<CarteCroyant> listCroyant) {
		this.listCroyant = listCroyant;
	}

}
