package GUI;

import Bol.SaveLoadKeylistener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Utilities;
import java.awt.event.KeyListener;

/**
 * Created by Administrator on 2017/1/9.
 */
public class HTextArea extends JTextArea {
    private String lastWord;

    public HTextArea(int rows, int columns) {
        super(rows, columns);
        this.lastWord = null;
        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkLastWord();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkLastWord();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkLastWord();
            }
        });

        KeyListener ks = new SaveLoadKeylistener();
        this.addKeyListener(ks);
    }

    protected void checkLastWord() {
        try {
            int start = Utilities.getWordStart(this, this.getCaretPosition());
            int end = Utilities.getWordEnd(this, this.getCaretPosition());
            String text = this.getDocument().getText(start, end - start);
            lastWord=text;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getLastWord() {
        return Integer.parseInt(lastWord);
    }

    public void setLastWord(String lastWord) {
        this.lastWord = lastWord;
    }
}
