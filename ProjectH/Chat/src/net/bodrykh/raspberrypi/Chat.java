package net.bodrykh.raspberrypi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

public class Chat implements ActionListener {
	String logMessege;
	JButton button;
	JFrame frame;
	JTextField userInput;
	JTextArea chatLog;
	JScrollPane scroller;
	String strToSpeak = "Welcome Roman!";
	int voiceType = 0;

	Jarvis jarvis = new Jarvis(strToSpeak, voiceType);

	Stack<String> chatStack = new Stack<>();

	public static void main(String args[]) {
		Chat chatGui = new Chat();
		chatGui.go();
	}

	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		userInput = new JTextField(20);
		userInput.addActionListener(this);

		chatLog = new JTextArea(10, 20);
		scroller = new JScrollPane(chatLog);
		chatLog.setLineWrap(true);

		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		frame.getContentPane().add(BorderLayout.SOUTH, scroller);

		frame.getContentPane().add(BorderLayout.NORTH, userInput);

		frame.setSize(460, 300);

		frame.setVisible(true);

	}

	/*
	 * public void speak(String str) { str = str.replace(" ", "_");
	 * System.out.println(str); Process p; try { p =
	 * Runtime.getRuntime().exec("espeak -s120 " + str + " 2>/dev/null");
	 * p.waitFor(); System.out.println("exit: " + p.exitValue()); p.destroy(); }
	 * catch (Exception e) { } }
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		Date date = new Date();
		String dateStr = dateFormat.format(date);
		String user, pcJarvis;
		
		String str = userInput.getText();
		userInput.setText("");
		
		
	
		user = dateStr + " User: " + str + "\n";
		chatStack.push(user);////
		chatLog.setText("");
		for (int index = chatStack.size() - 1; index >= 0; index--) {
			chatLog.append(chatStack.elementAt(index));
		}
		
	
		
		String getStr = jarvis.getFeedbackFrom(str);
		pcJarvis = dateStr + " Jarvis: " + getStr + "\n";
		chatStack.push(pcJarvis);/// send both that not OK!!!!!!!!!!!!!!!!!!!!
		jarvis.thread(new Jarvis(getStr, 0), false); //to say
		
		chatLog.setText("");
		for (int index = chatStack.size() - 1; index >= 0; index--) {
			chatLog.append(chatStack.elementAt(index));
		}
		
		
		chatLog.setCaretPosition(0);
	}
}
