import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class SendObject implements Comparable, Serializable{
	private short[] order;
	private short turn;
	private short action;
	private String message;
	
	public SendObject(short[] o, short t, short a){
		order=o;
		turn = t;
		action = a;
		message ="";
	}
	public SendObject(SendObject s, String message){
		order= s.getOrder();
		turn = s.getTurn();
		action = s.getAction();
		this.message = message;
		
	}
	public SendObject(short[] o, short t, short a, String message){
		order = o;
		turn = t;
		action = a;
		this.message = message;
	}
	
	public String toString(){
		String s="";
		for(int i = 0; i<order.length; i++){
			s+=order[i]+", ";
		}
		s+="\nTurn: "+turn+"\t Action: "+ action;
		return s;
	}
	
	public String getMessage(){
		return this.message;
	}
	public short getTurn() {
		return turn;
	}
	public short[] getOrder() {
		return order;
	}
	public short getAction() {
		return action;
	}
	public void setOrder(short[] order) {
		this.order = order;
	}
	public void setTurn(short turn) {
		this.turn = turn;
	}
	public void setAction(short action) {
		this.action = action;
	}
	public void setMessage(String message){
		this.message= message;
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException{
		out.defaultWriteObject();
	}
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
		in.defaultReadObject();
	}
	private void readObjectNoData() throws ObjectStreamException{
	
	
	}
	
	public int compareTo(Object arg0) {
		if(arg0 instanceof SendObject){
			SendObject obj = (SendObject)arg0;
			if(obj.order==this.order&&obj.turn==this.turn&&obj.action==this.action){
				return 0;
			}else if(obj.turn>this.turn){
				return 1;
			}else if(obj.turn<this.turn){
				return -1;
			}else{
				return 69;
			}
		}
		return -69;
	}

}
