/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package issuancesystem;

import issuancesystem.Objects.Account;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Lara
 */
public class LoginSystem {
    private final Scanner read = new Scanner(System.in);
    private final Path ACCOUNT_PATH = Paths.get("Storage\\AccountsFile.txt");
    private final List<Account> accounts;
    private Account currentUser;

    public LoginSystem(){
        this.accounts = new ArrayList<>();
        this.currentUser = null;
        InitializeAccounts();
    }
    
    private void InitializeAccounts(){
        Path filePath = Paths.get(ACCOUNT_PATH.toAbsolutePath().toString());
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()));
        
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming each line in the file contains account information in the format: accountNo,password,fullName
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String accountNo = parts[0];
                    String password = parts[1];
                    String fullName = parts[2];

                    Account account = new Account(accountNo, password, fullName);
                    accounts.add(account);
                }
            }
        }catch(IOException ex){
            System.err.println("Error reading file: " + ex.getMessage());
        }
    }
    
    public void login(){
        Scanner read = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n-----Login-----");
                String accNo = promptForAccountNumber();

                if (accNo == null) {
                    System.out.println("Number must contain 4 digits only.");
                    continue;
                }

                String password = promptForPassword();

                if (password == null) {
                    System.out.println("Password must be letters!");
                    continue;
                }

                if (verifyLogin(accNo, password)) {
                    greetUser();
                    startUserSession();
                } else {
                    System.out.println("Invalid username or password.");
                }

            } catch (InputMismatchException ex) {
                System.out.println("Invalid input! " + ex.getMessage());
                read.nextLine();
            }
        }
    } 
    
    private String promptForAccountNumber() {
        System.out.print("Account No: ");
        String accNo = read.nextLine();
        return accNo.matches("^[0-9]{4}$") ? accNo : null;
    }

    private String promptForPassword() {
        System.out.print("Password: ");
        String password = read.nextLine();
        return password.matches("^[a-zA-Z]+$") ? password : null;
    }

    private void greetUser() {
        System.out.println("--------------------------------");
        System.out.println("Hello, " + currentUser.getFullname() + "!");
        System.out.println("--------------------------------");
    }
    
    private void startUserSession() {
        CompliantUserEnd compliant = new CompliantUserEnd(currentUser);
        compliant.start();
    }
        
    public boolean verifyLogin(String accNo, String password) {
        if (accNo == null && password == null) {
            return false; // Account number or password cannot be empty or null
        }

        for (Account acc : accounts) {
            if (acc.getAccountNo().equals(accNo)  && acc.getPassword().equals(password)) {
                Account account = new Account(acc.getAccountNo(), acc.getPassword(), acc.getFullname());
                currentUser = account;
                return true; // Found matching account number and password
            }
        }
        return false; // No matching account found
    }
}
