package package1;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

@SuppressWarnings("deprecation")
public class DeleteDocsFromIndex {

	public static void main(String[] args) {
		RestHighLevelClient client=new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		String[] id= {"1","2","3","4","5","6","7"};
		/*String[] id= {"zUfUHIABsALJ8wAYGOe_",
				"zkfUHIABsALJ8wAYGecH",
				"z0fUHIABsALJ8wAYGecm",
				"0EfUHIABsALJ8wAYGec6",
				"0UfUHIABsALJ8wAYGedV",
				"0kfUHIABsALJ8wAYGeds",
				"00fUHIABsALJ8wAYGeeA"};
		*/
		for(int i=0;i<7;i++) {
			DeleteRequest deleteRequest=new DeleteRequest("employee_data",id[i]);
			
			DeleteResponse deleteResponse;
			
			try {
				deleteResponse=client.delete(deleteRequest, RequestOptions.DEFAULT);
				System.out.println(deleteResponse.getId()+"   "+deleteResponse.getResult());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
