package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import utils.FlyweightFactory;
import figures.enums.FigureType;
import figures.enums.LineType;

/**
 * Classe contenant l'ensemble des figures à dessiner (LE MODELE)
 * @author davidroussel
 *
 */
public class Drawing extends Observable implements Iterable<AbstractFigure>
{
	/**
	 * Liste des figures à dessiner
	 */
	private Vector<AbstractFigure> figures;

	/**
	 * Le type de figure à créer
	 */
	private FigureType type;

	/**
	 * La couleur de remplissage courante
	 */
	private Paint fillPaint;

	/**
	 * La couleur de trait courante
	 */
	private Paint edgePaint;

	/**
	 * La largeur de trait courante
	 */
	private float edgeWidth;

	/**
	 * Le type de trait courant (sans trait, trait plein, trait pointillé)
	 */
	private LineType edgeType;

	/**
	 * Les caractétistique à appliquer au trait en fonction de
	 * {@link #edgeType} et {@link #edgeWidth}
	 */
	private BasicStroke stroke;

	/**
	 * La factory des couleurs de remplissage
	 */
	private FlyweightFactory<Paint> fillPaintFactory;

	/**
	 * La factory des couleurs de trait
	 */
	private FlyweightFactory<Paint> edgePaintFactory;

	/**
	 * La factory des types de traits
	 */
	private FlyweightFactory<BasicStroke> edgeTypeFactory;

	/**
	 * Constructeur de modèle de dessin
	 */
	public Drawing()
	{
		figures = new Vector<AbstractFigure>();
		fillPaintFactory = new FlyweightFactory<Paint>();
		edgePaintFactory = new FlyweightFactory<Paint>();
		edgeTypeFactory = new FlyweightFactory<BasicStroke>();
		fillPaint = null;
		edgePaint = null;
		edgeWidth = 1.0f;
		edgeType = LineType.SOLID;
		stroke = new BasicStroke(edgeWidth, BasicStroke.CAP_ROUND,
		        BasicStroke.JOIN_ROUND);

		System.out.println("Drawing model created");
	}

	/**
	 * Nettoyage avant destruction
	 */
	@Override
	protected void finalize()
	{
		// Aide au GC
		figures.clear();
		fillPaintFactory.clear();
		edgePaintFactory.clear();
	}

	/**
	 * Mise à jour du ou des {@link Observer} qui observent ce modèle. On place
	 * le modèle dans un état "changé" puis on notifie les observateurs.
	 */
	public void update()
	{
		setChanged();
		notifyObservers();
	}

	/**
	 * Mise en place d'un nouveau type de figure à générer
	 * @param type le nouveau type de figure
	 */
	public void setType(FigureType type)
	{
		this.type = type;
	}

	/**
	 * Mise en place d'une nouvelle couleur de remplissage
	 * @param fillPaint la nouvelle couleur de remplissage
	 */
	public void setFillPaint(Paint fillPaint)
	{
		this.fillPaint = fillPaint;
		/*
		 * Au moment où on initiera une nouvelle figure, on mettra ce
		 * paint dans la fillPaintFactory
		 */
	}

	/**
	 * Mise en place d'une nouvelle couleur de trait
	 * @param edgePaint la nouvelle couleur de trait
	 */
	public void setEdgePaint(Paint edgePaint)
	{
		this.edgePaint = edgePaint;
		/*
		 * Au moment où on initiera une nouvelle figure, on mettra ce
		 * paint dans la edgePaintFactory
		 */
	}

	/**
	 * Mise en place d'un nouvelle épaisseur de trait
	 * @param width la nouvelle épaisseur de trait
	 */
	public void setEdgeWidth(float width)
	{
		if (edgeWidth != width)
		{
			edgeWidth = width;
			rebuildStroke();
		}
	}

	/**
	 * Mise en place d'un nouvel état de ligne pointillée
	 * @param type le nouveau type de ligne
	 */
	public void setEdgeType(LineType type)
	{
		if (edgeType != type)
		{
			edgeType = type;
			rebuildStroke();
		}
	}

	/**
	 * Reconstruction du {@link #stroke} résultant des modification soit de
	 * {@link #edgeWidth}, soit de {@link #edgeType}.
	 */
	private void rebuildStroke()
	{
		switch (edgeType)
		{
			case NONE:
				stroke = null;
				break;
			case SOLID:
				stroke = new BasicStroke(edgeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
				break;
			case DASHED:
				final float dash1[] = { 2*edgeWidth };
				stroke = new BasicStroke(edgeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, edgeWidth, dash1, 0.0f);
				break;
			default:
				stroke = null;
				break;
		}
	}

	/**
	 * Initialisation d'une figure de type {@link #type} au point p et ajout
	 * de cette figure à la liste des {@link #figures}
	 * @param p le point où initialiser la figure
	 * @return la nouvelle figure créée à x et y avec les paramètres courants
	 */
	public AbstractFigure initiateFigure(Point2D p)
	{
		/*
		 * Maintenant que l'on s'apprête effectivement à créer une figure
		 * on ajoute les Paints et le Stroke aux factories
		 */
		fillPaintFactory.get(fillPaint);
		edgePaintFactory.get(edgePaint);
		edgeTypeFactory.get(stroke);

		/*
		 * Obtention de la figure correspondant au type de figure choisi
		 * grâce à type.getFigure(...)
		 */
		
		AbstractFigure f = type.getFigure(stroke, edgePaint, fillPaint, p);


		/*
		 * Ajout de la figure à #figures si !null et MAJ
		 */
		if (f != null) {
			figures.add(f);
		}
		else {
			System.out.println("null figure");
		}
		return null; // <-- Celui qui laisse ça est un abruti!
	}

	/**
	 * Obtention de la dernière figure (implicitement celle qui est en cours
	 * de dessin)
	 * @return la dernière figure du dessin
	 */
	public AbstractFigure getLastFigure()
	{
		return figures.lastElement();
	}

	/**
	 * Obtention de la dernière figure contenant le point p.
	 * @param p le point sous lequel on cherche une figure
	 * @return une référence vers la dernière figure contenant le point p
	 * ou à défaut null.
	 */
	public AbstractFigure getFigureAt(Point2D p)
	{
		for (ListIterator<AbstractFigure> it = reverseIterator(); it.hasPrevious();) {
			AbstractFigure f = it.previous();
			if (f.contains(p)) {
				return f;
			}
		}
		return null;
	}

	/**
	 * Retrait de la dernière figure (sera déclencé par une action undo)
	 */
	public void removeLastFigure()
	{
		figures.remove(figures.size()-1);
	}

	/**
	 * Effacement de toutes les figures (sera déclenché par une action clear)
	 */
	public void clear()
	{
		figures.removeAllElements();
	}

	/**
	 * Accès aux figures
	 * @return l'itérateur sur les figures du dessin
	 */
	@Override
	public Iterator<AbstractFigure> iterator()
	{
		return figures.iterator();
	}

	/**
	 * Accès aux figures en commençant par la dernière
	 * @return un {@link ListIterator} permettant de parcourir les figures
	 * à l'envers
	 */
	public ListIterator<AbstractFigure> reverseIterator()
	{
		return figures.listIterator(figures.size()-1);
	}
}
