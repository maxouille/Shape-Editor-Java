package figures.creationListeners;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JLabel;

import figures.Drawing;

public class PolygonShapeListener extends AbstractCreationListener {

	
	protected Point2D previousPoint;
	protected Point2D lastCreate;
	protected double distanceMini;
	
	public PolygonShapeListener(Drawing model, JLabel infoLabel, int nbSteps) {
		super(model, infoLabel, nbSteps);
		tips=new String[]{
				"Cliquez Bouton Gauche pour commencer la figure  ",
				"Cliquez Bouton Gauche pour placer un autre point  ",
				"Double cliquez pour terminer le polygone                  "};
		lastCreate=null;
		previousPoint=null;
		distanceMini=10;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		switch(currentStep){
		case 0:
			startFigure(arg0);
			lastCreate=arg0.getPoint();
			previousPoint=lastCreate;
			break;
		case 1:
			currentFigure.setLastPoint(previousPoint);
			lastCreate = previousPoint;
			drawingModel.update();
			nextStep();
			break;
		case 2:
			if(arg0.getPoint().distance(lastCreate)<distanceMini){
				endFigure(arg0);
			}else{
				currentFigure.setLastPoint(previousPoint);
				lastCreate = previousPoint;
				drawingModel.update();
			}
			break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		if(currentStep>=1){
			if(arg0.getPoint().distance(lastCreate)<distanceMini){
				if(!previousPoint.equals(lastCreate)){
					currentFigure.setLastPoint(lastCreate);
					previousPoint=lastCreate;
					drawingModel.update();
				}
			}else{
				if(!previousPoint.equals(arg0.getPoint())){
					currentFigure.setLastPoint(arg0.getPoint());
					previousPoint=arg0.getPoint();
					drawingModel.update();
				}
			}
		}
	}

}
