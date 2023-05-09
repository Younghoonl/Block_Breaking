import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class StartPanel extends Background {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean flickering = false;
	Thread t;
	StartPanel(){
		try {
			File file = new File("sound\\back.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
             clip.start();
		} catch (Exception e) {}
		t = new Thread(()->{
			while(true) {
				try {
					Thread.sleep(150);
					flickering = !flickering;
					repaint();
					
				} catch (InterruptedException e1) {
					return;
				}
			}
		});
		t.start();
	}
	StartPanel(GameStage1 gs1){
		try {
			File file = new File("sound\\back.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
             clip.start();
		} catch (Exception e) {}
		setFocusable(true);
		requestFocus();
		t = new Thread(()->{
			while(true) {
				try {
					Thread.sleep(150);
					flickering = !flickering;
					repaint();
					
				} catch (InterruptedException e1) {
					return;
				}
			}
		});
		t.start();
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_SPACE) {
					t.interrupt();
					setVisible(false);
					gs1.setVisible(true);
					gs1.t.start();
					gs1.requestFocusInWindow();
				}
			}
		});
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(255, 251, 235));
		g.setFont(new Font("Serif",Font.BOLD,45));
		g.drawString("Java Programming", 100, 120);
		g.drawString("Homework #5", 150, 170);
		g.setColor(new Color(231, 246, 242));
		g.setFont(new Font("Serif",Font.BOLD,60));
		g.drawString("Block Breaker", 100, 290);
		
		if (flickering) {
		g.setColor(new Color(255, 251, 235));
		g.setFont(new Font("Serif",Font.BOLD,27));
		g.drawString("Press Spacebar to play",151, 471);
		g.setColor(new Color(216, 33, 72));
		g.drawString("Press Spacebar to play",150, 470);
		}
		
	}
}
