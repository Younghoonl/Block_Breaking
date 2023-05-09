import java.awt.*;



public abstract class GamePanel extends Background{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int w;
	Wall leftWall;
	Wall rightWall;
	Wall upWall;
	GamePanel(){
		setLayout(null);
		w = 15;
		leftWall = new Wall(0,w,w,getHeight()-w);
		rightWall = new Wall(getWidth()-w, w,w, getHeight()-w);
		upWall = new Wall(0, 0, getWidth(), w);		
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		leftWall = new Wall(0,w,w,getHeight()-w);
		leftWall.draw(g);
		rightWall = new Wall(getWidth()-w*2, w,w, getHeight()-w);
		rightWall.draw(g);
		upWall = new Wall(0, 0, getWidth(), w);
		upWall.draw(g);
		
	}
}

class Wall{
	int x,y;
	int width, height;
	Color color;
	Wall(int x,int y, int width, int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		color = new Color(60, 64, 72);
	}
	void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(new Color(178, 178, 178));
		g.drawRect(x, y, width, height);
	}
}
