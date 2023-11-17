package esOps.aggregation;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;

@SuppressWarnings("deprecation")
public class Aggreagation1 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		RestHighLevelClient client=new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		SearchRequest searchRequest=new SearchRequest("employee_data");
		SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery())
		.aggregation(AggregationBuilders.terms("aaaa").field("JoiningLocation"));
		searchSourceBuilder.query(QueryBuilders.boolQuery()
				.must(QueryBuilders.matchQuery("age", "23")));
		searchRequest.source(searchSourceBuilder);
		
		try {
			SearchResponse searchResponse=null;
			searchResponse=client.search(searchRequest, RequestOptions.DEFAULT);
			Terms agg1=searchResponse.getAggregations().get("aaaa");
			for(Terms.Bucket bucket:agg1.getBuckets()) {
				System.out.println(bucket.getKey()+" "+bucket.getDocCount());
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
