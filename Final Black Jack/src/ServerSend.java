import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ServerSend extends JFrame implements Runnable{
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	private JTextField userText;
	private JTextArea chatWindow;
	private SendObject storedSendObject;
	private String p1Name, p2Name;
	private boolean isConnected = false;
	
	
	
	public ServerSend(short[] s, short a, short t, String name){
		super("HostChatWindow");
		p1Name=name;
		userText= new JTextField();
		userText.setEditable(false);
		userText.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						sendMessage(event.getActionCommand());
						userText.setText("");
					}});
		this.storedSendObject=new SendObject(s, t, a);
		chatWindow= new JTextArea();
		chatWindow.setEditable(false);
		add(new JScrollPane(chatWindow));
		add(userText, BorderLayout.NORTH);
		this.setSize(500,250);
		this.setLocation(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
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
			server = new ServerSocket(6969, 1);
			
			while(true){
				try{
					//Connect and have conversation
					waitForConnection();
					setUpStreams();
					whileChatting();					
				}catch(EOFException eofException){
					showMessage("\n Server Ended The Connection!");
				}finally{
					closeCrap();
				}
			}
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	private void waitForConnection() throws IOException{
		showMessage("Waiting for someone to connect... \n");
		connection= server.accept();
		showMessage("Now connected to" + connection.getInetAddress().getHostName());
	
	}
	private void setUpStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		//get rid of data left over in the stream
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\nStreams Are Now Set Up\n");
		
	}
	private void whileChatting() throws IOException{
		String message = "You are now Connected to: " + p1Name;
		sendMessage(message);
		ableToType(true);
		isConnected = true;
		do{
			try{
				String m = ((SendObject)input.readObject()).getMessage();
				showMessage("\n"+m);
				p2Name= m.substring(35);
			}catch(ClassNotFoundException classNotFoundException){
				showMessage("\n Server: IDK What Happened");
			}
		}while(p2Name==null);
		do{
			//have conversation
			try{
				SendObject s = (SendObject)input.readObject();
				message = s.getMessage();
				this.updateSendObject(s);
				showMessage("\n"+message);
			}catch(ClassNotFoundException classNotFoundException){
				showMessage("\n Server: IDK What Happened");
			}
			
		}while(!message.equals("Client - END"));
	}
	public void closeCrap(){
		showMessage("\n Closing Connections... \n");
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
			output.writeObject(new SendObject(this.storedSendObject, "Host - " +message));
			output.flush();
			showMessage("\nHost  - " + message);
		}catch(IOException ioException){
			chatWindow.append("\n ERROR: Cant Send That Message, Try Again");
		}
	}
	private void showMessage(final String text){
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
						chatWindow.append(text);
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
		short[] sho= {1,2,3,4,5};
		ServerSend s= new ServerSend(sho, (short)1, (short)0, "Robbie");
		Thread t = new Thread(s);
		t.start();
	}
		
}
