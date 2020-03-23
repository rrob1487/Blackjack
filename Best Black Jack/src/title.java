import java.util.Scanner;

public class title {
	public static void main(String[] args){
		GameScreen GS = new GameScreen();
		boolean play =  false;
		boolean start= false;
		System.out.println("Hello And Welcome To Robbie's Black Jack Casino");
		System.out.println("Would You Like To Play?");
		System.out.println("Type y For Yes, n For No");
		play = title.getAnswer(play);
		if(play = true){
			GS.Game();
		}
		else{}
	}
	
	public static boolean getAnswer(boolean answer) {
		Scanner sc = new Scanner(System.in);
		String response = sc.next();
		if (response.equals("y")){
			answer = true;
			}
		else{
			answer = false;			
		}
		return answer;
	}

}
