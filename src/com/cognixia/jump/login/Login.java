package com.cognixia.jump.login;

import java.util.InputMismatchException;


import java.util.List;
import java.util.Scanner;

public class Login {

	
	public static Scanner sc;
	public static int id;
	public String name;
	public String department;
	public int salary;
	public String email;


	public static void main(String[] args) {

		
		sc = new Scanner(System.in);
		

		System.out.println("WELCOME TO THE PTP \n");

		mainMenu();

	}

	public static void mainMenu() {

		while (true) {

			try {
				System.out.println("\nPlease enter one of the following options :" 
									+ "\n1.) SignIn"
									+ "\n2.) SignUp" 
									+ "\n3.) Exit");
				
				System.out.println("Enter your choice : ");
				int option = sc.nextInt();
				sc.nextLine(); // getting rid of new line character

				switch (option) {
				case 1:
					SignIn();
					break;
				case 2:
					SignUp();
					break;
				case 3:
					break;

				default:
					System.out.println("\nPlease enter a number between 1 and 3");
					break;
				}

				if (option == 6) {
					break;
				}

			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("\nPlease enter a number between 1 and 3");
			}

		}

	}
	
	public static void SignIn() {
		
		
		while(true) {
			
			try {
				


				    String Username;
				    String Password;

				    Password = "123";
				    Username = "pip";

				    Scanner input1 = new Scanner(System.in);
			
				    System.out.println("Please Enter Username : ");
				    String username = input1.next();

				    Scanner input2 = new Scanner(System.in);
				    System.out.println("Please Enter Password : ");
				    String password = input2.next();

				    if (username.equals(Username) && password.equals(Password)) {

				        System.out.println("Access Granted! Welcome!");
				    }

				    else if (username.equals(Username)) {
				        System.out.println("Invalid Password!");
				    } else if (password.equals(Password)) {
				        System.out.println("Invalid Username!");
				    } else {
				        System.out.println("Invalid Username & Password!");
				    }
			
			}

			finally {
			    // ... cleanup that will execute whether or not an error occurred ...
			}	
	
		}}
			
			
		
	
	
	public static void SignUp() {
		
while(true) {
			
			try {
			
				 String Email;
				    String Password;

				    Email = "123";
				    Password = "PTP";

				    Scanner input1 = new Scanner(System.in);
					System.out.println("WELCOME TO THE PTP \n");
				    System.out.println("Please Enter Email : ");
				    String username = input1.next();
				
				    Scanner input2 = new Scanner(System.in);
				    System.out.println("Please Enter Password : ");
				    String password = input2.next();
				    
				    Scanner input3 = new Scanner(System.in);
				    System.out.println("Please Enter BirthDate : ");
				   String BirthDate = input3.next();
				   
			
				   
				   
				    Scanner input4 = new Scanner(System.in);
				    System.out.println("Please Enter Gender : ");
				   String Gender = input4.next();
				   
				   
				    Scanner input5 = new Scanner(System.in);
				    System.out.println("Please Enter Country : ");
				   String Country = input5.next();
				   
				   
				   Scanner input6 = new Scanner(System.in);
				    System.out.println("Please Enter State : ");
				     String State = input6.next();
				   
				  
				  Scanner input7 = new Scanner(System.in);
				    System.out.println("Please Enter City : ");
				  String City = input7.next();
				  
				  
				   
				   
				   
				    
				    if (username.equals(Email) && password.equals(Password)) {

				        System.out.println("Access Granted! Welcome!");
				    }

				    else if (username.equals(Email)) {
				        System.out.println("Invalid Password!");
				    } else if (password.equals(Password)) {
				        System.out.println("Invalid Username!");
				    } else {
				        System.out.println("Invalid Username & Password!");
				    }

				
				
			}finally {
			    // ... cleanup that will execute whether or not an error occurred ...
			}
	
			}
	

	
				
	
}}


