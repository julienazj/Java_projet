package Carte;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import GUI.PButton;
import GUI.ViewJouer;
import Joueur.Joueur;
import Joueur.JoueurPhysique;
import Partie.Partie;

/**
 * la classe Apocalypse represente les cartes Apocalypse que l'on peut utiliser dans le jeu.
 * dans cette classe, nous avons realise les fonctions fondamentales de cette type de carte.
 * @author Siyuan
 * @author Zijie
 *
 */
public class Apocalypse extends CarteAction {

	private String origine;
	public final static String[] Origines={"Jour","Nuit","Neant","Sans"};
	public final static int[] valeur= {0,1,2,3,3};
	private int id;

	/**
	 * Pour donner l'idendite a la carte Apocalypse
	 * @param id identite de chaque carte
	 */
	public void setValeur(int id){
		this.origine=Apocalypse.Origines[Apocalypse.valeur[id]];
		this.id=id;
	}
	
	public Apocalypse(int id){
		this.setValeur(id);
	}
	/**
	 * Pour realiser la fonction de carte Apocalyse.
	 * @param partie La partie du jeu.
	 * @throws IOException 
	 */
	public void utiliser(Partie partie) throws IOException {
		ArrayList<Integer> Nbrs=new ArrayList<Integer>();
		int duplicateMin=0;
		int duplicateMax=0;
		Iterator<Joueur> it=partie.getJoueur().iterator();
		while(it.hasNext()){
			Nbrs.add(it.next().getNombreCroyant());
		}
		//------Remove stace-------------//
		Iterator<Joueur> itJ=partie.getJoueur().iterator();
		while(itJ.hasNext()){
			Joueur j=itJ.next();
			Iterator<GuideSpirituel> itG=j.getAssociationGC().keySet().iterator();
			while(itG.hasNext()){
				GuideSpirituel g=itG.next();
				g.setStace(false);
				Iterator<CarteCroyant> itC=g.getCroyantRattache().iterator();
				while(itC.hasNext()){
					itC.next().setStace(false);
				}
			}
			
		}
		//Effect Apocalypse---------------------
		if (partie.getNumeroTour()==1){
			System.out.println("Apocalypse est sans effect car c'est premier tour !");
			ViewJouer.LabelSystem.setTexte("Apocalypse est sans effect car premier tour");
		}
		if(partie.getJoueur().size()>=4){// Plus de 4 joueurs
			
			int nbrPetit=Collections.min(Nbrs);
			for(int i=0;i<Nbrs.size();i++){
				if(Nbrs.get(i)==nbrPetit){
					duplicateMin++;
				}
			}			
			if(duplicateMin>1){
				System.out.println("La carte apocaplyps est défaussé sans effect car plusieur joueurs sont les derniers à égalité");
				ViewJouer.LabelSystem.setTexte("La carte apocaplyps est défaussé sans effect car plusieur joueurs sont les permiers à égalité");
			}else{
				int index=Nbrs.indexOf(nbrPetit);
				System.out.println(partie.getJoueur().get(index).toString() +" a été eliminé du jeu");
				ViewJouer.LabelSystem.setTexte(partie.getJoueur().get(index).toString() +" a été eliminé du jeu");
				if (partie.getJoueur().get(index)instanceof JoueurPhysique){
					partie.finDuJeu();
				}
				else {
					partie.getJoueur().remove(index);
					removeJoueurGraphique(index);
				}
				
			}
		}
		else if(partie.getJoueur().size()<4){// Moins de 4 jouers
			int nbrGrand=Collections.max(Nbrs);
			for(int i=0;i<Nbrs.size();i++){
				if(Nbrs.get(i)==nbrGrand){
					duplicateMax++;
				}
			}
			
			if(duplicateMax>1){
				System.out.println("La carte apocaplyps est défaussé sans effect car plusieur joueurs sont les permiers à égalité");
				ViewJouer.LabelSystem.setTexte("La carte apocaplyps est défaussé sans effect car plusieur joueurs sont les permiers à égalité");
			}else{
				Iterator<Joueur> itj=partie.getJoueur().iterator();
				while(itj.hasNext()){
					Joueur j=itj.next();
					if(j.getNombreCroyant()==nbrGrand){
						partie.finDuJeu(j);
					}
				}
				
			}			
		}
		
	}
/**
 * Pour effacer les cartes dans GUI
 * @param i identite de Joueur dans GUI
 */
	public void removeJoueurGraphique(int i){

		if(i==1){
			ViewJouer.ViewJouerEnCours.getDW1().removeAll();
		}
		if(i==2){
			ViewJouer.ViewJouerEnCours.getDW2().removeAll();
		}
		if(i==3){
			ViewJouer.ViewJouerEnCours.getDW3().removeAll();
		}
		if(i==4){
			ViewJouer.ViewJouerEnCours.getDN1().removeAll();
		}
		if(i==5){
			ViewJouer.ViewJouerEnCours.getDN2().removeAll();
		}
		if(i==6){
			ViewJouer.ViewJouerEnCours.getDN3().removeAll();
		}
	}
	/**
	 * Pour monter l'utilisation de carte Apocalypse
	 */
	public String toString(){
		return new String(
				"-----------------------------------------\r\n"+				
				"Apocalipse \r\n"
				+"Origine:"+this.origine+"\r\n"	
				+"-----------------------------------------\r\n"
		        );
	}

	public String getOrigine() {
		return this.origine;
	}

	public void setOrigine(String origne) {
		this.origine = origne;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
