package appfunctionality;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Run {
	
	static Scanner input = new Scanner(System.in);
	public static int isInteger() { // handle exception za integer brojeve
		while (true) {
			try {
				return input.nextInt();
			} catch (InputMismatchException e) {
				input.next();
				System.out.println("Your entry is wrong. Try again! ");
			}
		}
	}
	
	public static void run() throws SQLException {
		int option = -1;
		do {
		System.out.println("1 - Sign Up"
				+ "\n2 - Sign In"
				+ "\n0 - Exit");
		
		option = isInteger();
		
		switch (option) {
		case 1:
			SignUp.register();
		case 2:
			SignIn.options();
		}
		
		}while (option != 0);
	}

}
