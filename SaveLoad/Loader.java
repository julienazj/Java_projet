package SaveLoad;

import Partie.Partie;

import java.io.*;

/**
 * Nous realisons la fonction de load dans cette classe.
 * Created by siyuan on 2016/12/13.
 */
public class Loader implements Serializable {
    private Partie partie;
/**
 * Methode pour realiser la fonction de load.
 * @return
 */
    public Partie load(){
        try {
            FileInputStream fileIn = new FileInputStream("./partie.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            partie = (Partie) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();
            return null;
        }catch(ClassNotFoundException c) {
            System.out.println("./partie.ser Not Found ");
            c.printStackTrace();
            return null;
        }
        return partie;

    }
}
