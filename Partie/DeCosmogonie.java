package Partie;

import java.io.Serializable;
/**
 * La classe DeCosmogonie represente le de cosmogonie dans le jeu.
 * Dans cette classe, on realise la fonction de de.
 * @author Siyuan
 * @author Zijie
 *
 */
public class DeCosmogonie implements Serializable {
     private String phase;
     /**
      * Methode pour lancer le de chaque dans chaque tour par l'utilisation de nombre random.
      */
     public void lancer(){
    	 int dicenum; 
         dicenum=(int)(Math.random()*3); 
         if(dicenum==0){
        	 this.phase="Jour";
         }else if(dicenum==1){
        	 this.phase="Nuit";
         }else{
        	 this.phase="Neant";
         }
     }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
}
