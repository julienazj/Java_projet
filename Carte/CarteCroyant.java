package Carte;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import Evenements.PointRepaintEvent;
import GUI.ViewJouer;
import Joueur.Joueur;
import Joueur.JoueurPhysique;
import Partie.Partie;

/**
 * La classe CarteCroyant represente les cartes Croyant que l'on peut utiliser dans le jeu.
 * Dans cette classe, nous avons realise des fonctions fondamentales de cette type de carte et aussi les fonctions particulieres de chaque carte.
 * @author Siyuan
 * @author Zijie
 *
 */
public class CarteCroyant extends CarteAction  {

	protected  ArrayList<String> dogmes=new ArrayList<String>();
	protected  String nom;
	protected  int NombreCroyantRepresente;
	protected String origine;
	protected String description;
	private int id;
	
	
	public final static String Dogmes[] ={"Nature","Humain","Mystique","Symboles","Chaos"};
	public final static String Origines[]={"Jour","Nuit","Neant","Aube","Crepuscule"};
	
	// Carte croyant: nom,dogme1,dogme2,dogme3,origine,nombreCroyantRepresente,discription
	/**
	 * Pour identifier chaque carte.
	 */
	public static final Object valeur[][]={
			{"Moines",1,2,3,1,2,"Donne un point d'Action d'Origine Jour."},
			{"Moines",2,5,3,1,2,"Donne un point d'Action d'Origine Jour."},
			{"Moines",4,5,3,1,2,"Donne un point d'Action d'Origine Jour."},
			{"Moines",1,4,3,1,2,"Donne un point d'Action d'Origine Jour."},
			{"Moines",1,5,3,1,2,"Donne un point d'Action d'Origine Jour."},
			//4
			{"Travailleurs",2,5,4,1,2,"Empêche une Divinité possédant le Dogme Nature ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour."},
			{"Travailleurs",2,1,4,1,2,"Empêche une Divinité possédant le Dogme Nature ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour."},
			{"Travailleurs",2,5,3,1,2,"Vous piochez deux cartes au hasard dans la main d'une autre Divinité."},
			//7
			{"Ermite",1,3,5,1,1,"Impose le sacrifice d'un Croyant d'un autre joueur, qui choisit la carte. La capacité spéciale du sacrifice est jouée."},
			{"Ermite",1,3,4,1,1,"Impose le sacrifice d'un Croyant d'un autre joueur, qui choisit la carte. La capacité spéciale du sacrifice est jouée."},
			//9
			{"Integristes",1,2,5,1,1,"Impose le sacrifice d'un Guide Spirituel d'un autre joueur, qui choisit la carte. La capacité spéciale du sacrifice est jouée."},
			//10
			{"Guerriers Saints",1,3,4,1,4,"Un Guide Spirituel revient dans la main de sa Divinité. Ses Croyants reviennent au centre de la table."},
			//11
			{"Diplomates",2,4,5,1,4,"Un Guide Spirituel revient dans la main de sa Divinité. Ses Croyants reviennent au centre de la table."},
			//12
			{"Demons",1,2,3,2,2,"Donne un point d'Action d'Origine Nuit."},
			{"Demons",5,2,3,2,2,"Donne un point d'Action d'Origine Nuit."},
			{"Demons",3,4,5,2,2,"Donne un point d'Action d'Origine Nuit."},
			{"Demons",1,4,3,2,2,"Donne un point d'Action d'Origine Nuit."},
			{"Demons",1,5,3,2,2,"Donne un point d'Action d'Origine Nuit."},
			//17
			{"Alchimistes",1,5,4,2,2,"Empêche une Divinité possédant le Dogme Humain ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour de jeu."},
			{"Alchimistes",1,5,3,2,2,"Empêche une Divinité possédant le Dogme Humain ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour de jeu."},
			{"Alchimistes",1,5,4,2,2,"Vous piochez deux cartes au hasard dans la main d'une autre Divinité."},
			//20
			{"Vampire",1,2,4,2,1,"Impose le sacrifice d'un Croyant d'un autre joueur. Celui-ci choisit le sacrifié. La capacité spéciale du sacrifice est jouée."},
			{"Vampire",3,2,5,2,1,"Impose le sacrifice d'un Croyant d'un autre joueur. Celui-ci choisit le sacrifié. La capacité spéciale du sacrifice est jouée."},
			//22
			{"Lycanthropes",1,2,5,2,4,"Retirez tous les Croyants attachés à l'un des Guides Spirituels d'une autre Divinité. Les Croyants reviennent au milieu de la table, le Guide Spirituel est défaussé."},
			//23
			{"Illusionnistes",4,2,5,2,4,"Vous bénéficiez de la capacité spéciale de sacrifice d'une carte de Croyants appartenant à une autre Divinité. La carte en question reste en jeu."},
			//24
			{"Esprits",1,2,3,3,2,"Donne un point d'Action d'Origine Néant."},
			{"Esprits",5,2,3,3,2,"Donne un point d'Action d'Origine Néant."},
			{"Esprits",3,4,5,3,2,"Donne un point d'Action d'Origine Néant."},
			{"Esprits",1,4,3,3,2,"Donne un point d'Action d'Origine Néant."},
			{"Esprits",1,5,3,3,2,"Donne un point d'Action d'Origine Néant."},
			//29
			{"Alienes",5,2,4,3,2,"Empêche une Divinité possédant le Dogme Nature ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour de jeu."},
			{"Alienes",1,2,4,3,2,"Empêche une Divinité possédant le Dogme Nature ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour de jeu."},
			{"Alienes",5,1,3,3,2,"Empêche une Divinité possédant le Dogme Nature ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour de jeu."},
			//32
			{"Revenant",2,1,3,3,1,"Lancez le dé de Cosmogonie. Le tour se fini normalement, mais sous cette nouvelle influence."},
			{"Revolutionnaires",4,1,5,3,2,"Imposez le sacrifice d'une carte de Croyants à autant de Divinités que vous le voulez. Chaque Divinité choisit la carte à sacrifier. Les capacités spéciales sont jouées."},
			{"Nihillistes",4,3,5,3,4,"Jusqu'à la fin du tour, plus aucune Divinité ne reçoit de points d'Action."}
			//35			
			
	};
	
	private boolean stace;
	/**
	 * Pour donner les attributs particuliers de chaque carte de cette type.
	 * @param input Les attributs nous avons importe avant.
	 */
	public void setValeur(Object input[]){
		this.nom=(String) input[0];//Set nom
		for(int i=1;i<4;i++){//Set dogmes
			this.dogmes.add(CarteAction.Dogmes[(int)input[i]-1]);
		}
		this.NombreCroyantRepresente=(int) input[5];//Set nombre croyant
		this.origine=CarteAction.Origines[(int)input[4]-1];//Set origine
		this.description=(String) input[6];//set description
	}
	
	public CarteCroyant(int id) {
		this.setValeur(valeur[id]);
		this.id=id;
	}
	
/**
 * Pour creer un Croyant dans le centre de la table apres l'utilisation de CarteCroyant.
 * @param partie 
 */
	public void creerCroyant(Partie partie){
		partie.getCentreCommun().addCroyant(this);
		ViewJouer.CentreTable.repaintCentre();
		System.out.println("Une croyant "+this.toString()+ "\r\n a été créé au centre de la table");
	}
	/**
	 * Pour realise la fonction de sacrifice de la carte moines.
	 * @param j Joueur qui utilise cette carte.
	 */
	public void moinesSacrifier(Joueur j){
		System.out.println(j+ " a sacrifié moines");
		if(j.getPeutAddPteAction()==true){
		j.setPointActionJour(j.getPointActionJour()+1);
		System.out.println(j.toString() + "a récupéré 1 point d'action jour par sacrifice de Moines");
		j.notifyObservers(new PointRepaintEvent());//GUI mis à jour
		}else{
		System.out.println("Le point d'action n'est pas ajouté");
		ViewJouer.LabelSystem.setTexte("Le point d'action n'est pas ajouté");
		}
	}
	/**
	 * Pour realise la fonction de sacrifice de la carte travailleur1.
	 * @param j Joueur qui utilise cette carte.
	 */
	public void travailleur1Sacrifier(Joueur j,Partie partie){
	Joueur	jC=j.ChoisirUnJoueur(partie);
	System.out.println(j+ "a utlisé travailleur à "+ jC);
		ViewJouer.LabelSystem.setTexte(j+ "a utlisé travailleur à "+ jC);
	boolean containM=jC.getCarteDevinite().getDogmes().contains("Mystique");
	boolean containN=jC.getCarteDevinite().getDogmes().contains("Nature");
		if(containM||containN){
			jC.setPeutScrifierCroyant(false);
			System.out.println(jC.toString()+" ne peut pas sacrifier croyant durant ce tour !");
			ViewJouer.LabelSystem.setTexte(jC.toString()+" ne peut pas sacrifier croyant durant ce tour !");
			jC.notifyObservers(new PointRepaintEvent());
		}else{
			System.out.println("L'influence de travailleur n'est pas pris en compte car la Devinité ne possède pas de dogme  Mystique ou Nature !");
			ViewJouer.LabelSystem.setTexte("L'influence de travailleur n'est pas pris en compte car la Devinité ne possède pas de dogme  Mystique ou Nature !");
			return;
		}
	}
	/**
	 * Pour realise la fonction de sacrifice de la carte travailleur2.
	 * @param j Joueur qui utilise cette carte.
	 */
    public void travailleur2Sacrifier(Joueur j1,Partie partie){    	
    	Joueur j2=j1.ChoisirUnJoueur(partie);
    	System.out.println(j1+ "a utlisé travailleur à "+ j2);
		ViewJouer.LabelSystem.setTexte(j1+ "a utlisé travailleur à "+ j2);

    	if(j2.getCarte().size()>=2){
    		System.out.println(j2.toString()+" a pris 2 cartes de "+j1.toString());
			ViewJouer.LabelSystem.setTexte(j2.toString()+" a pris 2 cartes de "+j1.toString());
			for(int i=0;i<2;i++){
				int random=(int) (Math.random()*j2.getCarte().size());
				CarteAction ca=j2.getCarte().get(random);
				j2.getCarte().remove(ca);
				j1.getCarte().add(ca);
				if(j2 instanceof JoueurPhysique){			
					System.out.println("Votre carte :"+ca.toString()+" a été enlevé");
				}
				if(j1 instanceof JoueurPhysique){			
					System.out.println("Vous avez pris la carte : "+ca.toString());
				}
				}
			
		}else if(j2.getCarte().size()==1){//Si le joueur choisi n'a qu'un seule carte
			System.out.println(j2.toString()+" a pris 1 cartes de "+j1.toString());
			ViewJouer.LabelSystem.setTexte(j2.toString()+" a pris 1 cartes de "+j1.toString());
			int random=(int) (Math.random()*j2.getCarte().size());
			CarteAction ca=j2.getCarte().get(random);
			j2.getCarte().remove(ca);
			j1.getCarte().add(ca);
			if(j2 instanceof JoueurPhysique){			
				System.out.println("Votre carte :"+ca.toString()+" a été enlevé");
			}
			if(j1 instanceof JoueurPhysique){			
				System.out.println("Vous avez pris la carte : "+ca.toString());
			}
		}else{
			System.out.println(j2.toString()+" a pris 0 cartes de "+j1.toString());
		}
	}
    /**
     * Impose le sacrifice d'un Croyant d'un autre joueur, qui choisit la carte. La capacité spéciale du sacrifice est jouée.
     * @param j Joueur qui pose la carte
     * @param partie
     */
    public void ermiteSacrifier(Joueur j, Partie partie){
    	Joueur jC=j.ChoisirUnJoueur(partie);
    	System.out.println(j+" a utilisé Ermite à "+ jC);
		ViewJouer.LabelSystem.setTexte(j+" a utilisé Ermite à "+ jC);
    	if(jC.getAssociationGC().size()==0){
    		System.out.println("Joueur choisi n'a pas de guide à sacrifier !!");
			ViewJouer.LabelSystem.setTexte("Joueur choisi n'a pas de guide à sacrifier !!");

    		return;
    	}
		CarteCroyant c=jC.choisirUneCroyantAssocie();
		try {
			jC.sacrifierCroyant(c,partie);
			System.out.println(jC+ " a sacrifier le croyant "+ c);
			ViewJouer.LabelSystem.setTexte(jC+ " a sacrifier le croyant "+ c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
	 * Pour realise la fonction de sacrifice de la carte integristes.
	 * @param j Joueur qui utilise cette carte.
	 * @param partie partie.
	 */
    public void integristesSacrifier(Joueur j, Partie partie) throws IOException{
    	Joueur jC=j.ChoisirUnJoueur(partie);
    	System.out.println(j +"a utilisé integristes à "+ jC);
		ViewJouer.LabelSystem.setTexte(j +"a utilisé integristes à "+ jC);
    	if(jC.getAssociationGC().size()==0){
    		System.out.println(jC+" n'a pas de guide à sacrifier !!");
			ViewJouer.LabelSystem.setTexte(jC+" n'a pas de guide à sacrifier !!");
    		return;
    	}
		GuideSpirituel g= jC.choisirUneGuideSpirituelAssocie();
		jC.sacrifierGuideSpirituel(g,partie);
    }
    /**
	 * Pour realise la fonction de sacrifice de la carte guerriersSaints.
	 * @param j Joueur qui utilise cette carte.
	 * @param partie partie.
	 */
    public void guerriersSaintsSacrifier(Joueur j, Partie partie){
    	Joueur jC=j.ChoisirUnJoueur(partie);
    	System.out.println(j+" a utilisé Guerriers Saints à "+ jC);
		GuideSpirituel g= j.choisirGuideSpirituelDautreJoueur(jC);
		if(g==null){
			System.out.println(jC+" n'a pas de guide spirituel sur son champ");
			ViewJouer.LabelSystem.setTexte(jC+" n'a pas de guide spirituel sur son champ");
			return;
		}
		ArrayList<CarteCroyant> croyantAttache=g.getCroyantRattache();
		Iterator<CarteCroyant> it=croyantAttache.iterator();
		while(it.hasNext()){
			partie.getCentreCommun().addCroyant(it.next());
			croyantAttache.remove(it.next());
		}
		jC.getAssociationGC().remove(g);
		System.out.println(g +"est défaussé avec ses cartes croyants!");
		ViewJouer.LabelSystem.setTexte(g +"est défaussé avec ses cartes croyants!");
    }
    
    /**
     * Relancez le dé de Cosmogonie. Le tour se finit normalement sous la nouvelle influence.
     * @param j joueur qui relance le dé
     * @param partie
     */
    public void diplomatesSacrifier(Joueur j,Partie partie){
    	System.out.println(j + " a relancer le dé par l'effect de diplomates");
		ViewJouer.LabelSystem.setTexte(j + " a relancer le dé par l'effect de diplomates");
    	partie.ajouterPointAction(j.lancerDeCosmogonie(partie));
    }
    /**
	 * Pour realise la fonction de sacrifice de la carte demons.
	 * @param j Joueur qui utilise cette carte.
	 */
    public void demonsSacrifier(Joueur j){
    	
    	if(j.getPeutAddPteAction()==true){
    		j.setPointActionNuit(j.getPointActionNuit()+1);
    		System.out.println(j.toString() + " a récupéré 1 point d'action nuit par sacrifice de Demons");
			ViewJouer.LabelSystem.setTexte(j.toString() + " a récupéré 1 point d'action nuit par sacrifice de Demons");
    		}else{
    		System.out.println("Le point d'action n'est pas ajouté");
			ViewJouer.LabelSystem.setTexte("Le point d'action n'est pas ajouté");
    		}
    }
    /**
	 * Pour realise la fonction de sacrifice de la carte alchimistes.
	 * @param j Joueur qui utilise cette carte.
	 */
    public void alchimistesSacrifier(Joueur j,Partie partie){
    	Joueur jC=j.ChoisirUnJoueur(partie);
    	System.out.println(j +" a utilisé Alchimistes à "+jC);
    	boolean containM=jC.getCarteDevinite().getDogmes().contains("Humain");
    	boolean containN=jC.getCarteDevinite().getDogmes().contains("Mystique");
    		if(containM||containN){  			
    			jC.setPeutScrifierCroyant(false);
    			System.out.println(jC + "ne peut pas sacrifier croyant ce tour");
				ViewJouer.LabelSystem.setTexte(jC + "ne peut pas sacrifier croyant ce tour");
    		}else{
    			System.out.println("L'influence de Alchimistes n'est pas pris en compte car la Devinité ne possède pas de dogme  Mystique ou Nature !");
				ViewJouer.LabelSystem.setTexte("L'influence de Alchimistes n'est pas pris en compte car la Devinité ne possède pas de dogme  Mystique ou Nature !");
    			return;
    		}
    }
    /**
     * Impose le sacrifice d'un Croyant d'un autre joueur. Celui-ci choisit le sacrifié. La capacité spéciale du sacrifice est jouée.
     * @param j Joueur qui pose la carte
     * @param partie
     */
    public void vampireSacrifier(Joueur j, Partie partie){
    	Joueur jC=j.ChoisirUnJoueur(partie);
    	System.out.println(j+" a imposé "+jC +"  sacrifier un croyant");
    	if(jC.getAssociationGC().size()==0){
    		System.out.println("Joueur choisi n'a pas de croyant à sacrifier !!");
			ViewJouer.LabelSystem.setTexte("Joueur choisi n'a pas de croyant à sacrifier !!");
    		return;
    	}
		CarteCroyant c=jC.choisirUneCroyantAssocie();
		try {
			jC.sacrifierCroyant(c,partie);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    /**
	 * Pour realise la fonction de sacrifice de la carte lycanthropes.
	 * @param j Joueur qui utilise cette carte.
	 */
    public void lycanthropesSacrifier(Joueur jP,Partie partie){
    	System.out.println(jP + " a utilisé lycanthropes");
		ViewJouer.LabelSystem.setTexte(jP + " a utilisé lycanthropes");
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
		}
    	ArrayList<CarteCroyant> croyants=card.getCroyantRattache();
    	Iterator<CarteCroyant> itC=croyants.iterator();
    	//Croyant revient millieu table
    	while(itC.hasNext()){
    		CarteCroyant ca=itC.next();
    		card.getCroyantRattache().remove(ca);
    		partie.getCentreCommun().addCroyant(ca);
    		System.out.println(ca + " a été retiré de guide et est ajouté au centre de la table ");
			ViewJouer.LabelSystem.setTexte(ca + " a été retiré de guide et est ajouté au centre de la table ");
    	}
    	//Remove carte guide
        while(it.hasNext()){
  			Joueur j=it.next();
  			if(j.getAssociationGC().keySet().contains(card)){
  				j.getAssociationGC().remove(card);
  				System.out.println(card+ " est defaussé");
				ViewJouer.LabelSystem.setTexte(card+ " est defaussé");
  			}
  		}
        //Defausse add guide
    	partie.getPiocheCarteDefausse().getContenue().add(card);
    	
    }
    
    /**
     * Récupérez les points d'Action d'une Divinité n'ayant pas encore joué durant ce tour. Les points d'Action gardent leur Origine. La Divinité perd ses points.
     * @param j Joueur qui pose la carte
     * @param p partie
     */
    public void pillardSacrifier(Joueur j,Partie p){
    	Joueur jC=j.ChoisirUnJoueur(p.getJoueurPasJoue());
    	j.setPointActionJour(j.getPointActionJour()+jC.getPointActionJour());
    	j.setPointActionNeant(j.getPointActionNeant()+jC.getPointActionNeant());
    	j.setPointActionNuit(j.getPointActionNuit()+jC.getPointActionNuit());
    	System.out.println(j + " a utilisé Pillard !");
		ViewJouer.LabelSystem.setTexte(j + " a utilisé Pillard !");
    	System.out.println(j + " a récupérer ");
		ViewJouer.LabelSystem.setTexte(j + " a récupérer ");
    	System.out.println(jC.getPointActionJour()+" point action Jour");
		ViewJouer.LabelSystem.setTexte(jC.getPointActionJour()+" point action Jour");
    	System.out.println(jC.getPointActionJour()+" point action Nuit");
		ViewJouer.LabelSystem.setTexte(jC.getPointActionJour()+" point action Nuit");
    	System.out.println(jC.getPointActionJour()+" point action Neant");
		ViewJouer.LabelSystem.setTexte(jC.getPointActionJour()+" point action Neant");
    	jC.setPointActionJour(0);
    	jC.setPointActionNeant(0);
    	jC.setPointActionNuit(0);
    }
    
    /**
     * Vous bénéficiez de la capacité spéciale de sacrifice d'une carte de Croyants appartenant à une autre Divinité. La carte en question reste en jeu.
     * @param j Joueur qui pose la carte
     * @param partie
     */
    public void illusionnistesSacrifier(Joueur j, Partie partie){
    	System.out.println(j + "a utilisé illusionnistes");
		ViewJouer.LabelSystem.setTexte(j + "a utilisé illusionnistes");
    	Joueur jC=j.ChoisirUnJoueur(partie);
    	if(jC.avoirCroyantAssocie()){
    		CarteCroyant c=(CarteCroyant) j.ChoisirUneCard(jC.champCroyant());
        	c.setResteEnJeu(true);
        	try {
				j.sacrifierCroyant(c,partie);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
        	c.setResteEnJeu(false);
    	}   	 	
    }
    
    /**
     * Donne un point d'Action d'Origine Néant.
     * @param j joueur qui pose la carte
     */
    public void espritsSacrifier(Joueur j){
    	System.out.println(j + "a utilisé esprits");
		ViewJouer.LabelSystem.setTexte(j + "a utilisé esprits");
    	if(j.getPeutAddPteAction()==true){
    		j.setPointActionNeant(j.getPointActionNeant()+1);
    		System.out.println(j.toString() + "a récupéré 1 point d'action néant par sacrifice de Esprits");
			ViewJouer.LabelSystem.setTexte(j.toString() + "a récupéré 1 point d'action néant par sacrifice de Esprits");
    		}else{
    		System.out.println("Le point d'action n'est pas ajouté");
			ViewJouer.LabelSystem.setTexte(j + "a utilisé esprits");
    		}
    	
    }

    /**
     * Empêche une Divinité possédant le Dogme Nature ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour de jeu.
     * @param j
     * @param partie
     */
    public void alieneSacrifier(Joueur j,Partie partie){
    	Joueur jC=j.ChoisirUnJoueur(partie);
    	System.out.println(j + "a utilisé Aliene à "+ jC);
		ViewJouer.LabelSystem.setTexte(j + "a utilisé Aliene à "+ jC);
    	boolean containM=jC.getCarteDevinite().getDogmes().contains("Nature");
    	boolean containN=jC.getCarteDevinite().getDogmes().contains("Mystique");
    		if(containM||containN){
    			jC.setPeutScrifierCroyant(false);
    		}else{
    			System.out.println("L'influence de Aliene n'est pas pris en compte car la Devinité ne possède pas de dogme  Mystique ou Nature !");
				ViewJouer.LabelSystem.setTexte("L'influence de Aliene n'est pas pris en compte car la Devinité ne possède pas de dogme  Mystique ou Nature !");
    			return;
    		}
    }
   
    /**
     * Lancez le dé de Cosmogonie. Le tour se fini normalement, mais sous cette nouvelle influence.
     * @param j
     * @param partie
     */
    public void revenantSacrifier(Joueur j,Partie partie){
    	System.out.println(j+ "a utilisé Revenant, il relance le Dé Cosmogonie !");
		ViewJouer.LabelSystem.setTexte(j+ "a utilisé Revenant, il relance le Dé Cosmogonie !");
    	partie.ajouterPointAction(j.lancerDeCosmogonie(partie));
    }
    
    /**
     * Imposez le sacrifice d'une carte de Croyants à autant de Divinités que vous le voulez. Chaque Divinité choisit la carte à sacrifier. Les capacités spéciales sont jouées.
     * @throws IOException 
     */
    
    public void revolutionnairesSacrifier(Joueur j,Partie partie) throws IOException{
    	System.out.println(j +" a utilisé Revolutionnaire !");
		ViewJouer.LabelSystem.setTexte(j +" a utilisé Revolutionnaire !");
    	LinkedList<Joueur>list=j.choisirListJoueur(partie);
    	Iterator<Joueur> it=list.iterator();
    	while(it.hasNext()){
    		Joueur jC=it.next();
    		System.out.println(jC + " est imposé à sacrifier un croyant");
			ViewJouer.LabelSystem.setTexte(jC + " est imposé à sacrifier un croyant");
    		if(jC.avoirCroyantAssocie()){
    			jC.sacrifierCroyant(jC.choisirUneCroyantAssocie(),partie);
    		}
    	}
    }
    /**
	 * Pour realise la fonction de sacrifice de la carte nihillistes.
	 * @param j Joueur qui utilise cette carte.
	 */
    public void nihillistesSacrifier(Partie p){
    	System.out.println(" Effect de nihilliste est activé. Aucun joueur recoit pointe d'action durant ce tour");
		ViewJouer.LabelSystem.setTexte(" Effect de nihilliste est activé. Aucun joueur recoit pointe d'action durant ce tour");
    	Iterator<Joueur> it=p.getJoueur().iterator();
    	while(it.hasNext()){
    		Joueur j=it.next();
    		j.setPeutAddPteAction(false);
    	}
    	
    }
    /**
     * Pour realiser la fonction de sacrifice d'une carte Croyant.
     * @param j Joueur qui sacrifice la carte.
     * @param partie partie
     * @throws IOException IOException
     */
    public void sacrifier(Joueur j,Partie partie) throws IOException{
    	if(this.getId()>=0 && this.getId()<5){
			this.moinesSacrifier(j);
			}
			if(this.getId()>=5 && this.getId()<7){
				this.travailleur1Sacrifier(j,partie);
				}
			if(this.getId()==7){
				this.travailleur2Sacrifier(j,partie);
				}
			if(this.getId()>7 && this.getId()<10){
				this.ermiteSacrifier(j, partie);
				}
			if(this.getId()==10){
				this.integristesSacrifier(j, partie);
				}
			if(this.getId()==11){
				this.guerriersSaintsSacrifier(j, partie);
				}
			if(this.getId()==12){
				this.diplomatesSacrifier(j, partie);
				}
			if(this.getId()>12 && this.getId()<18){
				this.demonsSacrifier(j);
				}
			if(this.getId()>17 && this.getId()<21){
				this.alchimistesSacrifier(j, partie);
				}
			if(this.getId()>20 && this.getId()<23){
				this.vampireSacrifier(j, partie);
				}
			if(this.getId()==23){
				this.lycanthropesSacrifier(j, partie);
				}
			if(this.getId()==24){
				this.illusionnistesSacrifier(j, partie);
				}
			if(this.getId()>24 && this.getId()<30){
				this.espritsSacrifier(j);
				}
			if(this.getId()>29 && this.getId()<33){
				this.alieneSacrifier(j, partie);
				}
			if(this.getId()==33){
				this.revenantSacrifier(j, partie);
				}
			if(this.getId()==34){
				this.revolutionnairesSacrifier(j, partie);
				}
			if(this.getId()==35){
				this.nihillistesSacrifier(partie);
				}
			

    }
    /**
     * Pour montrer l'utilisation d'une carte croyant.
     */
	public String toString(){
		return new String(
				"-----------------------------------------\r\n"+
				"Croyant   "+this.nom+"\r\n"
				+"Origine:"+this.origine+"\r\n"
		       +"Dogmes : "+this.dogmes.get(0)+ " "+this.dogmes.get(1)+" "+this.dogmes.get(2)+"\r\n"
				+"Sacrifice : "
		        +this.description+"\r\n"
		        +"Nombre de Croyant représenté : "+this.NombreCroyantRepresente+"\r\n"
		        +"-----------------------------------------\r\n");
				
	}


	public ArrayList<String> getDogmes() {
		return dogmes;
	}


	public void setDogmes(ArrayList<String> dogmes) {
		this.dogmes = dogmes;
	}


	public String getNom() {
		return  nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getNombreCroyantRepresente() {
		return NombreCroyantRepresente;
	}


	public void setNombreCroyantRepresente(int nombreCroyantRepresente) {
		NombreCroyantRepresente = nombreCroyantRepresente;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getStace() {
		return stace;
	}

	public void setStace(boolean stace) {
		this.stace = stace;
	}




}