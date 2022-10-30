package javaPractice;

import java.io.*;
import java.lang.*;
import java.util.*;
class ProcessBuilderDemo {
	public static void main(String[] arg) throws IOException
	{
		StringBuilder scanOputput=new StringBuilder();

		List<String> commands = new ArrayList<String>();
		commands.add("hostname");
	//	commands.add("dir");
		ProcessBuilder pb = new ProcessBuilder(commands);
		pb.directory(new File("C:\\Users\\Prakash P Gaikwad\\Java11\\1/"));
		Process process = pb.start();
		
		System.out.println("check1");
		BufferedReader stdInput
			= new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		//System.out.println("check2");

		System.out.println("op is "+stdInput.readLine());
		String s;
		while ((s = stdInput.readLine()) != null) {
			System.out.println("check3");

			System.out.println(s);
			//scanOputput.append(s+"\n");
		}
		System.out.println(scanOputput);
	}
}

