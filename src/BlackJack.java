import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BlackJack extends JFrame{
	
	JLabel intro;
	Button start, online;

	public BlackJack(boolean t){
		super("BlackJack");
		this.setLayout(null);
		BlackJack.setDefaultLookAndFeelDecorated(true);
		this.setLocationRelativeTo(null);
		BufferedImage jpg=null;
		try {
			URL url = BlackJack.class.getResource("/image2.jpg");
			jpg=ImageIO.read(url);
			Image pic =jpg.getScaledInstance(800, 800, 100);
			jpg=null;
			this.setContentPane(new JLabel(new ImageIcon(pic)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.pack();
		this.setLocation(500, 200);
		this.setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(t);
		
		
				
	}
		
	public static void main(String[] args){
		BlackJack j = new BlackJack(false);
		j.run();
		j.dispose();
		
	}
	public void run(){
		BlackJack title = new BlackJack(true);
		intro = new JLabel();
		start= new Button("Start", 350, 150, 235, 225);
		online= new Button("Online", 350, 150, 235, 450);
		
		
		intro.setLayout(null);
		intro.setFont(new Font("Times New Roman", 2, 80));
		intro.setText("Welcome To BlackJack");
		intro.setForeground(Color.RED);
		intro.setSize(755, 60);
		intro.setLocation(25, 100);
		title.add(intro);
		

		start.setFont(new Font("Times", 2, 35));
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
				Settings s = new Settings();
				title.dispose();
				
				
				
			}});
		title.add(start);
		
		online.setFont(new Font("Times", 2, 35));
		online.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				Online_Settings settings = new Online_Settings();
				title.dispose();
			}});
		title.add(online);
		
		
		

	}
	public void close(){
		 
	}

	
	
}
