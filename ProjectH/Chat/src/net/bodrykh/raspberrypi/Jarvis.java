package net.bodrykh.raspberrypi;

public class Jarvis {
	private int voiceType;

	private JBrain brain = new JBrain(voiceType);

	public Jarvis(int voiceType) {
		this.voiceType = voiceType;
		go();
		
	}

	public void go() {

	}

	public String getFeedbackFrom(String strUserInput) {
		return brain.think(strUserInput);
	}

}