import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class Block extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x,y;
	int width, height;
	Color c;
	Color c2;
	Block(int width, int height, Color c,Color c2){
		this.c=c;
		this.c2=c2;
		this.width=width;
		this.height=height;
	}
	abstract void collision();
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(c);
		g.fillRect(0, 0, width, height);
		g.setColor(c2);
		g.fillRect(8, 8, width-16, height-16);
		g.setColor(Color.black);
		g.drawRect(0, 0, width, height);
	}
}

class WhiteBlock extends Block{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Color c = Color.white;
	WhiteBlock(int w, int h) {
		super(w,h,c, new Color(249, 249, 249));
	}
	void collision() {}
}

class YelloBlock extends Block{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Color c = Color.yellow;
	YelloBlock(int w, int h) {
		super(w,h,new Color(249, 208, 15),new Color(240, 174, 44));
	}
	void collision() {}
		
}