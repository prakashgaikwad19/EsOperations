package esOps.esConnectionClass;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.plugins.Plugin;

public class EsConnection extends Plugin{
	
	private RestHighLevelClient client;
	
	private String masterNodeIp;
	private int masterNodePort;
	private String userName;
	private String password;
	
	public EsConnection() {
	}

	public EsConnection(String masterNodeIp, int masterNodePort, String userName,
			String password) {
		this.masterNodeIp = masterNodeIp;
		this.masterNodePort = masterNodePort;
		this.userName = userName;
		this.password = password;
	}

	public RestHighLevelClient getClient() {
		return client;
	}

	public boolean createEsConnection() {
		RestClientBuilder builder = null;
		try {
			System.out.println("-----------Creating connection-----------");
			
			final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
			
			List<HttpHost> hosts = new ArrayList<HttpHost>();
			
			hosts.add(new HttpHost(masterNodeIp, masterNodePort, "http"));
			
			builder = RestClient.builder(hosts.toArray(new HttpHost[hosts.size()]));
			
			if(!(userName.equalsIgnoreCase("null") && password.equalsIgnoreCase("null"))) {
				
				System.out.println("Authenticating connection");
				
				HttpClientConfigCallback httpClientConfigCallback = new HttpClientConfigCallback() {
					
					public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
						return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
					}
				};
				
				builder.setHttpClientConfigCallback(httpClientConfigCallback);
			}
			
			client = new RestHighLevelClient(builder);
			
			ClusterHealthRequest clusterHealthRequest = new ClusterHealthRequest();
			
			ClusterHealthResponse clusterHealthResponse = client.cluster().health(clusterHealthRequest, RequestOptions.DEFAULT);
			
			System.out.println("Cluster Name: "+clusterHealthResponse.getClusterName());
			
			System.out.println(new EsConnection(masterNodeIp, masterNodePort, userName, password).toString());

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String toString() {
		return "EsConnection [masterNodeIp=" + masterNodeIp + ", masterNodePort=" + masterNodePort + ", userName="
				+ userName + ", password=" + password + "]";
	}
	
	
}
