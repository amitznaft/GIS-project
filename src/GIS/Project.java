package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Project implements GIS_project {
	private ArrayList<GIS_layer> array = new ArrayList<GIS_layer>();
	private DataProject m;
	
	public Project () {
		m = new DataProject();
	}
	@Override
	public int size() {
		return array.size();
	}

	@Override
	public boolean isEmpty() {
		return array.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return array.contains(o);
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		return array.iterator();
	}

	@Override
	public Object[] toArray() {
		return array.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return array.toArray(a);
	}

	@Override
	public boolean add(GIS_layer e) {
		return array.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return array.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return array.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		return array.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return array.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return array.removeAll(c);
	}

	@Override
	public void clear() {
		array.clear();

	}

	@Override
	public Meta_data get_Meta_data() {
		return (Meta_data) m;
	}

}
