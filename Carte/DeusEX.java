package Carte;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import Evenements.ChampRepaintEvent;
import Evenements.PointRepaintEvent;
import GUI.ViewJouer;
import Joueur.Joueur;
import Partie.Partie;


/**
 * La classe DeusEX represente les cartes deusEX dans le jeu.
 * Dans cette classe, nous avons realise les fonctions de utilisation des cartes deusEX.
 * @author Zijie
 *@author Siyuan
 */
public class DeusEX extends CarteAction{
	
	private int id;
	private String nom;
	private String origine;
	private String description;
	public final static String Origines[]={"Jour","Nuit","Neant","Aube","Crepuscule","Sans"};
	public final static String Noms[]={"Colere Divine","Colere Divine","Stase","Ordre Celeste","Fourberie",
	"Diversion","Concentration","Trou Noir","Phoenix","Influence Jour","Influence Nuit","Influence Neant",
	"Influence Nulle","Influence Nulle","Transe","Miroir","Bouleversement","Inquisition"
	};

	public final static String Descriptions[]={
		"Détruit une carte Guide Spirituel d'Origine Nuit ou Néant, dont la capacité spéciale n'a pas effet. Les Croyants attachés reviennent au centre de la table.",
		"Détruit une carte Guide Spirituel d'Origine Jour ou Néant, dont la capacité spéciale n'a pas effet. Les Croyants attachés reviennent au centre de la table.",
		"Protège un Guide Spirituel et ses Croyants jusqu'à ce que cette carte soit annulée ou jusqu'à la prochaine tentative d'Apocalypse.",
		"Vous récupérez un des Guides Spirituels posés devant une autre Divinité et le placez devant vous avec les Croyants qui y sont attachés.",
		"Sacrifiez 2 cartes Croyants d'une autre Divinité. Les capacités spéciales ne sont pas jouées.",
		"Prenez 3 cartes dans la main d'un autre joueur et incluez-les à votre main.",
		"Vous récupérez un des Guides Spirituels posés devant une autre Divinité et le placez devant vous avec les Croyants qui y sont attachés.",
		"Aucun autre joueur ne gagne de points d'Action durant ce tour.",
		"Permet de bénéficier de la capacité spéciale de l'un de vos Croyants ou Guides Spirituels sans sacrifier la carte.",
		"Annule la capacité spéciale d'une carte d'Action d'Origine Nuit ou Néant.",
		"Annule la capacité spéciale d'une carte d'Action d'Origine Jour ou Néant.",
		"Annule la capacité spéciale d'une carte d'Action d'Origine Jour ou Nuit.",
		"Annule la capacité spéciale d'une autre carte d'Action.",
		"Annule la capacité spéciale d'une autre carte d'Action.",
		"Permet de récupérer les effets bénéfiques d'une carte d'Action posée par une autre Divinité. S'il s'agit d'une carte Croyants ou Guide Spirituel, vous posez la carte devant vous.",
		"Retourne les effets d'une carte d'Action sur la Divinité qui l'a posée.",
		"Relancez le dé de Cosmogonie. Le tour de jeu se terminera normalement, mais sous la nouvelle influence.",
		"Choisissez un des Guides Spirituels d'un autre joueur, et l'un des votres. Lancez le dé de Cosmogonie. Sur Jour, le Guide adverse est sacrifié, sur Nuit le votre est sacrifié, sur Néant rien ne se passe. "
		
	};
	public final static int indexOrigine[]={0,1,0,0,1,1,2,2,2,5,5,5,5,5,5,5,5,5};
	/**
	 * Pour donner les identites et les attribut aux cartes deusEX 
	 * @param id les identites nous avons defini
	 */
	public void setValeur(int id){
		this.id=id;
		this.nom=DeusEX.Noms[id];
		this.origine=DeusEX.Origines[DeusEX.indexOrigine[id]];
		this.description=DeusEX.Descriptions[id];		
	}
	
	public DeusEX(int id){
		this.setValeur(id);
	}

	/**
	 * Détruit une carte Guide Spirituel d'Origine Nuit ou Néant, dont la capacité spéciale n'a pas effet. Les Croyants attachés reviennent au centre de la table.
	 * @param jP Joueur qui utilise cette carte.
	 * @param partie
	 */
	public void colereDevineSacrifier(Joueur jP,Partie partie){
		System.out.println(jP+"a utilise DeusEx ColereDevine");
		ViewJouer.LabelSystem.setTexte(jP+"a utilise DeusEx ColereDevine");
		Iterator<Joueur> it=partie.getJoueur().iterator();
		ArrayList<GuideSpirituel> guides=new ArrayList<GuideSpirituel>();
		GuideSpirituel card = null;
		//Donner cartes disponibles
		while(it.hasNext()){
			Joueur j=it.next();
			if(j.getAssociationGC().size()>0 && j!=jP){
			Iterator<GuideSpirituel> itg=j.getAssociationGC().keySet().iterator();
			while(itg.hasNext()){
				GuideSpirituel g=itg.next();
				if(g.isStace()==false){
				guides.add(g);
				}
			}
			}
		}
		//Choix de carte
		if(guides.size()>0){
	    card=(GuideSpirituel) jP.ChoisirUneCard(guides);		
		//Retrouve le carte et le defausser
		while(it.hasNext()){
			Joueur j=it.next();
			if(j.getAssociationGC().keySet().contains(card)){
				j.getAssociationGC().remove(card);
				System.out.println("la carte GuideSpirituel"+card.toString()+"a ete detruite par l'effet de ColereDevine");
				ViewJouer.LabelSystem.setTexte("la carte GuideSpirituel"+card.toString()+"a ete detruite par l'effet de ColereDevine");
				j.setNombreCroyant(j.champCroyant().size());
			}
		}
		partie.getPiocheCarteDefausse().getContenue().add(card);
		Iterator<CarteCroyant> itC=card.getCroyantRattache().iterator();
		while(itC.hasNext()){
			CarteCroyant cr=itC.next();
			partie.getCentreCommun().getListCroyant().add(cr);
		}
		}
	}
	/**
	 * Pour utiliser la fonction particulere de carte stase.
	 * @param j Joueur qui utilise cette carte.
	 */
	public void staseSacrifier(Joueur j){
		System.out.println(j+"a utilise DeusEx Stase");
		ViewJouer.LabelSystem.setTexte(j+"a utilise DeusEx Stase");
		if(j.getAssociationGC().size()==0){
			System.out.println(j+" n'a pas de guide sur son champ  Stace est defaussé!");
			ViewJouer.LabelSystem.setTexte(j+" n'a pas de guide sur son champ  Stace est defaussé!");
			return;
		}
		GuideSpirituel g=j.choisirUneGuideSpirituelAssocie();
		g.setStace(true);
		System.out.println("La carte GuideSpirituel"+g.toString()+"a ete protege");
		ViewJouer.LabelSystem.setTexte("La carte GuideSpirituel"+g.toString()+"a ete protege");
		Iterator<CarteCroyant> it=g.getCroyantRattache().iterator();
		while(it.hasNext()){
			CarteCroyant croyant=it.next();
			croyant.setStace(true);
			System.out.println("le Croyant rattache"+croyant.toString()+"a ete protege");
			ViewJouer.LabelSystem.setTexte("le Croyant rattache"+croyant.getNom()+"a ete protege");
		}
	}
	/**
	 * Ordre Céleste
	 * Vous récupérez un des Guides Spirituels posés devant une autre Divinité et 
	 * le placez devant vous avec les Croyants qui y sont attachés.
	 * @param jP
	 * @param partie
	 */
	public void ordreCelesteSacrifier(Joueur jP,Partie partie){
		System.out.println(jP+"a utilise DeusEx OrdreCeleste");
		ViewJouer.LabelSystem.setTexte(jP+"a utilise DeusEx OrdreCeleste");
		Iterator<Joueur> it=partie.getJoueur().iterator();
		ArrayList<GuideSpirituel> guides=new ArrayList<GuideSpirituel>();
		GuideSpirituel card = null;
		//Donner cartes disponibles
		while(it.hasNext()){
			Joueur j=it.next();
			if(j.getAssociationGC().size()>0 && j!=jP){
			Iterator<GuideSpirituel> itg=j.getAssociationGC().keySet().iterator();
			while(itg.hasNext()){
				GuideSpirituel g=itg.next();
				if(g.isStace()==false){
				guides.add(g);
				}
			}
			}
		}
		//Choix de carte
		if(guides.size()>0){
	    card=(GuideSpirituel) jP.ChoisirUneCard(guides);
	    while(it.hasNext()){
			Joueur j=it.next();
			if(j.getAssociationGC().keySet().contains(card)){
				jP.getAssociationGC().put(card, card.getCroyantRattache());
				j.getAssociationGC().remove(card);
				System.out.println("La carte GuideSpirituel"+card.toString()+"et ses croyants rattache de"+j.toString()+"appartiennent a"+jP.toString()+"maintenant!");
				ViewJouer.LabelSystem.setTexte("La carte GuideSpirituel"+card.toString()+"et ses croyants rattache de"+j.toString()+"appartiennent a"+jP.toString()+"maintenant!");
			}
		}
		}
		
		
	}
	/**
	 * Pour utiliser la fonction particulere de carte fourberie.
	 * @param jP Joueur qui utilise cette carte.
	 * @param partie
	 */
	public void fourberieSacrifier(Joueur jP,Partie partie){
		Joueur jC=jP.ChoisirUnJoueur(partie);
		System.out.println(jP+"a utilise DeusEx Fourberie a"+jC);
		ViewJouer.LabelSystem.setTexte(jP+"a utilise DeusEx Fourberie a"+jC);
		if(jC.champCroyant().size()>=2){
		//Choix carte et le defausse
		for(int i=0;i<2;i++){
			CarteCroyant c=(CarteCroyant) jP.ChoisirUneCard(jC.champCroyant());
			Iterator<ArrayList<CarteCroyant>> it=jC.getAssociationGC().values().iterator();
			while(it.hasNext()){
				ArrayList<CarteCroyant> cs=it.next();
				Iterator<CarteCroyant> itC=cs.iterator();
				while(itC.hasNext()){
					if(itC.next()==c){
						cs.remove(c);
						partie.getPiocheCarteDefausse().getContenue().add(c);
						System.out.println("La carte Croyant"+c.toString()+"a ete sacrifie par l'effet de Fourberie");
						ViewJouer.LabelSystem.setTexte("La carte Croyant"+c.toString()+"a ete sacrifie par l'effet de Fourberie");
					}
				}
			}
		}
		}
		else{
			System.out.println("Le joueur choisi n'a pas assez de croyant à sacrifer !");
			ViewJouer.LabelSystem.setTexte("Le joueur choisi n'a pas assez de croyant à sacrifer !");
		}
		
	}
	/**
	 * Prenez 3 cartes dans la main d'un autre joueur et incluez-les à votre main.
	 * @param j
	 * @param partie
	 * @throws IOException
	 */
	public void diversionSacrifier(Joueur j, Partie partie) throws IOException{
		ArrayList<Joueur> jD=new ArrayList<Joueur>();
		Iterator<Joueur> it=partie.getJoueur().iterator();
		while(it.hasNext()){
			Joueur jIT=it.next();
			if(jIT.getCarte().size()>=3){
				jD.add(jIT);
			}
		}
		Joueur jC=j.ChoisirUnJoueur(jD);
		System.out.println(j+"a utilise DeusEx Diversion a"+jC);
		ViewJouer.LabelSystem.setTexte(j+"a utilise DeusEx Diversion a"+jC);
		for(int i=0;i<3;i++){
			CarteAction ca=jC.choisirUneCarte();
			j.getCarte().add(ca);
			jC.getCarte().remove(ca);
			System.out.println("La carteAction"+ca.toString()+" de "+jC.toString()+" appartient a"+j.toString()+" maintenant");
			ViewJouer.LabelSystem.setTexte("La carteAction"+ca.toString()+" de "+jC.toString()+" appartient a"+j.toString()+" maintenant");
		}
	}
	
	public void concentrationSacrifier(Joueur jP,Partie partie){
		System.out.println(jP+"a utilise DeusEx Concentration");
		ViewJouer.LabelSystem.setTexte(jP+"a utilise DeusEx Concentration");
		this.ordreCelesteSacrifier(jP, partie);
	}
	
	/**
	 * Aucun autre joueur ne gagne de points d'Action durant ce tour.
	 * @param partie
	 */
	public void trouNoirSacrifier(Partie partie){
		System.out.println("Un Joueur a utilise DeusEx TrouNoir");
		ViewJouer.LabelSystem.setTexte("Un Joueur a utilise DeusEx TrouNoir");
		Iterator<Joueur> it=partie.getJoueur().iterator();
    	while(it.hasNext()){
    		Joueur j=it.next();
    		j.setPeutAddPteAction(false);
    	}
		System.out.println("Aucun autre joueur ne gagnede points d'Action durant ce tour");
		ViewJouer.LabelSystem.setTexte("Aucun autre joueur ne gagnede points d'Action durant ce tour");
	}
	/**
	 * Permet de bénéficier de la capacité spéciale de l'un de vos Croyants ou Guides Spirituels sans sacrifier la carte.
	 * @param j
	 * @param partie
	 * @throws IOException
	 */
	public void phoenixSacrifier(Joueur j,Partie partie) throws IOException{
		System.out.println(j+"a utilise DeusEx Phoenix");
		ViewJouer.LabelSystem.setTexte(j+"a utilise DeusEx Phoenix");
		if(j.getAssociationGC().size()==0){
			System.out.println(j+" n'a aucun guide spirituel ou croyant sur son champ");
			ViewJouer.LabelSystem.setTexte(j+" n'a aucun guide spirituel ou croyant sur son champ");
			return;
		}
		ArrayList<CarteAction> tous=new ArrayList<CarteAction>();
		tous.addAll(j.champGuide());
		tous.addAll(j.champGuide());
		CarteAction choix=j.ChoisirUneCard(tous);
			if(choix instanceof CarteCroyant){
				j.sacrifierCroyant((CarteCroyant) choix, partie);
				System.out.println("La capacite de la carte Croyant"+choix.toString()+"peut etre utilise sans sacrifier cette carte");
				ViewJouer.LabelSystem.setTexte("La capacite de la carte Croyant"+choix.toString()+"peut etre utilise sans sacrifier cette carte");
			}else{
				j.sacrifierGuideSpirituel((GuideSpirituel)choix,partie);
				System.out.println("La capacite de la carte GuideSpirituel"+choix.toString()+"peut etre utilise sans sacrifier cette carte");
				ViewJouer.LabelSystem.setTexte("La capacite de la carte GuideSpirituel"+choix.toString()+"peut etre utilise sans sacrifier cette carte");
			}
			int position=j.returnPositionCard(choix);
		    if(position==2){// Carte choisi dans les guide utilisé
				GuideSpirituel choixG=(GuideSpirituel)choix;
				j.getAssociationGC().put(choixG, choixG.getCroyantRattache());
			}else if(position==3){//Carte choisi dans croyant associé
				Iterator<ArrayList<CarteCroyant>> it=j.getAssociationGC().values().iterator();
				while(it.hasNext()){
					ArrayList<CarteCroyant> listCroyant=it.next();
					Iterator<CarteCroyant> itA=listCroyant.iterator();
					while(itA.hasNext()){
						if(itA.next()==choix){
							listCroyant.add((CarteCroyant) choix);
						}
					}
				}
			}
			choix=j.ChoisirUneCard(tous);
		}
	/**
	 * Pour utiliser la fonction particulere de carte influenceJour.
	 * @param j Joueur qui utilise cette carte.
	 * @param ca La carte que l'on va arreter.
	 */
	public void influenceJourSacrifier(Joueur j,CarteAction ca){
		System.out.println(j+ " a utilise DeusEx InfluenceJour !");
		ViewJouer.LabelSystem.setTexte(j+ " a utilise DeusEx InfluenceJour !");
		if(ca.getOrigine()=="Nuit"|| ca.getOrigine()=="Neant"){
			ca.setInteruupted(true);
			System.out.println("La capacite de"+ca.getName()+"a ete annule");
			ViewJouer.LabelSystem.setTexte("La capacite de"+ca.getName()+"a ete annule");
		}
	}
	/**
	 * Pour utiliser la fonction particulere de carte influenceNuit.
	 * @param j Joueur qui utilise cette carte.
	 * @param ca La carte que l'on va arreter.
	 */
	public void influenceNuitSacrifier(Joueur j,CarteAction ca){
		System.out.println(j+ " a utilise DeusEx InfluenceNuit !");
		ViewJouer.LabelSystem.setTexte(j+ " a utilise DeusEx InfluenceNuit !");
		if(ca.getOrigine()=="Jour"|| ca.getOrigine()=="Neant"){
			ca.setInteruupted(true);
			System.out.println("La capacite de"+ca.getName()+"a ete annule");
			ViewJouer.LabelSystem.setTexte("La capacite de"+ca.getName()+"a ete annule");
		}
	}
	/**
	 * Pour utiliser la fonction particulere de carte influenceNeant.
	 * @param j Joueur qui utilise cette carte.
	 * @param ca La carte que l'on va arreter.
	 */
	public void influenceNeantSacrifier(Joueur j,CarteAction ca){
		System.out.println(j+ " a utilise DeusEx InfluenceNeant !");
		ViewJouer.LabelSystem.setTexte(j+ " a utilise DeusEx InfluenceNeant !");
		if(ca.getOrigine()=="Nuit"|| ca.getOrigine()=="Jour"){
			ca.setInteruupted(true);
			System.out.println("La capacite de"+ca.getName()+"a ete annule");
			ViewJouer.LabelSystem.setTexte("La capacite de"+ca.getName()+"a ete annule");
		}
	}
	/**
	 * Pour utiliser la fonction particulere de carte influenceNull.
	 * @param j Joueur qui utilise cette carte.
	 * @param ca La carte que l'on va arreter.
	 */
	public void influenceNullSacrifier(CarteAction ca,Joueur j){
		System.out.println(j+ " a utilise DeusEx InfluenceNull !");
		ViewJouer.LabelSystem.setTexte(j+ " a utilise DeusEx InfluenceNull !");
			ca.setInteruupted(true);
			System.out.println("La capacite de"+ca.getName()+"a ete annule");
		ViewJouer.LabelSystem.setTexte("La capacite de"+ca.getName()+"a ete annule");
	}
	/**
	 * Pour utiliser la fonction particulere de carte transe.
	 * @param j Joueur qui utilise cette carte.
	 * @param ca La carte que l'on va sacrifier.
	 * @param partie
	 */
	public void transeSacrifier(CarteAction ca,Joueur j,Partie partie) throws IOException{
		System.out.println(j+"a utilise DeusEx Transe a la carte"+ca);
		ViewJouer.LabelSystem.setTexte(j+"a utilise DeusEx Transe a la carte"+ca);
		ca.setInteruupted(true);// L'effect de carte n'est pas effectué
		
		if(ca instanceof CarteCroyant){//Croyant
			// Ajouter 2 fois la carte
		ArrayList<CarteCroyant> toList=new ArrayList<CarteCroyant>();
		toList.add((CarteCroyant) ca);
		toList.add((CarteCroyant) ca);
		j.getAssociationGC().put(null,toList);		
		j.sacrifierCroyant((CarteCroyant) ca, partie);
		System.out.println("La carte Croyant"+ca.toString()+"a ete sacrifie par joueur"+j.toString());
			ViewJouer.LabelSystem.setTexte("La carte Croyant"+ca.toString()+"a ete sacrifie par joueur"+j.toString());
		}
		else if(ca instanceof GuideSpirituel){//Guide
			ArrayList<CarteCroyant> toList=new ArrayList<CarteCroyant>();
			j.getAssociationGC().put((GuideSpirituel) ca, toList);
			j.getAssociationGC().put((GuideSpirituel) ca, toList);
			j.sacrifierGuideSpirituel((GuideSpirituel) ca, partie);
			System.out.println("La carte GuideSpirituel"+ca.toString()+"a ete sacrifie par joueur"+j.toString());
			ViewJouer.LabelSystem.setTexte("La carte GuideSpirituel"+ca.toString()+"a ete sacrifie par joueur"+j.toString());
		}
		else if(ca instanceof DeusEX){//DeusEX
			j.getCarte().add(ca);
			j.utiliserCarteAction(ca, partie);
			System.out.println("La carte DeusEx"+ca.toString()+"a ete utilise par joueur"+j.toString());
			ViewJouer.LabelSystem.setTexte("La carte DeusEx"+ca.toString()+"a ete utilise par joueur"+j.toString());
		}
	}
	/**
	 * Pour utiliser la fonction particulere de carte transe.
	 * @param j Joueur qui utilise cette carte.
	 * @param ca La carte que l'on va influencer.
	 */
	public void miroirSacrifier(Joueur j,CarteAction ca){
		System.out.println(j+"a utilise DeusEx Miroir a la carte"+ca);
		ViewJouer.LabelSystem.setTexte(j+"a utilise DeusEx Miroir a la carte"+ca);

	}
	/**
	 * Pour utiliser la fonction particulere de carte bouleversemen.
	 * @param j Joueur qui utilise cette carte.
	 * @param partie
	 */
	public void bouleversementSacrifier(Joueur j,Partie partie){
		partie.ajouterPointAction(j.lancerDeCosmogonie(partie));
		System.out.println(j+ "a utilise DeusEx Bouleversement, il relance le De Cosmogonie !");
		ViewJouer.LabelSystem.setTexte(j+ "a utilise DeusEx Bouleversement, il relance le De Cosmogonie !");
	}
	/**
	 * Pour utiliser la fonction particulere de carte inquisition.
	 * @param j Joueur qui utilise cette carte.
	 * @param partie
	 */
	public void inquisitionSacrifier(Joueur j,Partie partie) throws IOException{
		System.out.println(j+"a utilise DeusEx Inquisition,le duel commence!");
		ViewJouer.LabelSystem.setTexte(j+"a utilise DeusEx Inquisition,le duel commence!");
		GuideSpirituel g1=j.choisirUneGuideSpirituelAssocie();
		GuideSpirituel g2=j.choisirGuideSpirituelDautreJoueur(j.ChoisirUnJoueur(partie));
		if(g1==null || g2==null){
			return;
		}
		Joueur jC = null;
		Iterator<Joueur> it=partie.getJoueur().iterator();
		while(it.hasNext()){
			Joueur jI=it.next();
			if(jI.champGuide().contains(g2)){
				jC=jI;
			}
		}
		
		String face=j.lancerDeCosmogonie(partie);
		if(face=="Jour"){
			jC.sacrifierGuideSpirituel(g2, partie);
			System.out.println(j.toString()+"a gagne le duel!");
			ViewJouer.LabelSystem.setTexte(j.toString()+"a gagne le duel!");
			System.out.println(jC.toString()+"sacrifie sa carte GuideSpirituel"+g2.toString());
			ViewJouer.LabelSystem.setTexte(jC.toString()+"sacrifie sa carte GuideSpirituel"+g2.toString());
		}else if(face=="Nuit"){
			j.sacrifierGuideSpirituel(g1, partie);
			System.out.println(jC.toString()+"a gagne le duel!");
			ViewJouer.LabelSystem.setTexte(jC.toString()+"a gagne le duel!");
			System.out.println(j.toString()+"sacrifie sa carte GuideSpirituel"+g1.toString());
			ViewJouer.LabelSystem.setTexte(j.toString()+"sacrifie sa carte GuideSpirituel"+g1.toString());
		}else{
			System.out.println("Ils font match nul!");
			ViewJouer.LabelSystem.setTexte("Ils font match nul!");
		}
	}
	/**
	 * Certaines carte deusEX peut interrupter l'utilisation d'autres cartes.
	 * Cette methode est cree pour realiser la fonction de interrupter. 
	 * @param j Joueur qui utlise cette carte
	 * @param ca La carte que l'on interrupte
	 * @param partie
	 * @throws IOException
	 */
	public void interrupter(Joueur j, CarteAction ca,Partie partie) throws IOException{
	    if(this.getId()==9){
			this.influenceJourSacrifier(j,ca);
		}
		else if(this.getId()==10){
			this.influenceNuitSacrifier(j,ca);
		}
		else if(this.getId()==11){
			this.influenceNeantSacrifier(j,ca);
		}
		else if(this.getId()==12 || this.getId()==13){
			this.influenceNullSacrifier(ca,j);
		}
		else if(this.getId()==14){
			this.transeSacrifier(ca, j, partie);
		}
		else if(this.getId()==15){
			this.miroirSacrifier(j, ca);
		}
	}
	
	/**
	 * Pour realiser l'utilisation de carte deusEX
	 * @param j Joueur qui utilise cette carte
	 * @param partie
	 * @param ca la carte que l'on utilise.
	 * @throws IOException
	 */
	public void sacrifier(Joueur j,Partie partie,CarteAction ca) throws IOException{
		if(this.getId()==0 || this.getId()==1){
			this.colereDevineSacrifier(j, partie);
		}
		else if(this.getId()==2){
			this.staseSacrifier(j);
		}
		else if(this.getId()==3){
			this.ordreCelesteSacrifier(j, partie);
		}
		else if(this.getId()==4){
			this.fourberieSacrifier(j, partie);
		}
		else if(this.getId()==5){
			this.diversionSacrifier(j, partie);
		}
		else if(this.getId()==6){
			this.concentrationSacrifier(j, partie);
		}
		else if(this.getId()==7){
			this.trouNoirSacrifier(partie);
		}
		else if(this.getId()==8){
			this.phoenixSacrifier(j, partie);
		}
		
		else if(this.getId()==16){
			this.bouleversementSacrifier(j, partie);
		}
		else if(this.getId()==17){
			this.inquisitionSacrifier(j, partie);
		}

	}
	/**
	 * Pour montrer l'utilisation de cette carte.
	 */
	public String toString(){
		return new String(
				"-----------------------------------------\r\n"+
				"Deus EX  "+this.nom+"\r\n"
				+"Origine:"+this.origine+"\r\n"
		        +this.description+"\r\n"	
		        +"-----------------------------------------\r\n"
		        );
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	
	
}
