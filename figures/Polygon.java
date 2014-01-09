package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.util.List;

public class Polygon extends AbstractFigure {
	
	
	private List<Point2D> l;
	
	/**
	 * @param stroke
	 * @param edge
	 * @param fill
	 * @param l
	 */
	public Polygon(BasicStroke stroke, Paint edge, Paint fill, List<Point2D> l) {
		super(stroke, edge, fill);
		this.l = l;
	}

	/**
	 * @return the l
	 */
	public List<Point2D> getL() {
		return l;
	}

	/**
	 * @param l the l to set
	 */
	public void setL(List<Point2D> l) {
		this.l = l;
	}

	@Override
	public void setLastPoint(Point2D p) {
		// TODO Auto-generated method stub

	}

	@Override
	public Point2D getCenter() {
		// TODO Auto-generated method stub
		return null;
	}

}
