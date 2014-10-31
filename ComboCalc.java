package Elsword;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class ComboCalc {
	private Node head;
	private String className;
	private BufferedWriter output;
	private int max;
	private int count;

	ComboCalc(String className, int max) {
		this.count = 0;
		this.className = className;
		this.max = max;
		head = generateGATree();
		try {
			System.out.println("Generating file...");
			File file = new File(this.className + ".txt");
			output = new BufferedWriter(new FileWriter(file));
			System.out.println("Done!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getName() {
		return className;
	}

	public void calculateResets() {

		for (Node f : head.followUps) {
			calc("", 0, f, false);
		}
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void calc(String str, double kd, Node n, boolean ak) {
		if (ak)
			return;
		if (count == max)
			return;

		String r = str + n.name;
		kd += n.kd;
		if (n.name.equals("Assault Kick"))
			ak = true;

		if (kd == 100) {
			try {
				output.write(r+"\n");
				count++;
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		if (kd > 100)
			return;
		else {
			for (Node f : n.followUps)
				calc(r, kd, f, ak);
		}

	}

	private Node generateGATree() {
		Node h = new Node(" ", 0);
		Node z = new Node("z", 7);
		Node zz = new Node("z", 7);
		Node zzx = new Node("x", 20);
		Node zzxx1 = new Node("x(1)", -30);
		Node zzxx2 = new Node("x(2)", -60);
		Node zzz = new Node("z", 15);
		Node uux1 = new Node("^^x(1)", 15);
		Node uux2 = new Node("^^x(2)", 30);
		Node uuxx1 = new Node("x(1)", 15);
		Node uuxx2 = new Node("x(2)", 30);
		Node djz = new Node(">>^z", 20);
		Node djzx = new Node("x", 0);
		Node uuz = new Node("^^z", 20);
		Node dz = new Node(">>z", 10);
		Node dzz = new Node("z", 5);
		Node x = new Node("x", 15);
		Node xx = new Node("x", 14);
		Node ak = new Node("Assault Kick", -20);
		Node ux = new Node("^x",15);

		h.followUps.add(z);
		h.followUps.add(uux1);
		h.followUps.add(uux2);
		h.followUps.add(djz);
		h.followUps.add(uuz);
		h.followUps.add(dz);
		h.followUps.add(x);
		h.followUps.add(ak);
		h.followUps.add(ux);
		
		ux.followUps.add(h);

		ak.followUps.add(h);

		z.followUps.add(zz);

		uux1.followUps.add(uuxx1);
		uux1.followUps.add(uuxx2);
		uux1.followUps.add(h);
		uux2.followUps.add(uuxx1);
		uux2.followUps.add(uuxx2);
		uux2.followUps.add(h);

		djz.followUps.add(djzx);
		djz.followUps.add(h);

		zz.followUps.add(zzx);
		zz.followUps.add(zzz);

		zzx.followUps.add(zzxx1);
		zzx.followUps.add(zzxx2);
		zzx.followUps.add(h);

		zzz.followUps.add(zzx);
		zzz.followUps.add(h);

		dz.followUps.add(dzz);
		dz.followUps.add(h);

		dzz.followUps.add(h);

		x.followUps.add(xx);

		xx.followUps.add(h);

		djzx.followUps.add(uuz);
		djzx.followUps.add(z);

		uuz.followUps.add(h);

		return h;

	}

	private class Node {

		Node(String name, double kd) {
			followUps = new ArrayList<Node>();
			this.name = name;
			this.kd = kd;
		}

		ArrayList<Node> followUps;
		String name;
		double kd;

	}

}
