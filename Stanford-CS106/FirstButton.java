package programm;
/*
 * File: FirstButton.java
 * ----------------------
 * This program shows a simple example of using a button
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class FirstButton extends ConsoleProgram {
	
	public void init() {
		setFont("Times New Roman-24");
		
		add(new JButton("Hi"), SOUTH);
		
		addActionListeners();
	}
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Hi")) {
			println("Hello there");
		} 
	}
	
}

