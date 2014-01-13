package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Point2D;

public class Polygon extends AbstractFigure {
	
	/**
	 * @param stroke
	 * @param edge
	 * @param fill
	 */
	public Polygon(BasicStroke stroke, Paint edge, Paint fill) {
		super(stroke, edge, fill);
		/*shape = new Pol;*/
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
