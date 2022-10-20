package sylaires.invasion.util;

import java.lang.reflect.Field;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnemyUtil {
	
	public static Object getPrivateField(String name, @SuppressWarnings("rawtypes") Class clazz, Object obj) {
		Field field;
		Object o = null;
		
		try {
			field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			o = field.get(obj);
			
		} catch(NoSuchFieldException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return o;
	}

}
