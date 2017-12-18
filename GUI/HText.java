package GUI;

import Joueur.JoueurPhysique;
import Partie.Partie;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;

public class HText extends JPanel{

	public static HTextArea TextArea = new HTextArea(30, 30);




	public HText(){

		setLayout(new BorderLayout());
	      add(new JScrollPane(TextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

	  	OutputStream textAreaStream = new OutputStream() {
			public void write(int b) throws IOException {
		    TextArea.append(String.valueOf((char)b));
			}
			public void write(byte b[]) throws IOException {
			TextArea.append(new String(b));
			}

			public void write(byte b[], int off, int len) throws IOException {
			TextArea.append(new String(b, off, len));
			}
			};
			PrintStream myOut = new PrintStream(textAreaStream);
			System.setOut(myOut);
			System.setErr(myOut);

		TextArea.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					if (JoueurPhysique.ChoixListCarte==false && JoueurPhysique.ChoixListJoueur==false) {
                        try {
                            Partie.PartieEnCours.bol.deposerInt(TextArea.getLastWord());
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }else {
					    //get last line
                        Document document = TextArea.getDocument();
                        Element rootElem = document.getDefaultRootElement();
                        int numLines = rootElem.getElementCount();
                        Element lineElem = rootElem.getElement(numLines - 1);
                        int lineStart = lineElem.getStartOffset();
                        int lineEnd = lineElem.getEndOffset();
                        try {
                            String lineText = document.getText(lineStart, lineEnd - lineStart);
                            System.out.println("fezgerghrehrehtreherhre   "+ lineText.charAt(0));
                            Partie.PartieEnCours.bolString.deposerString(lineText);
                            JoueurPhysique.ChoixListCarte=false;
                            JoueurPhysique.ChoixListJoueur=false;
                        } catch (BadLocationException e1) {
                            e1.printStackTrace();
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});


	}



}


