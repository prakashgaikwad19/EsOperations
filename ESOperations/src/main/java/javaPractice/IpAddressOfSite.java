package javaPractice;

import java.net.InetAddress;
import java.net.URL;

public class IpAddressOfSite {
	public static void main(String[] args)
	{
		try {
			// Creating an object of InetAddress class to
			// get the Ip address
			InetAddress myIP = InetAddress
					.getByName(new URL("https://www.instagram.com/geeks_for_geeks/?hl=en")
							.getHost());
		
			System.out.println("My IP Address is : ");
			System.out.println(myIP.getHostAddress());
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some Error Occurred");
		}
	}
}
