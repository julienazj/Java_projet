package Strategie;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Carte.CarteAction;
import Carte.CarteCroyant;
import Carte.DeusEX;
import Carte.GuideSpirituel;
import Joueur.Joueur;
import Joueur.JoueurVirtuel;
import Partie.Partie;
/**
 * La classe strategieRandom represente la strategie plus facile.
 * 
 * @author Zijie
 * @author Siyuan
 *
 */
public class StrategieRandom implements Strategie,Serializable{
	
	private JoueurVirtuel joueur;
	
	public int generateRandom(int lower,int higher){
		int random = (int)(Math.random() * (higher-lower)) + lower;
		return random;
	}
	
	public boolean generateBoolean(){
		boolean result;
		if(Math.random()>0.5){
			result=true;
		}
		else{
			result=false;
		}
		return result;
	}

	public int choisirOperation(Partie partie) throws IOException {
		if(this.joueur.cartesLibreAUtiliser(partie).size()>0){
			return 3;
		}
		return this.generateRandom(1, 2);
	}

	public boolean choisirSiUtiliserCapaciteSpeciale() {
		
		if (this.joueur.getCarteDevinite().getNom() == "Romtec") {
			return false;
		}
		if(this.joueur.isCapaciteUtilise()){
			return false;
		}
		return this.generateBoolean();
		
	}

	public LinkedList<CarteAction> choisirListCarte() throws IOException {
		int random=this.generateRandom(0,this.joueur.getCarte().size()-1);
		LinkedList<CarteAction> list= new LinkedList<CarteAction> ();
		for(int i=0;i<random;i++){
			list.add(this.joueur.getCarte().get(i));
		}
		return list;
	}


	public CarteAction choisirUneCarte() {
		return this.joueur.getCarte().get(this.generateRandom(0, this.joueur.getCarte().size()-1));
	}


	public Joueur ChoisirUnJoueur( Partie partie) {
		return partie.getJoueur().get(this.generateRandom(0, partie.getJoueur().size()-1));
	}

	
	public CarteCroyant choisirCroyantRattacher(ArrayList<CarteCroyant> cartePossible) {
	 int numero=this.generateRandom(0, cartePossible.size()-1);
	 CarteCroyant croyant=cartePossible.get(numero);
	 return croyant;
	}

	public void setJoueur(JoueurVirtuel j){
		this.joueur=j;
	}

	@Override
	public void decideSiIterruption(Partie partie, CarteAction ca) throws IOException {
		if(this.joueur.peutIterruption(ca).size()==0){
			return;
		}else{
			boolean choise=this.generateBoolean();
			if(choise){
				this.joueur.interrupter( (DeusEX) this.joueur.ChoisirUneCard(this.joueur.peutIterruption(ca)),ca, partie );
			}
		}
		
	}
	
	public void decideInterruptionRomtec(GuideSpirituel guide){
		if(this.joueur.getCarteDevinite().getNom()=="Romtec"){
			int choise=this.generateRandom(0, 1);
			if(choise==1){
			this.joueur.getCarteDevinite().romtecCapacite(guide);
			}
		}
	}
	@Override
	public LinkedList<Joueur> choisirListJoueur(Partie partie) throws IOException {
		LinkedList<Joueur> js= new LinkedList<Joueur>();
		js.addAll( partie.getJoueur());
		return js;
	}

	@Override
	public CarteAction ChoisirUneCard(List c) {
		if(c.size()==0){
			return null;
		}
		int random=this.generateRandom(0, c.size()-1);
		return (CarteAction) c.get(random);
	}


	public CarteCroyant choisirUneCroyantAssocie() {
		if(this.joueur.champCroyant().size()==0){
			return null;
		}
		ArrayList<CarteCroyant> croyant=new ArrayList<CarteCroyant>();
		Iterator<ArrayList<CarteCroyant>> it=this.joueur.getAssociationGC().values().iterator();
		while(it.hasNext()){
			Iterator<CarteCroyant> itC=it.next().iterator();
			while(itC.hasNext()){
				croyant.add(itC.next());
			}
		}
 		int result=this.generateRandom(0, croyant.size()-1);
 		return croyant.get(result);	
	}

	public GuideSpirituel choisirUneGuideSpirituelAssocie() {
		if(this.joueur.getAssociationGC().size()==0){
			System.out.println(this+" n'a pas de guide spirituel sur son champ");
			return null;
		}
		ArrayList<GuideSpirituel> guide=new ArrayList<GuideSpirituel>();
		Iterator<GuideSpirituel> it=this.joueur.getAssociationGC().keySet().iterator();
		while(it.hasNext()){
				guide.add(it.next());

		}
 		int result=this.generateRandom(0, guide.size()-1);
 		return guide.get(result);
	}

	@Override
	public GuideSpirituel choisirGuideSpirituelDautreJoueur(Joueur jC) {
	    ArrayList<GuideSpirituel> listGuide=new ArrayList<GuideSpirituel>();
		Iterator<GuideSpirituel> it=jC.getAssociationGC().keySet().iterator();
		while(it.hasNext()){
			listGuide.add(it.next());
		}
		if(listGuide.size()==0){
			return null;
		}
		int choise=this.generateRandom(0, listGuide.size()-1);
		return listGuide.get(choise);
	}

	@Override
	public Joueur ChoisirUnJoueur(List c) {
		int random=this.generateRandom(0, c.size()-1);
		return (Joueur) c.get(random);
	}

	@Override
	public boolean decideSiSacrifier() {
		// TODO Auto-generated method stub
		if(this.joueur.getAssociationGC().size()==0){
			return false;
		}
		return false;
	}

	@Override
	public void decideSiUtiliserSansOrigine(Partie partie) throws IOException{
		if(this.joueur.avoirCarteSansOrigine().size()>0){
			int choise=this.generateRandom(0, 1);		
			if(choise==1){
				this.joueur.utiliserCarteAction(this.ChoisirUneCard(this.joueur.avoirCarteSansOrigine()), partie);
			}
		}
		
	}
}
