package Strategie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Carte.CarteAction;
import Carte.CarteCroyant;
import Carte.GuideSpirituel;
import Joueur.Joueur;
import Joueur.JoueurVirtuel;
import Partie.Partie;
/**
 * L'interface Strategie est une interface contient les methode de Joueur virtuel.
 * Ici, on utilise le patron de strategie.
 * @author Siyuan
 * @author Zijie
 *
 */
public interface Strategie {
	
	public int choisirOperation (Partie partie)throws IOException;

	public boolean choisirSiUtiliserCapaciteSpeciale();

	public LinkedList<CarteAction> choisirListCarte() throws IOException ;


	public CarteAction choisirUneCarte() ;

	public Joueur ChoisirUnJoueur( Partie partie) ;

	public CarteCroyant choisirCroyantRattacher(ArrayList<CarteCroyant> cartePossible) ;

	public void decideSiIterruption(Partie partie, CarteAction ca) throws IOException ;
	
	public void decideInterruptionRomtec(GuideSpirituel guide);	

	public LinkedList<Joueur> choisirListJoueur(Partie partie) throws IOException ;

	public CarteAction ChoisirUneCard(List c) ;

	public CarteCroyant choisirUneCroyantAssocie() ;

	public GuideSpirituel choisirUneGuideSpirituelAssocie() ;

	public GuideSpirituel choisirGuideSpirituelDautreJoueur(Joueur jC) ;

	public Joueur ChoisirUnJoueur(List c);
	
	public boolean decideSiSacrifier() ;
	public void decideSiUtiliserSansOrigine(Partie partie) throws IOException;
	public void setJoueur(JoueurVirtuel j);
}
