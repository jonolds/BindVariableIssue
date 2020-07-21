package aaa.cubeup.cube.utils;

import java.util.Comparator;

import aaa.cubeup.cube.data.nonentities.ZZZZButtonable;


public class ButtonComparator implements Comparator<ZZZZButtonable> {
	@Override
	public int compare(ZZZZButtonable obj, ZZZZButtonable other) {
		if (compareButtonRank(obj, other) != 0) return compareButtonRank(obj, other);
		return compareName(obj, other);
	}
	public static int compareButtonRank(ZZZZButtonable obj, ZZZZButtonable other) {
		return Integer.compare(obj.cattype().buttonRank, other.cattype().buttonRank);
	}
	public static int compareName(ZZZZButtonable obj, ZZZZButtonable other) {
		return obj.findName().compareTo(other.findName());
	}
}
