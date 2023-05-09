import java.awt.*;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

interface Objects{
	abstract void update();
	abstract void draw(Graphics g);
	abstract boolean iscollision();
}

public class Ball implements Objects{
	int x,y;
	int r;
	int vx=-4, vy=-4;
	Ball(int x){
		this.x=x;
		y=550;
		r=12;
	}
	Ball(int x, int y, int vx, int vy){
		this(x);
		this.y=y;
		this.vx=vx;
		this.vy=vy;
		r=12;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public void update() {
		x+=vx;
		y+=vy;
	}
	@Override
	public void draw(Graphics g) {
		g.fillOval((int)(x - r), (int)y, r, r);
		g.setColor(new Color(237, 228, 224));
		g.drawOval((int)(x - r),(int) y, r, r);
	}
	@Override
	public boolean iscollision() {
		return true;
	}
	boolean blockCollision(LinkedList<Block> blocks, LinkedList<Ball> balls) {
		Iterator<Block> iter = blocks.iterator();
		while (iter.hasNext()) {
			Block block = (Block) iter.next();
			if ((block.x<= x+r && x<=block.x+block.width) && (block.y+block.height >= y && block.y<=y)){
				try {
					File file = new File("sound\\block.wav");
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(file));
		             clip.start();
				} catch (Exception e) {}
				vy=-vy-1;
				iter.remove();
				block.setVisible(false);
				if (block instanceof YelloBlock) {
					balls.add(new Ball(x,y,5,4));
					balls.add(new Ball(x,y,4,5));
					return true; // yellow
				}

				
               
			}
		}
		return false;
	}
	
	void wallCollision(Wall leftWall, Wall rightWall,Wall upWall) {
		if (leftWall.x+leftWall.width*2 >= x) {
			vx=-vx;
		}
		if (rightWall.x <= x) {
			vx=-vx;
		}
		if (upWall.y+upWall.height >= y) {
			vy=-vy;
		}
	}
	
	void boardCollision(BallBoard board) {
		if ((board.x<= x && x<=board.x+board.width) && (board.y <= y+r)) {
			
			if (board.y+board.height < y+r) return;
			try {
				File file = new File("sound\\ballCollisionBlock.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(file));
	             clip.start();
			} catch (Exception e) {}
			vx=-vx;
			vy=-vy;
		}
	}
	
	boolean bottomCollision(Dimension d) {
		if (d.height<y) {
			return true;
		}
		return false;
	}
}

class BallBoard implements Objects{
	int x,y;
	int width, height;
	int v=5;;
	public BallBoard(int x) {
		this.x=x;
		y=550+12+1;
		width = 120;
		height = 15;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	@Override
	public void update() {
		x+=v;
	}
	public void update(int vx) {
		x+=vx;
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(162, 123, 92));
		g.fillRect(x, y, width, height);
		g.setColor(new Color(102, 90, 72));
		g.drawRect(x, y, width, height);
	}
	@Override
	public boolean iscollision() {
		return true;
	}
}