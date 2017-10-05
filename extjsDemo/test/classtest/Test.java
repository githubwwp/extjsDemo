package classtest;

import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {
		String str = "{a: 3; b: \"3\"; c: false; d: null}";
		JSONObject json = JSONObject.fromObject(str);
		System.out.println(json.get("a").getClass().getSimpleName());
		System.out.println(json.get("b").getClass().getSimpleName());
		System.out.println(json.get("c").getClass().getSimpleName());
		System.out.println(json.get("d").getClass().getSimpleName());
	}
	
	
}
