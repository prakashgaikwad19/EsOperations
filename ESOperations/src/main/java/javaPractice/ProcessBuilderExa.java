package javaPractice;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessBuilderExa {

	public static void main(String[] args) throws IOException, InterruptedException {
		StringBuilder scanOputput=new StringBuilder();
//		java.lang.ProcessBuilder pb=new java.lang.ProcessBuilder("sh","-c",executionCommand);
		java.lang.ProcessBuilder pb=new java.lang.ProcessBuilder("ipconfig"+"/"+"all");
		System.out.println(System.getProperty("user.home"));
		//pb.directory( new File(System.getProperty("user.home")));
		Process process=pb.start();
		
		BufferedReader reader=new BufferedReader(
				new InputStreamReader(process.getInputStream()));
		String line;
		while ((line=reader.readLine())!=null) {
			scanOputput.append(line+"\n");
		}
		System.out.println(scanOputput);
		int exitVal=process.waitFor();
		process.destroyForcibly();
	}

}
