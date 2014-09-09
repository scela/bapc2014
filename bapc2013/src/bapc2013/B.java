package bapc2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashSet;

public class B {

	public static void main(String[] args) throws IOException {
		new B().solve();
	}

	private BufferedReader bufferedReader;
	private int total;
	private int goal;
	private int[] hcosts;
	private int[] hprobs;
	private int money;
	private HashSet<Integer> set;

	private void solve() throws IOException {
		bufferedReader = new BufferedReader(new FileReader("B.in"));
		String firstLine = bufferedReader.readLine();
		String[] split = firstLine.split(" ");
		total = Integer.valueOf(split[0]);
		goal = Integer.valueOf(split[1]);
		money = Integer.valueOf(split[2]);
		hcosts = new int[total];
		hprobs = new int[total];
		for (int i = 0; i < total; i++) {
			String[] vals = bufferedReader.readLine().split(" ");
			hcosts[i] = Integer.valueOf(vals[0]);
			hprobs[i] = Integer.valueOf(vals[1]);
		}
		int gd = (int) Math.pow(2, goal) - 1;
		int td = (int) Math.pow(2, total) - 1;
		int z = gd;
		double max = Double.MIN_VALUE;
		while (z < td) {
			double p;
			p = prob(z);
			if (p > max) max = p;
			z = next(z);
		}
		System.err.println(String.format("%.6f", max));
	}

	private double prob(int z) {
		double p=1;
		int s = 0;
		for (int i=0;i<16;i++){
			if (isBitOn(z, i)){
				p*=(hprobs[i]/100.d);
				s+=hcosts[i];
			}
			else
				
			if (s>money) return -1;
		}
		System.err.println(s);
		System.err.println(p);
		return p;
	}

	boolean isBitOn(int n, int i){
		return ((1<<i)&n)!=0;
	}
	
	private int next(int gd) {
		int c = (gd & -gd);
		int r = c + gd;
		int z = (((r ^ gd) >> 2) / c) | r;
		return z;
	}

}
