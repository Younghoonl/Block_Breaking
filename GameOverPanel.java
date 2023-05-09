import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameOverPanel extends Background {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean flickering = false;
	Thread t;
	StartPanel start;
	GameOverPanel(StartPanel start){
		this.start=start;
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
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_SPACE) {
					t.interrupt();
					setVisible(false);
					start.setVisible(true);
					start.t.start();
					start.requestFocusInWindow();
				}
			}
		});
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(255, 251, 235));
		g.setFont(new Font("Serif",Font.BOLD,60));
		g.drawString("Game Over", 140, 220);
		if (flickering) {
			g.setColor(Color.red);
			g.setFont(new Font("Serif",Font.BOLD,37));
			g.drawString("Press Spacebar",171, 451);
			g.setColor(new Color(216, 33, 72));
			g.drawString("Press Spacebar",170, 450);
		}
	}
}
