package utils;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.util.HashMap;

/**
 * Flyweight gérant les différents éléments utilisés dans la zone de dessin.
 * Utilisable avec les {@link Paint} et avec les {@link BasicStroke} des figures
 * Gère les éléments dans une HashMap<Integer, T> dont la clé correspond au
 * hashCode de l'élément correspondant. Lorsque l'on demande un élément au
 * Factory, celui ci le recherche dans sa table de hachage : Si l'élément n'est
 * pas déjà présent dans la table de hachage il est ajouté, puis renvoyé, s'il
 * est déjà présent dans la table de hachage il est directement renvoyé et celui
 * demandé est alors destructible par le garbage collector.
 *
 * @author davidroussel
 */
public class FlyweightFactory<T>
{
	/**
	 * La table de hachage contenant les différentes paires <hashcode,elt> et
	 * dont les clés sont les hashCode des différents élements.
	 */
	protected HashMap<Integer, T> map;

	/**
	 * Constructeur d'un FlyweightFactory.
	 * Initialise la {@link HashMap}
	 */
	public FlyweightFactory()
	{
		map = new HashMap<Integer, T>();
	}

	/**
	 * Obtention d'un élément (nouveau ou pas) : Lorsque l'élément demandé est
	 * déjà présent dans la table on le renvoie directement sinon celui ci est
	 * ajouté à la table avant d'être renvoyé
	 *
	 * @param element l'élément demandé [celui ci pourra être détruit par le
	 *            garbage collector si il en existe déjà un équivalent dans la
	 *            table]
	 * @return l'élément demandé en provenance de la table
	 */
	public T get(T element)
	{
		if (element != null)
		{
			int hash = 0;
			hash = element.hashCode();
			if (map.containsKey(hash)) {
				return element;
			}
			else {
				map.put(hash, element);
				return element;
			}
		}
		else
		{
			return null;
		}
	}

	/**
	 * Nettyage de tous les éléments
	 */
	public void clear()
	{
		map.clear();
	}
}
