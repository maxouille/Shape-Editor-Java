package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Circle extends AbstractFigure {

	private Double h;
	private Point2D upper_left;
	
	
	/**
	 * @param stroke
	 * @param edge
	 * @param fill
	 * @param radius
	 * @param center
	 */
	public Circle(BasicStroke stroke, Paint edge, Paint fill, Double h,
			Point2D upper_left) {
		super(stroke, edge, fill);
		this.upper_left = upper_left;
		this.h = h;
		instanceNumber++;
		shape = new Ellipse2D.Double(upper_left.getX(), upper_left.getY(), h, h);
	}

	@Override
	public void setLastPoint(Point2D p) {
		h = Math.abs(p.getX() - upper_left.getX());
		shape = new Ellipse2D.Double(upper_left.getX(), upper_left.getY(), h,h);
	}

	@Override
	public Point2D getCenter() {
		return new Point2D.Double(upper_left.getX()+(h/2), upper_left.getY()-(h/2));
	}

}
