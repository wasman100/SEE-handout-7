package holder;

import acm.graphics.*;
import acm.program.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class Interactors extends GraphicsProgram {

	public void init() {
		buttonSetup();
		Storage = new HashMap<String, GObject>();
		addMouseListeners();
		addActionListeners();
	}

	private void buttonSetup() {
		// TODO Auto-generated method stub
		added = new JButton("add");
		remove = new JButton("remove");
		clear = new JButton("clear");
		tf = new JTextField(25);
		filedName = new JLabel("Name");
		add(filedName, SOUTH);
		add(tf, SOUTH);
		add(added, SOUTH);
		add(remove, SOUTH);
		add(clear, SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd == "add") {
			addBox(tf.getText());
		} else if (cmd == "remove") {
			removeBox(tf.getText());
		} else {
			nukeTheScreen();
		}

	}

	private void nukeTheScreen() { 
		for (Map.Entry mapElement : Storage.entrySet()) {
			String name = (String)mapElement.getKey();
			removeBox(name);
		}
	}

	private void removeBox(String name) {
		// TODO Auto-generated method stub
		GObject box = Storage.get(name);
		if (box != null) {
			remove(box);
		}
	}

	private void addBox(String name) {
		// TODO Auto-generated method stub
		GCompound box = new GCompound();
		GRect rect = new GRect(BOX_WIDTH, BOX_HEIGHT);
		GLabel called = new GLabel(name);
		box.add(rect, -BOX_WIDTH / 2, -BOX_HEIGHT / 2);
		box.add(called, -called.getWidth() / 2, -called.getAscent() / 2);
		add(box, getWidth() / 2, getHeight() / 2);
		Storage.put(name, box);

	}

	public void mousePressed(MouseEvent e) {
		mover = new GPoint(e.getPoint());
		currentBox = getElementAt(mover);
	
	}
	public void mouseDragged(MouseEvent e) {
		if(currentBox != null) {
			currentBox.move(e.getX() - mover.getX(), e.getY() - mover.getY());
			mover= new GPoint(e.getPoint());
		}
	}
	public void mouseRelesed(MouseEvent e) {	
	}
	
	private JButton added;
	private JButton remove;
	private JButton clear;
	private JTextField tf;
	private JLabel filedName;
	private GPoint mover;
	private GObject currentBox;
	private HashMap<String, GObject> Storage;

	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;

}
