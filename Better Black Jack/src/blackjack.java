import java.util.Scanner;
import java.util.Random;

public class blackjack {
	public int bj(int m){
		Random card = new Random();
		Scanner in = new Scanner(System.in);
		Scanner in2 = new Scanner(System.in);
		Scanner in3 = new Scanner(System.in);
		System.out.println("Black Jack");
		int bet,player,dealer;
		String cont;
		int n = 2;
		int o = 15;
		int bj=1;
		
		while(bj==1){
			System.out.println("Money In Pocket:\t" + m + " $");
			System.out.println("Place bet");
			bet = in.nextInt();
			m-=bet;
			int c[]= {(card.nextInt(10)+1),(card.nextInt(11)+1),(card.nextInt(11)+1),(card.nextInt(11)+1),(card.nextInt(11)+1),(card.nextInt(11)+1),(card.nextInt(11)+1),(card.nextInt(11)+1),(card.nextInt(11)+1),(card.nextInt(11)+1),(card.nextInt(11)+1),(card.nextInt(11)+1),(card.nextInt(11)+1),(card.nextInt(11)+1),(card.nextInt(11)+1),(card.nextInt(11)+1),(card.nextInt(11)+1),(card.nextInt(11)+1),(card.nextInt(10)+1)};
			System.out.println("You   \tDealer");
			System.out.println(c[0]+" + "+c[1]+"\t"+c[15]+" + ?");
			player = c[0]+c[1];
			dealer = c[15]+c[16];
			System.out.println("Hit or Stay?");
			String hs= in2.nextLine();
				while (hs.equals("hit")|| hs.equals("Hit")){
					System.out.println("Next Card:\t" + c[n]);
					player+=c[n];
					System.out.println("Current Score:\t" + player);
					n++;
					System.out.println("Hit or Stay?");
					hs= in2.nextLine();
					}
				while (dealer < 17){
					System.out.println("Dealer Hits. His Card is:\t" + c[o]);
					dealer+=c[o];
					o--;
					}
			System.out.println("Final Scores Are");
			System.out.println("You\tDealer");
			System.out.println(player + "\t"+ dealer);
			if (player > 21 || (player < dealer&& dealer <= 21)){
				System.out.println("You Lose");
			}
			if ((player > dealer && player <=21)||(player <= 21 && dealer>21)){
				System.out.println("You Win");
				m+=bet;
				m+=bet;
			}
			if ((player == dealer)|| (player >21&&dealer >21)){
				System.out.println("Tie Goes To The Table, Good Luck Next Time");
			}
			if (m<=0){bj = 0;}
			System.out.println("Money In Pocket:\t" + m + " $");
			System.out.println("Type q To Quit, Otherwise Press Enter");
			cont = in3.nextLine();
			switch (cont){
			case "Q":
				bj = 0;
				break;
			case "q":
				bj = 0;
				break;
			default:
				break;
			}
		}//End of While Loop
		return m;
	}
}
