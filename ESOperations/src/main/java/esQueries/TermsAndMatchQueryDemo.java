package esQueries;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class TermsAndMatchQueryDemo {

	public static void main(String[] args) {
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("employee_data");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
	//	searchSourceBuilder.query(QueryBuilders.termQuery("JoiningLocation", "delHi"));
    //	searchSourceBuilder.query(QueryBuilders.matchQuery("JoiningLocation", "delHi"));
		searchSourceBuilder.query(QueryBuilders.termsQuery("JoiningLocation", "delhi","mumbai","indore"));

		/*1.term query matches exact terms and terms query also matches exact terms but 
		    using terms query we can match more than one value for same field
		  2.if we write delHi instead of delhi, it gives different results. 
		  3.One exception for term and terms query that even
		  	index contain Delhi for joiningLocation but u have to mention 1st 
		  	letter small otherdwise, if we write Delhi instead delhi it dont matches
		  	e.g Delhi(This value is present in kiban)
		  		if we write Delhi in term/terms query it dont give result
		  		but if we write delhi in term/terms query it gives result
		  4. in matchQuery we can write delhi OR delHi and it gives same result*/ 
		
		searchRequest.source(searchSourceBuilder);
		
		try {
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			int totalHits = (int) searchResponse.getHits().getTotalHits().value;
			System.out.println("TotalHits "+ totalHits);
			if(searchResponse.getHits().getHits().length > 0) {
				System.out.println("Inside If conditions");
				for(int i=0; i<totalHits; i++){
					System.out.println(searchResponse.getHits().getHits()[i].getSourceAsMap());
				}
			}
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
