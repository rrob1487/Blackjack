import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Label extends JLabel{

	public Label(String text, int fontSize, int x, int y){
		super();
		this.setFont(new Font("Times New Roman", 2, fontSize));
		this.setText(text);
		this.setForeground(Color.red);
		this.setSize(750, 100);
		this.setLocation(x, y);
	}
}
