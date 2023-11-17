package esOps.esConnectionClass;

import java.io.IOException;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

public class TestingEsConnection {

	public static void main(String[] args) {
		EsConnection obj = new EsConnection("localhost",9200,"null","null");
		System.out.println("Connection Created: "+ obj.createEsConnection());
		RestHighLevelClient client = obj.getClient();
		String[] id= {"1","2","3","4","5","6","7"};
		for(int i=0;i<7;i++) {
			GetRequest getRequest=new GetRequest("employee_data",id[i]);
			GetResponse getResponse=null;
			
			try {
				getResponse=client.get(getRequest, RequestOptions.DEFAULT);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println(getResponse.getId()+"   "+getResponse.getSourceAsMap());
		}
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
