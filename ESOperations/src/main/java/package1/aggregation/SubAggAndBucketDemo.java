package package1.aggregation;

import java.io.IOException;
import java.util.Iterator;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class SubAggAndBucketDemo {

	public static void main(String[] args) throws Exception{
		RestHighLevelClient client=new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		SearchRequest searchRequest=new SearchRequest();
		searchRequest.indices("employee_data");
		SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
															//OuterAgg, 	InnerAgg
		AggregationBuilder agg = AggregationBuilders.terms("OuterAgg").field("empNo")
																 //   .size(100)  //this is index_doc_limit_value
				.subAggregation(AggregationBuilders.terms("InnerAgg").field("JoiningLocation")
																		//   .size(100)	//this is index_doc_limit_value
						);
		
		BoolQueryBuilder query = QueryBuilders.boolQuery();
		query.must(QueryBuilders.matchQuery("JoiningLocation", "delhi"));
	//	MatchAllQueryBuilder query = QueryBuilders.matchAllQuery();
		
		searchSourceBuilder.query(query);
		searchSourceBuilder.aggregation(agg)
							.size(0)
							.version(true);
		searchRequest.source(searchSourceBuilder);
		
		try {
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
															//OuterAgg
			Terms terms = searchResponse.getAggregations().get("OuterAgg");
			Iterator<Bucket> itr = (Iterator<Bucket>) terms.getBuckets().iterator();
			while(itr.hasNext()) {
				Bucket outerBucket = itr.next();
				String outerBucketKey = outerBucket.getKeyAsString();
																	//InnerAgg
				Terms subTerms = outerBucket.getAggregations().get("InnerAgg");
				Iterator<Bucket> subItr = (Iterator<Bucket>) subTerms.getBuckets().iterator();
			while(subItr.hasNext()) {
				Bucket innerBucket = subItr.next();
				System.out.println(innerBucket.getKeyAsString());
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
