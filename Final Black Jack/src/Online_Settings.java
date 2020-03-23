import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Online_Settings extends BlackJack{

	private Button host, join;
	private String name, ipAddress, code;
	private JTextField enterName, enterGameID, enterCode;
	private ActionListener okayAction, joinAction, enterAction, hostAction;
	private JPanel panel;
	private Label gameIdText, gameIdText2;

	public Online_Settings(){
		super(true);
		host = new Button("Host", 350, 100, 235, 325);
		join = new Button("Join", 350, 100, 235, 425);
		enterName = new JTextField();
		enterName.setText("Enter Name Here");
		enterName.setSize(500, 100);
		enterName.setLocation(135, 200);
		enterName.setFont(new Font("", 1, 50));
		enterName.setForeground(Color.red);
		enterName.setBackground(Color.LIGHT_GRAY);
		enterName.setBorder(null);

		enterGameID = new JTextField();
		enterGameID.setText("Enter GameID");
		enterGameID.setSize(500, 100);
		enterGameID.setLocation(135, 200);
		enterGameID.setFont(new Font("", 1, 50));
		enterGameID.setForeground(Color.red);
		enterGameID.setBackground(Color.LIGHT_GRAY);

		enterCode = new JTextField();
		enterCode.setText("Enter Code");
		enterCode.setSize(500, 100);
		enterCode.setLocation(135, 300);
		enterCode.setFont(new Font("", 1, 50));
		enterCode.setForeground(Color.red);
		enterCode.setBackground(Color.LIGHT_GRAY);

		this.add(enterName);
		okayAction =new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				Brain_Host brain = new Brain_Host(name);
				Thread t= new Thread(brain);
				t.start();
			}
		};
		enterAction =new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ipAddress=decrypt(enterGameID.getText().trim(), enterCode.getText().trim());
				dispose();
				Brain_Client brain = new Brain_Client(name, ipAddress);
				Thread t= new Thread(brain);
				t.start();
			}
		};
		joinAction =new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println(enterName.getText().equals("Enter Name Here"));
				if(enterName.getText().equals("Enter Name Here")==false && enterName.getText().length()>1){
					name = enterName.getText().trim();
					remove(enterName);
					enterName.revalidate();
					remove(join);
					join.revalidate();
					add(enterCode);
					add(enterGameID);
					host.setText("Join Game");
					host.setLocation(235, 425);
					host.removeActionListener(hostAction);
					host.addActionListener(enterAction);
					host.revalidate();
					repaint();
				}

			}
		};
		hostAction =new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					if(enterName.getText().equals("Enter Name Here")==false && enterName.getText().length()>1){
						name = enterName.getText().trim();
						ipAddress = InetAddress.getLocalHost().getHostAddress();
						ipAddress = encrypt(ipAddress);
						remove(enterName);
						enterName.revalidate();
						remove(host);
						host.revalidate();
						gameIdText = new Label("  Give Opponent This Info: ", 50, 100, 200);
						gameIdText2= new Label("GameId: "+ ipAddress+ "     Code: "+code, 30, 80, 300);
						add(gameIdText2);
						add(gameIdText);
						join.setText("Start Game");
						join.setLocation(235, 425);
						join.removeActionListener(joinAction);
						join.addActionListener(okayAction);
						join.revalidate();
						repaint();
						

					}


				} catch (UnknownHostException ev){
					ev.printStackTrace();
				}

			}
		};
		host.addActionListener(hostAction);
		join.addActionListener(joinAction);
		this.add(join);
		this.add(host);


	}
	private String encrypt(String ipAddress){
		String s = "";
		int count =0;
		for(int i =ipAddress.length()-1; i>=0; i--){
			if(ipAddress.charAt(i)=='.'){
				count=count*10;
			}else{
				s+=ipAddress.charAt(i);
				count++;
			}
		}
		code=Integer.toString(count);
		code=this.reverseString(code);
		return s;
	}
	private String decrypt(String gameId, String code){
		String s = "";
		char[] rayC = code.toCharArray();
		int[] rayI = new int[rayC.length];
		int count=0;
		for(int i =0; i<rayC.length; i++){
			rayI[i]= Integer.parseInt(Character.toString(rayC[i]));
		}
		for(int i = gameId.length()-1; i>=0; i--){
			if(rayI[count]==0){
				s+='.';
				count++;
			}
			s+=gameId.charAt(i);
			rayI[count]--;

		}
		return s;
	}
	private String reverseString(String s){
		String ret = "";
		for(int i = 0; i< s.length(); i++){
			ret+=s.charAt(s.length()-1-i);
		}
		return ret;
	}

	public static void main(String[] args){
		String ipAddress="102.84.5113.84";
//		try {
//			ipAddress = InetAddress.getLocalHost().getHostAddress();
//		} catch (UnknownHostException e){
//			e.printStackTrace();
//		}
		System.out.println(ipAddress);
		Online_Settings set = new Online_Settings();
		set.setVisible(false);
		ipAddress=set.encrypt(ipAddress);
		System.out.println(ipAddress);
		System.out.println(set.code);
		ipAddress=set.decrypt(ipAddress, set.code);
		System.out.println(ipAddress);

	}
}
