import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Settings {
	BlackJack settings;
	ArrayList<Button> startCash;
	ArrayList<Button> difficulty;
	ArrayList<Button> decks;
	Button enter, barrier, p, d;
	JPanel dp, cash, deck, dif; 
	Label lplayer, lcash, lname, lpassword, lpassword2, ldifficulty;
	Field name, password, name2, password2;
	int storeCash, storeDecks, storeDifficulty;
	int state=0;
	ProgressBar bar;
	
	public Settings(){
		settings=new BlackJack(true);
		barrier = new Button("", 10, 900, 400, 0);
		barrier.setForeground(Color.WHITE);
		barrier.setEnabled(false);
		lplayer = new Label("Player 1", 75, 50, 10);
		lname= new Label("Name", 45, 50, 150);
		lcash = new Label("Starting Cash", 45, 50, 300);
		enter = new Button("Enter", 200, 100, 550, settings.getHeight()-150);
		name= new Field(10, 230);
		this.addButtons();
		this.setDefaults();
		
		settings.add(name);
		settings.add(barrier);
		settings.add(lcash);
		settings.add(enter);
		settings.add(lplayer);
		settings.add(lname);
	}
	
	private void addButtons(){
		dp= new JPanel();
		cash= new JPanel();
		deck = new JPanel();
		startCash= new ArrayList<Button>(0);
		decks = new ArrayList<Button>(0);

		dp.setLayout(new GridLayout(1, 2));
		dp.setSize(300, 100);
		dp.setLocation(this.settings.getWidth()-325, 10);
		p = new Button("Player 2", 100, 100, 0, 0);
		p.setEnabled(false);
		d = new Button("Dealer",100, 100, 0, 0);
		dp.add(d);
		d.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				p.setForeground(Color.black);
				d.setForeground(Color.red);
				setRightSideDealer();
				
			}});
		p.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				d.setForeground(Color.black);
				p.setForeground(Color.RED);
				setRightSidePlayer();
			}});
		dp.add(p);
		this.settings.add(dp);

		deck.setLayout(new GridLayout(1,5));
		deck.setSize(400,75);
		deck.setLocation(0, this.settings.getHeight()-120);
		for(int i =1; i<=5; i++){
			decks.add(new Button(Integer.toString(i)+" Decks", 0,0,0,0));
			int num =i;
			decks.get(i-1).addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					setDecks(num);
					resetDeckColor();
					decks.get(num-1).setForeground(Color.RED);
					settings.revalidate();
				}
			});
			deck.add(decks.get(i-1));
		}
		settings.add(deck);


		cash.setLayout(new GridLayout(1, 5));
		cash.setSize(350,100);
		cash.setLocation(25, 380);
		for(int i=1; i<=5; i++){
			startCash.add(new Button("$"+Integer.toString(i*100), 0,0,0,0));
			int num=i;
			startCash.get(i-1).addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					setCash(num);
					resetCashColor();
					startCash.get(num-1).setForeground(Color.RED);
					settings.revalidate();
				}
			});
			cash.add(startCash.get(i-1));
		}
		settings.add(cash);


	}
	public void setCash(int i){
		this.storeCash=i*100;
		System.out.println(this.storeCash);
	}
	public void setDecks(int i){
		this.storeDecks=i;
		System.out.println(this.storeDecks);
	}
	public void setDifficulty(int i){
		this.storeDifficulty=i+1;
		System.out.println(this.storeDifficulty);
	}
	public void resetDeckColor(){
		for(int i =0; i<this.decks.size();i++){
			this.decks.get(i).setForeground(Color.black);
		}
	}
	public void resetCashColor(){
		for(int i =0; i<this.startCash.size(); i++){
			this.startCash.get(i).setForeground(Color.BLACK);
		}
	}
	public void resetDifficultyColor(){	
		for(int i=0; i<this.difficulty.size();i++){
			this.difficulty.get(i).setForeground(Color.BLACK);
		}
	
	}
	public void setRightSidePlayer(){
	
		if(this.state==2){
			this.settings.remove(ldifficulty);
			this.settings.remove(dif);
		}
		if(this.state==1){
			return;
		}
		lpassword2= new Label("Password(Number 1-9)", 35, 410, 470);
		lpassword= new Label("Password(Number 1-9)", 35, 10, 470);
		lname=new Label("Name", 45, 450, 150);
		name2=new Field(420, 230);
		password2=new Field(410,540);
		password=new Field(10, 540);

		cash.setLocation(225,380);
		lcash.setLocation(250, 300);
		settings.add(password);
		settings.add(password2);
		settings.add(name2);
		settings.add(lpassword);
		settings.add(lname);
		settings.add(lpassword2);
		
		state=1;
		settings.setVisible(false);
		settings.setVisible(true);
		settings.revalidate();
	}
	public void setRightSideDealer(){
		if(state==1){
			settings.remove(password);
			settings.remove(password2);
			settings.remove(name2);
			settings.remove(lpassword2);
			settings.remove(lname);
			settings.remove(lpassword);
			
		}
		if(state==2){
			return;
		}

		ldifficulty= new Label("Difficulty", 45, 450, 150);
		dif = new JPanel();
		dif.setLayout(new GridLayout(1, 2));
		dif.setSize(300, 100);
		dif.setLocation(450, 225);
		difficulty=new ArrayList<Button>(0);
		for(int i =0; i<5; i++){
			difficulty.add(new Button(Integer.toString(i+1), 0,0,0,0));
			int num =i;
			this.difficulty.get(i).addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					resetDifficultyColor();
					difficulty.get(num).setForeground(Color.RED);
					setDifficulty(num);
					
				}});
			this.dif.add(difficulty.get(i));
		}
		
		//this.difficulty.get(storeDifficulty-1).setForeground(Color.red);
		settings.add(dif);
		settings.add(ldifficulty);
		cash.setLocation(25, 380);
		lcash.setLocation(50, 300);
		
		state=2;
		settings.setVisible(false);
		settings.setVisible(true);
		settings.revalidate();
	}
	public void setDefaults(){
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				//Deck doesnt work here either
				startGame();
			}});
		d.setForeground(Color.red);
		setRightSideDealer();
		storeCash=100;
		storeDecks=1;
		storeDifficulty=1;
		this.decks.get(0).setForeground(Color.red);
		this.startCash.get(0).setForeground(Color.RED);
		this.difficulty.get(0).setForeground(Color.RED);
	}
	public BlackJack getGetFrame(){
		return this.settings;
	}
	public void startGame(){
		if(state==2){
			//Deck d = new Deck(1, "");
			SwingUtilities.invokeLater(new Runnable(){
				public void run() {
					Table1 table = new Table1(name.getText(), storeDecks, storeCash, storeDifficulty);					
				}
			});
			
			this.settings.dispose();
			
		}
		else{
			System.out.println("Multiplayer is not ready yet, sorry for your inconvience");
		}
	}
}
