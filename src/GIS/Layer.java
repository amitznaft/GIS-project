package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Layer implements GIS_layer  {
	
	private DataLayer m;
	private ArrayList<GIS_element> array = new ArrayList<GIS_element>();
	
	public Layer () {
		m = new DataLayer();
	}

	
	public int size() {
		return array.size();
	}

	
	public boolean isEmpty() {
		return array.isEmpty();
	}


	public boolean contains(Object o) {
		return array.contains(o);
	}


	public Iterator<GIS_element> iterator() {
		return this.array.iterator();
	}


	public Object[] toArray() {
		return array.toArray();
	}


	public <T> T[] toArray(T[] a) {
		return array.toArray(a);
	}


	public boolean add(GIS_element e) {
		return array.add(e);
	}

	public boolean remove(Object o) {
		return array.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return array.containsAll(c);
	}

	public boolean addAll(Collection<? extends GIS_element> c) {
		return array.addAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return array.retainAll(c);
	}

	public boolean removeAll(Collection<?> c) {
		return array.removeAll(c);
	}

	public void clear() {
		array.clear();
		
	}
		
	public Meta_data get_Meta_data() {
		return (Meta_data) m;
	}
	
	

}
