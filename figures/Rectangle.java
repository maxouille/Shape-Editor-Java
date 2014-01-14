package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends AbstractFigure {
	
	protected Double x;
	protected Double y;
	protected Double w;
	protected Double h;
	
	/**
	 * @param stroke
	 * @param edge
	 * @param fill
	 */
	public Rectangle(BasicStroke stroke, Paint edge, Paint fill, Double x, Double y) {
		super(stroke, edge, fill);
		this.x = x;
		this.y = y;
		w = 0.0;
		h = 0.0;
		shape = new Rectangle2D.Double(x, y, 0,0);
	}
	
	@Override
	public void setLastPoint(Point2D p) {
		w = Math.abs(p.getX() - x);
		h = Math.abs(p.getY() - y);
		shape = new Rectangle2D.Double(x,y,w,h);
	}

	@Override
	public Point2D getCenter() {
		return new Point2D.Double((x+w)/2, (y-h)/2);
	}

}
