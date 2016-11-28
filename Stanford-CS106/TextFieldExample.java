package programm;
/*
 * File: TextFieldExample
 * ----------------------
 * This program shows an example of a text field
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

/** This class displays a greeting whenever a name is entered */
public class TextFieldExample extends ConsoleProgram {

   public void init() {
	  setFont("Times New Roman-24");
	  
      add(new JLabel("Name"), SOUTH);
      
      nameField = new JTextField(50);
      nameField.setEditable(true);;
      
      add(nameField, SOUTH);
      nameField.addActionListener(this);
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == nameField) {
         println("Hello, " + nameField.getText());
      }
   }

/* Private instance variables */
   private JTextField nameField;
}

