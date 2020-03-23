import java.awt.Component;
import java.util.ArrayList;
import java.util.Timer;

public class FPS extends Thread{
	
	private ArrayList<Component> refresh;
	private boolean running;
	
	
	public FPS(ArrayList<Component> refresh){
		super();
		this.refresh=refresh;
		running =true;
	}
	
	public void run(){
		while(running){
			for(int i =0; i<refresh.size();i++){
				this.getRefresh().get(i).revalidate();
			}
			try {
				this.sleep(16);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}
	public void setRefresh(ArrayList<Component> list){
		this.refresh=list;
	}
	public ArrayList<Component> getRefresh(){
		return this.refresh;
	}
	public void setRunning(boolean run){
		this.running=run;
	}
	public boolean getRunning(){
		return this.running;
	}
	
	public static void main(String[] args){
		BlackJack test = new BlackJack(true);
		ArrayList<Component> send = new ArrayList<Component>(0);
		send.add(test);
		FPS fps = new FPS(send);
		fps.start();
		for(int i=500; i<1000;i++){
			test.setSize(i,i);	
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		fps.setRunning(false);
		test.setSize(500,500);
		
	}
}
