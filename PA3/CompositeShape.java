import java.awt.Graphics2D;


public interface CompositeShape {
	public void draw(Graphics2D g2);
	public CompositeShape getBounds();
	public int getWidth();
	public int getHeight();
}
