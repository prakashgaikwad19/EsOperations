 package esQueries;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class BoolQueryTest {

	public static void main(String[] args) {
		@SuppressWarnings("deprecation")
		RestHighLevelClient client=new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		
		SearchRequest searchRequest=new SearchRequest("employee_data");
		SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
		BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();
		boolQueryBuilder
					.must(QueryBuilders.matchQuery("empNo", 1))
					.must(QueryBuilders.matchQuery("empName", "emp1"));
		searchSourceBuilder.query(boolQueryBuilder);
		searchRequest.source(searchSourceBuilder);
		
		SearchResponse searchResponse;
		try {
			searchResponse=client.search(searchRequest, RequestOptions.DEFAULT);
			SearchHits hits=searchResponse.getHits();
			for(SearchHit hit:hits) {
				UpdateRequest updateRequest=new UpdateRequest("employee_data",hit.getId());
				Map<String, Object> jsonMap=new HashMap<String, Object>();
				jsonMap.put("empName", "unknown");
				updateRequest.doc(jsonMap);
				UpdateResponse updateResponse=client.update(updateRequest, RequestOptions.DEFAULT);
				System.out.println(updateResponse.getResult());
				/*DeleteRequest deleteRequest=new DeleteRequest("employee_data",hit.getId());
				DeleteResponse deleteResponse;
				
				try {
					deleteResponse=client.delete(deleteRequest, RequestOptions.DEFAULT);
					System.out.println(deleteResponse.getId()+"   "+deleteResponse.getResult());
				} catch (IOException e) {
					e.printStackTrace();
				}*/
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
