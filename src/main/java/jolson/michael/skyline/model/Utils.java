package jolson.michael.skyline.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
	private Utils() {}
	
	public static void initializeRoleToIntegerMap(Map<Role, Integer> map, boolean putNull) {
		map.put(Role.ACCOUNTANT, 0);
		map.put(Role.BUILDER, 0);
		map.put(Role.CONTRACTOR, 0);
		map.put(Role.EXECUTIVE, 0);
		map.put(Role.INVESTOR, 0);
		map.put(Role.LAWYER, 0);
		
		if (putNull) {
			map.put(null, 0);
		}
	}
	
	public static <K> Map<K, Integer> getCounts(List<K> list) {
		Map<K, Integer> counts = new HashMap<>();
		for (K k : list) {
			Integer value = counts.get(k);
			counts.put(k, value == null ? 1 : value + 1);
		}
		
		return counts;
	}
}
