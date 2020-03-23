import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


public class ProgressBar extends JFrame{

	private Button bar, end;
	
	public ProgressBar(){
		super("Loading Deck...");
		end = new Button("", 1, 50, 275,10);
		end.setEnabled(false);
		bar=new Button("Put Game Tip Here", 0, 50, 10,10);
		bar.setBackground(Color.cyan);
		
		this.add(end);
		this.add(bar);
		
		this.setLayout(null);
		this.setAlwaysOnTop(true);
		this.setSize(300, 100);
		this.setLocation(500, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);		
	}
	public Button getBar(){
		return this.bar;
	}
	public void setLength(int i, int numOfDecks){
		bar.setLocation(10, 10);
		this.bar.setSize((int)(270*(i/(52.0*numOfDecks))), 50);
		if(i==52*numOfDecks){
			bar.setText("Done, Click To Close");
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			bar.setEnabled(true);		}
		this.bar.revalidate();
		
		
			
	}
//	public static void main(String[] args){
//		ProgressBar b = new ProgressBar();
//		for(int i =0; i<100;i++){
//			b.setLength(i, 1);
//		}
//	}
	
	
	
}
