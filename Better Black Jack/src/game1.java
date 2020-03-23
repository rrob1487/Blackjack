import java.util.Scanner;

public class game1 {
	public static void main(String[] args){
		System.out.println("Welcome To Doug And Sons Cassino");
		Scanner in = new Scanner(System.in);
		int running = 1;
		blackjack bjObject = new blackjack();
		war warObject = new war();
		highlow hlObject = new highlow();
		int m = 20;
		
		while (running == 1){
			System.out.println("Money in pocket:\t" + m);
			System.out.println("Press 1 To Play Black Jack");
			System.out.println("Press 2 To Play War");
			System.out.println("Press 3 For High Low"); 
			System.out.println("Press 4 To Quit");
			String start=in.next();
			
			
			switch (start){
				case "1":
					m = bjObject.bj(m);
					break;
				case "2":
					m = warObject.w(m);
					break;
				case "3":
					m = hlObject.hl(m);
					break;
				case "4":
					System.out.println("GoodBye");
					running--;
					break;
				default:
					System.out.println("You messed up try again");
						}
			if(m<1){
				System.out.println("Sorry You're All Out Of Money, Come Again");
				running--;
		
			}	
		}	
	}
}
