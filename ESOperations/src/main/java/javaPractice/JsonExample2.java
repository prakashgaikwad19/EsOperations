package javaPractice;

import org.json.JSONObject;

public class JsonExample2 {

	public static void main(String[] args) {
		String string="{  \"firstName\": \"John\",  \"lastName\": \"Mark\",  \"gender\": \"man\",  \"age\": 30,  \"address\": {    \"streetAddress\": \"150\",    \"city\": \"San Diego\",    \"state\": \"CA\",    \"postalCode\": \"263142\"  }}";  
		JSONObject json = new JSONObject(string);
		
		JSONObject newjson=json.getJSONObject("address");
		
		JSONObject emptyJson=new JSONObject();
		JSONObject emptyJson1=new JSONObject();
		
		System.out.println(json);
		System.out.println(newjson);
		if(json.equals(newjson)) {
			System.out.println("true");
		}else {
			System.out.println("false");
		}
	}

}
