package net.bodrykh.raspberrypi;

import java.util.Random;

public class JBrain implements Runnable {
	private Thread jarvisBrainThread;
	private String strUserInput;
	private String strJarvisOutput = "Welcome Roman!";
	private String inMindNow;
	private int voiceType;

	private JVoice jVoice = new JVoice(strJarvisOutput, voiceType);

	JBrain(int voiceType) {
		this.voiceType = voiceType;
		jVoice.toSpeak(strJarvisOutput);

		inMindNow = "and a random decision is";
	}

	@Override
	public void run() {
		if (strUserInput == null) {
			strJarvisOutput = "Welcome Roman!";
		} else {
			strJarvisOutput = mind(strUserInput);
			System.out.println("inside - JBrainThread : after mind--- " + "--- strUserInput: " + strUserInput
					+ "  ---+--- strToSpeak: " + strJarvisOutput);
			jVoice.toSpeak(strJarvisOutput);
			System.out.println("inside - JBrainThread : after voice--- " + "--- strUserInput: " + strUserInput
					+ "  ---+--- strToSpeak: " + strJarvisOutput);
		}
	}

	private boolean randomDecision() {
		Random rand = new Random();

		int n = rand.nextInt(100) + 1;
		if (n < 50)
			return true;
		return false;
	}

	private String mind(String str) {

		String inStr = str + ". received " + inMindNow + " " + randomDecision();
		return inStr;
	}

	public String think(String strUserInput) {
		this.strUserInput = strUserInput;
		jarvisBrainThread = new Thread(this, "JarvisBrainThread");
		jarvisBrainThread.setDaemon(false);
		jarvisBrainThread.start();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strJarvisOutput;

	}
}