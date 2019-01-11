package net.bodrykh.raspberrypi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Chat implements ActionListener {
	String logMessege;
	JButton button;
	JFrame frame;
	JTextField userInput;
	JTextArea chatLog;
	JScrollPane scroller;

	public static void main(String args[]) {
		Chat chatGui = new Chat();
		chatGui.go();
	}

	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		button = new JButton("Click me");
//		button.addActionListener(this);

		userInput = new JTextField(20);
		//userInput.setText("hello there");
		userInput.addActionListener(this);

		chatLog = new JTextArea(10, 20);
		scroller = new JScrollPane(chatLog);
		chatLog.setLineWrap(true);

		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		frame.getContentPane().add(BorderLayout.SOUTH, scroller);

		frame.getContentPane().add(BorderLayout.NORTH, userInput);

		frame.setSize(300, 300);

		frame.setVisible(true);

	}

	public void speak(String str) {
		str = str.replace(" ", "_");
		System.out.println(str);
		Process p;
		try {
			p = Runtime.getRuntime().exec("espeak -s120 " + str + " 2>/dev/null");
			p.waitFor();
			System.out.println("exit: " + p.exitValue());
			p.destroy();
		} catch (Exception e) {
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = userInput.getText();
		userInput.setText("");
		chatLog.append(str + "\n");
		speak(str);
	}
}
