import java.util.Scanner;
import java.util.Random;

public class highlow {
	public int hl(int m){
		Random dice = new Random();
		Scanner in = new Scanner(System.in);
		Scanner in2 = new Scanner(System.in);
		Scanner in3 = new Scanner(System.in);
		int bet;
		int hl=1;
		System.out.println("High Low");
		System.out.println("Payouts:");
		System.out.println("High(8-12) = 1 x Bet");
		System.out.println("Low(2-6) = 1 x Bet");
		System.out.println("Seven = 4 x Bet");
		String choice, cont;
		String roll = null;
		
		while (hl==1){
			System.out.println("Money In Pocket:\t" + m + " $");
			System.out.println("Place Bets");
			bet = in.nextInt();
			m-=bet;
			System.out.println("High(h), Low(l), or Seven(7)?");
			choice = in2.nextLine();
			int d[] = {(dice.nextInt(6)+1),(dice.nextInt(6)+1)};
			System.out.println("The Roll Is-\t"+d[0]+"\t"+d[1]);
			if(d[0]+d[1]>7){roll = "h";}
			if(d[0]+d[1]<7){roll = "l";}
			if(d[0]+d[1]==7){roll = "7";}
			if (choice.equals(roll)){
				System.out.println("You Win");
				if(choice.equals("7")){
					m+=bet;
					m+=bet;
					m+=bet;
					m+=bet;
					m+=bet;
				}else{
					m+=bet;
					m+=bet;
				}
			}else{System.out.println("You Lose");}
			if (m<=0){hl = 0;}
			System.out.println("Money In Pocket:\t" + m + " $");
			System.out.println("Type q To Quit, Otherwise Press Enter");
			cont = in3.nextLine();
			switch (cont){
			case "Q":
				hl = 0;
				break;
			case "q":
				hl = 0;
				break;
			default:
				break;
			}
		
		}
		return m;	
	}
}
