package package1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings("deprecation")
public class FetchDataFromEs {

	public static void main(String[] args) {
		RestHighLevelClient client=new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		SearchRequest searchRequest=new SearchRequest("employee_data");
		SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
		BoolQueryBuilder query1 = QueryBuilders.boolQuery();
		query1
		.must(QueryBuilders.matchQuery("JoiningLocation", "Mumbai"))
		.must(QueryBuilders.matchQuery("empName", "emp1"));
		searchSourceBuilder.query(query1);
		/*.query(QueryBuilders.matchQuery("JoiningLocation", "Mumbai"))
		.query(QueryBuilders.matchQuery("empName", "emp1"))*/
		//;
	
		searchRequest.source(searchSourceBuilder);
		
		SearchResponse searchResponse;
		ArrayList<Object> names=new ArrayList<Object>();
		String names1="";
		String locan="";
		String empNo="";

		try {
			searchResponse=client.search(searchRequest, RequestOptions.DEFAULT);
			SearchHits hits=searchResponse.getHits();
			for(SearchHit hit:hits) {
				
				JSONObject json=new JSONObject(hit.getSourceAsMap());
				names1=names1.concat(json.getString("empName")+",");
				locan=locan.concat(json.getString("JoiningLocation")+",");
			//	System.out.println(json.getInt("empNo"));
				empNo=empNo.concat(Integer.toString(json.getInt("empNo"))+",");

				/*names.add(json.getString("empName"));
				names.add(json.getString("JoiningLocation"));
				names.add(json.getInt("empNo")); */
				
				/*JSONArray json=new JSONArray(Arrays.asList(hit.getSourceAsMap()));
				System.out.println(json);*/

				
				/*JSONObject json=new JSONObject(hit.getSourceAsString());
				System.out.println("Json obj1"+json);
				System.out.println(json.getString("JoiningLocation"));*/
			}
			/*System.out.println("ArrayList"+names);

			String namesString=names.toString();
			System.out.println("String"+namesString);
			
			HashMap<String, String> namesMap=new HashMap<String, String>();
			namesMap.put("map", namesString);
			System.out.println("Map"+namesMap);
			
			JSONObject obj=new JSONObject(namesMap);
			System.out.println("Json "+obj);*/
			
			HashMap<String, String> namesMap=new HashMap<String, String>();
			namesMap.put("nameV", names1);
			namesMap.put("locanV", locan);
			namesMap.put("empNoV", empNo);
			System.out.println("map values"+namesMap);
			
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
