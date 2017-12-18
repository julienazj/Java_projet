package Partie;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Scanner;

import Bol.BolInt;
import Bol.BolString;
import Bol.action;
import Carte.*;
import Evenements.ChampRepaintEvent;
import Evenements.PointRepaintEvent;
import GUI.CButton;
import GUI.ViewJouer;
import Joueur.Joueur;
import Joueur.JoueurPhysique;
import Joueur.JoueurVirtuel;
import Pioche.PiocheCarteAction;
import Pioche.PiocheCarteDefausse;
import Pioche.PiocheCarteDevinite;
import SaveLoad.Loader;
import SaveLoad.SaveLoadGui;
import SaveLoad.Saver;
import Strategie.StrategieDifficile;
import Strategie.StrategieRandom;
/**
 * La classe partie represente la partie du jeu.
 * Elle contient les methode pour controler le jeu.
 * @author Zijie
 * @author Siyuan
 *
 */
public class Partie extends Observable implements Serializable {

    private ArrayList<Joueur> joueur;
    private CroyantPublique centreCommun;
    private PiocheCarteAction piocheCarteAction;
    private PiocheCarteDefausse piocheCarteDefausse;
    private PiocheCarteDevinite piocheCarteDevinite;
    private DeCosmogonie de;
    private int numeroTour;
    private int indexJoueurEnCours;
    private boolean partieTermine;
    private Saver saver;
    private Loader loader;
    public static Partie PartieEnCours;
    public CarteAction carteEnCours;
    public static BolInt bol;
    public static BolString bolString;
    private int difficulte;



    public Partie(BolInt bol, BolString bolString) {
        this.numeroTour = 0;
        this.bol = bol;
        this.bolString = bolString;
        this.setCentreCommun(new CroyantPublique());
        this.setDe(new DeCosmogonie());
        this.setPiocheCarteAction(new PiocheCarteAction());
        this.setPiocheCarteDefausse(new PiocheCarteDefausse());
        this.setPiocheCarteDevinite(new PiocheCarteDevinite());
        this.setJoueur(new ArrayList<Joueur>());
        this.setPartieTermine(false);
        Partie.PartieEnCours = this;
        this.saver=new Saver();
        this.loader=new Loader();
        carteEnCours = null;
    }
/**
 * Methode pour commencer le jeu.
 * @param jp 
 * @param nombreJoueurVirtuel
 * @throws IOException
 */
    public void commencerLaPartie(JoueurPhysique jp, int nombreJoueurVirtuel) throws IOException {
        this.difficulte=jp.choisirDiff();
        this.addJoueurs(jp, nombreJoueurVirtuel);
        this.distribuerCarteDevinite();
        this.distribuerCarteAction();
        ViewJouer viewJouer = new ViewJouer(this, bol);
        while (this.partieTermine == false) {
            this.tour();
        }
    }
/**
 * Methode pour distribuer les cartes d'actions a chaque joueur.
 */
    public void distribuerCarteAction() {
        for (Iterator<Joueur> it = this.getJoueur().iterator(); it.hasNext(); ) {
            Joueur j = it.next();

            for (int i = 0; i < 7; i++) {
                CarteAction ca = this.getPiocheCarteAction().trierCarteDessus();
                j.getCarte().add(ca);
            }
        }
    }
/**
 * Methode pour distribuer les cartes Devinites a chaque joueur.
 */
    public void distribuerCarteDevinite() {
        System.out.println("Distribution de carte Devinite...");
        for (Iterator<Joueur> it = this.getJoueur().iterator(); it.hasNext(); ) {
            Devinite devinite = this.getPiocheCarteDevinite().trierCarteDessus();
            Joueur j = it.next();
            j.setCarteDevinite(devinite);
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println(j.toString());
            System.out.println(j.getCarteDevinite().toString());
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        }
    }

    /**
     * Méthode qui ajoute des points act aux joueurs apreès lancer le dé
     *
     * @param face
     */
    public void ajouterPointAction(String face) {
        for (Iterator<Joueur> it = this.getJoueur().iterator(); it.hasNext(); ) {
            Joueur j = it.next();
            String origine = j.getCarteDevinite().getOrigine();
            switch (face) {
                case "Jour":


                    if (origine == "Jour") {
                        j.setPointActionJour(j.getPointActionJour() + 2);
                        System.out.println(j + " a recu 2 point action jour");
                    } else if (origine == "Aube") {
                        j.setPointActionJour(j.getPointActionJour() + 1);
                        System.out.println(j + " a recu 1 point action jour");
                    }
                case "Nuit":


                    if (origine == "Nuit") {
                        j.setPointActionNuit(j.getPointActionNuit() + 2);
                        System.out.println(j + " a recu 2 point action nuit");
                    } else if (origine == "Crepuscule") {
                        j.setPointActionNuit(j.getPointActionNuit() + 1);
                        System.out.println(j + " a recu 1 point action nuit");
                    }
                case "Neant":


                    if (origine == "Aube") {
                        j.setPointActionNeant(j.getPointActionNeant() + 1);
                        System.out.println(j + " a recu 2 point action neant");
                    } else if (origine == "Crepuscule") {
                        j.setPointActionNeant(j.getPointActionNeant() + 1);
                        System.out.println(j + " a recu 1 point action neant");
                    }
            }
        }
    }

    /**
     * Méthode qui exécute l'opération choisi par le joueur
     *
     * @param j
     * @param choixDeJoueur
     * @throws IOException
     */
    public void executerOperation(Joueur j, int choixDeJoueur) throws IOException {
        // Si la condition ne permet pas au joueur à choisir opération 3, Il
        // refait le choix

        boolean rechoise = j.cartesLibreAUtiliser(this).size() == 0 && choixDeJoueur == 3;
        while (rechoise) {
            if (j instanceof JoueurPhysique) {
                System.out.println("Votre carte ne vous permet pas de choisir cette opération. Veuillez rechoisir une autre Operation");
                ViewJouer.LabelJoueur.setTexte("Votre carte ne vous permet pas de choisir cette opération. Veuillez rechoisir une autre Operation");
            }
            choixDeJoueur = j.choisirOperation(this);
            rechoise = j.cartesLibreAUtiliser(this).size() == 0 && choixDeJoueur == 3;
        }
        // Executer operation
        if (choixDeJoueur == 1) {
            j.defausserCartes(j.choisirListCarte(), this);
        } else if (choixDeJoueur == 2) {
            j.completerMain(this);
        } else if (choixDeJoueur == 3) {
            j.utiliserCarteAction(j.ChoisirUneCard(j.cartesLibreAUtiliser(this)), this);
            if (j.decideSiSacrifier()) {
                //Add actlistener to buttons champs
                ArrayList<CarteAction> list = new ArrayList<CarteAction>();
                Iterator<GuideSpirituel> iterator = j.getAssociationGC().keySet().iterator();
                Component[] components = j.getChampPanel().getComponents();
                while (iterator.hasNext()) {
                    GuideSpirituel guideSpirituel = iterator.next();
                    list.add(guideSpirituel);
                    //Add action listener to buttons
                    for (int i = 0; i < components.length; i++) {
                        Component c = components[i];
                        if (c instanceof CButton) {
                            CButton cButton = (CButton) c;
                            if (cButton.getCa() == guideSpirituel) {
                                cButton.addActionListener(new action() {
                                    public void run() {
                                        try {
                                            Partie.bol.deposerInt(list.indexOf(guideSpirituel));
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }
                    }
                    Iterator<CarteCroyant> iterator1 = guideSpirituel.getCroyantRattache().iterator();
                    while (iterator1.hasNext()) {
                        CarteCroyant croyant = iterator1.next();
                        list.add(croyant);
                        //Add action listener to buttons
                        for (int i = 0; i < components.length; i++) {
                            Component c = components[i];
                            if (c instanceof CButton) {
                                CButton cButton = (CButton) c;
                                if (cButton.getCa() == croyant) {
                                    cButton.addActionListener(new action() {
                                        public void run() {
                                            try {
                                                Partie.bol.deposerInt(list.indexOf(croyant));
                                            } catch (InterruptedException e) {

                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
                CarteAction carteChoisi = j.ChoisirUneCard(list);
                if (carteChoisi instanceof CarteCroyant) {
                    j.sacrifierCroyant((CarteCroyant) carteChoisi, this);
                } else if (carteChoisi instanceof GuideSpirituel) {
                    j.sacrifierGuideSpirituel((GuideSpirituel) carteChoisi, this);
                }
            }
        }
        ViewJouer.Joueur.repaintCA();

    }
/**
 * Methode pour commercer un tour.
 * @throws IOException
 */
    public void tour() throws IOException {
        this.setNumeroTour(this.getNumeroTour() + 1);
        this.setChanged();
        this.notifyObservers(numeroTour);
        Joueur premierJoueur = this.getJoueur().get(0);
        this.ajouterPointAction(premierJoueur.lancerDeCosmogonie(this));
        for (int i = 0; i < this.joueur.size(); i++) {
            if (this.isPartieTermine()) {
                return;
            }


            Joueur jAct = this.joueur.get(i);
            System.out.println("------------------------------------");


			/*GuideSpirituel guideSpirituel=new GuideSpirituel(0);
            ArrayList<CarteCroyant> list=new ArrayList<CarteCroyant>();
			list.add(new CarteCroyant(0));
			jAct.getAssociationGC().put(guideSpirituel,list);
			jAct.notifyElement(new ChampRepaintEvent());*/


            this.setIndexJoueurEnCours(this.getJoueur().indexOf(jAct));
            System.out.println("Tour de " + jAct);
            ViewJouer.LabelSystem.setTexte("Tour de " + jAct.getCarteDevinite().getNom());
            if (jAct instanceof JoueurVirtuel) {
                System.out.println("Champs---------------------------------");
                if (jAct.champGuide().size() > 0) {
                    for (int j = 0; j < jAct.champGuide().size(); j++) {
                        System.out.println(jAct.champGuide().get(j));
                    }
                }
                if (jAct.champCroyant().size() > 0) {
                    for (int k = 0; k < jAct.champCroyant().size(); k++) {
                        System.out.println(jAct.champCroyant().get(k));
                    }
                }
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<>><<<<<<<<<<<<<<<<<<<<");
            }
            this.executerOperation(jAct, jAct.choisirOperation(this));//Le joueur joue
            if (jAct.choisirSiUtiliserCapaciteSpeciale()) {
                jAct.utiliserCapaciteSpeciale(this);
            }
            this.demanderSansOrigine();
            System.out.println("------------------------------------");

        }

        // Le dernier joueur devient le premier
        Joueur firstJoueur = this.getJoueur().get(0);
        this.getJoueur().remove(firstJoueur);
        this.getJoueur().add(this.getJoueur().size(), firstJoueur);
    }
/**
 * Methode pour obtenir les joueur qui ne joue pas
 * @return
 */
    public ArrayList<Joueur> getJoueurPasJoue() {
        ArrayList<Joueur> joueurPasJoue = new ArrayList<Joueur>();
        for (int i = this.getIndexJoueurEnCours() + 1; i < this.getJoueur().size(); i++) {
            joueurPasJoue.add(this.getJoueur().get(i));
        }
        return joueurPasJoue;
    }
/**
 * Methode pour afficher le vainqueur.
 * @param j
 */
    public void affichierGagnat(Joueur j) {

        System.out.println("Le gagnant est : " + j.toString());
        ViewJouer.LabelSystem.setTexte("Le gagnant est : " + j.toString());
    }

    public JoueurPhysique getJoueurPhysique() {
        Joueur j = null;
        Iterator<Joueur> it = this.getJoueur().iterator();
        while (it.hasNext()) {
            Joueur joueur = it.next();
            if (joueur instanceof JoueurPhysique) {
                j = joueur;
            }
        }
        return (JoueurPhysique) j;
    }
/**
 * Methode pour justifier le fin du jeu et afficher le vainqueur.
 * @param j
 * @throws IOException
 */
    public void finDuJeu(Joueur j) throws IOException {
        this.affichierGagnat(j);
        this.partieTermine = true;

        if (this.getJoueurPhysique().choisirSiRecommencerPartie()) {
            JoueurPhysique jp = new JoueurPhysique(0, this.bol);
            Partie partie = new Partie(this.bol, this.bolString);
            partie.commencerLaPartie(jp, jp.choisirNombreJoueur());
        }
    }
    /**
     * Methode de fin du jeu dans GUI
     * @throws IOException
     */
    public void finDuJeu( ) throws IOException {
        this.partieTermine = true;
        ViewJouer.LabelSystem.setTexte("Vous etes elimine ! Perdu XD");
        if (this.getJoueurPhysique().choisirSiRecommencerPartie()) {
            JoueurPhysique jp = new JoueurPhysique(0, this.bol);
            Partie partie = new Partie(this.bol, this.bolString);
            partie.commencerLaPartie(jp, jp.choisirNombreJoueur());
        }
    }

    public void lancerPartieSuivant() {

    }

    /**
     * Methode qui creer et ajouter les joueurs virtuel dans la partie.
     *
     * @param jp                  Joueur physique doit aussi etre ajoute
     * @param nombreJoueurVirtuel
     */
    public void addJoueurs(JoueurPhysique jp, int nombreJoueurVirtuel) {
        this.getJoueur().add(jp);
        if(difficulte==0) {
            for (int i = 1; i <= nombreJoueurVirtuel; i++) {
                JoueurVirtuel jv = new JoueurVirtuel(i, new StrategieRandom());
                this.getJoueur().add(jv);
            }
        }else{
            for (int i = 1; i <= nombreJoueurVirtuel; i++) {
                JoueurVirtuel jv = new JoueurVirtuel(i, new StrategieDifficile());
                this.getJoueur().add(jv);
            }
        }
        for (int i = 0; i < this.getJoueur().size(); i++) {
            this.addObserver(this.getJoueur().get(i));
        }
    }

    public void demanderInterruption(CarteAction ca, Joueur j) throws IOException {
        Iterator<Joueur> it = this.getJoueur().iterator();
        while (it.hasNext()) {
            Joueur jI = it.next();
            if (jI != j) {
                jI.decideSiIterruption(this, ca);
                if (ca instanceof GuideSpirituel) {
                    GuideSpirituel guide = (GuideSpirituel) ca;
                    jI.decideInterruptionRomtec(guide);
                }
            }
        }
    }

    public void demanderSansOrigine() throws IOException {
        Iterator<Joueur> it = this.getJoueur().iterator();
        while (it.hasNext()) {
            Joueur j = it.next();
            j.decideSiUtiliserSansOrigine(this);
        }
    }

    public void notifyElement(Object o){
        this.setChanged();
        this.notifyObservers(o);
    }

    public void updateNbrCroyantJoueur(){
        for (int i=0;i<this.getJoueur().size();i++){
            Joueur joueur=this.getJoueur().get(i);
            Iterator<CarteCroyant>it=joueur.champCroyant().iterator();
            int nbrCroyant=0;
            while (it.hasNext()){
                nbrCroyant+=it.next().getNombreCroyantRepresente();
            }
            joueur.setNombreCroyant(nbrCroyant);
        }
    }
    public ArrayList<Joueur> getJoueur() {
        return joueur;
    }

    public void setJoueur(ArrayList<Joueur> joueur) {
        this.joueur = joueur;
    }

    public CroyantPublique getCentreCommun() {
        return centreCommun;
    }

    public void setCentreCommun(CroyantPublique centreCommun) {
        this.centreCommun = centreCommun;
    }

    public PiocheCarteAction getPiocheCarteAction() {
        return piocheCarteAction;
    }

    public void setPiocheCarteAction(PiocheCarteAction piocheCarteAction) {
        this.piocheCarteAction = piocheCarteAction;
    }

    public PiocheCarteDefausse getPiocheCarteDefausse() {
        return piocheCarteDefausse;
    }

    public void setPiocheCarteDefausse(PiocheCarteDefausse piocheCarteDefausse) {
        this.piocheCarteDefausse = piocheCarteDefausse;
    }

    public PiocheCarteDevinite getPiocheCarteDevinite() {
        return piocheCarteDevinite;
    }

    public void setPiocheCarteDevinite(PiocheCarteDevinite piocheCarteDevinite) {
        this.piocheCarteDevinite = piocheCarteDevinite;
    }


    public DeCosmogonie getDe() {
        return de;
    }

    public void setDe(DeCosmogonie de) {
        this.de = de;
    }

    public int getNumeroTour() {
        return numeroTour;
    }

    public void setNumeroTour(int numeroTour) {
        this.numeroTour = numeroTour;

    }

    public int getIndexJoueurEnCours() {
        return indexJoueurEnCours;
    }

    public void setIndexJoueurEnCours(int indexJoueurEnCours) {
        this.indexJoueurEnCours = indexJoueurEnCours;
    }

    public boolean isPartieTermine() {
        return partieTermine;
    }

    public void setPartieTermine(boolean partieTermine) {
        this.partieTermine = partieTermine;
    }

    public Saver getSaver() {
        return saver;
    }

    public Loader getLoader() {
        return loader;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }
}
