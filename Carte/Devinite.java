package Carte;

import java.io.Console;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import Joueur.Joueur;
import Partie.CroyantPublique;
import Partie.Partie;



/**
 * La classe devinite reprente les cartes devinites dans le jeu.
 * Nous avons realise les fonction de l'utilisation de capacite d'une devinite.
 * @author Siyuan
 *@author Zijie
 */
public class Devinite implements Serializable {
	private  ArrayList<String> dogmes=new ArrayList<String>();
	private  String nom;
	private String origine;
	private String description;
	private String capacite;
	private int id;
	
	public final static String Dogmes[] ={"Nature","Humain","Mystique","Symboles","Chaos"};
	public final static String Origines[]={"Jour","Nuit","Neant","Aube","Crepuscule"};
	public final static String Noms[]={"Shingva","Llewella","Drinded","Gorpa","Pui-Tara","Yarstur","Romtec","Gwenghelen","Killinstred"};
	public final static String Descriptions[]={
			"Perverse et retorse, Shingva est une Divinité que toutes les autres détestent.",
			"Divinité machiavélique et manipulatrice, Killinstred cherche à influencer et contrôler ses ennemis.",
			"Défenseur des hommes, quelles que soient leurs croyances, Drinded protège les chefs religieux",
			"Divinité joueuse et espiègle, Gorpa aime gêner ses consœurs dans leur recherche de puissance.",
			"Pui-Tara est la Divinité sur laquelle l'influence de la Nuit s'est faite la plus forte.",
			"Dernier pur du jour, Yarstur combat le Néant sous toutes ses formes.",
			"Romtec est une Divinité individualiste, pour qui chaque être vivant doit garder son libre arbitre.",
			"Première Divinité à recevoir l'influence du Néant, Gwenghelen est celle qui en a reçu le plus de puissance.",
			"Divinité machiavélique et manipulatrice, Killinstred cherche à influencer et contrôler ses ennemis."
			
	};
	/**
	 * Les descriptions des capacites des devinites.
	 */
	public final static String Capacites[]={
			"Peut imposer le sacrifice d'un Guide Spirituel ayant le Dogme Symboles ou Nature."	,
			"Peut obliger un joueur à poser une carte Apocalypse s'il en possède une.",
			"Peut empêcher le sacrifice d'un des Guides Spirituels de n'importe quel joueur.",
			"Peut récupérer les points d'Action d'une autre Divinité en plus des siens. L'autre Divinité ne reçoit aucun point d'Action ce tour-ci.",
			"Peut détruire toutes les cartes de Croyants au centre de la table d'Origine Jour.",
			"Peut détruire toutes les cartes de Croyants au centre de la table d'Origine Néant.",
			"Peut empêcher un jour de créer un Guide Spirituel. La carte est défaussée.",
			"Récupère autant de points d'Action supplémentaires d'Origine Néant que le nombre de Guides Spirituels que la Divinité possède.",
			"Peut obliger un joueur à poser une carte Apocalypse s'il en possède une."
	};
	// Carte Devinite nom, dogme1,dogme2,dogme3,origine,description,capacite
	public static final int valeur[][]={
			{1,5,2,3,4,1,1},
			{2,5,1,3,2,2,2},
			{3,1,2,4,1,3,3},
			{4,5,1,4,5,4,4},
			{5,1,4,3,2,5,5},
			{6,5,4,3,1,6,6},
			{7,5,2,1,5,7,7},
			{8,4,2,3,4,8,8},
			{9,5,1,3,2,9,9},
			
	};
	/**
	 * Pour donner les identites et les attributs aux cartes devinites.
	 * @param id les identites des cartes devinites
	 */
	public Devinite(int id) {
		this.setValeur(valeur[id]);
		this.id=id;
	}
	/**
	 * Pour donner les identites et les attributs aux cartes devinites.
	 * @param id les identites des cartes devinites
	 */
	public void setValeur(int input[]){
		this.nom=this.Noms[input[0]-1];//Set nom
		//System.out.println(this.nom);
		for(int i=1;i<4;i++){//Set dogmes
			this.dogmes.add(Devinite.Dogmes[input[i]-1]);
			//System.out.println(Devinite.Dogmes[input[i]-1]);
		}
		this.origine=Devinite.Origines[input[4]-1];//Set origine
		this.description=Devinite.Descriptions[input[5]-1];//set description
		this.capacite=Devinite.Capacites[input[6]-1];//set description
	}
	
	//brewalenCapacite empêcher l'utilisation d'une carte Apocalypse.La carte est défaussée.
		//Quand quelqu'un utilise une carte Apocalypse, demander "brewalen" s'il veut utiliser sa capacite
	/**
	 * Pour utiliser la capacite de la devinite brewalen.
	 */
		public void brewalenCapacite(){
			System.out.println("La divinite Brewalen a utilise sa capacite.");
			System.out.println("L'uilisation de la carte Apocalyse est empechee.");
		
		}
		
		//DrindedCapacite Peut empêcher le sacrifice d'un des GuidesSpirituels de n'importe quel joueur.
		//Quand quelqu'un utilise utilise une carte GuideSpirituel, demander s'il veut utiliser sa capacite
		/**
		 * Pour utiliser la capacite de la devinite drinded.
		 * @param j Joueur qui utilise cette capacite
		 * @param partie
		 */
		public void drindedCapacite(Joueur j,Partie partie){
			Joueur j2 = j.ChoisirUnJoueur(partie);
			System.out.println("La divinite Drinded"+j+" a utilise sa capacite a"+j2);
			GuideSpirituel g = j.choisirGuideSpirituelDautreJoueur(j2);
			if(g==null){
				return;
			}
			System.out.println("L'utilisation de la carte GuideSpirituel"+g+"est empechee.");
			j2.setPeutSacrifierGuideSpirituel(false);
		}
		
		//Yarstur Peut detruire toutes les cartes de Croyants au centre de la table d'Origine Neant
		/**
		 * Pour utiliser la capacite de la devinite yarstur.
		 * @param partie
		 */
		public void yarsturCapacite(Partie partie){
			System.out.println("la divinite Yarstur a utilise sa capacite.");
			CroyantPublique cp=partie.getCentreCommun();
			int size = cp.getListCroyant().size();
			CarteCroyant[] cpl = ( CarteCroyant[])cp.getListCroyant().toArray(new CarteCroyant[size]);
			
			for(int i=0;i<size;i++){
				if (cpl[i].getOrigine().equals("Neant")){
					cp.getListCroyant().remove(cpl[i]);
					partie.getPiocheCarteDefausse().getContenue().add(cpl[i]);
					System.out.println("La carte Croyant"+cpl[i].toString()+"a ete detruit.");
				}
				else{
					continue;
				}
			}
		}
		
		//Killinstred Peut obliger un joueur a poser une carteApocalypse s'il en possede une.
		/**
		 * Pour utiliser la capacite de la devinite killinstred.
		 * @param j Joueur qui utilise cette capacite.
		 * @param partie
		 */
		public void killinstredCapacite(Joueur j,Partie partie) throws IOException{
			Joueur j2 = j.ChoisirUnJoueur(partie);
			System.out.println("La divinite Killinstred"+j+" a utilise sa capacite a"+j2);
			System.out.println(j+"oblige"+j2+"a utiliser sa carte Apocalypse.");
			if(j2.avoirApocalypse(partie)!=null){
				if(partie.getNumeroTour()==1){
					System.out.println("Apocalypse est interdit au premier tour");
				}
			Apocalypse	apo=j2.avoirApocalypse(partie);
			j2.utiliserCarteAction(apo, partie);
			}else{
				System.out.println(j2+" n'a pas d'apocalypse à poser");
			}
			
		}
		
		//Llewella Peut obliger un joueur a poser une carteApocalypse s'il en possede une.
		/**
		 * Pour utiliser la capacite de la devinite Llewella.
		 * @param j Joueur qui utilise cette capacite.
		 * @param partie
		 */
		public void LlewellaCapacite(Joueur j,Partie partie) throws IOException{
			Joueur j2 = j.ChoisirUnJoueur(partie);
			System.out.println("La divinite Llewella "+j+" a utilise sa capacite a"+j2);
			System.out.println(j+"oblige"+j2+"a utiliser sa carte Apocalypse.");
			if(j2.avoirApocalypse(partie)!=null){
				if(partie.getNumeroTour()==1){
					System.out.println("Apocalypse est interdit au premier tour");
				}
			Apocalypse	apo=j2.avoirApocalypse(partie);
			j2.utiliserCarteAction(apo, partie);
			}else{
				System.out.println(j2+" n'a pas d'apocalypse à poser");
			}
			
		}
		
		//Pui-Tara Peut detruire toutes les cartes de Croyants au centre de la table d'Origine Jour.
		/**
		 * Pour utiliser la capacite de la devinite pui_Tara.
		 * @param partie
		 */
		public void pui_TaraCapacite(Partie partie){
			System.out.println("La divinite Pui-Tara a utilise sa capacite ");
			CroyantPublique cp=partie.getCentreCommun();
			int size = cp.getListCroyant().size();
			CarteCroyant[] cpl = ( CarteCroyant[])cp.getListCroyant().toArray(new CarteCroyant[size]);
			
			for(int i=0;i<size;i++){
				if (cpl[i].getOrigine().equals("Jour")){
					cp.getListCroyant().remove(cpl[i]);
					partie.getPiocheCarteDefausse().getContenue().add(cpl[i]);
					System.out.println("La carte Croyant"+cpl[i].toString()+"a ete detruit.");
				}
				else{
					continue;
				}
			}
		}
		
		//Gwenghelen Recupere autant de points d'Action supplementaires d'Origine Neant que le nombre deGuides Spirituels que la Divinite possede.
		/**
		 * Pour utiliser la capacite de la devinite gwenghelen.
		 * @param j Joueur qui utilise cette capacite.
		 */
		public void gwenghelenCapacite(Joueur j){
			System.out.println("La divinite Gwenghelen"+j+" a utilise sa capacite ");
			j.setPointActionNeant(j.getPointActionNeant()+j.getAssociationGC().size());
			System.out.println(j.toString()+"a gagne"+ j.getAssociationGC().size() +" points Action Neant par l'utilisation de sa capacite");
				
		}
		
		//Shingva Peut imposer le sacrifice d'un Guide Spirituel ayant le Dogme Symboles ou Nature.
		/**
		 * Pour utiliser la capacite de la devinite shingva.
		 * @param j Joueur qui utilise cette capacite.
		 * @param partie
		 */
		public void shingvaCapacite(Joueur j,Partie partie) throws IOException{
			Joueur j2 = j.ChoisirUnJoueur(partie);
			System.out.println("La divinite Shingva"+j+" a utilise sa capacite a"+j2);
			GuideSpirituel g = j.choisirGuideSpirituelDautreJoueur(j2);
			if(g==null){
				return;
			}
			boolean containM = g.getDogmes().contains("Symboles");
			boolean containN = g.getDogmes().contains("nature");
			if (containM||containN){
				j2.sacrifierGuideSpirituel(g, partie);
				System.out.println(j+"oblige"+j2+"a sacrifier sa carte GuideSpirituel"+g.toString());
			}
			else {
				System.out.println("Vous ne pouvez pas sacrifier cette carte.");
			}
		}
		
		//Gorpa Peut recuperer les points d'Action d'une autre Divinite en plus des siens. L'autre Divinite ne recoit aucun point d'Action ce tour-ci.
		//
		/**
		 * Pour utiliser la capacite de la devinite gorpa.
		 * @param j Joueur qui utilise cette capacite.
		 * @param partie
		 */
		public void gorpaCapacite(Joueur j,Partie partie){
			Joueur j2 = j.ChoisirUnJoueur(partie);
			System.out.println("La divinite Gorpa"+j+" a utilise sa capacite a"+j2);
			int pointJour = j2.getPointActionJour()-j.getPointActionJour();
			int pointNuit = j2.getPointActionNuit()-j.getPointActionNuit();
			int pointNeant = j2.getPointActionNeant()-j.getPointActionNeant();
			j.setPointActionJour(j.getPointActionJour()+pointJour);
			j.setPointActionNuit(j.getPointActionNuit()+pointNuit);
			j.setPointActionNeant(j.getPointActionNeant()+pointNeant);
			System.out.println("Il a récupéré "+pointJour+"Point Action Jour, "+pointNuit+"point action nuit, "+pointNeant+" Point Action neant");
			j2.setPeutAddPteAction(false);
		}
		
		//Romtec Peut empêcher un jour de créer un Guide Spirituel.La carte est défaussée.
		//quand quelqu'un utilise une carte Guide Spirituel, demander s'il veut utiliser capacite de Romtec
		/**
		 * Pour utiliser la capacite de la devinite romtec.
		 * @param guide La carte guidespirtuel que l'on va empecher.
		 */
		public void romtecCapacite(GuideSpirituel guide){
			System.out.println("La divinite Romtec a utilise sa capacite ");
			guide.setInteruupted(true);
			System.out.println("L'utilisation de la carte GuideSpirituel est empeche");
		}
		/**
		 * Pour utiliser la capacite d'une devinite
		 * @param j Joueur qui va utiliser la capacite
		 * @param partie
		 * @throws IOException
		 */
		public void utiliserCapacite(Joueur j, Partie partie) throws IOException{
			switch (this.id){
			case 0:
				this.shingvaCapacite(j, partie);
				break;
			case 1:
				this.LlewellaCapacite(j, partie);
				break;
			case 2:
				this.drindedCapacite(j, partie);
				break;
			case 3:
				this.gorpaCapacite(j, partie);
				break;
			case 4:
				this.pui_TaraCapacite(partie);
				break;
			case 5:
				this.yarsturCapacite(partie);
				break;
			case 6:
				//this.romtecCapacite(guide);
				break;
			case 7:
				this.gwenghelenCapacite(j);
				break;
			case 8:
				this.killinstredCapacite(j, partie);
				break;
			}
				
		}
		/**
		 * Pour montrer l'utilisation de capacite.
		 */
		public String toString(){
			return new String(
					"-----------------------------------------\r\n"+
					"Devinité   "+this.nom+"\r\n"
					+"Origine:"+this.origine+"\r\n"
			        +"Dogmes : "+this.dogmes.get(0)+ " "+this.dogmes.get(1)+" "+this.dogmes.get(0)+"\r\n"
			        +this.description+"\r\n"
					+"Capacité Spéciale : "
					+this.capacite+"\r\n"		
					+"-----------------------------------------\r\n"
			        );
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
	public String getCapacite() {
		return capacite;
	}
	public void setCapacite(String capacite) {
		this.capacite = capacite;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}