package package1.aggregation;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.Cardinality;
import org.elasticsearch.search.aggregations.metrics.Max;
import org.elasticsearch.search.aggregations.metrics.Min;
import org.elasticsearch.search.aggregations.metrics.Sum;
import org.elasticsearch.search.aggregations.metrics.ValueCount;
import org.elasticsearch.search.builder.SearchSourceBuilder;

@SuppressWarnings("deprecation")
public class MetricAgg {

	public static void main(String[] args) {
		RestHighLevelClient client=new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		SearchRequest searchRequest=new SearchRequest("employee_data");
		SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery())
					//.aggregation(AggregationBuilders.min("min").field("empNo"));
					//.aggregation(AggregationBuilders.max("max").field("empNo"));
					//.aggregation(AggregationBuilders.avg("avg").field("empNo"));
					//.aggregation(AggregationBuilders.sum("sum").field("empNo"));
					//.aggregation(AggregationBuilders.cardinality("cardinality").field("empNo"));
					.aggregation(AggregationBuilders.count("count").field("empNo"));
		searchRequest.source(searchSourceBuilder);
		
		SearchResponse searchResponse = null;
		try {
			searchResponse=client.search(searchRequest, RequestOptions.DEFAULT);
			SearchHits hits=searchResponse.getHits();
			System.out.println("No of Hits: "+hits.getTotalHits());
			/*for(SearchHit hit:hits) {
				System.out.println(hit);
			}*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*Min minAgg=searchResponse.getAggregations().get("min");
		double minAggResult=minAgg.getValue();
		System.out.println("min Agg value : "+minAggResult);
		*/
		
		/*Max maxAgg=searchResponse.getAggregations().get("max");
		double maxAggResult=maxAgg.getValue();
		System.out.println("max Agg value : "+maxAggResult);
		*/
		
		/*Avg avgAgg=searchResponse.getAggregations().get("avg");
		double avgAggResult=avgAgg.getValue();
		System.out.println("avg Agg value : "+avgAggResult);
		*/
		
		/*Sum sumAgg=searchResponse.getAggregations().get("sum");
		double sumAggResult=sumAgg.getValue();
		System.out.println("sum Agg value : "+sumAggResult);
		*/
		
		/*Cardinality cardinalityAgg=searchResponse.getAggregations().get("cardinality");
		double cardinalityAggResult=cardinalityAgg.getValue();
		System.out.println("count agg: "+cardinalityAggResult);
		*/
		
		ValueCount countAgg=searchResponse.getAggregations().get("count");
		double countAggResult=countAgg.getValue();
		System.out.println("count agg: "+countAggResult);
		
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

