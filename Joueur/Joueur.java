package Joueur;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import Carte.Apocalypse;
import Carte.CarteAction;
import Carte.CarteCroyant;
import Carte.DeusEX;
import Carte.Devinite;
import Carte.GuideSpirituel;
import Evenements.CentreRepaintEvent;
import Evenements.ChampRepaintEvent;
import Evenements.PointRepaintEvent;
import GUI.ChampPanel;
import GUI.PButton;
import GUI.ViewJouer;
import Partie.DeCosmogonie;
import Partie.Partie;
import Pioche.PiocheCarteAction;
/**
 * La classe Joueur represente les joueur dans le jeu.
 * Nous avons realise des fonctions fondamentales des joueur pour jouer le jeu et aussi GUI de joueur
 * @author Zijie
 * @author Siyuan
 *
 */
public abstract class Joueur extends Observable implements Observer, Serializable {
	private int numeroJoueur;
	private Devinite carteDevinite;
	private LinkedList<CarteAction> carte;
	private int nombreCroyant;
	private int pointActionNuit;
	private int pointActionJour;
	private int pointActionNeant;
	private boolean peutSacrifierGuideSpirituel;
	private boolean capaciteUtilise;
	private boolean peutScrifierCroyant;
	private boolean peutAddPteAction;
	private PButton button;
	private ChampPanel champPanel;


	private Map<GuideSpirituel, ArrayList<CarteCroyant>> associationGC = new LinkedHashMap<GuideSpirituel, ArrayList<CarteCroyant>>();

	public Joueur(int numeroJoueur) {
		this.numeroJoueur = numeroJoueur;
		this.setCarte(new LinkedList<CarteAction>());
		this.setNombreCroyant(0);
		this.setPointActionJour(0);
		this.setPointActionNeant(0);
		this.setPointActionNuit(0);
		this.setPeutSacrifierGuideSpirituel(true);
		this.setPeutScrifierCroyant(true);
		this.setPeutAddPteAction(true);
		this.setAssociationGC(new LinkedHashMap<GuideSpirituel, ArrayList<CarteCroyant>>());
		this.setCapaciteUtilise(false);
	}
/**
 * Methode pour mettre tous les cartes dans une arraylist.
 * @return tous les cartes dans le jeu
 */
	public ArrayList<CarteAction> tousLesCartes() {
		ArrayList<CarteAction> tous = new ArrayList<CarteAction>();
		Iterator<CarteAction> itC = this.getCarte().iterator();
		Iterator<GuideSpirituel> itG = this.getAssociationGC().keySet().iterator();
		Iterator<ArrayList<CarteCroyant>> itCr = this.getAssociationGC().values().iterator();
		while (itC.hasNext()) {
			tous.add(itC.next());
		}
		while (itCr.hasNext()) {
			Iterator<CarteCroyant> itA = itCr.next().iterator();
			while (itA.hasNext()) {
				tous.add(itA.next());
			}
		}
		while (itG.hasNext()) {
			tous.add(itG.next());
		}

		return tous;
	}
/**
 * Methode pour obtenir la liste de croyant dans le centre de la table.
 * @return liste des croyants.
 */
	public ArrayList<CarteCroyant> champCroyant() {
		ArrayList<CarteCroyant> cs = new ArrayList<CarteCroyant>();
		if (this.getAssociationGC().size() == 0) {
			return cs;
		}
		Iterator<ArrayList<CarteCroyant>> itCr = this.getAssociationGC().values().iterator();
		while (itCr.hasNext()) {
			Iterator<CarteCroyant> itc = itCr.next().iterator();
			while (itc.hasNext()) {
				cs.add(itc.next());
			}
		}
		return cs;
	}

	public ArrayList<GuideSpirituel> champGuide() {
		ArrayList<GuideSpirituel> g = new ArrayList<GuideSpirituel>();
		Iterator<GuideSpirituel> itG = this.getAssociationGC().keySet().iterator();
		while (itG.hasNext()) {
			g.add(itG.next());
		}
		return g;
	}
/**
 * Ici on utilise patron de observer.
 */
	public void update(Observable obj, Object arg) {
		this.setPeutSacrifierGuideSpirituel(true);
		this.setPeutSacrifierGuideSpirituel(true);
		this.setPeutAddPteAction(true);
	}
/**
 * Methode pour defausser les cartes en main.
 * @param cartes les cartes que l'on va defausser.
 * @param partie
 */
	public void defausserCartes(LinkedList<CarteAction> cartes, Partie partie) {
		System.out.println(this+" a defaussé ses Cartes:");
		ViewJouer.LabelSystem.setTexte(this+" a defaussé ses Cartes");
		for (int i = 0; i < cartes.size(); i++) {
			this.carte.remove(cartes.get(i));
			partie.getPiocheCarteDefausse().getContenue().add(cartes.get(i));
			System.out.println(cartes.get(i)+"\r\n");
			this.setChanged();
			this.notifyObservers();
		}
		
	}
/**
 * Methode pour completer les cartes en main.
 * @param partie
 */
	public void completerMain(Partie partie) {
		while (this.carte.size() < 7) {
			PiocheCarteAction pa = partie.getPiocheCarteAction();
			this.carte.add(pa.trierCarteDessus());
			this.setChanged();
			this.notifyObservers();
		}
		System.out.println(this + " a complete sa main à 7 cartes");
		ViewJouer.LabelSystem.setTexte(this + " a complete sa main à 7 cartes");

	}

	public abstract int choisirOperation(Partie partie) throws IOException;

	/**
	 * Déterminer si un joueur tien une carte d'interruption, et les retourner
	 * dans un liste
	 * 
	 * @return ArrayList<DeusEX>
	 */
	public ArrayList<DeusEX> peutIterruption(CarteAction ca) {
		ArrayList<DeusEX> deus = new ArrayList<DeusEX>();
		Iterator<CarteAction> it = this.getCarte().iterator();
		while (it.hasNext()) {
			CarteAction card = it.next();
			if (card instanceof DeusEX) {
				DeusEX carteDeus = (DeusEX) card;
				if (ca.getOrigine() == "Jour") {
					if (carteDeus.getId() > 9 && carteDeus.getId() <= 13) {
						deus.add(carteDeus);
					}
				} else if (ca.getOrigine() == "Nuit") {
					if (carteDeus.getId() >= 9 && carteDeus.getId() <= 13 && carteDeus.getId() != 10) {
						deus.add(carteDeus);
					}
				} else if (ca.getOrigine() == "Neant") {
					if (carteDeus.getId() >= 9 && carteDeus.getId() <= 13 && carteDeus.getId() != 11) {
						deus.add(carteDeus);
					}
				} else if (ca.getOrigine() == "Sans") {
					if (carteDeus.getId() == 12 || carteDeus.getId() == 13) {
						deus.add(carteDeus);
					}
				}
			}
		}

		return deus;
	}

	public abstract void decideSiIterruption(Partie partie, CarteAction ca) throws IOException;

	public abstract void decideInterruptionRomtec(GuideSpirituel guide);

	public abstract boolean decideSiSacrifier();

	public abstract void decideSiUtiliserSansOrigine(Partie partie) throws IOException;
/**
 * Methode pour sacrifier un croyant rattrache.
 * @param croyant La carte croyant que l'on va sacrifier
 * @param partie
 * @throws IOException
 */
	public void sacrifierCroyant(CarteCroyant croyant, Partie partie) throws IOException {
		partie.demanderInterruption(croyant,this);
		if (this.peutScrifierCroyant == false) {
			if (this instanceof JoueurPhysique) {
				System.out.println("Vous ne pouvez pas sacrifier carte croyant durant ce tour");
				ViewJouer.LabelJoueur.setTexte("Vous ne pouvez pas sacrifier carte croyant durant ce tour");
			}
			return;
		} else if (croyant.getStace() == true) {
			if (this instanceof JoueurPhysique) {
				System.out.println("Vous ne pouvez pas sacrifier cette croyant car il est protégé par Carte Stace");
				ViewJouer.LabelJoueur.setTexte("Vous ne pouvez pas sacrifier cette croyant car il est protégé par Carte Stace");
			}
			return;
		} else if (croyant.isInteruupted() == true) {
			if (this instanceof JoueurPhysique) {
				System.out.println("Votre carte est interrupté");
				ViewJouer.LabelJoueur.setTexte("Votre carte est interrupté");
			}

			// -----------Defausse la carte-------------------//
			if (croyant.isResteEnJeu() == false) {
				Iterator<ArrayList<CarteCroyant>> it = this.getAssociationGC().values().iterator();
				while (it.hasNext()) {
					ArrayList<CarteCroyant> listCroyant = it.next();
					for(int i=0;i<listCroyant.size();i++){
						if (listCroyant.get(i) == croyant) {
							listCroyant.remove(croyant);
						}
					}
				}

				partie.getPiocheCarteDefausse().getContenue().add(croyant);
			}

			this.notifyElement(new ChampRepaintEvent());
			return;
		}

		else {//effect
			System.out.println(this+"a sacrifé "+croyant.getName());
			ViewJouer.LabelSystem.setTexte(this+"a sacrifé "+croyant.getName());
			croyant.sacrifier(this, partie);
			partie.carteEnCours=croyant;
			//ViewJouer.CarteEnCours.setImage(croyant);
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// -----------Defausse la carte-------------------//
			if (croyant.isResteEnJeu() == false) {
				Iterator<ArrayList<CarteCroyant>> it = this.getAssociationGC().values().iterator();
				while (it.hasNext()) {
					ArrayList<CarteCroyant> listCroyant = it.next();
					for(int i=0;i<listCroyant.size();i++){
						if (listCroyant.get(i) == croyant) {
							listCroyant.remove(croyant);
						}
					}
				}

				partie.getPiocheCarteDefausse().getContenue().add(croyant);
			}
			this.notifyElement(new ChampRepaintEvent());
		}
		this.setNombreCroyant(this.champCroyant().size());
		this.setChanged();
		this.notifyElement(new PointRepaintEvent());
	}

	/**
	 * return the position of card
	 * 
	 * @param card
	 * @return 0 if card is not in the player 1 card in hand of player 2 card in
	 *         Association guides 3 card in Association croyant
	 */
	public int returnPositionCard(CarteAction card) {
		int result = 0;
		if (this.getCarte().contains(card)) {
			result = 1;
		} else if (this.getAssociationGC().containsKey(card)) {
			result = 2;
		} else if (this.getAssociationGC().containsValue(card)) {
			result = 3;
		}
		return result;

	}
/**
 * Methode pour sacrifier une carte guidespirituel.
 * @param guide La carte guidespirituel que l'on va sacrifier
 * @param partie
 * @throws IOException
 */
	public void sacrifierGuideSpirituel(GuideSpirituel guide, Partie partie) throws IOException {
		// Si autoriser à sacrifier?

		if (this.peutSacrifierGuideSpirituel == false) {

			System.out.println(this + " ne peut pas sacrifier guide spirituel durant ce tour");
			ViewJouer.LabelJoueur.setTexte(this + " ne peut pas sacrifier guide spirituel durant ce tour");

			return;
		}
		// Stace?
		else if (guide.isStace() == true) {

			System.out.println(this + " ne peut pas sacrifier cette Guide spirituel car il est protégé par Carte Stace");
			ViewJouer.LabelJoueur.setTexte(this + " ne peut pas sacrifier cette Guide spirituel car il est protégé par Carte Stace");

			return;
		}
		// Interruption?
		else if (guide.isInteruupted() == true) {

			System.out.println(guide + " est interrupté");
			ViewJouer.LabelJoueur.setTexte("Interruption !");
			//ViewJouer.CarteEnCours.setImage(guide);


			// -----------Defausse la carte-------------------//
			if (guide.isResteEnJeu() == false) {
				partie.getPiocheCarteDefausse().getContenue().add(guide);
				Iterator<CarteCroyant> it = guide.getCroyantRattache().iterator();
				while (it.hasNext()) {
					partie.getPiocheCarteDefausse().getContenue().add(it.next());
				}
				this.getAssociationGC().remove(guide);
				this.notifyElement(new ChampRepaintEvent());//Repaint champ
			}
			return;
		}
		// Sacrifice effectué
		guide.sacrifier(this, partie);
		partie.carteEnCours=guide;
		System.out.println(this + " a sacrifié " + guide);
		ViewJouer.LabelSystem.setTexte(this + " a sacrifié : "+ guide.getNom());
		//ViewJouer.CarteEnCours.setImage(guide);


		// -----------Defausse la carte-------------------//
		if (guide.isResteEnJeu() == false) {
			partie.getPiocheCarteDefausse().getContenue().add(guide);
			Iterator<CarteCroyant> it = guide.getCroyantRattache().iterator();
			while (it.hasNext()) {
				partie.getPiocheCarteDefausse().getContenue().add(it.next());
			}
			this.getAssociationGC().remove(guide);
			this.notifyElement(new ChampRepaintEvent());
		}
		this.setNombreCroyant(this.champCroyant().size());
		this.setChanged();
		this.notifyElement(new PointRepaintEvent());
	}
/**
 * Methode pour interrupter l'utilisation d'une carte par la carte deusEX
 * @param deus La carte deusEX que l'on va utiliser
 * @param ca La carte que l'on va interrupter
 * @param partie
 * @throws IOException
 */
	public void interrupter(DeusEX deus, CarteAction ca, Partie partie) throws IOException {
		//ViewJouer.CarteEnCours.setImage(deus);
		deus.interrupter(this, ca, partie);
		this.getCarte().remove(deus);
		ViewJouer.Joueur.repaintCA();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
/**
 * Methode pour utiliser une carte d'action.
 * Dans cette methode, on peut identifier l'identite de la carte et puis realiser sa fonction.
 * @param carte La carte que l'on va utiliser.
 * @param partie
 * @throws IOException
 */
	public void utiliserCarteAction(CarteAction carte, Partie partie) throws IOException {
		if(carte==null){
			return;
		}
			System.out.println(this+" a utilise "+carte);
		ViewJouer.LabelSystem.setTexte(this + " a utilisé : "+carte.getName());
		//ViewJouer.CarteEnCours.setImage(carte);
			
	
		// -----------Moins pte action------------------
		if (carte.getOrigine() == "Jour") {
			this.setPointActionJour(this.getPointActionJour() - 1);
		} else if (carte.getOrigine() == "Nuit") {
			this.setPointActionNuit(this.getPointActionNuit() - 1);
		} else if (carte.getOrigine() == "Neant") {
			if (this.getPointActionNeant() > 0) {
				this.setPointActionNeant(this.getPointActionNeant() - 1);
			} else if (this.getPointActionJour() >= 2) {
				this.setPointActionJour(this.getPointActionJour() - 2);
			} else if (this.getPointActionNuit() >= 2) {
				this.setPointActionNuit(this.getPointActionNuit() - 2);
			}
		}
		//----------------Interruption--------
		partie.demanderInterruption(carte,this);
		if (carte.isInteruupted()) {
			if (this instanceof JoueurPhysique) {
				System.out.println("Votre carte est interrupté");
				ViewJouer.LabelJoueur.setTexte("Votre carte est interrupté");
			}
			// Defausser la carte
			this.getCarte().remove(carte);
			ViewJouer.Joueur.repaintCA();
			return;
		}
		// ------Effect--------------------------
		partie.carteEnCours=carte;
		if (carte instanceof CarteCroyant) {
			((CarteCroyant) carte).creerCroyant(partie);
			
		} else if (carte instanceof GuideSpirituel) {
			//System.out.println(this+" a utilisé guide spirituel");
			((GuideSpirituel) carte).rattacherCroyants(partie, this);
			
		} else if (carte instanceof DeusEX) {
			//System.out.println(this+" a utilisé Deus EX: ");
			System.out.println(carte);
			((DeusEX) carte).sacrifier(this, partie, null);
			
		} else if (carte instanceof Apocalypse) {
			//System.out.println(this+" a utilisé Apocalypse ");
			((Apocalypse) carte).utiliser(partie);
			
		}



		// Defausser la carte
		this.getCarte().remove(carte);
		ViewJouer.Joueur.repaintCA();
		this.setChanged();
		this.notifyElement(new PointRepaintEvent());

		//Repaint
		for (int i=0;i<partie.getJoueur().size();i++){
			Joueur joueur=partie.getJoueur().get(i);
			joueur.notifyElement(new ChampRepaintEvent());
			joueur.notifyElement(new PointRepaintEvent());
		}
		partie.notifyElement(new CentreRepaintEvent());
		partie.updateNbrCroyantJoueur();
	}

	public abstract boolean choisirSiUtiliserCapaciteSpeciale();

	public void utiliserCapaciteSpeciale(Partie partie) throws IOException {
		this.getCarteDevinite().utiliserCapacite(this, partie);
		;
		this.setCapaciteUtilise(true);
		notifyElement(new PointRepaintEvent());
	}

	public void defausserUneCarte(CarteAction c) {

	}

	public abstract LinkedList<CarteAction> choisirListCarte() throws IOException;

	public abstract LinkedList<Joueur> choisirListJoueur(Partie partie) throws IOException;

	public abstract CarteAction choisirUneCarte();

	public abstract CarteAction ChoisirUneCard(List c);

	public abstract CarteCroyant choisirUneCroyantAssocie();

	public abstract GuideSpirituel choisirUneGuideSpirituelAssocie();

	public abstract GuideSpirituel choisirGuideSpirituelDautreJoueur(Joueur jC);

	public abstract Joueur ChoisirUnJoueur(Partie partie);

	public abstract Joueur ChoisirUnJoueur(List c);
/**
 * Methode pour obtenir la face de de.
 * @param partie
 * @return
 */
	public String lancerDeCosmogonie(Partie partie) {
		DeCosmogonie de = partie.getDe();
		de.lancer();
		String result = de.getPhase();
		System.out.println(this+" a lancé la dé de cosmogonie");
		ViewJouer.LabelSystem.setTexte(this+" a lancé la dé de cosmogonie");
		System.out.println("La dé de cosmogonie est sur la face " + result);
		ViewJouer.LabelSystem.setText("La dé de cosmogonie est sur la face " + result);
		return result;
	}
	
	public Apocalypse avoirApocalypse(Partie partie){
		Apocalypse apo=null;
		for(int i=0;i<this.cartesLibreAUtiliser(partie).size();i++){
			if(this.cartesLibreAUtiliser(partie).get(i) instanceof Apocalypse){
				apo=(Apocalypse) this.cartesLibreAUtiliser(partie).get(i) ;
			}
		}
		return apo;
	}
	/**
	 * Methode pour savoir si un joueur a une carte croyant ou guidespirituel.
	 * @return
	 */
	public boolean avoirCroyantOuGuide() {
		boolean result = false;
		if (this.getAssociationGC().size() > 0) {
			result = true;
		}
		return result;
	}

	public boolean avoirCroyantAssocie() {
		boolean result = false;
		for (Iterator<ArrayList<CarteCroyant>> itA = this.getAssociationGC().values().iterator(); itA.hasNext();) {
			if (itA.next().size() > 0) {
				result = true;
			}

		}
		return result;
	}
/**
 * Retourne tous les carte sans origine et non compris les carte d'interruption
 * @return sans :
 */
	public ArrayList<CarteAction> avoirCarteSansOrigine() {
		ArrayList<CarteAction> sans = new ArrayList<CarteAction>();
		for (Iterator<CarteAction> itC = this.getCarte().iterator(); itC.hasNext();) {
			CarteAction ca = itC.next();
			if (ca.getOrigine() == "Sans") {
				sans.add(ca);
			}
		}
		sans.removeAll(this.cartesInterruption());// Carte interruption est demandé au decideSiTnterruption Mais pas au deicideSiSansOrigne
		return sans;
	}

	/**
	 * Méthode qui teste si le joueur peut faire le 3ème opération(a point
	 * d'action suffisssant pour utiliser carte action et
	 * 
	 * @return
	 */
	public ArrayList<CarteAction> cartesLibreAUtiliser(Partie partie) {
		HashSet<CarteAction> pointActSuffit = new HashSet<CarteAction>();
		ArrayList<CarteAction> jour = new ArrayList<CarteAction>();
		ArrayList<CarteAction> nuit = new ArrayList<CarteAction>();
		ArrayList<CarteAction> neant = new ArrayList<CarteAction>();
		ArrayList<CarteAction> sans = new ArrayList<CarteAction>();
		// Trier les cartes
		for (Iterator<CarteAction> itC = this.getCarte().iterator(); itC.hasNext();) {
			CarteAction carte = itC.next();
			String origine = carte.getOrigine();
			if (origine == "Jour") {
				jour.add(carte);
			}
			if (origine == "Nuit") {
				nuit.add(carte);
			}
			if (origine == "Neant") {
				neant.add(carte);
			}
			if (origine == "Sans") {
				sans.add(carte);
			}
		}
		// point act jour
		if (this.getPointActionJour() > 0) {
			if (jour.size() > 0) {
				pointActSuffit.addAll(jour);
			}
			if ((neant.size() > 0) && this.getPointActionJour() >= 2) {
				pointActSuffit.addAll(neant);
			}
		}
		// point act nuit
		if (this.getPointActionNuit() > 0) {
			if (nuit.size() > 0) {
				pointActSuffit.addAll(nuit);
			}
			if ((neant.size() > 0) && this.getPointActionNuit() >= 2) {
				pointActSuffit.addAll(neant);
			}
		}
		// point act Néant
		if (this.getPointActionNeant() > 0) {
			if (neant.size() > 0) {
				pointActSuffit.addAll(neant);
			}
		}
		// Pour les cartes sans origine
		if (sans.size() > 0) {
			pointActSuffit.addAll(sans);
		}
		ArrayList<CarteAction> list = new ArrayList<CarteAction>();
		list.addAll(pointActSuffit);
		if(partie.getNumeroTour()==1){
			for(int i=0;i<list.size();i++){
				CarteAction ca = list.get(i);
				if (ca instanceof Apocalypse) {
					list.remove(ca);
				}
			}
		}
		// Si 0 cryant au centre table, guide spirituel n'est pas disponble
		if (partie.getCentreCommun().getListCroyant().size() == 0) {
			for(int i=0;i<list.size();i++){
				CarteAction ca = list.get(i);
				if (ca instanceof GuideSpirituel) {
					list.remove(ca);
				}
			}
		}
		//Les cartes interruption sont pas disponible non plus
		list.removeAll(this.cartesInterruption());

		return list;
	}
	
	/**
	 * 
	 * Retourner tous les cartes d'interruption que joueur possède
	 * @return
	 */
	public ArrayList<CarteAction> cartesInterruption(){
		ArrayList<CarteAction> CI=new ArrayList<CarteAction>();
		for(int i=0;i<this.getCarte().size();i++){
			CarteAction ca = this.getCarte().get(i);
			if (ca instanceof DeusEX ) {
				DeusEX deus=(DeusEX)ca;
				if(deus.getId()>=9 && deus.getId()<=15){
					CI.add(deus);
				}
			}
		}
		return CI;
	}

	public void notifyElement(Object o){
		this.setChanged();
		this.notifyObservers(o);
	}

	@Override
	public String toString() {
		return this.getCarteDevinite().getNom();
	}
/**
 * Methode pour afficher l'information de chaque Joueur.
 * @return
 */
	public String afficheInfo() {
		return "[Point Act Jour : " + this.getPointActionJour() + "  Point Act Nuit :" + this.getPointActionNuit()
				+ "  Point Act Neant : " + this.getPointActionNeant() + "]";
	}

	public int getNumeroJoueur() {
		return numeroJoueur;
	}

	public void setNumeroJoueur(int numeroJoueur) {
		this.numeroJoueur = numeroJoueur;
	}

	public Devinite getCarteDevinite() {
		return carteDevinite;
	}

	public void setCarteDevinite(Devinite carteDevinite) {
		this.carteDevinite = carteDevinite;
	}

	public LinkedList<CarteAction> getCarte() {
		return carte;
	}

	public void setCarte(LinkedList<CarteAction> carte) {
		this.carte = carte;
	}

	public int getNombreCroyant() {
		return nombreCroyant;
	}

	public void setNombreCroyant(int nombreCroyant) {
		this.nombreCroyant = nombreCroyant;
		this.notifyElement(new PointRepaintEvent());
	}

	public int getPointActionNuit() {
		return pointActionNuit;
	}

	public void setPointActionNuit(int pointActionNuit) {
		this.pointActionNuit = pointActionNuit;
		this.notifyElement(new PointRepaintEvent());
	}

	public int getPointActionJour() {
		return pointActionJour;
	}

	public void setPointActionJour(int pointActionJour) {
		this.pointActionJour = pointActionJour;
		this.notifyElement(new PointRepaintEvent());
	}

	public int getPointActionNeant() {
		return pointActionNeant;
	}

	public void setPointActionNeant(int pointActionNeant) {
		this.pointActionNeant = pointActionNeant;
		this.notifyElement(new PointRepaintEvent());
	}

	public abstract CarteCroyant choisirCroyantRattacher(ArrayList<CarteCroyant> cartePossible);

	public boolean isPeutSacrifierGuideSpirituel() {
		return peutSacrifierGuideSpirituel;
	}

	public void setPeutSacrifierGuideSpirituel(boolean peutSacrifierGuideSpirituel) {
		this.peutSacrifierGuideSpirituel = peutSacrifierGuideSpirituel;
	}

	public Map<GuideSpirituel, ArrayList<CarteCroyant>> getAssociationGC() {
		return associationGC;
	}

	public void setAssociationGC(Map<GuideSpirituel, ArrayList<CarteCroyant>> associationGC) {
		this.associationGC = associationGC;
	}

	public boolean getPeutScrifierCroyant() {
		return peutScrifierCroyant;
	}

	public void setPeutScrifierCroyant(boolean peutScrifierCroyant) {
		this.peutScrifierCroyant = peutScrifierCroyant;
	}

	public boolean getPeutAddPteAction() {
		return peutAddPteAction;
	}

	public void setPeutAddPteAction(boolean peutAdderPteAction) {
		this.peutAddPteAction = peutAdderPteAction;
	}

	public boolean isCapaciteUtilise() {
		return capaciteUtilise;
	}

	public void setCapaciteUtilise(boolean capaciteUtilise) {
		this.capaciteUtilise = capaciteUtilise;
	}

	public PButton getButton() {
		return button;
	}

	public void setButton(PButton button) {
		this.button = button;
	}
	public ChampPanel getChampPanel() {
		return champPanel;
	}

	public void setChampPanel(ChampPanel champPanel) {
		this.champPanel = champPanel;
	}
}
