package business.format;

import java.util.Comparator;

import org.apache.commons.beanutils.PropertyUtils;

import com.cc.framework.common.SortOrder;

public class ColumnComparator implements Comparator {

	/**
	 * Elément à comparer
	 */
	private	String	column = "";
	
	/**
	 * Ordre de tri
	 */
	private	SortOrder 	direction = SortOrder.NONE;

	/**
	 * Constructeur
	 * @param	column	colonne à trier
	 * @param	direction	Ordre de tri
	 */
	public ColumnComparator(String column, SortOrder direction) {
		super();
		
		this.column = column;
		this.direction = direction;
	}

	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		
		int result = 0;
			
		try {	
			String str1 = (String) PropertyUtils.getProperty(o1, column);
			String str2 = (String) PropertyUtils.getProperty(o2, column);
		
			if (direction.equals(SortOrder.ASCENDING)) {
				result = str1.compareTo(str2);
			} else if (direction.equals(SortOrder.DESCENDING)) {
				result = str2.compareTo(str1);
			}
		} catch (Throwable t) {}

		return result;
	}


}
