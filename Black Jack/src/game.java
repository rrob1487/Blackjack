import java.util.Scanner;

public class game {

	public static void main(String[] args) {
		int c1, c2, c3, c4, c5, d, m;
		String action;
		m = 5;
		System.out.println("Welcome To BlackJack");
		
		while (m > 0){ 
		
		System.out.println("You Currently Have " + m + "$");
		c1 = (int)Math.ceil(Math.random()*11);
		c2 = (int)Math.ceil(Math.random()*10);
		c3 = (int)Math.ceil(Math.random()*11);
		c4 = (int)Math.ceil(Math.random()*11);
		c5 = (int)Math.ceil(Math.random()*11);
		d = (int)(Math.random()*9+15);
		System.out.println("Your first card values are:" + c1 +" and " + c2);
		
		Scanner a = new Scanner(System.in);
		System.out.println("Would you like to hit or stay?");
		action = a.next();
		switch (action){
		
		case "hit":
			System.out.println("Your Next Card is: " + c3);
			System.out.println("Would you like to hit or stay?");
			action = a.next();
			switch (action){
			
			case "hit":
				System.out.println("Your Next Card is: " + c4);
				System.out.println("Would you like to hit or stay?");
				action = a.next();
				switch (action){
				
				case "hit":
					System.out.println("Your Next Card is: " + c5);
					if (d < (c1 + c2 + c3 + c4 + c5) && (c1 + c2 + c3 + c4 + c5)<=21 && d < 22){
						System.out.println("You Win");
						m++;
					}else{
						System.out.println("You Lose");
						m--;						
					}
					break;
				
				case "stay":
					System.out.println("The Dealer's Score is:" + d);
					if (d < (c1 + c2 + c3 + c4) && (c1 + c2 + c3 + c4)<=21 || d >= 22){
						System.out.println("You Win");
						m++;
					}else{
						System.out.println("You Lose");
						m--;
					}
					break;
				case "quit":
					m = 0;
					break;
				default:
					System.out.println("You messed up, please try again");
					break;
				}
				break;
			
			
			case "stay":
				System.out.println("The Dealer's Score is:" + d);
				if (d < (c1 + c2 + c3) && (c1 + c2 + c3)<=21 || d >= 22){
					System.out.println("You Win");
					m++;
				}else{
					System.out.println("You Lose");
					m--;
				}
				break;
			case "quit":
				m = 0;
				break;
			default:
				System.out.println("You messed up, please try again");
				break;
			}			
			break;
		
		case "stay":
			System.out.println("The Dealer's Score is:" + d);
			if (d < (c1 + c2) && (c1 + c2)<=21 || d >= 22){
				System.out.println("You Win");
				m++;
			}else{
				System.out.println("You Lose");
				m--;
			}
			break;
		case "quit":
			m = 0;
			break;
		default:
			System.out.println("You messed up, please try again");
			break;	
		}
		}
		System.out.println("You Are out of money :( Come Back Again Later");
		
	}

}
