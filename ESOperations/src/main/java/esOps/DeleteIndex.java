package esOps;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

@SuppressWarnings("deprecation")
public class DeleteIndex {

	public static void main(String[] args) {
		RestHighLevelClient client=new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		DeleteIndexRequest deleteIndexRequest=new DeleteIndexRequest("employee_data");
		
		try {
			AcknowledgedResponse deleteIndexResponse=client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
			System.out.println(deleteIndexResponse.isAcknowledged());
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
