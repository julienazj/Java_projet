package Bol;


import GUI.ViewJouer;

import java.io.Serializable;

public class BolInt implements Serializable{
	public int msg;
	
	
	
	
	public BolInt(){
		msg=123;
	}
	
	public synchronized void deposerInt(int msg) throws InterruptedException{
		while(this.msg!=123){
			
				
				wait();
			
		
		}
		this.msg=msg;
		//System.out.println("bolInt:  "+msg+"..........................");
		notifyAll();
	}
	
	public synchronized int lireInt(){
		while(this.msg==123){
			try {
			
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		
		int msgRecu=msg;
		msg=123;

//		ViewJouer.annuleAllBorder();

		return msgRecu;
	}


	

	
	
}