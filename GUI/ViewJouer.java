package GUI;

import Bol.BolInt;
import Bol.SaveLoadKeylistener;
import Bol.action;
import Evenements.PointRepaintEvent;
import Joueur.Joueur;
import Joueur.JoueurPhysique;
import Partie.Partie;
import SaveLoad.SaveLoadGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


/**
 * Created by siyuan on 2016/12/22.
 */
public class ViewJouer {

    public static JFrame frame;
    private BolInt bolInt;
    public static JPanel Jeux;
    public static ViewJouer ViewJouerEnCours;
    private JPanel DeviniteNorth;
    private JPanel DeviniteEast;
    public static PPanel Joueur;
       /* private PButton DEast1;
        private PButton Deast2;
       // private PButton Deast3;
        private JPanel DE1;//Panel devinité east1
        private JPanel DE2;
        private JPanel DE3;*/

    /* private PPoint PointDE1;
 private PPoint PointDE2;
 private PPoint PointDE3;*/
    private PPoint PointDW1;
    private PPoint PointDW2;
    private PPoint PointDW3;
    private PPoint PointDN1;
    private PPoint PointDN2;
    private PPoint PointDN3;
    private PPoint PointDJoueur;


    private PButton DNorth1;//Carte Devinité north
    private PButton DNorth2;
    private PButton DNorth3;
    private PButton DJoueur;
    private JPanel DeviniteWest;
    private JPanel DW1;
    private PButton DWest1;
    private JPanel DW2;
    private PButton DWest2;
    private JPanel DW3;
    private PButton DWest3;

    // public static CButton CarteEnCours;
    public static JPanel CartesCentre;
    public static CentreTablePanel CentreTable;

    private ChampPanel DJoueurChamp;
    private ChampPanel DW1Champ;
    private ChampPanel DW2Champ;
    private ChampPanel DW3Champ;
    private ChampPanel DN1Champ;
    private ChampPanel DN2Champ;
    private ChampPanel DN3Champ;

    public static Label LabelSystem;

    private JPanel DN1;
    private JPanel DN2;
    private JPanel DN3;

    private JPanel InfoSystem;

    private JPanel Info;
    public static Label LabelJoueur;
    public static JPanel ViewChoix;

    public static ArrayList<PButton> DButtons = new ArrayList<PButton>();

    public void createBoutonDevinite(Partie partie) {

        for (int i = 0; i < partie.getJoueur().size(); i++) {
            Joueur j = partie.getJoueur().get(i);
            if (j.getNumeroJoueur() == 0) {
                DJoueur = new PButton(j, bolInt);
            }
            if (j.getNumeroJoueur() == 1) {
                DWest1 = new PButton(j, bolInt);
            }
            if (j.getNumeroJoueur() == 2) {
                DWest2 = new PButton(j, bolInt);
            }
            if (j.getNumeroJoueur() == 3) {
                DWest3 = new PButton(j, bolInt);
            }
            if (j.getNumeroJoueur() == 4) {
                DNorth1 = new PButton(j, bolInt);
            }
            if (j.getNumeroJoueur() == 5) {
                DNorth2 = new PButton(j, bolInt);
            }
            if (j.getNumeroJoueur() == 6) {
                DNorth3 = new PButton(j, bolInt);
            }

        }

        for (int i = partie.getJoueur().size(); i < 7; i++) {
            if (i == 0) {
                DJoueur = new PButton();
            }
            if (i== 1) {
                DWest1 = new PButton();
            }
            if (i ==  2) {
                DWest2 = new PButton();
            }
            if (i ==  3) {
                DWest3 = new PButton();
            }
            if (i ==  4) {
                DNorth1 = new PButton();
            }
            if (i ==  5) {
                DNorth2 = new PButton();
            }
            if (i ==  6) {
                DNorth3 = new PButton();
            }

        }
    }

    public void createPointPanel(Partie partie) {
        for (int i = 0; i < partie.getJoueur().size(); i++) {
            Joueur j = partie.getJoueur().get(i);
            if (j.getNumeroJoueur()  == 0) {
                PointDJoueur = new PPoint(j);
                Joueur.add(PointDJoueur, FlowLayout.CENTER);

            }
               /* if(i==1){
                    PointDE1=new PPoint(j);
                    DE1.add(PointDE1,BorderLayout.CENTER);
                }
                if(i==2){
                    PointDE2=new PPoint(j);
                    DE2.add(PointDE2,BorderLayout.CENTER);
                }*/
            if (j.getNumeroJoueur() == 1) {
                PointDW1 = new PPoint(j);
                // DW1.add(PointDW1,BorderLayout.CENTER);
                DW1.setFocusable(true);
                DW1.add(PointDW1, BorderLayout.CENTER);
            }
            if (j.getNumeroJoueur() == 2) {
                PointDW2 = new PPoint(j);
                DW2.add(PointDW2, BorderLayout.CENTER);
            }
            if (j.getNumeroJoueur() == 3) {
                PointDW3 = new PPoint(j);
                DW3.add(PointDW3, BorderLayout.CENTER);
            }
            if (j.getNumeroJoueur() == 4) {
                PointDN1 = new PPoint(j);
                DN1.add(PointDN1, BorderLayout.CENTER);
            }
            if (j.getNumeroJoueur() == 5) {
                PointDN2 = new PPoint(j);
                DN2.add(PointDN2, BorderLayout.CENTER);
            }
            if (j.getNumeroJoueur() == 6) {
                PointDN3 = new PPoint(j);

                DN3.add(PointDN3, BorderLayout.CENTER);

            }
            if (i == 9) {
                // Deast3=new PButton(j);
            }
            j.notifyElement(new PointRepaintEvent());

        }

    }

    public void createChampPanel(Partie partie) {
        for (int i = 0; i < partie.getJoueur().size(); i++) {
            Joueur j = partie.getJoueur().get(i);
            if (j.getNumeroJoueur() == 0) {
                DJoueurChamp = new ChampPanel(j);
                Joueur.add(DJoueurChamp);
                j.setChampPanel(DJoueurChamp);
            }
            if (j.getNumeroJoueur() == 1) {
                DW1Champ = new ChampPanel(j);
                DW1.add(DW1Champ, BorderLayout.SOUTH);
                j.setChampPanel(DW1Champ);
            }
            if (j.getNumeroJoueur() == 2) {
                DW2Champ = new ChampPanel(j);
                DW2.add(DW2Champ, BorderLayout.SOUTH);
                j.setChampPanel(DW2Champ);
            }
            if (j.getNumeroJoueur() == 3) {
                DW3Champ = new ChampPanel(j);
                DW3.add(DW3Champ, BorderLayout.SOUTH);
                j.setChampPanel(DW3Champ);
            }
            if (j.getNumeroJoueur() == 4) {
                DN1Champ = new ChampPanel(j);
                DN1.add(DN1Champ,BorderLayout.EAST);
                j.setChampPanel(DN1Champ);
            }
            if (j.getNumeroJoueur() == 5) {
                DN2Champ = new ChampPanel(j);
                DN2.add(DN2Champ, BorderLayout.EAST);
                j.setChampPanel(DN2Champ);
            }
            if (j.getNumeroJoueur() == 6) {
                DN3Champ = new ChampPanel(j);
                DN3.add(DN3Champ, BorderLayout.EAST);
                j.setChampPanel(DN3Champ);
            }
            if (i == 7) {
                //  DEast1=new PButton(j,bolInt);
            }
            if (i == 8) {
                //Deast2=new PButton(j,bolInt);
            }
            if (i == 9) {
                // Deast3=new PButton(j);
            }
        }

    }

    public static void annuleAllBorder() {
        for (Component c : Jeux.getComponents()) {
            if (c instanceof JButton) {
                ((JButton) c).setBorder(BorderFactory.createEmptyBorder());
            }
        }
    }

    public void createCButton(Partie partie) {
           /* for (int i=0;i<partie.getJoueurPhysique().getCarte().size();i++){
                JoueurPhysique jp=partie.getJoueurPhysique();
                CButton cb=new CButton(jp.getCarte().get(i));
                this.Joueur.add(cb);

                cb.addActionListener(new action(cb){
                    public void run() {
                        int index=Partie.PartieEnCours.getJoueurPhysique().getCarte().indexOf(cb.getCa());
                        if(JoueurPhysique.ChoixListCarte==false) {
                            try {
                                bolInt.deposerInt(index);

                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }else {
                            CButton cButton= (CButton) this.getComponent();
                            cButton.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), null));
                            CButton.cButtonsChoisi.add(cButton);
                            Partie.bolString.number+=String.valueOf(index)+" ";

                        }
                    }
                });
            }*/
        Joueur.repaintCA();
    }

    public void createInfoSystem() {
        InfoSystem = new JPanel(new BorderLayout());
        Info.add(InfoSystem, BorderLayout.SOUTH);
    }

    public void createViewChoix() {
        ViewChoix = new JPanel();
        CardLayout cardLayout = new CardLayout();
        ViewChoix.setLayout(cardLayout);
        ViewChoix.add(new ViewNull(), "ViewNull");
        ViewChoix.add(new ViewChoisirOperation(), "ViewChoisirOperation");
        ViewChoix.add(new ViewValiderAnnuler(), "ViewValiderAnnuler");
        ViewChoix.add(new ViewOuiNon(), "ViewOuiNon");
        cardLayout.show(ViewChoix, "ViewNull");
        InfoSystem.add(ViewChoix, BorderLayout.SOUTH);

    }

    public void createLabelJoueur() {
        LabelJoueur = new Label();
        InfoSystem.add(LabelJoueur, BorderLayout.NORTH);
    }


    public ViewJouer(Partie partie, BolInt bolInt) {
        ViewJouerEnCours=this;
        this.bolInt = bolInt;
        frame = new JFrame();
        createBoutonDevinite(partie);
        demarrer(partie);
        frame.setVisible(true);
        //
        frame.setFocusable(true);
        frame.setContentPane(this.Jeux);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        KeyListener ks = new SaveLoadKeylistener();
        frame.addKeyListener(ks);
    }


    private void demarrer(Partie partie) {

        Jeux = new JPanel();
        Jeux.setLayout(new BorderLayout(0, 0));
        DeviniteNorth = new JPanel();
        DeviniteNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        DeviniteNorth.setBackground(new Color(-1579033));
        Jeux.add(DeviniteNorth, BorderLayout.NORTH);
        DeviniteNorth.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
        DN1 = new JPanel();
        DN1.setLayout(new BorderLayout(0, 0));
        DeviniteNorth.add(DN1);
        DNorth1.setText("");
        DN1.add(DNorth1, BorderLayout.WEST);

        DN2 = new JPanel();
        DN2.setLayout(new BorderLayout(0, 0));
        DeviniteNorth.add(DN2);
        DN2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
        DN2.add(DNorth2, BorderLayout.WEST);

        DN3 = new JPanel();
        DN3.setLayout(new BorderLayout(0, 0));
        DeviniteNorth.add(DN3);
        DN3.add(DNorth3, BorderLayout.WEST);

        DeviniteEast = new JPanel();
        DeviniteEast.setLayout(new GridBagLayout());
        Jeux.add(DeviniteEast, BorderLayout.EAST);
        //DE1 = new JPanel();
        //DE1.setLayout(new BorderLayout(0, 0));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        //DeviniteEast.add(DE1, gbc);
        //DEast1.setHideActionText(false);
        //  DEast1.setDisplayedMnemonicIndex(0);
        //DE1.add(DEast1, BorderLayout.WEST);
        // DE1Champ = new JPanel();
        //  DE1Champ.setLayout(new BorderLayout(0, 0));
        // DE1.add(DE1Champ, BorderLayout.EAST);
        // DE2 = new JPanel();
        // DE2.setLayout(new BorderLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        //DeviniteEast.add(DE2, gbc);
        //DE2.add(Deast2, BorderLayout.WEST);
        //DE2Champ = new JPanel();
        //DE2Champ.setLayout(new BorderLayout(0, 0));
        // DE2.add(DE2Champ, BorderLayout.EAST);

        //DE3 = new JPanel();
        // DE3.setLayout(new BorderLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        // DeviniteEast.add(DE3, gbc);
        //Deast3.setHorizontalTextPosition(4);
        //Deast3.setText("Button");
        // DE3.add(Deast3, BorderLayout.CENTER);
        // DE3Champ = new JPanel();
        // DE3Champ.setLayout(new BorderLayout(0, 0));
        // DE3.add(DE3Champ, BorderLayout.SOUTH);
        Joueur = new PPanel(partie.getJoueurPhysique(), DJoueur, bolInt);
        Jeux.add(Joueur, BorderLayout.SOUTH);
        Joueur.add(DJoueur);
        createCButton(partie);
        DeviniteWest = new JPanel();
        DeviniteWest.setLayout(new GridBagLayout());
        Jeux.add(DeviniteWest, BorderLayout.WEST);
        DeviniteWest.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
        DW1 = new JPanel();
        DW1.setLayout(new BorderLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DeviniteWest.add(DW1, gbc);
        DW1.add(DWest1, BorderLayout.NORTH);

        DW2 = new JPanel();
        DW2.setLayout(new BorderLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DeviniteWest.add(DW2, gbc);
        DWest2.setHorizontalAlignment(2);
        DW2.add(DWest2, BorderLayout.NORTH);

        DW3 = new JPanel();
        DW3.setLayout(new BorderLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DeviniteWest.add(DW3, gbc);
        DWest3.setText("");
        DW3.add(DWest3, BorderLayout.NORTH);

        Info = new JPanel();
        Info.setLayout(new BorderLayout(0, 0));
        Jeux.add(Info, BorderLayout.CENTER);
        LabelSystem = new Label();
        LabelSystem.setText("Label");
        Info.add(LabelSystem, BorderLayout.NORTH);


        CartesCentre = new JPanel(new BorderLayout());
        HText Histoire = new HText();
        CentreTable = new CentreTablePanel();
        // CarteEnCours = new CButton();
        //CarteEnCours.setText("");
        Info.add(CartesCentre, BorderLayout.CENTER);
        //CartesCentre.add(CarteEnCours,BorderLayout.WEST);
        CartesCentre.add(CentreTable, BorderLayout.EAST);
        CartesCentre.add(Histoire,BorderLayout.WEST);
        createPointPanel(partie);
        createInfoSystem();
        createLabelJoueur();
        createViewChoix();
        createChampPanel(partie);
    }

    public PPoint getPointDW1() {
        return PointDW1;
    }

    public JPanel getDW1() {
        return DW1;
    }

    public JPanel getDW2() {
        return DW2;
    }

    public JPanel getDW3() {
        return DW3;
    }

    public JPanel getDN1() {
        return DN1;
    }

    public JPanel getDN2() {
        return DN2;
    }

    public JPanel getDN3() {
        return DN3;
    }

    public JPanel getDeviniteNorth() {
        return DeviniteNorth;
    }

    public JPanel getDeviniteWest() {
        return DeviniteWest;
    }
}

