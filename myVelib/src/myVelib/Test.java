package myVelib;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Test {
	
	public static <K,V> Map<V,Set<K>> inverser(Map<K,V> map) {
		Map<V,Set<K>> result = new TreeMap<V,Set<K>>();
		for (Entry<K, V> e : map.entrySet()) { 
			if (result.containsKey(e.getValue())) 
				result.get(e.getValue()).add(e.getKey()); 
			else { TreeSet<K> set = new TreeSet<K>(); 
				set.add(e.getKey()); 
				result.put(e.getValue(), set); } 
			} 
		return result; 
		} 
	public static <K,V> Map<K,V> inverser2(Map<V,Set<K>> map) { 
		Map<K,V> result = new TreeMap<K,V>(); 
		for (Entry<V, Set<K>> e : map.entrySet()) 
			for (K k : e.getValue()) 
				result.put(k,e.getKey()); 
		return result; 
	} 
	public static void main(String...args) throws FileNotFoundException { 
		Map<String,Double> map1 = new TreeMap<String,Double>(); 
		map1.put("a", 1.0); map1.put("c", 2.0); 
		map1.put("h", 3.0); map1.put("f", 4.0); 
		map1.put("e", 3.0); map1.put("g", 4.0); 
		map1.put("e", 3.0); map1.put("d", 2.0); 
		map1.put("b", 1.0); 
		System.out.println(map1); 
		Map<Double,Set<String>> map2 = inverser(map1);
		System.out.println(map2);
		Map<String,Double> map3 = inverser2(map2); 
		System.out.println(map3);
		
		// {a=1.0, b=1.0, c=2.0, d=2.0, e=3.0, f=4.0, g=4.0, h=3.0} Map<Double,Set<String>> map2 = inverser(map1); System.out.println(map2); // {1.0=[a, b], 2.0=[c, d], 3.0=[e, h], 4.0=[f, g]} Map<String,Double> map3 = inverser2(map2); System.out.println(map3); // {a=1.0, b=1.0, c=2.0, d=2.0, e=3.0, f=4.0, g=4.0, h=3.0} }
	}

}
