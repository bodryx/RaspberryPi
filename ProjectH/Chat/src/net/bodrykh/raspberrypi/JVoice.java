package net.bodrykh.raspberrypi;

public class JVoice implements Runnable {
	private Thread jarvisVoiceThread;
	private String strToSpeak;
	private int voiceType;

	public JVoice(String strToSpeak, int voiceType) {
		this.strToSpeak = strToSpeak;
		this.voiceType = voiceType;
	}

	private void speak(String strToSpeak, int voiceType) {
		if (strToSpeak != null) {
			strToSpeak = strToSpeak.replace(" ", "_");
			Process p;
			try {
				p = Runtime.getRuntime().exec("espeak -s120 " + strToSpeak + " 2>/dev/null");
				p.waitFor();
				System.out.println("exit: " + p.exitValue());
				p.destroy();
			} catch (Exception e) {
			}
		}
	}

	public void setStrToSpeak(String strToSpeak) {
		this.strToSpeak = strToSpeak;
	}

	public void setVoiceType(int voiceType) {
		this.voiceType = voiceType;
	}

	@Override
	public void run() {
		speak(strToSpeak, voiceType);
	}

	public void toSpeak(String strToSpeak) {
		this.strToSpeak = strToSpeak;
		jarvisVoiceThread = new Thread(this, "JarvisVoiceThread");
		jarvisVoiceThread.setDaemon(false);
		jarvisVoiceThread.start();
	}

}
