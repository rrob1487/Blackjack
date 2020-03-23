import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Table1 {
	private Label Action, player1, player2, p1Cash, p2Cash, betLabel;
	private Button Hit, Stay, raiseBet, lowerBet, lockInBet, next;
	private Player p1;
	private Dealer p2;
	private Deck deck;
	private BlackJack screen;
	private int bet, turn;
	private ActionListener hitAction, stayAction, raiseAction, lowerAction, lockAction, nextAction, playAgainAction;
	private ArrayList<Component> refresh;
	private FPS frame;
	private boolean nextText;
	//For Single Player
	public Table1(String name, int decks, int cash, int difficulty){
		//ProgressBar bar = new ProgressBar();
		
		deck=new Deck(decks, name);
		p1 = new Player(name, cash, 0, 780, 550);
		p2 = new Dealer(cash, difficulty, 780,125);
		player1=new Label(p1.getName(), 60, 460, 400);
		p1Cash= new Label("$"+p1.getCash(),40, 460, 450);
		player2= new Label(p2.getName(), 60, 460, 0);
		p2Cash= new Label("$"+p2.getCash(), 40, 460, 50);
		this.initializeAcitonListeners();
		screen = new BlackJack(false);

		//add all
		screen.add(p1Cash);
		screen.add(player1);
		screen.add(p2Cash);
		screen.add(player2);
		screen.add(p1);
		screen.add(p2);
		refresh= new ArrayList<Component>(0);
		refresh.add(p1Cash);
		refresh.add(player1);
		refresh.add(p2Cash);
		refresh.add(player2);
		refresh.add(p1);
		refresh.add(p2);
		refresh.add(screen);
		frame=new FPS(refresh);
		deck.makeDeck();
		deck.getBar().setEnabled(false);
		deck.getBar().addActionListener(new ActionListener(){		
			public void actionPerformed(ActionEvent e)
				{
					deck.dispose();
					screen.setVisible(true);
					runTurn0();
				}});
		
			

	}
	
	public void runTurn0(){
		if(!frame.getRunning()){
		frame.start();
		}

		turn = 0;
		
		//Get Initial Hand
		p1.addCard(deck.getCard());
		p1.addCard(deck.getCard());
		p2.addCard(deck.getCard());
		p2.addCard(deck.getCard());
		p1.getHand().get(0).flip();
		p2.getHand().get(0).flip();
		screen.repaint();
		this.setBet();




	}
	public void runTurn1(){
		turn=1;
		Action=new Label("Your Move", 70, 100, 500);
		Hit=new Button("Hit", 200,100, 100, 280);
		Hit.addActionListener(hitAction);
		Stay=new Button("Stay", 200,100,100, 380);
		Stay.addActionListener(stayAction);

		this.screen.add(Hit);
		this.screen.add(Stay);
		this.screen.add(Action);
		this.refresh.add(Hit);
		this.refresh.add(Stay);
		this.refresh.add(Action);
		this.frame.setRefresh(refresh);
	}
	public void runTurn2(){
		turn=2;
		Action.setText(p2.getName()+"'s Turn");
		Action.setLocation(100,310);
		nextText=false;
		this.next=new Button("Next", 100,50,100, 410);
		next.addActionListener(nextAction);
		this.screen.add(next);
		this.refresh.add(next);
		this.frame.setRefresh(refresh);
		while(p2.hit()){
			while(!nextText){
				this.delay(1);
			}
			nextText=false;
			p2.addCard(deck.getCard());
			Action.setText(p2.getName()+" Hits");
		}
		while(!nextText){
			this.delay(1);
		}
		nextText=false;
		Action.setText(p2.getName()+" Stays");
		while(!nextText){
			this.delay(1);
		}
		nextText=false;
		this.runWinner();
		
	}
	public void runWinner(){
		if(p1.getCardWorth()>21){
			for(int i=0;i<p1.getHand().size();i++){
				p1.getHand().get(i).setWorth(0);
			}
		}
		if(p2.getCardWorth()>21){
			for(int i = 0; i<p2.getHand().size();i++){
				p2.getHand().get(i).setWorth(0);
			}
		}
		p2.getHand().get(0).flip();
		for(int i =0; i<p2.getHand().size();i++){
			p2.getHand().get(i).addFlip();
		}
		if(p1.getCardWorth()>p2.getCardWorth()){
			p1.setCash(p1.getCash()+(2*bet));
			p1Cash.setText(Integer.toString(p1.getCash()));
			Action.setText(p1.getName()+" Wins!");
		}
		else if (p1.getCardWorth()<p2.getCardWorth()){
			p2.setCash(p2.getCash()+(2*bet));
			p2Cash.setText(Integer.toString(p2.getCash()));
			Action.setText(p2.getName()+" Wins!");
		}else{
			p2.setCash(p2.getCash()+bet);
			p1.setCash(p1.getCash()+bet);
			p1Cash.setText(Integer.toString(p1.getCash()));
			p2Cash.setText(Integer.toString(p2.getCash()));
			Action.setText("Tie");
		}
		next.setText("Continue?");
		next.removeActionListener(nextAction);
		next.addActionListener(playAgainAction);
		//give bets to winner
		//Continue?
	}
	public void setBet(){
		bet=5;
		betLabel=new Label("Bet: "+Integer.toString(bet), 70, 100,400);
		this.raiseBet=new Button("Raise Bet", 100, 100, 100, 500);
		this.raiseBet.addActionListener(raiseAction);
		this.lowerBet=new Button("Lower Bet", 100, 100,200,500);
		this.lowerBet.addActionListener(lowerAction);
		this.lockInBet=new Button("Okay!", 200,100,100,600);
		this.lockInBet.addActionListener(lockAction);

		screen.add(lowerBet);
		screen.add(lockInBet);
		screen.add(raiseBet);
		screen.add(betLabel);
		refresh.add(lowerBet);
		refresh.add(lockInBet);
		refresh.add(raiseBet);
		refresh.add(betLabel);
		frame.setRefresh(refresh);

	}
	public void delay(int i){
		try {
			TimeUnit.SECONDS.sleep(i);
		} catch (InterruptedException j) {
			j.printStackTrace();
		}
	}
	public void initializeAcitonListeners(){
		hitAction=new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				p1.addCard(deck.getCard());
				if(p1.isUnder()==false){
					Hit.setEnabled(false);
				}
			}
		};
		stayAction=new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				SwingWorker swing = new SwingWorker(){
					protected Object doInBackground() throws Exception {
						screen.remove(Hit);
						screen.remove(Stay);
						Thread.sleep(100);
						refresh.remove(Hit);
						refresh.remove(Stay);
						frame.setRefresh(refresh);
						Thread.sleep(500);
						runTurn2();
						return null;
					}
					
				};
				swing.execute();
			}
		};
		raiseAction=new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(bet+5<=p1.getCash()&&bet+5<=p2.getCash()){
					bet=bet+5;
					betLabel.setText("Bet: "+Integer.toString(bet));
				}
			}
		};
		lowerAction=new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(bet-5>=5&&bet-5<=p2.getCash()){
					bet=bet-5;
					betLabel.setText("Bet: "+Integer.toString(bet));
				}
			}
		};
		lockAction=new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				screen.remove(raiseBet);
				screen.remove(lowerBet);
				screen.remove(lockInBet);
				screen.remove(betLabel);
				refresh.remove(raiseBet);
				refresh.remove(lowerBet);
				refresh.remove(lockInBet);
				refresh.remove(betLabel);
				frame.setRefresh(refresh);
				p1.setCash(p1.getCash()-bet);
				p2.setCash(p2.getCash()-bet);
				p1Cash.setText("$"+p1.getCash());
				p2Cash.setText("$"+p2.getCash());
				screen.setVisible(false);
				screen.setVisible(true);
				runTurn1();
			}
		};
		nextAction= new ActionListener(){
			public void actionPerformed(ActionEvent e){
				nextText=true;
			}
		};
		playAgainAction= new ActionListener(){
			public void actionPerformed(ActionEvent e){
				SwingWorker swing = new SwingWorker(){
					protected Object doInBackground() throws Exception {
						p1.clearHand();
						p2.clearHand();
						screen.remove(Action);
						refresh.remove(Action);
						screen.remove(next);
						refresh.remove(next);
						screen.repaint();
						frame.setRefresh(refresh);
						return null;
					}
					
				};
				swing.execute();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				runTurn0();
				
			}
		};
	}
	public Deck getDeck(){
		return this.deck;
	}
}
