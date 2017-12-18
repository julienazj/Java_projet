package Carte;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Bol.action;
import Evenements.ChampRepaintEvent;
import GUI.CButton;
import GUI.ViewJouer;
import Joueur.Joueur;
import Joueur.JoueurPhysique;
import Partie.CroyantPublique;
import Partie.Partie;

import javax.swing.*;
/**
 * La classe GuideSpirituel represente les cartes guidespirituels dans le jeu
 * Nous avons realise la fonction de sacrifice , rattracher les croyants des cartes guidespirituels
 * @author Siyuan
 * @author Zijie
 *
 */
public class GuideSpirituel extends CarteAction {
	protected ArrayList<String> dogmes = new ArrayList<String>();
	protected String nom;
	protected int NombreCroyantRatacche;
	protected String origine;
	protected String description;
	protected ArrayList<CarteCroyant> croyantRattache = new ArrayList<CarteCroyant>();
	private boolean stace;
	private int id;

	public final static String Dogmes[] = { "Nature", "Humain", "Mystique", "Symboles", "Chaos" };
	public final static String Origines[] = { "Jour", "Nuit", "Neant", "Aube", "Crepuscule" };

	// Carte GuideSpirituel:
	// nom,dogme1,dogme2,origine,NombreCroyantRatacche,description

	public static final Object valeur[][] = { { "Martyr", 1, 2, 1, 2, "Equivalent à la pose d'unecarte Apocalypse." },
			{ "Martyr", 2, 4, 2, 2, "Equivalent à la pose d'unecarte Apocalypse." },
			{ "Martyr", 1, 5, 3, 2, "Equivalent à la pose d'unecarte Apocalypse." },
			// 2
			{ "Clerc", 2, 5, 1, 2,
					"Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyantsrattach��es. L'Origine des points d'Action est au choix du joueur." },
			{ "Clerc", 1, 4, 2, 2,
					"Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyantsrattach��es. L'Origine des points d'Action est au choix du joueur." },
			{ "Clerc", 1, 3, 3, 2,
					"Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyantsrattach��es. L'Origine des points d'Action est au choix du joueur." },
			{ "Clerc", 1, 5, 1, 2,
					"Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyantsrattach��es. L'Origine des points d'Action est au choix du joueur." },
			{ "Clerc", 3, 4, 2, 2,
					"Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyantsrattach��es. L'Origine des points d'Action est au choix du joueur." },
			{ "Clerc", 4, 5, 3, 2,
					"Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyantsrattach��es. L'Origine des points d'Action est au choix du joueur." },
			{ "Clerc", 3, 5, 1, 2,
					"Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyantsrattach��es. L'Origine des points d'Action est au choix du joueur." },
			{ "Clerc", 1, 2, 2, 2,
					"Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyantsrattach��es. L'Origine des points d'Action est au choix du joueur." },
			// 10
			{ "Shaman", 1, 4, 2, 3,
					"Sacrifie tous les Croyants d'Origine N��ant d'une Divinit�� ayant le Dogme Humain. Les capacit��s sp��cials sont jou��es normalement." },
			// 11
			{ "Anarcbiste", 2, 4, 3, 3,
					"Sacrifie un Guide Spirituel, si lui ou sa Divinit�� ne croit pas au Dogme Chaos. Les capacit��s sp��ciales sont jou��es normalement." },
			// 12
			{ "Paladin", 2, 3, 1, 3,
					"Tous les Croyants, d'OrigineNuit ou N��ant et ayant leDogme Nature, actuellementsur la table sont d��fauss��s.Les capacit��s sp��ciales ne sontpas jou��es." },
			// 13
			{ "Ascete", 2, 4, 2, 1,
					"Sacrifie 2 cartes Croyantsd'une Divinit�� ayant leDogme Humain ou Symboles.Les capacit��s sp��ciales sontjou��es normalement." },
			// 14
			{ "Devin", 1, 3, 3, 1,
					"Oblige une Divinité ayant leDogme Nature ou Mystique�� sacrifier l'un de ses GuidesSpirituels." },
			// 15
			{ "Exorciste", 3, 5, 1, 1,
					"Une Divinité d'Origine Nuit ou ayant les Dogmes Mystique et Chaos reprend dans sa main l'un de ses Guides Spirituels. Les Croyants qui y ��taient attach��s sont d��fauss��s." },
			// 16
			{ "Sorcier", 3, 4, 2, 3,
					"Echangez l'un de vos GuidesSpirituels avec un d'une autreDivinit��. Vous choisissez lesdeux guides Spirituels enquestion. Les Croyantsrestent attach��s aux m��mescartes." },
			// 17
			{ "Tyran", 4, 5, 3, 3,
					"Défausse tous les Croyants ayant le Dogme Mystique actuellement au centre de la table." },
			// 18
			{ "Messie", 2, 3, 1, 3,
					"Le joueur pose le d�� deCosmogonie sur la face qu'ild��sire et commence unnouveau tour de jeu." },
			// 19

	};
/**
 * Pour donner les attributs aux cartes guidespirituel
 * @param input les attributs nous avons defini
 */
	
	public void setValeur(Object input[]) {
		this.nom = (String) input[0];// Set nom
		for (int i = 1; i < 3; i++) {// Set dogmes
			this.dogmes.add(CarteAction.Dogmes[(int) input[i] - 1]);
		}
		this.NombreCroyantRatacche = (Integer) input[4];// Set nombre
														// NombreCroyantRatacche
		this.origine = CarteAction.Origines[(int) input[3] - 1];// Set origine
		this.description = (String) input[5];// set description
		this.setStace(false);
	}

	public GuideSpirituel(int id) {
		this.setValeur(valeur[id]);
		this.setId(id);
	}
/**
 * Pour sacrifier la carte martyr/
 * @param j Joueur qui utilise cette carte
 * @param partie 
 * @throws IOException
 */
	public void martyrSacrifier(Joueur j, Partie partie) throws IOException {
		// same to function of the Apocalypse
		System.out.println(j + "a sacrifie GuideSprituel Martyr");
		ViewJouer.LabelSystem.setTexte(j + "a sacrifie GuideSprituel Martyr");
		Apocalypse apo = new Apocalypse(0);
		j.getCarte().add(apo);
		j.utiliserCarteAction(apo, partie);
	}

	//---------------------------revoir--------------------------
	/**
	 * Pour sacrifier la carte clerc.
	 * @param j Joueur qui utilise cette carte.
	 */
	public void clercSacrifier(Joueur j) {
		System.out.println(j + "a sacrifie GuideSprituel Clerc");
		ViewJouer.LabelSystem.setTexte(j + "a sacrifie GuideSprituel Clerc");
		if (j.getPeutAddPteAction() == true) {
			if (j instanceof JoueurPhysique) {
				System.out.println("Vous pouvez choisir une origine pour gagner des point : Tapez jour, nuit ou neant");
				Scanner s = new Scanner(System.in);
				while (true) {
					String line = s.nextLine();
					if (line.equals("jour")) {
						j.setPointActionJour(j.getPointActionJour() + 2);
						System.out.println(j.toString() + "a obtenu 2 points d'action jour par sacrifice de Clerc");
						ViewJouer.LabelSystem.setTexte(j.toString() + "a obtenu 2 points d'action jour par sacrifice de Clerc");
						break;
					} else if (line.equals("nuit")) {
						j.setPointActionJour(j.getPointActionNuit() + 2);
						System.out.println(j.toString() + "a obtenu 2 points d'action nuit par sacrifice de Clerc");
						ViewJouer.LabelSystem.setTexte(j.toString() + "a obtenu 2 points d'action nuit par sacrifice de Clerc");
						break;
					} else if (line.equals("neant")) {
						j.setPointActionJour(j.getPointActionNeant() + 2);
						System.out.println(j.toString() + "a obtenu 2 points d'action neant par sacrifice de Clerc");
						ViewJouer.LabelSystem.setTexte(j.toString() + "a obtenu 2 points d'action neant par sacrifice de Clerc");
						break;
					} else {
						System.out.println("Vous devez choisir l'origine entre 'jour''nuit'et'neant'.");
						break;
					}
				}
			}
			else{
				int random = (int)(Math.random() * (2)) ;
				if(random==0){
					j.setPointActionJour(j.getPointActionJour() + 2);
					System.out.println(j.toString() + "a obtenu 2 points d'action jour par sacrifice de Clerc");
				}else if(random==1){
					j.setPointActionJour(j.getPointActionNuit() + 2);
					System.out.println(j.toString() + "a obtenu 2 points d'action nuit par sacrifice de Clerc");
				}else{
					j.setPointActionJour(j.getPointActionNeant() + 2);
					System.out.println(j.toString() + "a obtenu 2 points d'action neant par sacrifice de Clerc");
				}
			}
		} else {
			System.out.println("Le point d'action n'est pas ajoute");
		}

	}
/**
 * Pour sacrifier la carte shaman
 * @param j Joueur qui utilise cette carte.
 * @param partie
 * @throws IOException
 */
	public void shamanSacrifier(Joueur j, Partie partie) throws IOException {
		System.out.println(j + "a sacrifie GuideSprituel Shaman");
		ViewJouer.LabelSystem.setTexte(j + "a sacrifie GuideSprituel Shaman");
		Joueur jC = j.ChoisirUnJoueur(partie);
		if (jC.getCarteDevinite().getDogmes().contains("Humain")) {
			Iterator<CarteCroyant> it = jC.champCroyant().iterator();
			while (it.hasNext()) {
				CarteCroyant croyant = it.next();
				if (croyant.getDogmes().contains("Neant")) {
					System.out.println("La carte Croyant" + croyant.toString() + "a ete scrifie.");
					ViewJouer.LabelSystem.setTexte("La carte Croyant" + croyant.getNom() + "a ete scrifie.");
					j.sacrifierCroyant(croyant, partie);
				}
			}
		} else {
			System.out.println("L'effect de Shaman n'est pas realise car Devinite choisi ne contient pas dogme Humain");
			ViewJouer.LabelSystem.setTexte("L'effect de Shaman n'est pas realise car Devinite choisi ne contient pas dogme Humain");
		}
	}
/**
 * Pour sacrifier la carte anarcbiste.
 * @param j1 Joueur qui utilise cette carte
 * @param partie
 * @throws IOException
 */
	public void anarcbisteSacrifier(Joueur j1, Partie partie) throws IOException {
		Joueur j2 = j1.ChoisirUnJoueur(partie);
		System.out.println(j1 + "a sacrifie GuideSprituel Anarcbiste a" + j2);
		ViewJouer.LabelSystem.setTexte(j1 + "a sacrifie GuideSprituel Anarcbiste a" + j2);
		boolean containM = j2.getCarteDevinite().getDogmes().contains("Chaos");
		GuideSpirituel g = j1.choisirGuideSpirituelDautreJoueur(j2);
		if (g == null) {
			return;
		}
		boolean containG = g.getDogmes().contains("Chaos");
		if (containM || containG) {
			System.out.println("vous ne pouvez pas sacrifier catte carte.");
			ViewJouer.LabelSystem.setTexte("vous ne pouvez pas sacrifier catte carte.");
			return;
		} else {
			System.out.println(j1.toString() + "oblige" + j2.toString() + "a sacrifie sa carte GuideSpirituel" + g.toString());
			ViewJouer.LabelSystem.setTexte(j1.toString() + "oblige" + j2.toString() + "a sacrifie sa carte GuideSpirituel" + g.toString());
			j2.sacrifierGuideSpirituel(g, partie);
		}
	}
/**
 * Pour sacrifier la carte paladin
 * @param j Joueur qui utilise cette carte.
 * @param partie 
 */
	public void paladinSacrifier(Joueur j, Partie partie) {
		System.out.println(j + "a sacrifie GuideSprituel Paladin");
		ViewJouer.LabelSystem.setTexte(j + "a sacrifie GuideSprituel Paladin");
		CroyantPublique cp = partie.getCentreCommun();
		int size = cp.getListCroyant().size();
		CarteCroyant[] cpl = (CarteCroyant[]) cp.getListCroyant().toArray(new CarteCroyant[size]);

		for (int i = 0; i < size; i++) {
			if ((cpl[i].getOrigine().equals("Nuit") & cpl[i].getDogmes().contains("Nature"))
					|| (cpl[i].getOrigine().equals("Neant") & cpl[i].getDogmes().contains("Nature"))) {
				cp.getListCroyant().remove(cpl[i]);
				partie.getPiocheCarteDefausse().getContenue().add(cpl[i]);
				System.out.println("La carte Croyant" + cpl[i].toString() + "a ete defausse");
				ViewJouer.LabelSystem.setTexte("La carte Croyant" + cpl[i].toString() + "a ete defausse");
			} else {
				continue;
			}
		}

	}
/**
 * Pour sacrifier la carte ascete
 * @param j Joueur qui utilise cette carte.
 * @param partie
 * @throws IOException
 */
	public void asceteSacrifier(Joueur j, Partie partie) throws IOException {
		Joueur j2 = j.ChoisirUnJoueur(partie);
		System.out.println(j + "a sacrifie GuideSprituel Ascete a" + j2);
		ViewJouer.LabelSystem.setTexte(j + "a sacrifie GuideSprituel Ascete a" + j2);
		CarteCroyant c1 = j.choisirUneCroyantAssocie();
		CarteCroyant c2 = j.choisirUneCroyantAssocie();
		boolean containM = c1.getDogmes().contains("Humain");
		boolean containN = c2.getDogmes().contains("Humain");
		boolean containP = c1.getDogmes().contains("Symbole");
		boolean containQ = c2.getDogmes().contains("Symbole");
		if ((containM & containN) || (containM & containQ) || (containN & containP) || (containP & containQ)) {
			System.out.println(j.toString() + "oblige" + j2.toString() + "a sacrifie sa carte Croyant" + c1.toString());
			ViewJouer.LabelSystem.setTexte(j.toString() + "oblige" + j2.toString() + "a sacrifie sa carte Croyant" + c1.toString());
			j2.sacrifierCroyant(c1, partie);
			System.out.println(j.toString() + "oblige" + j2.toString() + "a sacrifie sa carte Croyant" + c2.toString());
			ViewJouer.LabelSystem.setTexte(j.toString() + "oblige" + j2.toString() + "a sacrifie sa carte Croyant" + c2.toString());
			j2.sacrifierCroyant(c2, partie);
			;
		} else {
			System.out.println("Les Croyants choisis doivent avoir les Dogmes 'Humain' et 'Symbole'");
			ViewJouer.LabelSystem.setTexte("Les Croyants choisis doivent avoir les Dogmes 'Humain' et 'Symbole'");
		}

	}
/**
 * Pour sacrifier la carte devin
 * @param j Joueur qui utilise cette carte.
 * @param partie
 * @throws IOException
 */
	public void devinSacrifier(Joueur j, Partie partie) throws IOException {
		Joueur j2 = j.ChoisirUnJoueur(partie);
		System.out.println(j + "a sacrifie GuideSprituel Devin a" + j2);
		ViewJouer.LabelSystem.setTexte(j + "a sacrifie GuideSprituel Devin a" + j2);
		if(j2.champGuide().size()==0){
			System.out.println(j2 + "N'a pas de guide sur son champ !");
			ViewJouer.LabelSystem.setTexte(j2 + "N'a pas de guide sur son champ !" );
			return;
		}
		GuideSpirituel g = j2.choisirUneGuideSpirituelAssocie();
		boolean containM = j2.getCarteDevinite().getDogmes().contains("Nature");
		boolean containN = j2.getCarteDevinite().getDogmes().contains("Mystique");
		if (containM || containN) {
			System.out.println(j.toString() + "oblige" + j2.toString() + "a sacrifie sa carte GuideSpirituel" + g.toString());
			ViewJouer.LabelSystem.setTexte(j.toString() + "oblige" + j2.toString() + "a sacrifie sa carte GuideSpirituel" + g.getName());
			j2.sacrifierGuideSpirituel(g, partie);
		} else {
			System.out.println("Vous ne pouvez pas demander un joueur qui ne croit pas Nature ou Mystique");
			ViewJouer.LabelSystem.setTexte("Vous ne pouvez pas demander un joueur qui ne croit pas Nature ou Mystique");
		}
	}
/**
 * Pour sacrifier la carte exorcisete
 * @param j Joueur qui utilise cette carte
 * @param partie
 */
	public void exorcisteSacrifier(Joueur j, Partie partie) {
		Joueur j2 = j.ChoisirUnJoueur(partie);
		System.out.println(j + "a sacrifie GuideSprituel Exorciste a" + j2);
		ViewJouer.LabelSystem.setTexte(j + "a sacrifie GuideSprituel Exorciste a" + j2);
		GuideSpirituel g = j.choisirGuideSpirituelDautreJoueur(j2);
		if (g == null) {
			return;
		}
		ArrayList<CarteCroyant> cc = g.getCroyantRattache();
		Iterator<CarteCroyant> it = cc.iterator();
		boolean containM = j2.getCarteDevinite().getOrigine().equals("Nuit");
		boolean containN = j2.getCarteDevinite().getDogmes().contains("Mystique");
		boolean containP = j2.getCarteDevinite().getDogmes().contains("Chaos");
		if (containM || (containN & containP)) {
			while (it.hasNext()) {
				CarteCroyant c = it.next();
				System.out.println("La carte Croyant" + c.toString() + "a ete defausse");
				ViewJouer.LabelSystem.setTexte("La carte Croyant" + c.toString() + "a ete defausse");
				g.getCroyantRattache().remove(c);
				partie.getPiocheCarteDefausse().getContenue().add(c);
			}
			j2.getCarte().add(g);
			System.out.println(j2.toString() + "reprend la carte GuideSpirituel" + g.toString());
			ViewJouer.LabelSystem.setTexte(j2.toString() + "reprend la carte GuideSpirituel" + g.toString());
		} else {
			System.out.println("Vous ne pouvez pas choisir ce joueur");
			ViewJouer.LabelSystem.setTexte("Vous ne pouvez pas choisir ce joueur");
		}

	}
/**
 * Pour sacrifier la carte sorcier
 * @param j Joueur qui utilise cette carte
 * @param partie
 */
	public void sorcierSacrifier(Joueur j, Partie partie) {
		Joueur j2 = j.ChoisirUnJoueur(partie);
		System.out.println(j + "a sacrifie GuideSprituel Sorcier a" + j2);
		ViewJouer.LabelSystem.setTexte(j + "a sacrifie GuideSprituel Sorcier a" + j2);
		GuideSpirituel g1 = j.choisirUneGuideSpirituelAssocie();
		GuideSpirituel g2 = j.choisirGuideSpirituelDautreJoueur(j2);
		if (g1 == null || g2 == null) {
			return;
		}
		j.getCarte().add(g2);
		j.getCarte().remove(g1);
		System.out.println(j.toString() + "a obtenu la carte GuideSpirituel" + g2.toString()
				+ "et il a envoye la carte GuideSpirituel" + g1.toString() + "a" + j2.toString());
		ViewJouer.LabelSystem.setTexte(j.toString() + "a obtenu la carte GuideSpirituel" + g2.toString()
				+ "et il a envoye la carte GuideSpirituel" + g1.toString() + "a" + j2.toString());
		j2.getCarte().add(g1);
		j2.getCarte().remove(g2);
		System.out.println(j2.toString() + "a obtenu la carte GuideSpirituel" + g1.toString()
				+ "et il a envoye la carte GuideSpirituel" + g2.toString() + "a" + j.toString());
		ViewJouer.LabelSystem.setTexte(j2.toString() + "a obtenu la carte GuideSpirituel" + g1.toString()
				+ "et il a envoye la carte GuideSpirituel" + g2.toString() + "a" + j.toString());
	}
/**
 * Pour sacrifier la carte tyran.
 * @param partie
 */
	public void tyranSacrifier(Partie partie) {
		System.out.println("Joueur a sacrifie GuideSprituel Tyran");
		ViewJouer.LabelSystem.setTexte("Joueur a sacrifie GuideSprituel Tyran");

		CroyantPublique cp = partie.getCentreCommun();
		int size = cp.getListCroyant().size();
		CarteCroyant[] cpl = (CarteCroyant[]) cp.getListCroyant().toArray(new CarteCroyant[size]);

		for (int i = 0; i < size; i++) {
			if (cpl[i].getDogmes().contains("Mystique")) {
				cp.getListCroyant().remove(cpl[i]);
				partie.getPiocheCarteDefausse().getContenue().add(cpl[i]);
				System.out.println("La carte Croyant" + cpl[i].toString() + "a ete defausse");
				ViewJouer.LabelSystem.setTexte("La carte Croyant" + cpl[i].toString() + "a ete defausse");
			} else {
				continue;
			}
		}

	}
/**
 * Pour sacrifier la carte messie
 * @param j Joueur qui utilise cette carte.
 * @param partie
 */
	public void messieSacrifier(Joueur j, Partie partie) {
		System.out.println(j + "a sacrifie GuideSprituel Messie");
		ViewJouer.LabelSystem.setTexte(j + "a sacrifie GuideSprituel Messie");
		System.out.println("Choisir une face de de");
		ViewJouer.LabelSystem.setTexte("Choisir une face de de");
		Scanner s = new Scanner(System.in);
		while (true) {
			System.out.println(j + "a relance le de par l'effet de Messie");
			ViewJouer.LabelSystem.setTexte(j + "a relance le de par l'effet de Messie");
			String line = s.nextLine();
			switch (line) {
			case "jour":
				partie.ajouterPointAction(line);
				break;
			case "nuit":
				partie.ajouterPointAction(line);
				break;
			case "neant":
				partie.ajouterPointAction(line);
				break;
			default:
				System.out.println("Vous devez choisir entre'jour''nuit'et'neant'");
				ViewJouer.LabelSystem.setTexte("Vous devez choisir entre'jour''nuit'et'neant'");
				break;
			}
		}
	}
/**
 * Pour rattacher les croyant dans le centre de la table.
 * C'est la fonction fondamentale des cartes guidespirituels.
 * @param partie
 * @param j Joueur qui utilise la carte guidespirituel.
 */
	public void rattacherCroyants(Partie partie, Joueur j) {
		// Initialiser
		ArrayList<CarteCroyant> centre = partie.getCentreCommun().getListCroyant();
		ArrayList<CarteCroyant> cartePossible = this.possibleARattacher(partie, 0);
		//-------------Add Controleur à
		Component[] components = ViewJouer.CentreTable.getComponents();
		for(int i=0;i<components.length;i++){
			CButton cButton= (CButton) components[i];
			CarteAction ca=cButton.getCa();
			for (int k=0;k<cartePossible.size();k++) {
				if ( ca== cartePossible.get(k)) {
					int finalK = k;
					cButton.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GREEN), null));
					cButton.addActionListener(new action() {
						public void run() {
							try {
								Partie.bol.deposerInt(finalK);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		}

		if (this.possibleARattacher(partie, 0).size() == 0) {
			System.out.println(j
					+ " a utilise guide spirituel mais cette carte ne peut attacher aucun carte croyant car il n'y a pas de croyant sur la table OU les croyants sont trop nombreux à rattacher ");
			ViewJouer.LabelSystem.setTexte(j
					+ " a utilise guide spirituel mais cette carte ne peut attacher aucun carte croyant car il n'y a pas de croyant sur la table OU les croyants sont trop nombreux à rattacher ");
			System.out.println(this + " \r\n est défaussé !");
			return;
		}
		// Quand il reste encore de puissance ratacchement, on laisse joueur à
		// rechoisir les croyants
		int difference = 0;
		while (cartePossible.size() > 0) {
			CarteCroyant croyant = j.choisirCroyantRattacher(cartePossible);
			this.getCroyantRattache().add(croyant);
			centre.remove(croyant);
			System.out.println(j.toString() + "a utilise le GuideSpirituel " + this.toString()
					+ " pour rattacher le croyant \r\n" + croyant.toString());
			ViewJouer.LabelSystem.setTexte(j.toString() + "a utilise le GuideSpirituel " + this.getNom()
					+ " pour rattacher le croyant \r\n" + croyant.getNom());
			j.setNombreCroyant(j.getNombreCroyant() + croyant.NombreCroyantRepresente);
			difference = difference + croyant.getNombreCroyantRepresente();
			cartePossible = this.possibleARattacher(partie, difference);
		}
		j.getAssociationGC().put(this, this.getCroyantRattache());
		j.notifyElement(new ChampRepaintEvent());
		partie.getCentreCommun().getListCroyant().removeAll(this.getCroyantRattache());
		ViewJouer.CentreTable.repaintCentre();

	}

	/**
	 * Méthode qui retourne les cartes croyant possible à rattacher pour le
	 * guide spirituel
	 * 
	 * @param partie:
	 *            partie du jeu
	 * @param difference
	 *            : le nombre de croyant déjà consommé
	 * @return
	 */

	public ArrayList<CarteCroyant> possibleARattacher(Partie partie, int difference) {
		ArrayList<CarteCroyant> croyantPossible = new ArrayList<CarteCroyant>();
		ArrayList<CarteCroyant> centre = partie.getCentreCommun().getListCroyant();
		for (Iterator<CarteCroyant> it = centre.iterator(); it.hasNext();) {
			CarteCroyant croyant = it.next();
			ArrayList<String> dogmeC = croyant.getDogmes();
			ArrayList<String> dogmeG = this.getDogmes();
			int nombreC = croyant.getNombreCroyantRepresente();
			int nombreG = this.getNombreCroyantRatacche() - difference;
			for (int i = 0; i < dogmeC.size(); i++) {
				for (int j = 0; j < dogmeG.size(); j++) {
					if (dogmeC.get(i) == dogmeG.get(j)) {
						if (nombreC <= nombreG) {
							croyantPossible.add(croyant);
						}
					}
				}
			}
		}
		return croyantPossible;
	}
/**
 * Methode pour sacrifier une carte guidespirituel en main.
 * @param j Joueur qui sacrifie cette carte.
 * @param partie
 * @throws IOException
 */
	public void sacrifier(Joueur j, Partie partie) throws IOException {
		if (this.getId() >= 0 && this.getId() < 3) {
			this.martyrSacrifier(j, partie);
		} else if (this.getId() >= 3 && this.getId() <= 10) {
			this.clercSacrifier(j);
		} else if (this.getId() == 11) {
			this.shamanSacrifier(j, partie);
		} else if (this.getId() == 12) {
			this.anarcbisteSacrifier(j, partie);
		} else if (this.getId() == 13) {
			this.paladinSacrifier(j, partie);
		} else if (this.getId() == 14) {
			this.asceteSacrifier(j, partie);
		} else if (this.getId() == 15) {
			this.devinSacrifier(j, partie);
		} else if (this.getId() == 16) {
			this.exorcisteSacrifier(j, partie);
		} else if (this.getId() == 17) {
			this.sorcierSacrifier(j, partie);
		} else if (this.getId() == 18) {
			this.tyranSacrifier(partie);
		} else if (this.getId() == 19) {
			this.messieSacrifier(j, partie);
		}
	}
/**
 * Methode pour montrer l'utilisation de la carte guidespirituel.
 */
	public String toString() {
		return new String("-----------------------------------------\r\n" + "Guide Spirituel   " + this.nom + "\r\n"
				+ "Origine:" + this.origine + "\r\n" + "Dogmes : " + this.dogmes.get(0) + " " + this.dogmes.get(1)
				+ "\r\n" + "Sacrifice : " + this.description + "\r\n" + "Capacité d'attachement croyants : "
				+ this.NombreCroyantRatacche + "\r\n" + "-----------------------------------------\r\n");
	}

	public ArrayList<String> getDogmes() {
		return dogmes;
	}

	public void setDogmes(ArrayList<String> dogmes) {
		this.dogmes = dogmes;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNombreCroyantRatacche() {
		return NombreCroyantRatacche;
	}

	public void setNombreCroyantRatacche(int nombreCroyantRatacche) {
		NombreCroyantRatacche = nombreCroyantRatacche;
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<CarteCroyant> getCroyantRattache() {
		return croyantRattache;
	}

	public void setCroyantRattache(ArrayList<CarteCroyant> croyantRattache) {
		this.croyantRattache = croyantRattache;
	}

	public boolean isStace() {
		return stace;
	}

	public void setStace(boolean stace) {
		this.stace = stace;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
