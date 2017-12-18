package Joueur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Carte.CarteAction;
import Carte.CarteCroyant;
import Carte.GuideSpirituel;
import Partie.Partie;
import Strategie.Strategie;
/**
 * La classe JoueurVirtuel represente les operations d'un joueur virtuel.
 * Pour realiser la fonction de AI, on utilise le patron de strategie.
 * @author Zijie
 * @author Siyuan
 *
 */
public class JoueurVirtuel extends Joueur{	
	private Strategie strategie;
/**
 * Donner le nombre et la difficule de joueurvirtuels que l'on choisis
 * @param numeroJoueur
 * @param strategie
 */
	public JoueurVirtuel(int numeroJoueur,Strategie strategie) {
		super(numeroJoueur);
		this.strategie=strategie;
		strategie.setJoueur(this);
	}

	@Override
	public int choisirOperation(Partie partie) throws IOException {
		return this.strategie.choisirOperation(partie);
	}



	@Override
	public boolean choisirSiUtiliserCapaciteSpeciale() {
		// TODO Auto-generated method stub
		return this.strategie.choisirSiUtiliserCapaciteSpeciale();
	}

	@Override
	public LinkedList<CarteAction> choisirListCarte() throws IOException {
		// TODO Auto-generated method stub
		return this.strategie.choisirListCarte();
	}

	@Override
	public CarteAction choisirUneCarte() {
		// TODO Auto-generated method stub
		return this.strategie.choisirUneCarte();
	}

	@Override
	public Joueur ChoisirUnJoueur( Partie partie) {
		// TODO Auto-generated method stub
		return this.strategie.ChoisirUnJoueur(partie);
	}

	@Override
	public CarteCroyant choisirCroyantRattacher(ArrayList<CarteCroyant> cartePossible) {
		// TODO Auto-generated method stub
		return this.strategie.choisirCroyantRattacher(cartePossible);
	}

	@Override
	public void decideSiIterruption(Partie partie, CarteAction ca) throws IOException {
		this.strategie.decideSiIterruption(partie, ca);
		
	}
	public void decideInterruptionRomtec(GuideSpirituel guide){
		this.strategie.decideInterruptionRomtec(guide);
	}
	@Override
	public LinkedList<Joueur> choisirListJoueur(Partie partie) throws IOException {
		// TODO Auto-generated method stub
		return this.strategie.choisirListJoueur(partie);
	}

	@Override
	public CarteAction ChoisirUneCard(List c) {
		// TODO Auto-generated method stub
		return this.strategie.ChoisirUneCard(c);
	}

	@Override
	public CarteCroyant choisirUneCroyantAssocie() {
		// TODO Auto-generated method stub
		return this.strategie.choisirUneCroyantAssocie();
	}

	@Override
	public GuideSpirituel choisirUneGuideSpirituelAssocie() {
		// TODO Auto-generated method stub
		return this.strategie.choisirUneGuideSpirituelAssocie();
	}

	@Override
	public GuideSpirituel choisirGuideSpirituelDautreJoueur(Joueur jC) {
		// TODO Auto-generated method stub
		return this.strategie.choisirGuideSpirituelDautreJoueur(jC);
	}

	@Override
	public Joueur ChoisirUnJoueur(List c) {
		// TODO Auto-generated method stub
		return this.strategie.ChoisirUnJoueur(c);
	}

	@Override
	public boolean decideSiSacrifier() {
		return this.strategie.decideSiSacrifier();
	}

	@Override
	public void decideSiUtiliserSansOrigine(Partie partie) throws IOException {
		this.strategie.decideSiUtiliserSansOrigine( partie);
		
	}



}
