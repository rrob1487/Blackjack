import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Player extends JPanel{
	protected ArrayList<Card> hand;
	protected String name;
	protected int password;
	protected int cash;
	protected GridLayout grid;
	protected Point p;
	
	public Player(String name, int cash, int password, int x, int y){
		super();
		this.setCash(cash);
		this.setPassword(password);
		this.setName(name);
		hand = new ArrayList<Card>(0);
		
		grid = new GridLayout(1,2);
		this.setLayout(grid);
		this.setLocation(x, y);
		this.setSize(0, 200);
		p = new Point(this.getLocation());
	}
	
	public void setCash(int i){
		this.cash=i;
	}
	public void setPassword(int i){
		this.password=i;
	}
	public void setName(String s){
		if(s.equals("")){
			s="Player1";
		}
		this.name=s;
	}
	public int getCash(){
		return this.cash;
	}
	public int getPassword(){
		return this.password;
	}
	public ArrayList<Card> getHand(){
		return this.hand;
	}
	public String getName(){
		return this.name;
	}
	public void addCard(Card c){
		hand.add(c);
		this.add(c);
		grid.setColumns(grid.getColumns()+1);
		this.setLayout(grid);
		this.setSize(this.getWidth()+100, this.getHeight());
		p.setLocation(p.getX()-100, p.getY());
		this.setLocation(p);
		this.revalidate();
	}
	public void clearHand(){
		for(int i=(this.hand.size()-1);i>=0 ; i--){
			this.remove(this.hand.get(i));
			this.hand.remove(i);
			grid.setColumns(grid.getColumns()-1);
			this.setLayout(grid);
			this.setSize(this.getWidth()-100, this.getHeight());
			p.setLocation(p.getX()+100, p.getY());
			this.setLocation(p);
			this.revalidate();
		}
	}
	public int getCardWorth(){
		int worth=0;
		int aces=0;
		for(int i =0; i<this.hand.size(); i++){
			worth+=hand.get(i).getWorth();
			if(hand.get(i).getIndex()%13==0){
				aces++;
			}
		}
		if(worth>21&&aces>0){
			for(int i=0; i<aces&&worth>21; i++){
				worth-=10;
			}
				
		}
		return worth;
	}
	public boolean isUnder(){
		if(this.getCardWorth()>21){
			return false;
		}
		else{
			return true;
		}
	}
	public String toString(){
		String s ="";
		s+="Name: "+this.getName()+"\tCash: "+this.getCash()+"\tCurrent Hands Worth: "+this.getCardWorth()+"\n";
		for(int i=0; i<hand.size(); i++){
			s+=hand.get(i).toString()+", ";
		}
		return s;
	}
	public void testPassword(){
		//to be implimented
	}
//	public static void main(String[] args){
//		Player d = new Player("Robbie", 100, 0);
//		System.out.println(d+"\n");
//		d.addCard(new Card("AH", 11, 13));
//		d.addCard(new Card("AH", 11, 13));
//		System.out.println(d);
//		System.out.println();
//		d.clearHand();
//		System.out.println(d);
// 		
//	}
}
