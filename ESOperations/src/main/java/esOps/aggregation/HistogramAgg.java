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
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class HistogramAgg {

	public static void main(String[] args) {
		RestHighLevelClient client=new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		SearchRequest searchRequest=new SearchRequest("employee_data");
		SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery())
			//.aggregation(AggregationBuilders.histogram("histogram").field("empNo").interval(2));
			.aggregation(AggregationBuilders.dateHistogram("dateHistogram")
			.field("joinningLocation")
			.dateHistogramInterval(DateHistogramInterval.YEAR));
			//.calendarInterval(DateHistogramInterval.YEAR));
		searchRequest.source(searchSourceBuilder);
		
		SearchResponse searchResponse;
		
		try {
			searchResponse=client.search(searchRequest, RequestOptions.DEFAULT);
			
			Histogram histogramAgg=searchResponse.getAggregations().get("histogram");
			for(Histogram.Bucket entry1:histogramAgg.getBuckets()) {
				System.out.println(entry1.getKey()+"   "+entry1.getDocCount());
			}
			
			Histogram histogramAgg1=searchResponse.getAggregations().get("dateHistogram");
			for(Histogram.Bucket entry1:histogramAgg1.getBuckets()) {
				System.out.println(entry1.getKey()+"   "+entry1.getDocCount());
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
