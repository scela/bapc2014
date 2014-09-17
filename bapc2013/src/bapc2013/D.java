package bapc2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class D {

	public static void main(String[] args) throws Exception {
		D d = new D();
		for (int i = 0; i < d.total; i++) {
			d.solve();
		}
	}

	private Integer total;

	public D() throws Exception {
		bufferedReader = new BufferedReader(new FileReader("D.in"));
		total = Integer.valueOf(bufferedReader.readLine());

	}

	private BufferedReader bufferedReader;
	private Integer n;
	private Integer m;
	private Integer t;
	private Integer s;
	private Integer g;
	private Integer h;
	int[][] c;
	int[] ts;
	int[] oldd;
	int[] newd;

	private void solve() throws Exception {
		String firstLine = bufferedReader.readLine();
		String[] split = firstLine.split(" ");
		n = Integer.valueOf(split[0]);
		m = Integer.valueOf(split[1]);
		t = Integer.valueOf(split[2]);
		String[] split2 = bufferedReader.readLine().split(" ");
		s = Integer.valueOf(split2[0]) - 1;
		g = Integer.valueOf(split2[1]) - 1;
		h = Integer.valueOf(split2[2]) - 1;
		c = new int[n][n];
		ts = new int[t];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				c[i][j] = c[j][i] = -1;
			}
		}

		for (int i = 0; i < m; i++) {
			String[] s = bufferedReader.readLine().split(" ");
			Integer a = Integer.valueOf(s[0]) - 1;
			Integer b = Integer.valueOf(s[1]) - 1;
			Integer d = Integer.valueOf(s[2]);
			c[a][b] = c[b][a] = d;
		}
		for (int i = 0; i < t; i++) {
			ts[i] = Integer.valueOf(bufferedReader.readLine()) - 1;
		}

		Arrays.sort(ts);
		oldd = sp(s);
		if (oldd[g] < oldd[h]) {
			newd = sp(h);
			for (int i = 0; i < t; i++) {
				int actual = ts[i];
				if (oldd[actual] == oldd[g] + newd[actual] + c[g][h]) {
					System.err.print((actual + 1) + " ");
				}
			}
			System.err.println();
		} else {
			newd = sp(g);
			for (int i = 0; i < t; i++) {
				int actual = ts[i];
				if (oldd[actual] == oldd[h] + newd[actual] + c[h][g]) {
					System.err.print((actual + 1) + " ");
				}
			}
			System.err.println();
		}
	}

	private int[] sp(int source) {
		final int[] d = new int[n];
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(
				new Comparator<Integer>() {

					@Override
					public int compare(Integer o1, Integer o2) {
						return d[o1] - d[o2];
					}

				});
		d[source] = 0;
		for (int i = 0; i < n; i++) {
			if (i != source){
				d[i] = 1000000000;
			pq.add(i);
			}
		}
		pq.add(source);
		while (!pq.isEmpty()) {
			int actual = pq.poll();
			for (int i = 0; i < n; i++) {
				if ((actual != i) && (c[actual][i] > 0)) {
					int newd = d[actual] + c[actual][i];
					if (d[i] >= newd) {
						d[i] = newd;
						pq.remove(i);
						pq.add(i);
					}
				}
			}
		}
		return d;

	}

}
