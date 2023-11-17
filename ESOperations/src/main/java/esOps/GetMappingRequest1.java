package esOps;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetMappingsRequest;
import org.elasticsearch.client.indices.GetMappingsResponse;
import org.elasticsearch.cluster.metadata.MappingMetadata;

public class GetMappingRequest1 {

	public static void main(String[] args) {
		RestHighLevelClient client=new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		Map<String, Object> mapping=null;
		GetMappingsRequest request=new GetMappingsRequest();
		request.indices("employee_data");
		
		try {
			GetMappingsResponse getMappingsResponse=client.indices().getMapping(request, RequestOptions.DEFAULT);
			Map<String, MappingMetadata> allMappings=getMappingsResponse.mappings();
			MappingMetadata indexMapping=allMappings.get("employee_data");
			mapping=indexMapping.sourceAsMap();
			
			for(String key:mapping.keySet()) {
				System.out.println(key+" = "+mapping.get(key).toString());
				System.out.println();
			}
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
