
public class Winner {
	public static boolean getwinner(int playerscore, int dealerscore){
	boolean winner=true;
	System.out.println("Final Scores\n----------\nYou\t\t\tDealer");
	System.out.println(playerscore+"\t\t\t"+dealerscore);
	if(playerscore>dealerscore && playerscore<22){winner=true;}
	if(playerscore>21){winner=false;}
	if(playerscore<dealerscore&&dealerscore<22){winner=false;}
	
	return winner;
	}
}
