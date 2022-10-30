package package1;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;

public class IndexExistOrNot {

	public static void main(String[] args) {
		RestHighLevelClient client=new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		GetIndexRequest getIndexRequest=new GetIndexRequest("employee_data");
		try {
			boolean exist=client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
			System.out.println(exist+"created or not");
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
