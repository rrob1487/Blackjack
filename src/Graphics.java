

public class Graphics extends BlackJack implements Runnable{
	private Player p1, p2;
	private Button hit, stay;
	private Label p1NameLabel, p2NameLabel, p1CashLabel, p2CashLabel, actionLabel;
	private boolean running;
	
	
	public Graphics(String name1, String name2, int cash){
		super(false);
		this.p1 = new Player(name1, cash, 0, 780, 550);
		this.p2 = new Player(name2, cash, 0, 780, 125);
		this.p1NameLabel= new Label(p1.getName(), 60, 460, 400);
		this.p2NameLabel= new Label(p2.getName(), 60, 460, 0);
		this.p1CashLabel = new Label("$"+p1.getCash(),40, 460, 450);
		this.p2CashLabel = new Label("$"+p2.getCash(), 40, 460, 50);
		this.hit=new Button("Hit", 200,100, 100, 280);
		this.stay=new Button("Stay", 200,100,100, 380);
		this.add(p1);
		this.add(p2);
		this.add(p1NameLabel);
		this.add(p2NameLabel);
		this.add(p1CashLabel);
		this.add(p2CashLabel);
		//this.setVisible(true);
		this.actionLabel=new Label("Waiting for connection...", 40, 100, 200);
		this.add(actionLabel);
		running = true;
		
	}
	
	public void setP1(Player p1) {
		this.p1 = p1;
		this.p1.revalidate();
	}
	public void setP2(Player p2) {
		this.p2 = p2;
		this.p2.revalidate();
	}
	public void setP1NameLabel(String s) {
		this.p1NameLabel.setText(s);
		this.p2NameLabel.revalidate();
	}
	public void setP2NameLabel(String s) {
		this.p2NameLabel.setText(s);
		this.p2NameLabel.revalidate();
	}
	public void setP1CashLabel() {
		this.p1CashLabel.setText("$"+p1.getCash());
		this.p1CashLabel.revalidate();
	}
	public void setP2CashLabel() {
		this.p2CashLabel.setText("$"+p2.getCash());
		this.p2CashLabel.revalidate();
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
	public void setActionLabel(String s){
		this.actionLabel.setText(s);
		this.actionLabel.revalidate();
	}
	
	public Label getActionLabel(){
		return this.actionLabel;
	}
	public Player getP1(){
		return this.p1;
	}
	public Player getP2(){
		return this.p2;
	}
	public Button getHit(){
		return this.hit;
	}
	public Button getStay(){
		return this.stay;
	}
	public boolean isRunning() {
		return running;
	}
	
	
	public void run() {
		this.setVisible(true);
		boolean run;
		do{
			this.revalidate();
			this.repaint();
			run=running;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println("refresh");
		}while(run);
	}

	public static void main(String[] args){
		Graphics g = new Graphics("Robbie", "Doug", 500);
		Thread graphics = new Thread(g);
		graphics.start();
		g.getP1().addCard(new Card("2H", 2, 1));
	}
}
