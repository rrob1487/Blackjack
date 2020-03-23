import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Card extends JButton{
	private String face;
	private int worth;
	private int index;
	private String suit;
	private ImageIcon back;
	private ImageIcon front;
	private Label text;
	private Point p;
	private final int cardHeight=200;
	private final int cardWidth=100;
	private Image yugi, ash, jacksparrow;
	private ActionListener e;
	
	public Card(String s, int worth, int index){
		super();
		this.setFace(s);
		this.setWorth(worth);
		this.setIndex(index);
		this.setSuit();
		
		this.setLayout(null);
		this.setBackground();
		this.setIcon(front);
		this.setBack();
		
		e=new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				flip();
			}
		};
		this.addFlip();	
		this.addNumber();
		this.setBackImages();
	}
	
	public void addNumber(){
		p = new Point(this.getLocation());
		text = new Label(this.face.substring(0, this.face.length()-1), 45, (int)p.getX()+30, (int)p.getY()+50);
		text.setFont(new Font("Times", 0, 60));
		text.setForeground(Color.black);
		this.add(text);
	}
	public void setIndex(int i){
		this.index=i;
	}
	public void setFace(String s){
		this.face=s;
	}
	public void setWorth(int i){
		this.worth=i;
	}
	public void setSuit(){
		char s=this.face.charAt(face.length()-1);
		switch (s){
		case 'H':
			this.suit="Heart";
			break;
		case 'C':
			this.suit="Club";
			break;
		case 'D':
			this.suit="Diamond";
			break;
		case 'S':
			this.suit="Spade";
			break;
		default:
			this.suit="Heart2";
			break;
		}
	}
	public String getFace(){
		return this.face;
	}
	public int getWorth(){
		return this.worth;
	}
	public int getIndex(){
		return this.index;
	}
	public String getSuit(){
		return this.suit;
	}
	public void setBackground(){
		switch(this.suit){
		case "Heart":
		case "Heart2":
			try {
				URL url = BlackJack.class.getResource("/blankHeart.jpg");
				BufferedImage h = ImageIO.read(url);
				Image heart =h.getScaledInstance(this.cardWidth, this.cardHeight, 100);
				front = new ImageIcon(heart);	
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
		case "Spade":
			try {
				URL url = BlackJack.class.getResource("/blankSpade.jpg");
				BufferedImage s = ImageIO.read(url);
				Image spade =s.getScaledInstance(this.cardWidth, this.cardHeight, 100);
				front = new ImageIcon(spade);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
		case "Club":
			try {
				URL url = BlackJack.class.getResource("/blankClub.jpg");
				BufferedImage c = ImageIO.read(url);
				Image club =c.getScaledInstance(this.cardWidth, this.cardHeight, 100);
				front=new ImageIcon(club);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
		case "Diamond":
			try {
				URL url = BlackJack.class.getResource("/blankDiamond.jpg");
				BufferedImage d = ImageIO.read(url);
				Image diamond =d.getScaledInstance(this.cardWidth, this.cardHeight, 100);
				front=new ImageIcon(diamond);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
		}
	}
	public void setBack(){
		try {
			URL url = BlackJack.class.getResource("/cardBack.jpg");
			BufferedImage b = ImageIO.read(url);
			Image bck =b.getScaledInstance(this.cardWidth, this.cardHeight, 100);
			back = new ImageIcon(bck);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public String toString(){
		String s=this.getFace()+" : "+this.getIndex();
		return s;
	}
	public void flip(){
		if(this.getIcon().equals(front)){
			this.setIcon(back);
			this.text.setVisible(false);
			this.revalidate();
			return;
		}
		this.setIcon(front);
		this.text.setVisible(true);
		this.revalidate();
	}
	public void changeBack(String s){
		switch (s){
		case "Yugi":
		case "yugi":
			back = new ImageIcon(yugi);
			break;
		case "Jack Sparrow":
		case "jack sparrow":
		case "JackSparrow":
			back = new ImageIcon(jacksparrow);			
			break;
		case "Ash":
		case "ash":
			back = new ImageIcon(ash);
			break;
		}
		this.revalidate();
	}
	public void removeFlip(){
		this.removeActionListener(e);	
	}
	public void addFlip(){
		this.addActionListener(e);
		
	}
	public void setBackImages(){
		try {
			URL url = BlackJack.class.getResource("/yugi.jpg");
			BufferedImage b = ImageIO.read(url);
			yugi =b.getScaledInstance(this.cardWidth, this.cardHeight, 100);
			} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			URL url = BlackJack.class.getResource("/jacksparrow.jpg");
			BufferedImage b = ImageIO.read(url);
			jacksparrow =b.getScaledInstance(this.cardWidth, this.cardHeight, 100);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			URL url = BlackJack.class.getResource("/ash.jpg");
			BufferedImage b = ImageIO.read(url);
			ash =b.getScaledInstance(this.cardWidth, this.cardHeight, 100);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
