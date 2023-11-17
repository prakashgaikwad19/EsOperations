package esOps;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

@SuppressWarnings("deprecation")
public class CheckIndexExist {

	public static void main(String[] args) {
		RestHighLevelClient client=new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		GetIndexRequest getIndexRequest=new GetIndexRequest();
		getIndexRequest.indices("employee_data"/*"admin"*/);
		
		boolean getIndexResponse;
		try {
			getIndexResponse=client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
			System.out.println(getIndexResponse);
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
