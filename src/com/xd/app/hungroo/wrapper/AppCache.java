package com.xd.app.hungroo.wrapper;

import java.util.HashMap;
import java.util.Map;

public class AppCache {

	private static Map cacheMap = new HashMap<String, Object>();

	public static Map getCacheMap() {
		return cacheMap;
	}

	public static void setCacheMap(Map cacheMap) {
		AppCache.cacheMap = cacheMap;
	}

}
