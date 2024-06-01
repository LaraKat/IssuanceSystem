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
    private final String ACCOUNT_PATH = "AccountsFile.txt";
    private final List<Account> accounts;
    private Account currentUser;

    public LoginSystem(){
        this.accounts = new ArrayList<>();
        this.currentUser = null;
        InitializeAccounts();
    }
    
    private void InitializeAccounts(){
        Path filePath = Paths.get("C:\\Users\\Lara\\Documents\\NetBeansProjects\\IssuanceSystem\\Storage\\" + ACCOUNT_PATH);
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
        String regex = "^[a-zA-Z]+$";
        boolean flag = false;

        while(true){
            try{
                System.err.println("-----Login-----");
                System.err.print("Account No: ");
                String accNo = read.nextLine();
                
                 if(String.valueOf(accNo).length() == 4){ 
                        
                        System.out.print("Password: ");
                        String password = read.nextLine(); 
                        
                        if  (password.matches(regex)){
                            
                            if(verifyLogin(accNo, password)){
                                System.out.println("--------------------------------");
                                System.out.println("Hello, " + currentUser.getFullname() +"!");
                                System.out.println("--------------------------------");
                                CompliantUserEnd compliant = new CompliantUserEnd(currentUser);
                                compliant.start();
                            }else{
                                System.out.println("Invalid username or password.");
                            }
                        }else{
                            System.out.println("Password must be letters!");
                        }                      
                    }else{
                        System.out.println("Number must contain 4 digits only. ");
                    }
                
                }
            catch(InputMismatchException ex){
                    System.out.println("Invalid input!" + ex.getMessage());
                    read.nextLine();
            }
        }  
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
