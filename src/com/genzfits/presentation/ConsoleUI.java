package com.genzfits.presentation;

import java.util.Scanner;

// Presentation Layer.

public class ConsoleUI {

    private final Scanner scanner = new Scanner(System.in);
    
    
    //Inpout methods
    // Prints a label and waits for the user to type a line of text.

    public String prompt(String label) {
        System.out.print(label + ": ");
        return scanner.nextLine().trim();
    }

    public int promptInt(String label) {
        while (true) {
		            System.out.print(label + ": ");
		            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter the number.");
            }}}
   //It will print label and wait for a integer
    public void println(String text) {
        System.out.println(text);
    }
    
    //It will print blank line
    public void blank() {
        System.out.println();}

	    public void heading(String text) {
	        System.out.println();
	        System.out.println("=== " + text + " ===");
    }
}