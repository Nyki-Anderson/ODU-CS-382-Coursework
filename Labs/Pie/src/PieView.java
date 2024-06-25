

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;



/**
 *  A rectangular panel containing a picture of a pie.
 *  
 * @author zeil
 *
 */
public class PieView extends javax.swing.JPanel {
	
	/**
	 * The pie to draw
	 */
	private Pie pie;
	
	/**
	 * Create a new panel. The panel monitors the position
	 * of the mouse and sets the pie's slice angle to track
	 * the mouse position.
	 */
	public PieView()
	{
		pie = new Pie();
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				trackMouse (e.getX(), e.getY());
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});
		
		setPreferredSize(new Dimension(200,200));
	}
	
	/**
	 * Draw the panel
	 * 
	 * @param g the graphics context
	 */
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		Dimension d = getSize();
		pie.radius = Math.min(d.width, d.height)/2;
		int x = 0;
		int y = 0;
		if (d.width > d.height)
			x = (d.width - d.height) / 2;
		else
			y = (d.height - d.width) / 2;
		pie.center = new Point(x+pie.radius,y+pie.radius);
		pie.draw2 ((Graphics2D)g);
	}
	
	/**
	 * Compute the angle from the center of the pie to the
	 * location (x,y) and set the pie's slice accordingly.
	 * 
	 * @param x
	 * @param y
	 */
	private void trackMouse (int x, int y)
	{
		int x0 = pie.center.x;
		int y0 = pie.center.y;
		double theta = Math.atan2((double)(y-y0), (double)(x-x0));
		int degrees = -(int)((theta/2.0/Math.PI) * 360.0);
		pie.sliceAngle = degrees;
		repaint();
	}
	
	/**
	 * Get the pie being drawn.
	 * 
	 * @return the pie
	 */
	public Pie getPie()
	{
		return pie;
	}

}