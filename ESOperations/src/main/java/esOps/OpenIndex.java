package esOps;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

@SuppressWarnings("deprecation")
public class OpenIndex {

	public static void main(String[] args) {
		RestHighLevelClient client=new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		OpenIndexRequest openIndexRequest=new OpenIndexRequest("employee_data");
		
		OpenIndexResponse openIndexResponse;
		try {
			openIndexResponse=client.indices().open(openIndexRequest, RequestOptions.DEFAULT);
			System.out.println("Index opened or not"+openIndexResponse.isAcknowledged());
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
