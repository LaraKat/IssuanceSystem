/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package issuancesystem;

import issuancesystem.Objects.Account;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class CompliantUserEnd{
    private static final String ROOT_PATH = "C:\\Users\\Lara\\Documents\\NetBeansProjects\\IssuanceSystem\\Storage\\";
    private static final String BANKING_ISSUES_PATH = "BankingIssue\\";
    private static final String TECHNICAL_ISSUES_PATH = "TransactionIssue\\";
    private static final String OTHER_ISSUES_PATH = "OtherIssue\\";
    private final Account currentUser;
    
    public CompliantUserEnd(Account account){
        this.currentUser = account; 
    }
    
    public void start(){
        Scanner scanner = new Scanner(System.in);
        LoginSystem loginSystem = new LoginSystem();
        
        while(true){
            System.out.println("1. File an complain");
            System.out.println("2. Logout");
            System.out.print("Selection: ");
            int choice = scanner.nextInt();

            switch(choice){
                case 1 -> issuance();
                case 2 -> loginSystem.login();
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    
    public void issuance(){
        String accName = currentUser.getFullname();
        String accNo = String.valueOf(currentUser.getAccountNo());
        
        Scanner scanner = new Scanner(System.in);
            
        while(true){
            System.out.println("Enter the issue type: \n1. Banking\n2. Technical\n3. Other");
            System.out.print("Selection: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); //Consumes a line
            
            String issueType = switch (choice) {
                case 1 -> "Banking Issue";
                case 2 -> "Technical Issue";
                case 3 -> "Other Issue";
                default -> "";
            };
            
            if(!issueType.isBlank()){
                System.out.println("Enter a brief description of the issue: ");
                String issueDescription = scanner.nextLine();

                if(!issueDescription.isBlank()){
                    
                    System.out.println("Confirm if you want to submit(yes/no)");
                    String confirmation = scanner.nextLine();
                    
                    if("yes".equals(confirmation.toLowerCase())){
                        registerIssue(choice, issueType, accName, accNo, issueDescription);
                    }
                    break;
                }
            }
        }
    }
    
    public void registerIssue(int issueChoice, String type, String accountName, String accountNumber, String description) {
        String folderPath = getFileName(issueChoice);
        String fileName = generateCode();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        Path filePath = Paths.get(ROOT_PATH + folderPath + fileName +".txt");
        
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE)) {
            writer.write("Date: " + timeStamp);
            writer.newLine();
            writer.write("Account No: " + accountNumber);
            writer.newLine();
            writer.write("Account Name: " + accountName);
            writer.newLine();
            writer.write("Issue Type: " + type);
            writer.newLine();
            writer.write("Description: ");
            writer.write(description);
            writer.newLine();
            System.out.println("Issue registered successfully!");
        } catch (IOException e) {
            System.out.println("Error occurred while registering the issue: " + e.getMessage());
        }
    }

    private String getFileName(int choice) {
        return switch (choice) {
            case 1 -> BANKING_ISSUES_PATH;
            case 2 -> TECHNICAL_ISSUES_PATH;
            case 3 -> OTHER_ISSUES_PATH;
            default -> "";
        };
    }

    public String generateCode(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder(6);
        Random random = new Random();
        
        for(int i = 0; i < 6; i++){
            code.append(characters.charAt(random.nextInt(characters.length())));
        }
        return code.toString();
    }
}
