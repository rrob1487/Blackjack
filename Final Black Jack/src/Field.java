import java.awt.Font;

import javax.swing.JTextField;

public class Field extends JTextField{

	
	public Field(int x, int y){
		super();
		this.setLayout(null);
		this.setText("");
		this.setSize(385, 75);
		this.setLocation(x, y);
		this.setFont(new Font("times", 1, 45));
	}
}
