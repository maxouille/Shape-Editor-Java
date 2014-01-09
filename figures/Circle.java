package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Point2D;

public class Circle extends AbstractFigure {

	private int radius;
	private Point2D center;
	
	
	/**
	 * @param stroke
	 * @param edge
	 * @param fill
	 * @param radius
	 * @param center
	 */
	public Circle(BasicStroke stroke, Paint edge, Paint fill, int radius,
			Point2D center) {
		super(stroke, edge, fill);
		this.radius = radius;
		this.center = center;
	}

	/**
	 * @return the radius
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * @param radius the radius to set
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * @param center the center to set
	 */
	public void setCenter(Point2D center) {
		this.center = center;
	}

	@Override
	public void setLastPoint(Point2D p) {
		// TODO Auto-generated method stub

	}

	@Override
	public Point2D getCenter() {
		return center;
	}

}
