package javaPractice;

import java.util.ArrayList;
import java.util.List;

public final class CheckingOpOfFn {
	
	private static List<String> command;
	public CheckingOpOfFn(String... command) {
		this.command=new ArrayList<String>(command.length);
		for(String arg:command)
			this.command.add(arg);
	}
	public static void main(String[] args) {
		String str="ef";
		CheckingOpOfFn obj=new CheckingOpOfFn("ab","cd",str);
		System.out.println(command);
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("user.name"));
		String un=System.getProperty("user.name");
		System.out.println(un);
	}

}
