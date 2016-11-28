package programm;
/*
 * File: InteractiveDrawFace.java
 * ------------------------------
 * This program draws GFaces on the screen, but allows the
 * use to modify their size and color.
 */

import acm.program.*;
import acm.graphics.*;
import java.awt.*;			// needed for Colors
import java.awt.event.*;	// needed for event listeners
import javax.swing.*;		// needed for GUI components

public class InteractiveDrawFace extends GraphicsProgram {

	// init() method is called when program starts
	public void init() {
		// Button to clear display
		add(new JButton("Clear"), SOUTH);
		
		// Check box to display front or back of face
		checkbox = new JCheckBox("Front"); // checkbox is instance variable
		checkbox.setSelected(true);
		add(checkbox, SOUTH);

		initRadioButtons();
		
		initColorChooser();
		
		// Must call this method to be able to get mouse events
		addMouseListeners();
		
		// Must call this method to get button press events
		addActionListeners();
	}
	
	private void initRadioButtons() {
		smallRB = new JRadioButton("Small");	// instance variable
		medRB = new JRadioButton("Medium");		// instance variable
		largeRB = new JRadioButton("Large");	// instance variable

		// Radio button group for size
		ButtonGroup sizeBG = new ButtonGroup();
		
		// Add all radio buttons to button group
		sizeBG.add(smallRB);
		sizeBG.add(medRB);
		sizeBG.add(largeRB);
		
		// Set initial radio button selection
		medRB.setSelected(true);
		
		// Add all radio buttons to control bar
		add(smallRB, SOUTH);
		add(medRB, SOUTH);
		add(largeRB, SOUTH);
	}

	private void initColorChooser() {
		// Create combo box with color choices
		pickColor = new JComboBox<String>();	// instance variable
		pickColor.addItem("Black");
		pickColor.addItem("Blue");
		pickColor.addItem("Green");
		pickColor.addItem("Red");

		// Don't allow user to type in a color
		pickColor.setEditable(false);

		// Set initial color selection
		pickColor.setSelectedItem("Black");
		
		// Create label (with separating spaces) for combo box
		add(new JLabel("   Color:"), SOUTH);
		
		// Add combo box to control bar
		add(pickColor, SOUTH);
	}


	// Called every time user clicks mouse
	public void mouseClicked(MouseEvent e) {
		GObject obj;
		double diam = getDiamSize();		
		if (checkbox.isSelected()) {
			obj = new GFace(diam, diam);	// Draw front of face
		} else {
			obj = new GOval(diam, diam);	// Draw "back of face"
		}
		obj.setColor(getCurrentColor());
		add(obj, e.getX(), e.getY());
	}
	

	// Returns diameter size corresponding to radio button choice
	private double getDiamSize() {
		double size = 0; 
		if (smallRB.isSelected()) {
			size = SMALL_DIAM;
		} else if (medRB.isSelected()) {
			size = MED_DIAM;
		} else if (largeRB.isSelected()) {
			size = LARGE_DIAM;
		}
		return size;
	}
		
	// Returns Color object corresponding to combo box choice
	private Color getCurrentColor() {
		String name = (String) pickColor.getSelectedItem();
		if (name.equals("Blue")) {
			return Color.BLUE;
		} else if (name.equals("Green")) {
			return Color.GREEN;
		} else if (name.equals("Red")) {
			return Color.RED;
		} else return Color.BLACK;
	}

	
	// Called whenever an action event occurs
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Clear")) {
			removeAll(); // Clears the canvas
		}else if (e.getSource()==smallRB) {
			smallRB.setSelected(true);
			medRB.setSelected(false);
			largeRB.setSelected(false);
			
		}
	}
	
	
	/* Private constants */
	private static final double SMALL_DIAM = 20;
	private static final double MED_DIAM = 40;
	private static final double LARGE_DIAM = 60;
	
	
	/* Private instance variables */
	// Use instance variables to keep track of interactors whose
	// "state" you need to check as your program runs
	private JCheckBox checkbox;
	private JRadioButton smallRB;
	private JRadioButton medRB;
	private JRadioButton largeRB;
	private JComboBox<String> pickColor;

}
