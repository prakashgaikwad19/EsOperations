package esOps;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

@SuppressWarnings("deprecation")
public class UpdateDocsInIndex {

	public static void main(String[] args) {
		RestHighLevelClient client=new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("empNo", 8);
		map.put("empName", "emp8");
		map.put("JoiningLocation", "Pune");
		try {
			map.put("JoiningDate",sdf.format(sdf.parse("2018-03-10")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		UpdateRequest updateRequest=new UpdateRequest("employee_data","1");
		updateRequest.doc(map);
		
		UpdateResponse updateResponse;
		try {
			updateResponse=client.update(updateRequest, RequestOptions.DEFAULT);
			System.out.println(updateResponse.getId()+"   "+updateResponse.getResult());
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
