package testCode;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.json.JSONObject;

public class InsertJson {

	public static void main(String[] args) {

		RestHighLevelClient client=new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		
		String str = "{\r\n" + 
				"    \"network\":\"1\",\r\n" + 
				"    \"adminName\":\"admin2\",\r\n" + 
				"    \"userId\":\"admin@2\",\r\n" + 
				"    \"password\":\"password2\"\r\n" + 
				"}";
		JSONObject json = new JSONObject(str);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("json1", "json1");
		map1.put("json2", "json2");
		map.put("jsonmap", map1);
		map.put("jsonString", json.toString());
		
		IndexRequest indexRequest=new IndexRequest("test_json");

		indexRequest
		.id("1")
		.source(map);
		IndexResponse indexResponse;
		try {
			indexResponse=client.index(indexRequest, RequestOptions.DEFAULT);
			System.out.println(indexResponse.getId()+"   "+indexResponse.getResult());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
		client.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
	}

}
