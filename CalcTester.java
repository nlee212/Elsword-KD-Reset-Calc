package Elsword;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class CalcTester {

	public static void main(String[] args) {
		BufferedWriter output = null;
		ComboCalc c = new ComboCalc("GrandArcher",200000);

		System.out.println("Calculating combos...");
		c.calculateResets();
		System.out.println("Done!");

	}

}
