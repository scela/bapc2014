package bapc2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class F {
	public static void main(String[] args) throws IOException {
		new F().solve();
	}

	private BufferedReader bufferedReader;

	boolean[][] map;
	boolean[] visited;

	private Integer ncities;

	private Integer npilots;

	private int counter;

	private void solve() throws IOException {
		bufferedReader = new BufferedReader(new FileReader("F.in"));
		String firstLine = bufferedReader.readLine();
		String[] split = firstLine.split(" ");
		ncities = Integer.valueOf(split[0]);
		npilots = Integer.valueOf(split[1]);
		map = new boolean[ncities][ncities];
		visited = new boolean[ncities];
		for (int i = 0; i < npilots; i++) {
			String[] vals = bufferedReader.readLine().split(" ");
			int s = Integer.valueOf(vals[0]);
			int d = Integer.valueOf(vals[1]);
			map[s - 1][d - 1] = map[d - 1][s - 1] = true;
		}

//		debug(map);
		dfs(0);
		System.err.println(counter);
	}

	private void dfs(int i) {

		visited[i] = true;
		for (int c = 0; c < ncities; c++) {
			if ((!visited[c]) && (map[c][i])) {
				counter++;
				dfs(c);
			}
		}

	}

	private void debug(boolean[][] map2) {
		for (int i = 0; i < ncities; i++) {
			for (int j = 0; j < ncities; j++) {
				System.err.print(map[i][j] + "\t");
			}
			System.err.println();
		}

	}
}
