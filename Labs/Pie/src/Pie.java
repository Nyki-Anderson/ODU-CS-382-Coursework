

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * A model of a pie with a single slice taken out of it.
 * 
 * @author zeil
 *
 */
public class Pie {
	
	/**
	 * The size of the pie
	 */
	public int radius;
	
	/**
	 * Color used to draw the pie
	 */
	public Color baseColor;
	
	/**
	 * Color use to draw the empty space left behind by the slice
	 */
	public Color sliceColor;
	
	/**
	 * Angle of the slice, in degrees
	 */
	public int sliceAngle;
	
	/**
	 * Location of the center of the pie.
	 */
	public Point center;
	
	/**
	 * Create a new pie
	 */
	public Pie ()
	{
		radius = 20;
		center = new Point(20,20);
		sliceAngle = 0;
		baseColor = Color.red;
		sliceColor = Color.green;
	}
	
	
	/**
	 * Draw the pie on a graphics device
	 * 
	 * @param g the graphics device on which to draw
	 */
	public void draw2 (Graphics2D g)
	{
		g.setColor(baseColor);
		g.fillOval(center.x-radius, center.y-radius, 2*radius, 2*radius);
		g.setColor(sliceColor);
		g.fillArc(center.x-radius, center.y-radius, 2*radius, 2*radius, 0, sliceAngle);
	}

    @Override
    public String toString() {
        return "Yum!";
    }


}