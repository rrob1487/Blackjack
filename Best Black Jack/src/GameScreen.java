import java.util.Scanner;

public class GameScreen {
	public static int playerhits = 0, dealerhits=7;

	public void Game(){
		int m=100;
		boolean running = true;
		boolean action=true;
		String cn[]= {("2"),("3"),("4"),("5"),("6"),("7"),("8"),("9"),("10"),("J"),("Q"),("K"),("A")};
		int card[];
		int cv[]= {(2),(3),(4),(5),(6),(7),(8),(9),(10),(10),(10),(10),(11)};
		
		Scanner sc = new Scanner(System.in);
		
		GetCard GC = new GetCard();
		Winner W= new Winner();
		Dealer D = new Dealer();
		
		System.out.println("Welcome To The 10 Dollar Table");
		while (running == true){
			System.out.print("You Have ");
			GameScreen.GetMoney(m);
			System.out.println("$");
			card=GC.GC();
			int ps=0;
			ps= cv[card[playerhits]]+cv[card[(playerhits+1)]];
			boolean winner= true;
			System.out.println("You\t\t\tDealer");
			System.out.printf("%s, %s\t\t\t%s, ?\n",cn[card[playerhits]],(cn[card[(playerhits+1)]]),cn[card[dealerhits]]);
			playerhits=playerhits+1;
			action=true;
			while (action == true){
			action = GameScreen.action(action);
			if (action==true){
				System.out.println("Your Next Card Is "+cn[card[playerhits]]);
				ps= ps+cv[card[playerhits]];
				}
			}
			int ds= D.value(cv[card[dealerhits]]);
			winner=W.getwinner(ps,ds);
			if(winner==true){
				System.out.println("You Win");
				m=GameScreen.SetMoneyWin(m);
			}
			else{
				System.out.println("You Lose");
				m=GameScreen.SetMoneyLoose(m);
			}
			System.out.printf("You Know Have %d$\n",m);
			boolean cont= true;
			cont=GameScreen.cont(cont);
			if(cont==false){
			running=false;
			}
			
			
		}
		
	}
	public static int SetMoneyLoose(int money){
		money= money-10;
		return money;
	}
	public static int SetMoneyWin(int money){
		money= money+10;
		return money;
	}
	public static int GetMoney(int money){
		System.out.print(money);
		return money;
	}
	public static boolean action(boolean hs){
		System.out.println("Would You Like To Hit (h), or Stay(s)?");
		Scanner sc = new Scanner(System.in);
		String answer = sc.nextLine();
		if (answer.equals("h")){
			playerhits++;
			hs=true;
		}
		else{
			hs=false;
		}
		return hs;
	}
	public static boolean cont(boolean cont){
		Scanner sc= new Scanner(System.in);
		System.out.println("Continue? y/n");
		String contin; 
		contin = sc.nextLine();
		if(contin.equals("y")){
			cont=true;
		}
		else{
			cont=false;
		}
		return cont;
	}
}
