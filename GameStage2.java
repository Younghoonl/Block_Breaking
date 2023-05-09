import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class GameStage2 extends GamePanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int width, height;
	LinkedList<Block> blocks;
	LinkedList<Ball> balls;
	BallBoard board;
	Thread t;
	GameOverPanel gop;
	GameStage2(GameOverPanel gop){
		this.gop=gop;
		blocks = new LinkedList<>();
		width = 91; // block의 넓이
		height = 50; // block의 높이
		balls = new LinkedList<>();
		
		Ball firstBall = new Ball(300);
		balls.add(firstBall);
		
		board = new BallBoard(250-5);
		
		t = new Thread(this);

		for (int i=0;i<6;i++) {
			for (int j=0;j<6;j++) {
				Block bl;
				if ((int)(Math.random()*2) == 0) bl = new YelloBlock(width-w/2, height-w/2);
				else bl = new WhiteBlock(width-w/2, height-w/2);
				bl.x=w+width*j+8;
				bl.y = w+ height*i+8;
				bl.setBounds(bl.x, bl.y, width-w/2, height-w/2);
				blocks.add(bl);
				add(bl);
			}
		}
		

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int v = 0;
				if (e.getKeyCode() == KeyEvent.VK_LEFT) v=-9;
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) v=9;
				if (v + board.getX()< w || v + board.getX() > getWidth() - w - board.width) return;
				board.update(v);
				
				repaint();
			}
		});
		
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Ball b:balls) {
			b.draw(g);
		}
		board.draw(g);
	}
	@Override 
	public void run() {
		while (true) {
			try {
				Thread.sleep(15);
				Iterator<Ball> iter = balls.iterator();
				while (iter.hasNext()) {
					Ball b= (Ball) iter.next();
					if (b.blockCollision(blocks, balls)) break;
					b.wallCollision(leftWall, rightWall, upWall);
					b.boardCollision(board);
					if (b.bottomCollision(getSize())) iter.remove();
					b.update();
					repaint();
				}
				if (balls.size()==0 || blocks.size()==0) {
					// 게임 종료
					setVisible(false);
					t.interrupt();
					setVisible(false);
					gop.setVisible(true);
					gop.t.start();
					gop.requestFocusInWindow();
					try {
						File file = new File("sound\\BottomCollision.wav");
						Clip clip = AudioSystem.getClip();
						clip.open(AudioSystem.getAudioInputStream(file));
			             clip.start();
					} catch (Exception e) {}
				}
				
				
			} catch (InterruptedException e) {return;}
			
		}
	}
}
