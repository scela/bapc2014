package bapc2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class I {
	
	HashMap<String,Integer> map= new HashMap<String, Integer>();
	private BufferedReader bufferedReader;
	private Integer n;
	
	public static void main(String[] args) throws Exception {
		new I().solve();
	}

	private void solve() throws Exception {
		bufferedReader = new BufferedReader(new FileReader(
				"I.in"));
		String firstLine = bufferedReader.readLine();
		n = Integer.valueOf(firstLine);
		for (int i=0;i<n;i++){
			String[] split = bufferedReader.readLine().split(" ");
			if (map.containsKey(split[1]))
				map.put(split[1], map.get(split[1])+1);
			else map.put(split[1], 1);
		}
		long p=1;
		Collection<Integer> values = map.values();
		for (Integer integer : values) {
			p*=integer+1;
		}
		System.err.println(p-1);
	}


}
