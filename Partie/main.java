package Partie;

import java.io.IOException;

import Bol.BolInt;
import Bol.BolString;
import Carte.CarteCroyant;
import GUI.GUISelection;
import Joueur.JoueurPhysique;
/**
 * La classe main est la classe de fonction main.
 * @author AZJ
 *
 */
public class main {
	public static void main(String [ ] args) throws IOException{
		BolInt bol=new BolInt();
		BolString bolString=new BolString();
		JoueurPhysique jp=new JoueurPhysique(0,bol);
		Partie partie=new Partie(bol,bolString);
		GUISelection guiSelection=new GUISelection(bol);
		guiSelection.init();
		partie.commencerLaPartie(jp, jp.choisirNombreJoueur());

	}

}
