package bapc2013;

import java.io.File;
import java.util.Scanner;

public class D {

	private int n;
	private int m;
	private int t;
	private int s;
	private int g;
	private int h;
	int[][] c;
	int[] d;
	int[] dprime;
	int[] dest;

	public static void main(String[] args) throws Exception {
		new D().solve();
	}

	private void solve() throws Exception {
		Scanner scanner = new Scanner(new File("D.in"));
		n = scanner.nextInt();
		m = scanner.nextInt();
		t = scanner.nextInt();

		s = scanner.nextInt()-1;
		g = scanner.nextInt()-1;
		h = scanner.nextInt()-1;
		c=new int[n][n];
		d=new int[n];
		dprime=new int[n];
		dest=new int[t];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				c[i][j]=Integer.MIN_VALUE;
			}
			d[i]=Integer.MAX_VALUE;
			dprime[i]=Integer.MAX_VALUE
		}
		
		for (int i=0;i<m;i++){
			int a=scanner.nextInt()-1;
			int b=scanner.nextInt()-1;
			int d=scanner.nextInt();
			c[a][b]=c[b][a]=d;
		}
		
		for (int i=0;i<t;i++){
			dest[i]=scanner.nextInt()-1;
		}
		scanner.close();
		
		sp();
		//remove g <-> h edge
		//rerun sp
		//check d;
	}

}
