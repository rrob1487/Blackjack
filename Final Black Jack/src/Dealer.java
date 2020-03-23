public class Dealer extends Player{

	private int difficulty;
	
	public Dealer(int cash, int difficulty,int x,int y) {
		super("Dealer",cash, 0,x,y);
		this.setDifficutly(difficulty);
	}
	
	public void setDifficutly(int i){
		this.difficulty=i;
	}
	public int getDifficulty(){
		return this.difficulty;
	}
	public boolean hit(){
		if(this.getCardWorth()<=(12+difficulty)){
			return true;
		}
		if(this.getCardWorth()==17){
			return true;
		}
		return false;
	}
	public void addCard(Card c){
		c.removeFlip();
		this.hand.add(c);
		this.add(c);
		grid.setColumns(grid.getColumns()+1);
		this.setLayout(grid);
		this.setSize(this.getWidth()+100, this.getHeight());
		p.setLocation(p.getX()-100, p.getY());
		this.setLocation(p);
		this.revalidate();
	}
	
//	public static void main(String[] args){
//		Dealer d = new Dealer(100, 4,5);
//		d.addCard(new Card("AH", 14, 15));
//		System.out.println(d.hit());
//	}

}
