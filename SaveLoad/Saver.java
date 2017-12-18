package SaveLoad;

import Partie.Partie;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Nous realisons la fonction de save dans cette classe.
 * Created by siyuan on 2016/12/13.
 */
public class Saver implements Runnable,Serializable{
    private Partie partie;

    public Saver() {
        this.partie = Partie.PartieEnCours;
    }

    public void save(){
    Thread thread=new Thread(this);
    thread.start();

    }

    @Override
    public void run() {
        try {
            FileOutputStream fileOut = new FileOutputStream("./partie.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(partie);
            out.close();
            fileOut.close();

        }catch(IOException i) {
            i.printStackTrace();
            System.out.printf("NOT save");
        }
    }
}
