package Bol;

import java.io.Serializable;

/**
 * Created by siyuan on 2016/12/25.
 */
public class BolString implements Serializable{
    public String msg;
    public String number;


    public BolString(){
        number="";
        msg="";
    }

    public synchronized void deposerString(String msg) throws InterruptedException{
        while(this.msg!=""){
            wait();
        }
        System.out.println("BolString-----------------"+msg);
        this.msg=msg;
        this.number="";
        notifyAll();
    }

    public synchronized String lireString(){
        while(this.msg==""){
            try {

                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        String msgRecu=msg;
        msg="";


        return msgRecu;
    }
}