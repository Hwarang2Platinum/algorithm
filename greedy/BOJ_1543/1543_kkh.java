package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String docs = br.readLine();
			String search = br.readLine();
			int ans = 0;
			int i=0;
			int equal_length=0;
			while(i<docs.length()) {
				equal_length = 0;
				while(i+equal_length<docs.length()  && equal_length != search.length()) {
//					System.out.println(i+","+equal_length+":"+docs.charAt(i+equal_length)+","+search.charAt(equal_length));
					if(search.charAt(equal_length) == docs.charAt(i+equal_length)) {
						equal_length ++;
					}
					else {
						break;
					}
				}
				
				if(equal_length == search.length()) {
					i+=equal_length;
					ans++;
				}else {
					i++;
				}
			}
			System.out.println(ans);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
