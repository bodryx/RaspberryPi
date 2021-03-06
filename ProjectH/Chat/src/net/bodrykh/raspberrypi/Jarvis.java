package net.bodrykh.raspberrypi;

public class Jarvis {
	private String voiceTypeOptions;

	private JBrain brain;

	public Jarvis(String voiceTypeOptions) {
		this.voiceTypeOptions = voiceTypeOptions;	
		brain = new JBrain(this.voiceTypeOptions);
	}

	public String getFeedbackFromJarvis(String strJarvisInput) {
		return brain.mindHandler(strJarvisInput);
	}

}