import java.awt.*;
import javax.swing.*;


class Background extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(0,0,new Color(44, 51, 51),0,600,new Color(57, 91, 100)));
		g2.fillRect(0, 0, getWidth(), getHeight());
	}
	
}

public class HW5 extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StartPanel start;
	HW5(){
		setTitle("Java Homework5");
		setVisible(true);
		setSize(600,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setFocusable(false);
		
		StartPanel start2 =new StartPanel();
		start2.setBounds(0, 0, getWidth(), getHeight());
		start2.setFocusable(false);
		start2.setVisible(false);
		
		GameOverPanel gop = new GameOverPanel(start2);
		gop.setVisible(true);
		gop.setBounds(0, 0, getWidth(), getHeight());

		GameStage2 gs2 = new GameStage2(gop);
		gs2.setVisible(false);
		gs2.setBounds(0, 0, getWidth(), getHeight());
		
		
		GameStage1 gs1 = new GameStage1(gs2, gop);
		gs1.setVisible(false);
		gs1.setBounds(0, 0, getWidth(), getHeight());
		
		
		start =new StartPanel(gs1);
		start.setBounds(0, 0, getWidth(), getHeight());
		start.setFocusable(true);
		start.setVisible(true);
		System.out.println(getFocusOwner());
		add(start);
		add(gs1);
		add(gs2);
		add(gop);
		add(start2);
	}
	public static void main(String[] args) {
		new HW5();
	}

}
