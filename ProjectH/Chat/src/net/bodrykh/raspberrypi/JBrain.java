package net.bodrykh.raspberrypi;

import java.util.Random;

public class JBrain implements Runnable {
	private Thread jarvisBrainThread;
	private String strBrainInput;
	private String strBrainOutput;
	private String inMindNow;
	private String voiceTypeOptions;

	private JVoice jVoice;

	JBrain(String voiceTypeOptions) {
		this.voiceTypeOptions = voiceTypeOptions;
		jVoice = new JVoice(strBrainOutput, this.voiceTypeOptions);
		strBrainOutput = "Welcome Roman!";
		inMindNow = "Random decision is";
	}

	private int randomTime(int timeMax) {
		Random rand = new Random();
		int randTime = rand.nextInt(timeMax);
		if(randTime < 0) {
			randTime = randTime * (-1);
		}
		return randTime;
	}
	
	private boolean randomBooleanDecision() {
		Random rand = new Random();
		long oct = 100000000;
		long n1 = rand.nextLong() / oct;
		long n2 = rand.nextLong() / oct;
		long n3 = rand.nextLong() / oct;
		long n4 = rand.nextLong() / oct;
		long n5 = rand.nextLong() / oct;
		long n6 = rand.nextLong() / oct;
		long n7 = rand.nextLong() / oct;
		long n8 = rand.nextLong() / oct;
		long n9 = rand.nextLong() / oct;
		long n10 = rand.nextLong() / oct;
		n1 = n1 / oct;
		n2 = n2 / oct;
		n3 = n3 / oct;
		n4 = n4 / oct;
		n5 = n5 / oct;
		n6 = n6 / oct;
		n7 = n7 / oct;
		n8 = n8 / oct;
		n9 = n9 / oct;
		n10 = n10 / oct;
		long number = n1 * n2 * n3 * n4 * n5 * n6 * n7 * n8 * n9 * n10;
		if (number > 0)
			return true;
		return false;
	}

	@Override
	public void run() {
		if (strBrainInput == null) {
			strBrainOutput = "Welcome Roman!";
		} else {
			strBrainOutput = mind(strBrainInput);
		}
		
		jVoice.toSpeak(strBrainOutput);
	}

	private String mind(String inputStr) {

		String outputStr = inputStr + " - Received. " + inMindNow + " " + randomBooleanDecision() + ".";

		try {
			Thread.sleep(randomTime(1000)); // set time for mind "thinking", 0 - no pause
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return outputStr;
	}

	public String mindHandler(String strBrainInput) {
		this.strBrainInput = strBrainInput;
		strBrainOutput = "";
		jarvisBrainThread = new Thread(this, "JarvisBrainThread");
		jarvisBrainThread.setDaemon(false);
		jarvisBrainThread.start();
		int count = 0;

		while (strBrainOutput == "") { // wait and count time until jarvisBrainThread is dead
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
		}

		System.out.println("jarvisBrainThread running time  : " + count + " milliseconds.");
		return strBrainOutput; // main Jarvis's output

	}
}