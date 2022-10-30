package javaPractice;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
public class InetAddressGetAllByName {
    public static void main(String[] args) throws UnknownHostException, MalformedURLException {
        System.out.println(InetAddress.getByName(new URL("https://www.instagram.com/geeks_for_geeks/?hl=en").getHost()).getHostAddress());
    }
}