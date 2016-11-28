package programm;
/*
 * File: NewFirstButton.java
 * ----------------------
 * This program shows a simple example of using a button
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NewFirstButton extends ConsoleProgram {
	
	public void init() {
		setFont("Times New Roman-24");
		
		hiButton = new JButton("Hi"); // instance variable
		add(hiButton, SOUTH);
		addActionListeners();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (hiButton == e.getSource()) {
			println("Hello there");
		} 
	}
	
	/* Private instance variable */
	private JButton hiButton;
}
