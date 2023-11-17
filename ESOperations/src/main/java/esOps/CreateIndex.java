package esOps;

import java.io.IOException;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;


@SuppressWarnings("deprecation")
public class CreateIndex {

	public static void main(String[] args) {
		RestHighLevelClient client=new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		
		CreateIndexRequest createIndexRequest=new CreateIndexRequest("employee_data");
		
		createIndexRequest.settings(Settings.builder()
			.put("index.number_of_shards",2)
			.put("index.number_of_replicas",2));
		
		
		CreateIndexResponse createIndexResponse;
		
		try {
			createIndexResponse=client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
			System.out.println(createIndexResponse.isAcknowledged());
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
