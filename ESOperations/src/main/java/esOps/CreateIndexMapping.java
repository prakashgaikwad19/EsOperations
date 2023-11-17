package esOps;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentFactory;

public class CreateIndexMapping {

	public static void main(String[] args) throws Exception {
		XContentBuilder builder = createXcontentBuilder();
		createIndexMapping("employee_data",builder);			
	}

	private static XContentBuilder createXcontentBuilder() throws Exception {
		XContentBuilder builder = XContentFactory.jsonBuilder()
				.startObject()
				.field("dynamic", false)
				.field("properties")
					.startObject()
				
						.field("empNo")
							.startObject()
								.field("type", "keyword")
							.endObject()
				
						.field("age")
							.startObject()
								.field("type", "keyword")
							.endObject()
				
						.field("empName")
							.startObject()
								.field("type", "keyword")
							.endObject()
				
						.field("JoiningLocation")
							.startObject()
								.field("type", "keyword")
							.endObject()
				
						.field("CurrentLocation")
							.startObject()
								.field("type", "keyword")
							.endObject()
				
						.field("JoiningDate")
							.startObject()
								.field("type", "date")
								.field("format", "yyyy-MM-dd") //yyyy-MM-dd:mm:ss:SSS
							.endObject()
					
					.endObject()
				.endObject();
	return builder;	
	}

	private static void createIndexMapping(String indexName, XContentBuilder builder) {

		boolean result = false;
		RestHighLevelClient client=new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		PutMappingRequest request = new PutMappingRequest(indexName);
		request.source(builder);
		
		AcknowledgedResponse response;
		try {
			response = client.indices().putMapping(request, RequestOptions.DEFAULT);
			result = response.isAcknowledged();
			System.out.println("index mapping created "+result);
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
