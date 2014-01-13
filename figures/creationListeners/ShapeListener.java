package figures.creationListeners;

import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import figures.Drawing;

public class ShapeListener extends AbstractCreationListener {
		/**
		 * @param model
		 * @param infoLabel
		 * @param nbSteps
		 */
		public ShapeListener(Drawing model, JLabel infoLabel, int nbSteps) {
			super(model, infoLabel, nbSteps);
			tips[0] = "Bouton Gauche + drag pour commencer la figure";
			tips[1] = "Relacher le bouton pour finir la figure";
			updateTip();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			startFigure(e);
			updateTip();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			endFigure(e);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (currentStep == 1) {
				currentFigure.setLastPoint(e.getPoint());
				drawingModel.update();
				updateTip();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}

	}