package com.jelly.waimai.util;

import java.lang.reflect.Field;

import com.jelly.waimai.R;

public class GetPic {

	public static int getPic(String pid) {
		Field f;
		try {
			f = R.drawable.class.getField(pid);
			return f.getInt(null);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return 0;	
	}
}
