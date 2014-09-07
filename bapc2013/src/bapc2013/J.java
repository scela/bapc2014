package bapc2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

import javax.naming.LinkLoopException;

public class J {

	private Integer nrows;
	private Integer ncols;
	Point p1,p2;
	private char[][] t;
	private boolean[][] handled;
	private Stack<Point> doorsStack = new Stack<Point>();
	private BufferedReader bufferedReader;
	public static void main(String[] args) throws IOException {
		
		System.err.println(new J().solve());
	}

	private int solve() throws IOException {
		bufferedReader = new BufferedReader(new FileReader(
				"J.in"));
		String firstLine = bufferedReader.readLine();
		String[] split = firstLine.split(" ");
		nrows = Integer.valueOf(split[0]);
		ncols = Integer.valueOf(split[1]);
		t = new char[nrows][ncols];
		handled = new boolean[nrows][ncols];
		for (int i = 0; i < nrows; i++) {
			String string = bufferedReader.readLine();
			for (int j = 0; j < ncols; j++) {
				t[i][j] = string.charAt(j);
				if (t[i][j] == '$') {
					if (p1==null) {
						p1 = new Point(i,j);
					} else {
						p2 = new Point(i,j);
					}
				}
				else if (t[i][j]=='#') doorsStack.push(new Point(i,j));
			}
		}

//		debug(t);
		
		int[][] sp1 = sp(p1);
		System.err.println();
		int[][] sp2 = sp(p2);
		
		int actualMin = Integer.MAX_VALUE;
		
		//if they'd better meet
		while (!doorsStack.isEmpty()){
			Point pop = doorsStack.pop();
			actualMin = Math.min(ctgo(pop) + sp1[pop.x][pop.y]+sp2[pop.x][pop.y]-1,actualMin);
		}
		
		//if they'd better not meet
		actualMin = Math.min(actualMin, bestExit(sp1)+bestExit(sp2));
		
		bufferedReader.close();
		return actualMin;
	}

	private int ctgo(Point door) {
		int[][] spd = new int[nrows][ncols];
		spd = sp(door);
		
		int bestExit = bestExit(spd);
//		System.err.println(bestExit);
		return bestExit;
	}

	private int bestExit(int[][] spd) {
		int best = Integer.MAX_VALUE;
		for (int i=0;i<ncols;i++)
			best = Math.min(Math.min(best, spd[0][i]),spd[nrows-1][i]);
		for (int i = 0; i < nrows-1; i++) 
			best = Math.min(Math.min(best, spd[i][0]), spd[i][ncols-1]);
			
		return best;
	}

	private int[][] sp(Point p) {
		final int[][] d = new int[nrows][ncols];
		int[] dx = new int[] {-1,0,1, 0};
		int[] dy = new int[] {0 ,1,0,-1};
		ArrayDeque<Point> ad = new ArrayDeque<Point>(nrows*ncols);
		
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				d[i][j]=Integer.MAX_VALUE;
			}
		}
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				handled[i][j]=false;
			}
		}
		d[p.x][p.y]=0;
		ad.addFirst(p);
		while (!ad.isEmpty()){
			Point pop = ad.pop();
			for (int k=0;k<4;k++){
				int nx = pop.x+dx[k];
				int ny = pop.y+dy[k];
				if (invalid(nx, ny)) continue;
				Point np = new Point(nx,ny);
				d[nx][ny] = Math.min(d[nx][ny], (t[nx][ny]=='#'?1:0)+d[pop.x][pop.y]);
//				debugint(d);
				if (!handled[nx][ny]) {
					if (t[nx][ny]=='#')
					ad.addLast(np);
					else 
						ad.addFirst(np);
				}
			}
			handled[pop.x][pop.y]=true;
		}
		
//		debugint(d);
		
		
		return d;
	}

	private void debugint(int[][] d) {
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				System.err.print(d[i][j] + "\t");
			}
			System.err.println();
		}
	}

	private boolean invalid(int nx, int ny) {
		return (nx>=nrows)||(ny>=ncols)||(nx<0)||(ny<0)||(t[nx][ny]=='*');
	}

	private void debug(char[][] t) {
		for (int i = 0; i < nrows; i++) {
			System.err.print(i+" ");
			for (int j = 0; j < ncols; j++) {
				System.err.print(t[i][j] + " ");
			}
			System.err.println();
		}
		
	}

}

class Point {
	int x;
	int y;
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
	
	
	
}
