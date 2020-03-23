import java.util.Random;
public class GetCard {
	static Random card = new Random();
	
	public static int[] GC(){
		int cardvariable[]= {(card.nextInt(12)+1),(card.nextInt(12)+1),(card.nextInt(12)+1),(card.nextInt(12)+1),(card.nextInt(12)+1),(card.nextInt(12)+1),(card.nextInt(12)+1),(card.nextInt(12)+1)};
		
		return cardvariable;
	}
}