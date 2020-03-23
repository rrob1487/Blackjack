import java.util.Random;
import java.util.Scanner;

public class war {
	public int w(int m){
		Random card = new Random();
		Scanner in = new Scanner(System.in);
		Scanner in2 = new Scanner(System.in);
		System.out.println("War");
		int bet;
		String cont;
		
		int war = 1;
		while(war == 1){
			System.out.println("Money In Pocket:\t" + m + " $");
			System.out.println("Place bet");
			bet = in.nextInt();
			m-=bet;
			int c[]= {(card.nextInt(11)+1),(card.nextInt(11)+1),(card.nextInt(11)+1),(card.nextInt(11)+1)};
			System.out.println("You\t Dealer");
			System.out.println(c[0]+"\t"+c[1]);
			if (c[0]>c[1]){
				System.out.println("You Win!");
				m+=bet;
				m+=bet;
			}
			if (c[0]<c[1]){
				System.out.println("You Lose");
			}
			if (c[0]==c[1]){
				System.out.println("Round 2");
				System.out.println("You\t Dealer");
				System.out.println(c[2]+"\t"+c[3]);
				if (c[2]>c[3]){
					System.out.println("You Win!");
					m+=bet;
					m+=bet;
				}
				if (c[2]>c[3]){
					System.out.println("You Lose");
				}
			}
			if (m<=0){war = 0;}
			System.out.println("Money In Pocket:\t" + m + " $");
			System.out.println("Type q To Quit, Otherwise Press Enter");
			cont = in2.nextLine();
			switch (cont){
			case "Q":
				war = 0;
				break;
			case "q":
				war = 0;
				break;
			default:
				break;
			}				
		}//End of While Loop
		return m;
		
	}
}
