import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Brain_Host implements Runnable{
	private Graphics graphics;
	private Deck deck;
	private ServerSend server;
	private SendObject sendObject;
	private String p1Name, p2Name;
	private Thread graph, serv;
	private ActionListener hitAction, stayAction, continueAction, quitAction;

	public Brain_Host(String p1name){
		p1Name = p1name;
		p2Name = "p2";
		deck = new Deck(1, p1Name);
		deck.getBar().setEnabled(false);
		deck.makeDeck();
		deck.getBar().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				deck.dispose();
				serv.start();
				graph.start();
				sendObject.setOrder(deck.getShortOrder());
				server.setSendObject(sendObject);
			}});
		graphics = new Graphics(p1Name, p2Name, 100);
		sendObject = new SendObject(deck.getShortOrder(), (short)0, (short)0, p1Name);
		server = new ServerSend(sendObject.getOrder(), sendObject.getAction(), sendObject.getTurn(), p1Name);
		graph = new Thread(graphics);
		serv = new Thread(server);
		initializeActions();
	}

	private void startGame() throws InterruptedException{
		int turn=0;
		int plays=0;
		do{
			if(plays>4){
				deck.shuffle();
				this.graphics.setActionLabel("Shuffling Deck");
				this.sendObject.setOrder(deck.getShortOrder());
				server.getSendObject().setOrder(this.sendObject.getOrder());
				this.server.sendMessage("");
				
				Thread.sleep(1000);
				plays=0;
			}
			
			this.graphics.getP1().setCash(this.graphics.getP1().getCash()-5);
			this.graphics.setP1CashLabel();
			this.graphics.getP2().setCash(this.graphics.getP2().getCash()-5);
			this.graphics.setP2CashLabel();
			do{
				Thread.sleep(1000);
				//System.out.println("running");
				//Either the thread sleep or running work here, but if neither are here it doesn't work at all. Weird...
				if(server.getIsConnected()){
					this.sendObject.setTurn((short)1);
					this.server.setSendObject(sendObject);
					server.sendMessage("");
					turn=this.sendObject.getTurn();
					p2Name=server.getP2Name();
					graphics.setP2NameLabel(p2Name);
					
					
				}
			}while(turn==0);
			this.graphics.getP1().addCard(deck.getCard());
			this.graphics.getP1().getHand().get(0).flip();
			this.graphics.getP1().addCard(deck.getCard());
			this.graphics.getP2().addCard(deck.getCard());
			this.graphics.getP2().addCard(deck.getCard());
			this.graphics.getP2().getHand().get(0).flip();
			this.graphics.getP2().getHand().get(0).removeFlip();
			this.graphics.getP2().getHand().get(1).removeFlip();
			this.graphics.add(this.graphics.getHit());
			this.graphics.add(this.graphics.getStay());
			this.graphics.getHit().addActionListener(hitAction);
			this.graphics.getStay().addActionListener(stayAction);
			do{
				this.graphics.setActionLabel("Your Turn, Hit or Stay");	
				Thread.sleep(1000);
				turn = this.sendObject.getTurn();
			}while(turn==1);
			
			
			server.sendMessage("");
			this.graphics.remove(this.graphics.getHit());
			this.graphics.remove(this.graphics.getStay());
			this.graphics.getHit().removeActionListener(hitAction);
			this.graphics.getStay().removeActionListener(stayAction);
			this.graphics.setActionLabel("Waiting for opponents move");
			this.server.setSendObject(this.sendObject);
			
			
			
			do{
				this.sendObject= this.server.getSendObject();
				turn = this.sendObject.getTurn();
				Thread.sleep(2000);
			}while(turn == 2);
			
			this.sendObject.setAction(this.server.getSendObject().getAction());
			this.graphics.setActionLabel("Getting Opponents Information...");
			for(int i = 0; i<this.sendObject.getAction(); i++){
				this.graphics.getP2().addCard(deck.getCard());
				Thread.sleep(1000);
			}
			
			
			int p1Worth = this.graphics.getP1().getCardWorth();
			int p2Worth = this.graphics.getP2().getCardWorth();
			if(p1Worth>21){
				p1Worth=0;
			}
			if(p2Worth>21){
				p2Worth=0;
			}
			this.graphics.getP2().getHand().get(0).flip();
			this.graphics.getP2().getHand().get(0).addFlip();
			this.graphics.getP2().getHand().get(1).addFlip();
			if(p1Worth>p2Worth){
				this.graphics.setActionLabel(p1Name + " Wins!");
				this.graphics.getP1().setCash(this.graphics.getP1().getCash()+10);
				this.graphics.setP1CashLabel();
			}else if(p2Worth>p1Worth){
				this.graphics.setActionLabel(p2Name + " Wins!");
				this.graphics.getP2().setCash(this.graphics.getP2().getCash()+10);
				this.graphics.setP2CashLabel();
			}else{
				this.graphics.setActionLabel("Tie! Noone Wins!");
				this.graphics.getP1().setCash(this.graphics.getP1().getCash()+5);
				this.graphics.setP1CashLabel();
				this.graphics.getP2().setCash(this.graphics.getP2().getCash()+5);
				this.graphics.setP2CashLabel();
			}
			this.graphics.getHit().setText("Continue?");
			this.graphics.getStay().setText("Quit?");
			this.graphics.getHit().addActionListener(continueAction);
			this.graphics.getStay().addActionListener(quitAction);
			this.graphics.add(this.graphics.getHit());
			this.graphics.add(this.graphics.getStay());
			
			do{
				Thread.sleep(1000);
				turn = this.sendObject.getTurn();
			}while(turn == 3);
			
			
			this.sendObject.setAction((short) 0);
			server.setSendObject(this.sendObject);
			plays++;
		}while(server.getIsConnected());
	}
	private void initializeActions(){
		hitAction = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendObject.setAction((short)(sendObject.getAction()+1));
				graphics.getP1().addCard(deck.getCard());
				if(graphics.getP1().getCardWorth()>21){
					graphics.getHit().setEnabled(false);
				}
			}
		};
		stayAction = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendObject.setTurn((short)2);
				graphics.getHit().setEnabled(true);
			}
		};
		continueAction = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendObject.setTurn((short)0);
				graphics.getP1().clearHand();
				graphics.getP2().clearHand();
				graphics.getHit().setText("Hit");
				graphics.getStay().setText("Stay");
				graphics.getHit().removeActionListener(continueAction);
				graphics.getStay().removeActionListener(quitAction);
				graphics.remove(graphics.getHit());
				graphics.remove(graphics.getStay());


			}
		};
		quitAction = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				graphics.dispose();
				server.sendMessage("END");
				server.dispose();
				sendObject.setTurn((short)4);
			}
		};


	}

	public void run() {
		try {
			this.startGame();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		BlackJack jack = new BlackJack(true);
	}

	public static void main(String[] args){
		Brain_Host brain = new Brain_Host("Robbie");
		Thread t = new Thread(brain);
		t.start();
	}
}
