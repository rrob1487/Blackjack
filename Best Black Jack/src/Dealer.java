
public class Dealer {
	public int value(int card1){
	GetCard GC=new GetCard();
	int dv=0, dh=7;
	int cv[]= {(2),(3),(4),(5),(6),(7),(8),(9),(10),(10),(10),(10),(11)};
	String cn[]= {("2"),("3"),("4"),("5"),("6"),("7"),("8"),("9"),("10"),("J"),("Q"),("K"),("A")};
	int card[]=GC.GC();
	dv=card1+cv[card[(dh-1)]];
	while (dv<17){
		dh=5;
		System.out.println("Dealer Hits\n His Card Is: "+cn[card[dh]]);
		dv=dv+cv[card[dh]];
		dh--;
	}
	return dv;	
	}
}
