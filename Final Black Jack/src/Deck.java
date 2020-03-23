import java.util.ArrayList;
import javax.swing.SwingWorker;




public class Deck extends ProgressBar{
	private int numOfDecks,t;
	private String name;
	private ArrayList<Card> order;
	private int currentCard;
	private final char[] type = {'H','C','D','S'};
	private final String[] face= {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
	private final int[] worth = {2,3,4,5,6,7,8,9,10,10,10,10,11};	
	private SwingWorker swing;

	public Deck(int decks, String name){
		super();
		this.setDecks(decks);
		this.name = name;
		this.currentCard=0;
		order = new ArrayList<Card>(0);

	}

	public void setDecks(int i){
		this.numOfDecks=i;
	}
	public int getOrderLength(){
		return this.order.size();
	}
	public int getDecks(){
		return this.numOfDecks;		
	}
	public int getT(){
		return this.t;
	}
	public void setCurrentCard(int i){
		this.currentCard=i;
	}
	public void makeDeck(){	
		t=1;
		swing = new SwingWorker<Integer, Object>(){
			protected Integer doInBackground() throws Exception {
				for(int i =0; i<numOfDecks; i++){
					for(int j = 0; j<type.length; j++){
						for(int k =0; k<face.length; k++){
							order.add(new Card((face[k]+type[j]), worth[k], t));
							this.setProgress();
							t++;
						}
					}
				}
				EasterEggs(name);
				shuffle();
				return null;
			}
			public void setProgress(){
				setLength(getT(), numOfDecks);
			}
			
		};
		
		swing.execute();
	}
	public String toString(){
		String s ="";
		s+="Num of Cards: "+order.size()+"\n";
		for(int i = 0; i<order.size(); i++){
			s+=this.order.get(i).getFace()+":"+this.order.get(i).getIndex()+", ";
		}
		return s;
	}
	public Card getCard(){
		if(currentCard>order.size()-1){
			System.out.println("Shuffle");
			currentCard=0;
			for(int i=0; i<order.size();i++){
				order.remove(i);
			}
			makeDeck();
		}
		currentCard++;
		return order.get(currentCard-1);
	}
	public void shuffle(){
		ArrayList<Card> temp = new ArrayList<Card>(0);
		int j = 0;
		while(this.order.size()>0){
			j = (int)(Math.random()*(this.order.size()-1));
			temp.add(this.order.get(j));
			this.order.remove(j);		
		}
		j=0;
		while(temp.size()>0){
			j=(int)(Math.random()*(temp.size()-1));
			this.order.add(temp.get(j));
			temp.remove(j);
		}
	}
	public void EasterEggs(String name){
		if(name.equals("Ash")||name.equals("Yugi")||name.equals("Jack Sparrow")||name.equals("ash")||name.equals("yugi")||name.equals("JackSparrow")||name.equals("jack sparrow")){
			for(int i =0; i<order.size();i++){
				this.order.get(i).changeBack(name);
			}
		}
	}
	public short[] getShortOrder(){
		short[] shorder = new short[order.size()];
		for(int i=0; i<shorder.length;i++){
			shorder[i]=(short)order.get(i).getIndex();
		}
		return shorder;
	}
	public void setShortOrder(short[] shorder){
		ArrayList<Card> temp = new ArrayList<Card>(0);
		//System.out.println("Order size: " + order.size());
		for(int i = 0; i<shorder.length; i++){
			temp.add(order.get(this.findPosisitionOfCard((int)shorder[i])));
		}
		order=temp;
		temp=null;
	}
	private int findPosisitionOfCard(int index){
		for(int i =0; i< order.size(); i++){
			if(index==order.get(i).getIndex()){
				return i;
			}
		}
		return 1;
	}
	
	
	public static void main(String[] args){
		Deck test = new Deck(1, "Yugi");
		System.out.println(test);
		test.shuffle();
		System.out.println(test);
		test.makeDeck();


	}
}
