package jolson.michael.skyline.model;

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
}
