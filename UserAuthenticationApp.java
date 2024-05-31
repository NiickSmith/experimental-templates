package com.domain.smarthomesystem;

import com.domain.smarthomesystem.data.User;
import com.domain.smarthomesystem.data.UserDataManager;

import java.util.Scanner;

public class UserAuthenticationApp {
	
	private static UserDataManager userManager = new UserDataManager();
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		while (true) {
			System.out.println("Welcome to Smart Home System");
			System.out.println("1. Log In");
			System.out.println("2. Create Account");
			System.out.println("3. Forgot Password");
			System.out.println("4. Exit");
			System.out.println("Select an option: ");
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch (choice) {
			case 1:
				logIn();
				break;
			case 2:
				createAccount();
				break;
			case 3:
				forgotPassword();
				break;
			case 4:
				System.out.println("Exiting...");
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}
	
	private static void logIn() {
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
		
		if (userManager.authenticate(username, password)) {
			System.out.println("Account created successfully.");
		} else {
			System.out.println("Username already exists, please try a new one.");
		}
	}
	
	private static void forgotPassword() {
		System.out.println("Enter username: ");
		String username = scanner.nextLine();
		User user = userManager.getUser(username);
		
		if (user != null) {
			System.out.println("Your password is: " + user.getPassword());
		} else {
			System.out.println("Username not found.");
		}
	}
	
	private static void createAccount() {
		System.out.print("Enter new username: ");
		String username = scanner.nextLine();
		System.out.print("Enter new password: ");
		String password = scanner.nextLine();
		
		if (userManager.addUser(username, password)) {
			System.out.print("Verify by re-typing new password: ");
			String verifyPassword = scanner.nextLine();
			if (verifyPassword.equals(password)) {
				System.out.println("Account created successfully");
			} else {
				System.out.println("Error: Re-typed password does not match new password.");
			}
		}
	}
}
