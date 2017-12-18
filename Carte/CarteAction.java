package Carte;

import GUI.CButton;
import Joueur.Joueur;

import java.io.Serializable;

/**
 * La classe CarteAction represente tous les cartes d'action dans le jeu.
 * Dans cette classe, nous avons definir quelques fonctions et structures fondamentales des cartes d'action.
 * @author Zijie
 * @author Siyuan
 */
public abstract class CarteAction implements Serializable {
private String origine;
private boolean resteEnJeu;
private boolean interuupted;
private CButton button;

public final static String Dogmes[] ={"Nature","Humain","Mystique","Symboles","Chaos"};
public final static String Origines[]={"Jour","Nuit","Neant","Aube","Crepuscule","Sans"};

public String getOrigine() {
	return origine;
}

public void setOrigine(String origine) {
	this.origine = origine;
}

public boolean isResteEnJeu() {
	return resteEnJeu;
}

public void setResteEnJeu(boolean resteEnJeu) {
	this.resteEnJeu = resteEnJeu;
}
/**
 * Pour obtenir le nom d'une carte d'action.
 * @return Le nom de cette carte.
 */
public String getName(){
	   String nom=null;
	   if(this instanceof CarteCroyant){
	      CarteCroyant croyant= (CarteCroyant) this;
	      nom="croyant "+croyant.getNom();
	   }
	   else if(this instanceof  DeusEX){
	      DeusEX deusEX=(DeusEX)this;
	      nom="DeusEX "+deusEX.getNom();
	   }else if (this instanceof GuideSpirituel){
	      GuideSpirituel guideSpirituel=(GuideSpirituel)this;
	      nom="GuideSpirituel "+guideSpirituel.getNom();
	   }
	   else if (this instanceof Apocalypse){
	      nom="Apocalypse";
	   }
	   return nom;

	}

public boolean isInteruupted() {
	return interuupted;
}

public void setInteruupted(boolean interuupted) {
	this.interuupted = interuupted;
}

	public CButton getButton() {
		return button;
	}

	public void setButton(CButton button) {
		this.button = button;
	}
}