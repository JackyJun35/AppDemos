package programm;
/*
 * File: ButtonPress.java
 * ----------------------
 * This program shows a simple example of adding interactors
 * to a console program.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonPress extends ConsoleProgram {
	
	public void init() {
		setFont("Times New Roman-24");
		
		add(new JButton("Hello"), NORTH);
		add(new JButton("CS106A"), SOUTH);
		add(new JButton("BasketWeaving101"), SOUTH);
		addActionListeners();
	}
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Hello")) {
			println("Hello there");
		} else if (cmd.equals("CS106A")) {
			println("CS106A rocks!");
		} else if (cmd.equals("BasketWeaving101")) {
			println("Not so much...");
		}
	}
	
}
