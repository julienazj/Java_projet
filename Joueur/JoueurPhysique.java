package Joueur;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;
import java.util.stream.Stream;

import Bol.BolInt;
import Carte.CarteAction;
import Carte.CarteCroyant;
import Carte.DeusEX;
import Carte.GuideSpirituel;
import Evenements.PButtonBorderEvent;
import GUI.ViewJouer;
import Partie.DeCosmogonie;
import Partie.Partie;
import Pioche.PiocheCarteAction;

import javax.swing.*;
/**
 * La classe JoueurPhysique represente la classe de Joueur humaine.
 * Dans cette classe, nous avons realise des operations des Joueurs.
 * @author Zijie
 * @author Siyuan
 *
 */
public class JoueurPhysique extends Joueur {
	public static boolean ChoixListCarte=false;
	public static boolean ChoixListJoueur=false;
	public static List  ChoixParmiList;
	public BolInt bol;
	public JoueurPhysique(int numeroJoueur,BolInt bol) {
		super(numeroJoueur);
		this.bol=bol;
	}
	/**
	 * Methode pour completer les cartes en main.
	 */
	public void completerMain(Partie partie) {
		while (this.getCarte().size() < 7) {
			PiocheCarteAction pa = partie.getPiocheCarteAction();
			CarteAction ca=pa.trierCarteDessus();
			this.getCarte().add(ca);
			System.out.println("Cartes complétés ");
			System.out.println(ca);
		}
		System.out.println(this + " a comlete sa main à 7 cartes");
	}
/**
 * Methode pour choisir le nombre de joueur virtuel.
 * @return le nombre de joueur virtuel choisis.
 */
	public int choisirNombreJoueur() {
		System.out.println("Veuillez saisir le nombre de joueurs virtuels");
		/*Scanner reader = new Scanner(System.in);
		int choise = reader.nextInt();
		return choise;*/
		return this.bol.lireInt();
	}
	/**
	 * Methode pour choisir la difficulte de joueur virtuels
	 * @return 
	 */
	public int choisirDiff() {
		System.out.println("Veuillez saisir le difficulté de joueurs virtuels");
		/*Scanner reader = new Scanner(System.in);
		int choise = reader.nextInt();
		return choise;*/
		return this.bol.lireInt();
	}
/**
 * Methode pour commencer une autre partie
 * @return
 */
	public boolean choisirSiRecommencerPartie() {
		System.out.println("Fin du partie");
		System.out.println("Voulez-vous lancer une partie suivante ?");

		ViewJouer.LabelJoueur.setTexte("Voulez-vous lancer une partie suivante ?");
		CardLayout cardLayout= (CardLayout) ViewJouer.ViewChoix.getLayout();
		cardLayout.show(ViewJouer.ViewChoix, "ViewOuiNon");

		int choise = this.bol.lireInt();
		boolean result = false;
		// reader.close();
		if (choise == 0) {
			result = true;
		} else if (choise == 1) {
			result = false;
		}
		return result;
	}
/**
 * Methode pour afficher l'information de carte dans la console.
 */
	public void afficherCartes() {
		System.out.println("Main---------------------------------");
		for (int i = 0; i < this.getCarte().size(); i++) {
			System.out.println(i+"  "+this.getCarte().get(i));
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<>><<<<<<<<<<<<<<<<<<<<");
		}
		System.out.println("Champs---------------------------------");
		if (this.champGuide().size() > 0) {
			for (int i = 0; i < this.champGuide().size(); i++) {
				System.out.println(this.champGuide().get(i));
			}
		}
		if (this.champCroyant().size() > 0) {
			for (int i = 0; i < this.champCroyant().size(); i++) {
				System.out.println(this.champCroyant().get(i));
			}
		}
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<>><<<<<<<<<<<<<<<<<<<<");
	}
/**
 * Methode pour choisir une operation fondamentale.
 */
	public int choisirOperation(Partie partie) {
		//this.afficherCartes();
		System.out.println(this.afficheInfo());
		System.out.println("Quel opération voulez-vous effectuer?");
		System.out.println("1   Defausser les cartes en main");
		System.out.println("2   Completer votre main jusqu'à 7 cartes");
		System.out.println("3   Utiliser une carte Action et Sacrifier un Croyant ou Guide Spirituel");
		ViewJouer.LabelJoueur.setText("Quel opération voulez-vous effectuer?");
		CardLayout cardLayout= (CardLayout) ViewJouer.ViewChoix.getLayout();
		cardLayout.show(ViewJouer.ViewChoix,"ViewChoisirOperation");




		int result = bol.lireInt();

		//Il est en train de défausser les carte, on active l'action listener de CButton

		/*if (reader.hasNextInt()) {
			result = reader.nextInt();
		}*/
		System.out.println(result);
		return result;
	}


	/**
	 * Choisir une list de carte pour defausser
	 */
	public LinkedList<CarteAction> choisirListCarte() throws IOException {
		JoueurPhysique.ChoixListCarte=true;
		System.out.println(
				"Entrez les numéros de cartes que vous voulez choisir. Veuillez insérer espace entre les numeros");

		ViewJouer.LabelJoueur.setTexte("Veuillez choisir une ou plusieurs cartes et cliquez Valider");
		CardLayout cardLayout= (CardLayout) ViewJouer.ViewChoix.getLayout();
		cardLayout.show(ViewJouer.ViewChoix,"ViewValiderAnnuler");

		//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//String numbers = reader.readLine();// Lire la ligne saisi
		String numbers=Partie.bolString.lireString();
		System.out.println(numbers);
		int[] tableNumeroChoisi = Stream.of(numbers.split(" ")).mapToInt(token -> Integer.parseInt(token)).toArray();//Convertir au tableu

		HashSet<Integer> noDuplicate = new HashSet<Integer>();// Create un Set
																// pour annuler
																// la
																// duplication
		for (int i = 0; i < tableNumeroChoisi.length; i++) {// Add tous les
															// éléments dans le
															// Hashset
			if (tableNumeroChoisi[i] < this.getCarte().size()) {
				noDuplicate.add(tableNumeroChoisi[i]);
			}
		}
		LinkedList<CarteAction> cartes = new LinkedList<CarteAction>();

		Iterator<Integer> it = noDuplicate.iterator();
		while (it.hasNext()) {// Parcourir dans le hashset. Add les cartes
			int numeroChoisi = it.next();
			cartes.add(this.getCarte().get(numeroChoisi));
		}
		return cartes;

	}
/**
 * Methode pour choisir une Joueur quand on utilise une carte.
 */
	public LinkedList<Joueur> choisirListJoueur(Partie partie) throws IOException {
		JoueurPhysique.ChoixListJoueur=true;
		System.out.println(
				"Entrez les numéros de cartes que vous voulez choisir. Veuillez insérer espace entre les numeros");
		ViewJouer.LabelJoueur.setTexte("Veuillez choisir une ou plusieurs joueurs et cliquez Valider");
		CardLayout cardLayout= (CardLayout) ViewJouer.ViewChoix.getLayout();
		cardLayout.show(ViewJouer.ViewChoix,"ViewValiderAnnuler");

		//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//String numbers = reader.readLine();// Lire la ligne saisi
		String numbers=Partie.bolString.lireString();
		for (int i = 0; i < partie.getJoueur().size(); i++) {
			System.out.println(partie.getJoueur().get(i).toString());
		}


		int[] tableNumeroChoisi = Stream.of(numbers.split(" "))// Convertir les
																// chiffres
																// saisi en
																// Array
																// toArray:make
																// the stream to
																// int[]
				.mapToInt(token -> Integer.parseInt(token)).toArray();

		HashSet<Integer> noDuplicate = new HashSet<Integer>();// Create un Set
																// pour annuler
																// la
																// duplication
		for (int i = 0; i < tableNumeroChoisi.length; i++) {// Add tous les
															// éléments dans le
															// Hashset
			if (tableNumeroChoisi[i] < this.getCarte().size()) {
				noDuplicate.add(tableNumeroChoisi[i]);
			}
		}

		LinkedList<Joueur> joueurs = new LinkedList<Joueur>();

		Iterator<Integer> it = noDuplicate.iterator();
		while (it.hasNext()) {// Parcourir dans le hashset. Add les cartes
			int numeroChoisi = it.next();
			joueurs.add(partie.getJoueur().get(numeroChoisi));
		}
		return joueurs;

	}
/**
 * Methode pour choisir une carte.
 */
	public CarteAction choisirUneCarte() {
		for (Iterator it = this.getCarte().iterator(); it.hasNext();) {
			System.out.println(it.next());
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		}
		//Scanner reader = new Scanner(System.in);
		//int numero = reader.nextInt();
		int numero=bol.lireInt();
		// reader.close();
		return this.getCarte().get(numero);
	}
/**
 * Methode pour choisir une carte croyant.
 */
	public CarteCroyant choisirUneCroyantAssocie() {
		if(this.getAssociationGC().size()==0){
			System.out.println("Vous n'avez pas de Croyant sur champ");
			ViewJouer.LabelJoueur.setTexte("Vous n'avez pas de Croyant sur champ");
			return null;
		}
		System.out.println("Veuillez choisir un croyant dans votre association");
		ViewJouer.LabelJoueur.setTexte("Veuillez choisir un croyant dans votre association");
		ArrayList<CarteCroyant> croyant = new ArrayList<CarteCroyant>();
		Iterator<ArrayList<CarteCroyant>> it = this.getAssociationGC().values().iterator();
		while (it.hasNext()) {
			Iterator<CarteCroyant> itC = it.next().iterator();
			while (itC.hasNext()) {
				croyant.add(itC.next());
			}
		}
		for (int i = 0; i < croyant.size(); i++) {
			System.out.println(croyant.get(i).toString());
		}
		CarteAction result=this.ChoisirUneCard(croyant);
		return (CarteCroyant) result;
	}
/*
 * Methode pour choisir une carte guidespirituel qui associe avec les croyants.
 * @see Joueur.Joueur#choisirUneGuideSpirituelAssocie()
 */
	public GuideSpirituel choisirUneGuideSpirituelAssocie() {
		if(this.getAssociationGC().size()==0){
			System.out.println("Vous n'avez pas de GuideSpirituel sur champ");
			ViewJouer.LabelJoueur.setTexte("Vous n'avez pas de GuideSpirituel sur champ");
			return null;
		}
		System.out.println("Veuillez choisir un guide spirituel dans votre association");
		ViewJouer.LabelJoueur.setTexte("Veuillez choisir un guide spirituel dans votre association");

		ArrayList<GuideSpirituel> guide = new ArrayList<GuideSpirituel>();
		Iterator<GuideSpirituel> it = this.getAssociationGC().keySet().iterator();
		while (it.hasNext()) {
			guide.add(it.next());
		}
		for (int i = 0; i < guide.size(); i++) {
			System.out.println(guide.get(i).toString());
		}
		CarteAction result=this.ChoisirUneCard(guide);
		return (GuideSpirituel) result;
	}

	/**
	 * Choisir des croyants à ratacher pour guide Spirituel
	 */
	public CarteCroyant choisirCroyantRattacher(ArrayList<CarteCroyant> cartePossible) {

		System.out.println("Veuillez choisir les cartes croyant à rattacher pour votre guide spirituel");
		ViewJouer.LabelJoueur.setTexte("Veuillez choisir les cartes croyant à rattacher pour votre guide spirituel");
		for (int i=0;i<cartePossible.size();i++) {
			CarteCroyant itc = cartePossible.get(i);
			System.out.println(i+" "+itc.toString());
		}
		//Scanner reader = new Scanner(System.in);
		//int numero = reader.nextInt();
		int numero=bol.lireInt();
		CarteCroyant croyant = cartePossible.get(numero);

		return croyant;

	}
/**
 * Methode pour choisir si on veut utiliser la capacite de devinite.
 */
	public boolean choisirSiUtiliserCapaciteSpeciale() {
		if (this.getCarteDevinite().getNom() == "Romtec") {
			return false;
		}
		if(this.isCapaciteUtilise()){
			return false;
		}
		System.out.println("Voulez-vous utiliser capacité spéciale? (0 pour Non, 1 pour Oui)");
		ViewJouer.LabelJoueur.setTexte("Voulez-vous utiliser capacité spéciale?");
		CardLayout cardLayout= (CardLayout) ViewJouer.ViewChoix.getLayout();
		cardLayout.show(ViewJouer.ViewChoix, "ViewOuiNon");
		//Scanner reader = new Scanner(System.in);
		//int choise = reader.nextInt();
		int choise=bol.lireInt();
		boolean result = false;
		// reader.close();
		if (choise == 0) {
			result = false;
		} else if (choise == 1) {
			result = true;
			this.setCapaciteUtilise(true);
		}
		return result;
	}
/**
 * Methode pour choisir un Joueur .
 */
	public Joueur ChoisirUnJoueur(Partie partie) {
		System.out.println("Veuillez choisir un joueur");
		ViewJouer.LabelJoueur.setTexte("Veuillez choisir un joueur");
		for (int i = 0; i < partie.getJoueur().size(); i++) {
			if (partie.getJoueur().get(i) != this) {
				System.out.println(i + " " + partie.getJoueur().get(i).toString());
			}
		}

		//Scanner reader = new Scanner(System.in);
		//int numero = reader.nextInt();
		int numero=bol.lireInt();
		return partie.getJoueur().get(numero);// TODO Auto-generated method stub

	}
	/**
	 * Methode pour choisir un Joueur dans GUI.
	 */
	public Joueur ChoisirUnJoueur(List c) {
		System.out.println("Veuillez choisir un joueur");
		ViewJouer.LabelJoueur.setTexte("Veuillez choisir un joueur");


		for (int i = 0; i < c.size(); i++) {
			if (c.get(i) != this) {
				System.out.println(i+" "+c.get(i).toString());
				Joueur joueur= (Joueur) c.get(i);
				joueur.getButton().setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.green), null));
			}
		}
		//Scanner reader = new Scanner(System.in);
		//int numero = reader.nextInt();
		int numero=bol.lireInt();
		Joueur joueurChoisi=Partie.PartieEnCours.getJoueur().get(numero);
		while (!c.contains(joueurChoisi)) {
			ViewJouer.LabelJoueur.setTexte("Veuillez rechoisir un joueur");
			numero=bol.lireInt();
			joueurChoisi=Partie.PartieEnCours.getJoueur().get(numero);
		}
		return joueurChoisi;// TODO Auto-generated method stub
	}
/**
 * Methode pour choisir une carte dans GUI.
 */
	public CarteAction ChoisirUneCard(List c) {
		if(c.size()==0){
			System.out.println("Pas de carte disponible ");
			ViewJouer.LabelJoueur.setTexte("Pas de carte disponible ");
			return null;
		}
		ChoixParmiList=c;//Variable global pour que l'action listener de CButton se réagir

		System.out.println("Veuillez choisir une carte parmis les cartes suivante:");
		ViewJouer.LabelJoueur.setTexte("Veuillez choisir une carte parmis les cartes entouré");
		for (int i = 0; i < c.size(); i++) {
				System.out.println(i + " " + c.get(i).toString());
			CarteAction carteAction= (CarteAction) c.get(i);
			carteAction.getButton().setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.green), null));

		}
		//Scanner reader = new Scanner(System.in);
		//int numero = reader.nextInt();
		int numero=bol.lireInt();
		ChoixParmiList=null;
		return (CarteAction) c.get(numero);// TODO Auto-generated method stub
	}
/**
 * Methode pour demander si le joueur veut sacrifier une carte.
 */
	public boolean decideSiSacrifier() {
		boolean result = false;
		if (this.getAssociationGC().size() == 0) {
			return false;
		}
		System.out.println("Voulez-vous sacrifier un croyant ou guide spirituel? ( 0 pour non, 1 pour oui)");
		ViewJouer.LabelJoueur.setTexte("Voulez-vous sacrifier un croyant ou guide spirituel? ");
		CardLayout cardLayout= (CardLayout) ViewJouer.ViewChoix.getLayout();
		cardLayout.show(ViewJouer.ViewChoix,"ViewOuiNon");
		//Scanner reader = new Scanner(System.in);
		//int choise = reader.nextInt();
		int choise=bol.lireInt();
		/*while (choise != 0 && choise != 1) {
			System.out.println("Veuillez rechoisir");
			choise = reader.nextInt();
		}*/
		if (choise == 0) {
			result = false;
		} else if (choise == 1) {
			result = true;
		}
		return result;
	}
/**
 * Methode pour demander si l'on veut utiliser une carte d'interruption.
 */
	public void decideInterruptionRomtec(GuideSpirituel guide) {
		if (this.getCarteDevinite().getNom() == "Romtec" || this.getCarteDevinite().getNom() == "Drinded" ) {
			if (this.isCapaciteUtilise()==true){
				return;
			}
			System.out.println("Voulez vous interrupter de guide spirituel par votre capacité spéciale? (0 pour non, 1 pour oui)");
			ViewJouer.LabelJoueur.setTexte("Voulez vous interrupter de guide spirituel par votre capacité spéciale?");
			CardLayout cardLayout= (CardLayout) ViewJouer.ViewChoix.getLayout();
			cardLayout.show(ViewJouer.ViewChoix,"ViewOuiNon");
			//Scanner reader = new Scanner(System.in);
			//int choise = reader.nextInt();
			int choise=bol.lireInt();
			/*while (choise != 0 && choise != 1) {
				System.out.println("Veuillez rechoisir");
				choise = reader.nextInt();
			}*/
			if (choise == 0) {

			} else if (choise == 1) {
				this.getCarteDevinite().romtecCapacite(guide);
				this.setCapaciteUtilise(true);
			}
		}
	}
/**
 * Methode pour choisir si l'on veut utiliser une carte d'interruption.
 */
	public void decideSiIterruption(Partie partie, CarteAction ca) throws IOException {
		if (this.peutIterruption(ca).size() == 0) {
			return;
		} else {
			System.out.println("Voulez-vous utiliser carte d'interruption?");
			System.out.println("0 pour non, 1 pour oui)");

			ViewJouer.LabelJoueur.setTexte("Voulez-vous utiliser carte d'interruption?");
			CardLayout cardLayout= (CardLayout) ViewJouer.ViewChoix.getLayout();
			cardLayout.show(ViewJouer.ViewChoix,"ViewOuiNon");
			/*Scanner reader = new Scanner(System.in);
			int choise = reader.nextInt();
			while (choise != 0 && choise != 1) {
				System.out.println("Veuillez rechoisir");
				choise = reader.nextInt();
			}*/
			int choise=bol.lireInt();
			if (choise == 0) {

			} else if (choise == 1) {
				this.interrupter((DeusEX) this.ChoisirUneCard(this.peutIterruption(ca)), ca, partie);
			}
		}
	}

	/**
	 * Méthode qui décide si utiliser san origine. Si oui, il choisira la carte quil veut utiliser.
	 * @param partie
	 * @throws IOException
	 */
	public void decideSiUtiliserSansOrigine(Partie partie) throws IOException {
		if (this.avoirCarteSansOrigine().size() > 0) {
			System.out.println("Voulez-vous utiliser votre carte action sans origine?");
			System.out.println("0 pour non, 1 pour oui");

			ViewJouer.LabelJoueur.setTexte("Voulez-vous utiliser votre carte action sans origine?");
			CardLayout cardLayout= (CardLayout) ViewJouer.ViewChoix.getLayout();
			cardLayout.show(ViewJouer.ViewChoix,"ViewOuiNon");
			/*Scanner reader = new Scanner(System.in);
			int choise = reader.nextInt();
			while (choise != 0 && choise != 1) {
				System.out.println("Veuillez rechoisir");
				choise = reader.nextInt();
			}*/
			int choise=bol.lireInt();
			if (choise == 1) {
				ArrayList<CarteAction> carteDispo=this.avoirCarteSansOrigine();
				this.utiliserCarteAction(this.ChoisirUneCard(carteDispo), partie);
			}
		}
	}
/**
 * Methode pour choisir une carte guidespirituel d'autre joueur quand on utilise certains cartes particulieres.
 */
	public GuideSpirituel choisirGuideSpirituelDautreJoueur(Joueur jC) {
		if(jC.getAssociationGC().size()==0){
				System.out.println(jC+" n'a pas de guide spirituel sur son champ");
			ViewJouer.LabelJoueur.setTexte(jC.getCarteDevinite().getNom()+" n'a pas de guide spirituel sur son champ");
			return null;
		}

		System.out.println("Veuillez choisir un guide spirituel ");
		ViewJouer.LabelJoueur.setTexte("Veuillez choisir un guide spirituel ");
		ArrayList<GuideSpirituel> listGuide = jC.champGuide();
		for (int i = 0; i < listGuide.size(); i++) {
			System.out.println(i + " " + listGuide.get(i).toString());
			listGuide.get(i).getButton().setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.green), null));
		}
		//Scanner reader = new Scanner(System.in);
		//int choise = reader.nextInt();
		int choise=bol.lireInt();
		return listGuide.get(choise);
	}


}
