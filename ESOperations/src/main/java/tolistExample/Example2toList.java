package tolistExample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Example2toList {

	public static void main(String[] args) {
		JSONArray tecs=new JSONArray();
		tecs.put("redis");
		tecs.put("kafka");
		tecs.put("docker");
		tecs.put("elastic");
		
		System.out.println("tecs json array: "+tecs);
		
		List<Object> lis=toList(tecs);
		
		for(Object l:lis) {
			System.out.println(l);
		}
		System.out.println(lis);

	}

	private static List<Object> toList(JSONArray array) throws JSONException {
		List<Object> list=new ArrayList<Object>();
		for(int i=0;i<array.length();i++) {
			Object value=array.get(i);
			if(value instanceof JSONArray) {
				value=toList((JSONArray) value);
			}
			else if(value instanceof JSONObject) {
				value=toMap((JSONObject)value);
			}
			list.add(value);
		}
		return list;
	}

	private static Map<String, Object> toMap(JSONObject object) throws JSONException{
		Map<String, Object> map=new HashMap<String, Object>();
		
		Iterator<String> keysItr=object.keys();
		while(keysItr.hasNext()) {
			String key=keysItr.next();
			Object value=object.get(key);
			if(value instanceof JSONArray) {
				value=toList((JSONArray) value);
			}
			else if(value instanceof JSONObject) {
				value=toMap((JSONObject)value);
			}
			map.put(key, value);
			
		}
		return map;
	}

}
