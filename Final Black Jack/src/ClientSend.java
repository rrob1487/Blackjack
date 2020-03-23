import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ClientSend extends JFrame implements Runnable {
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private JTextField userText;
	private JTextArea chatWindow;
	private SendObject storedSendObject;
	private String serverIP;
	private Socket connection;	
	private String message;
	private boolean isConnected=false;
	private String p1Name, p2Name;

	public ClientSend(String ip, short[] s, short a, short t, String name){
		super("ClientChatWindow");
		p1Name = name;
		serverIP=ip;
		this.storedSendObject=new SendObject(s, t, a);		
		userText= new JTextField();
		userText.setEditable(false);
		userText.addActionListener(
				new ActionListener(){	
					public void actionPerformed(ActionEvent event){
						sendMessage(event.getActionCommand());
						userText.setText("");
					}
				}
				);
		this.add(userText, BorderLayout.NORTH);
		chatWindow= new JTextArea();
		chatWindow.setEditable(false);
		add(new JScrollPane(chatWindow), BorderLayout.CENTER);
		setSize(500, 250);
		this.setLocation(500, 500);
		setVisible(true);
	}

	public void setSendObject(SendObject send){
		this.storedSendObject=send;
	}
	public SendObject getSendObject(){
		return this.storedSendObject;
	}
	public boolean getIsConnected(){
		return this.isConnected;
	}
	public String getP2Name(){
		return this.p2Name;
	}
	
	private void startRunning(){
		try{
			//run
			connectToServer();
			setupStreams();
			whileChatting();
		}catch(EOFException eofException){
			showMessage("\n Client terminated connection");
		}catch(IOException ioException){
			ioException.printStackTrace();
		}finally{
			closeCrap();
		}
	}
	private void connectToServer() throws IOException{
		showMessage("Attempting Connection...\n");
		connection= new Socket(InetAddress.getByName(serverIP), 6969);
		showMessage("Connnection Successfull: "+ connection.getInetAddress());
	}
	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\n Streams are now active\n");
	}
	private void whileChatting() throws IOException{
		ableToType(true);
		sendMessage("You are now Connected to: "+p1Name);
		isConnected = true;
		do{
			try{
				String m = ((SendObject)input.readObject()).getMessage();
				showMessage("\n"+m);
				p2Name= m.substring(33);
			}catch(ClassNotFoundException classNotFoundException){
				showMessage("\n Server: IDK What Happened");
			}
		}while(p2Name==null);
		do{
			try{
				SendObject s = (SendObject)input.readObject();
				message = s.getMessage();
				this.updateSendObject(s);
				showMessage("\n"+message);
			}catch(ClassNotFoundException classNotFoundException){
				showMessage("\n Object type unknown");
			}
		}while(!message.equals("Host - END"));
	}
	public void closeCrap(){
		showMessage("\n Ending Connection");
		ableToType(false);
		isConnected = false;
		try{
			output.close();
			input.close();
			connection.close();
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public void sendMessage(String message){
		try{
			output.writeObject(new SendObject(this.storedSendObject,"Client - "+message));
			output.flush();
			showMessage("\nClient - "+ message);
		}catch(IOException ioException){
			chatWindow.append("\n Error Sending Message");
		}
	}
	private void showMessage(final String message){
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
						chatWindow.append(message);
					}
				}
		);
		
	}
	private void ableToType(final boolean tof){
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
						userText.setEditable(tof);
					}
				}
		);
	}
	private void updateSendObject(SendObject recieve){
		switch(this.getSendObject().compareTo(recieve)){
		case -1:
			break;
		case 0:
			break;
		case 1:
		case 69:
			this.setSendObject(recieve);
			break;
		default:
			break;
		}
	}
	
	
	public void run() {
		startRunning();
	}

	public static void main(String[] args){
		try {
			short[] sho= {1,2,3,4,5};
			ClientSend s= new ClientSend(InetAddress.getLocalHost().getHostAddress(), sho, (short)1, (short)0, "Doug");
			Thread t = new Thread(s);
			try {
				Thread.sleep(25000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			t.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

}
