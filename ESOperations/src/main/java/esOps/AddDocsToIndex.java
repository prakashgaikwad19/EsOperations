package esOps;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

@SuppressWarnings("deprecation")
public class AddDocsToIndex {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		RestHighLevelClient client=new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		
		int[] empNo= {1,2,3,4,5,6,7}; 
		String[] age= {"23","23","23","23","23","24","24"};
		String[] empName= {"emp1","emp2","emp3","emp4","emp5","emp6","emp7"};
		String[] JoiningLocation= {"Mumbai","Mumbai","Delhi","Delhi","Indore","Banglure","Chennai"};
		String[] CurrentLocation = {"Mumbai","Mumbai","Delhi","Delhi","Indore","Banglure","Chennai"};
		String[] JoiningDate= {"2021-10-28","2021-10-28",
								"2021-10-28","2021-10-28",
								"2022-3-28","2022-3-28",
								"2022-3-28"};
		String[] id= {"1","2","3","4","5","6","7"};
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<7;i++) {
			IndexRequest indexRequest=new IndexRequest("employee_data");
			
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("empNo", empNo[i]);
			map.put("empName", empName[i]);
			map.put("age", age[i]);
			map.put("JoiningLocation", JoiningLocation[i]);
			map.put("CurrentLocation", CurrentLocation[i]);
			try {
				map.put("JoiningDate", sdf.format(sdf.parse(JoiningDate[i])));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			indexRequest
						.id(id[i])
						.source(map);
			IndexResponse indexResponse;
				try {
					indexResponse=client.index(indexRequest, RequestOptions.DEFAULT);
					System.out.println(indexResponse.getId()+"   "+indexResponse.getResult());
				} catch (IOException e) {
					e.printStackTrace();
				}
			//System.out.println(i);
		}
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
