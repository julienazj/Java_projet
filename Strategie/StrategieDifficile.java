package Strategie;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import Carte.CarteAction;
import Carte.CarteCroyant;
import Carte.DeusEX;
import Carte.GuideSpirituel;
import GUI.ViewJouer;
import Joueur.Joueur;
import Joueur.JoueurVirtuel;
import Partie.Partie;
import Partie.CroyantPublique;
/**
 * La classe StrategieDifficile represente la strategie plus difficile de joueur virtuel
 * @author Siyuan
 * @author Zijie
 *
 */
public class StrategieDifficile implements Strategie,Serializable{

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
        boolean result=false;
        if (this.joueur.getCarteDevinite().getNom() == "Romtec") {
            result= false;
        }
        if(this.joueur.getCarteDevinite().getNom()=="Shingva") {
            for (int i = 0; i < Partie.PartieEnCours.getJoueur().size(); i++) {
                if (Partie.PartieEnCours.getJoueur().get(i).champGuide().size() > 0){
                    for (int j=0;j<Partie.PartieEnCours.getJoueur().get(i).champGuide().size();i++){
                        GuideSpirituel g=Partie.PartieEnCours.getJoueur().get(i).champGuide().get(i);
                        boolean containM = g.getDogmes().contains("Symboles");
                        boolean containN = g.getDogmes().contains("nature");
                        if (containM||containN){
                            result=true;
                        }
                    }
                }
            }
        }
        if(this.joueur.getCarteDevinite().getNom()=="Llewella"||this.joueur.getCarteDevinite().getNom()=="Killinstred") {

                ArrayList<Integer> Nbrs=new ArrayList<Integer>();
                int duplicateMin=0;
                int duplicateMax=0;
                Iterator<Joueur> it=Partie.PartieEnCours.getJoueur().iterator();
                while(it.hasNext()){
                    Nbrs.add(it.next().getNombreCroyant());
                }
                if(Partie.PartieEnCours.getJoueur().size()>=4){// Plus de 4 joueurs

                    int nbrPetit= Collections.min(Nbrs);
                    for(int i=0;i<Nbrs.size();i++){
                        if(Nbrs.get(i)==nbrPetit){
                            duplicateMin++;
                        }
                    }
                    if(duplicateMin>1){
                        result=false;
                    }else {
                        int index = Nbrs.indexOf(nbrPetit);
                        if (Partie.PartieEnCours.getJoueur().get(index) == this.joueur) {
                            result = false;
                        } else {
                            result = true;

                        }
                    }
                }
                else if(Partie.PartieEnCours.getJoueur().size()<4){// Moins de 4 jouers
                    int nbrGrand=Collections.max(Nbrs);
                    for(int i=0;i<Nbrs.size();i++){
                        if(Nbrs.get(i)==nbrGrand){
                            duplicateMax++;
                        }
                    }
                    if(duplicateMax>1){
                       result=false;
                    }else{
                        if (joueur.getNombreCroyant()==nbrGrand){
                            result=true;
                    }
                }
        }

        }
        if(this.joueur.getCarteDevinite().getNom()=="Drinded") {
            result=false;
        }
        if(this.joueur.getCarteDevinite().getNom()=="Gorpa") {
            result=generateBoolean();
        }
        if(this.joueur.getCarteDevinite().getNom()=="Pui-Tara") {
            CroyantPublique croyantPublique= Partie.PartieEnCours.getCentreCommun();
            ArrayList<CarteCroyant> centre=croyantPublique.getListCroyant();
            for (int i=0;i<centre.size();i++){
                if (centre.get(i).getOrigine()=="Jour"){
                    result=true;
                }
            }
        }
        if(this.joueur.getCarteDevinite().getNom()=="Yarstur") {
            CroyantPublique croyantPublique= Partie.PartieEnCours.getCentreCommun();
            ArrayList<CarteCroyant> centre=croyantPublique.getListCroyant();
            for (int i=0;i<centre.size();i++){
                if (centre.get(i).getOrigine()=="Neant"){
                    result=true;
                }
            }
        }
        if(this.joueur.getCarteDevinite().getNom()=="Gwenghelen") {
            if (Partie.PartieEnCours.getNumeroTour()==3){
                result=true;
            }
        }
            return result;
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
        GuideSpirituel result=null;
        ArrayList<Integer> listGuideNC=new ArrayList<Integer>();
        Iterator<GuideSpirituel> it=jC.getAssociationGC().keySet().iterator();
        while(it.hasNext()){
            listGuideNC.add(it.next().getNombreCroyantRatacche());
        }
        if(listGuideNC.size()==0){
            return null;
        }
        int nbrGrand=Collections.max(listGuideNC);
        Iterator<GuideSpirituel> it1=jC.getAssociationGC().keySet().iterator();
        while(it.hasNext()){
            GuideSpirituel guideSpirituel=it1.next();
            if (guideSpirituel.getNombreCroyantRatacche()==nbrGrand){
                result=guideSpirituel;
            }
        }
        return result;
    }

    @Override
    public Joueur ChoisirUnJoueur(List c) {
        Joueur result=null;
        List<Joueur>list= (List<Joueur>) c;
        ArrayList<Integer>integers=new ArrayList<Integer>();
        Iterator<Joueur> iterator=c.iterator();
        while (iterator.hasNext()){
            integers.add(iterator.next().getAssociationGC().size());
        }
        int maxSize=Collections.max(integers);
        Iterator<Joueur> iterator1=c.iterator();
        while (iterator1.hasNext()){
            Joueur j=iterator1.next();
            if (j.getAssociationGC().size()==maxSize){
                result= j;
            }
        }
        return result;
    }

    @Override
    public boolean decideSiSacrifier() {
        // TODO Auto-generated method stub
        if(this.joueur.getAssociationGC().size()==0){
            return false;
        }
        return this.generateBoolean();
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
