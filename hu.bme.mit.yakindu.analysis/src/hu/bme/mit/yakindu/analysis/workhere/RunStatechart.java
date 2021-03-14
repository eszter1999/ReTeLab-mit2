package hu.bme.mit.yakindu.analysis.workhere;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import hu.bme.mit.yakindu.analysis.RuntimeService;
import hu.bme.mit.yakindu.analysis.TimerService;
import hu.bme.mit.yakindu.analysis.example.ExampleStatemachine;
import hu.bme.mit.yakindu.analysis.example.IExampleStatemachine;



public class RunStatechart {
	
//	public static void main(String[] args) throws IOException {
//		ExampleStatemachine s = new ExampleStatemachine();
//		s.setTimer(new TimerService());
//		RuntimeService.getInstance().registerStatemachine(s, 200);
//		s.init();
//		s.enter();
//		s.runCycle();
//		print(s);
//		s.raiseStart();
//		s.runCycle();
//		System.in.read();
//		s.raiseWhite();
//		s.runCycle();
//		print(s);
//		System.exit(0);
//	}
//
	public static void print(IExampleStatemachine s) {
		System.out.println("W = " + s.getSCInterface().getWhiteTime());
		System.out.println("B = " + s.getSCInterface().getBlackTime());
	}
	
	public static void main(String[] args) throws IOException {
		//making state machine
		ExampleStatemachine s = new ExampleStatemachine();
		s.setTimer(new TimerService());
		RuntimeService.getInstance().registerStatemachine(s, 200);
		s.init();
		s.enter();
		s.runCycle();
		//reading inputs
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String esemeny;
		while(true) {
			esemeny = reader.readLine();
			switch (esemeny) {
			case "white":
				s.raiseWhite();
				s.runCycle();
				print(s);
				break;
			case "black":
				s.raiseBlack();
				s.runCycle();
				print(s);
				break;
			case "start":
				s.raiseStart();
				s.runCycle();
				print(s);
				break;
			case "exit":
				s.exit();
				print(s);
				System.exit(0);
				break;
			default:
				System.out.println("ervenytelen");
				break;
			}
		}
	}
}
