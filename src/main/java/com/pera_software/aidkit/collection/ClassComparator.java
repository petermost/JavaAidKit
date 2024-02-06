package com.pera_software.aidkit.collection;

import java.util.Comparator;

public class ClassComparator implements Comparator<Class<?>> {

	@Override
	public int compare(Class<?> class1, Class<?> class2)
	{
		return class1.getName().compareTo(class2.getName());
	}

}
