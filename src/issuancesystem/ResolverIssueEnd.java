/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package issuancesystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Lara
 */
    
public class ResolverIssueEnd {
    private static final String ROOT_PATH = "C:\\Users\\Lara\\Documents\\NetBeansProjects\\IssuanceSystem\\Storage\\";
    private static final String ACCOUNT_RELATED_PATH = "Account-related\\";
    private static final String TRANSACTION_RELATED_PATH = "Transaction-related\\";
    private static final String OTHER_ISSUES_PATH = "OtherIssue\\";
    
    public void start(){
        Scanner scanner = new Scanner(System.in);
        
        while(true){
        
        try{
            System.out.println("Choose an Category\n1. Account-related\n2. Transaction\n3. Others\n4. Exit");
            System.out.println("Selection: ");
            int choice = scanner.nextInt();

            if(choice == 4){
                return;
            }
            String issueType = switch (choice) {
                case 1 -> "Account-related";
                case 2 -> "Transaction-related";
                case 3 -> "Other Issue";
                default -> "";
            };

            if(!issueType.isEmpty()){
                getAllIssuanceByCategory(choice, issueType);
                break;
            }
            
            System.out.println("Invalid Input try again!");

        }catch(InputMismatchException ex){
            System.out.println("Invalid input!");
            scanner.nextLine();
        }
    }
    }
    
    private void getAllIssuanceByCategory(int choice, String type){
        Scanner scan = new Scanner(System.in);
        String folderPath = getFileName(choice);
        String fullPath = ROOT_PATH + folderPath;
        File dir = new File(fullPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));
        
        while(true){
            
            System.out.println("---" + type.toUpperCase() + "---");

            if( files == null || files.length == 0){
                System.out.println("No issue was submitted.");
                System.out.println("-------------------");
                return;
            }

            for(File file : files){
                String fileName = file.getName();
                String baseName = fileName.substring(0, fileName.lastIndexOf("."));
                System.out.println(baseName);
            }
            System.out.println("-------------------");
            //thinks
            try{     
                System.out.println("1. Resolve Issue\n2. Back");
                System.out.print("Selection: ");
                int selection = scan.nextInt();
                scan.nextLine(); //Consumes a line
                switch(selection){
                    case 1 -> {
                        System.out.print("Enter issuance code to view: ");
                        String issuanceCode = scan.nextLine();
                        viewIssue(fullPath, issuanceCode);
                    }
                    case 2 -> start();
                    default -> System.out.println("Invalid Input");
                }

            }catch(InputMismatchException ex){
                System.err.println("Invalid data type!");
                scan.nextLine(); //Consumes a line
            }
        }
    }
    
    
    public void viewIssue(String fullPath, String fileName){
        Scanner scan = new Scanner(System.in);
        Path filePath = Paths.get(fullPath + fileName + ".txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            //thanks po dearest <3
        } catch (IOException ex) {
            System.err.println("File not found or could not read the file.");
            return;
        }
        
        System.out.println("Do you want to resolve this?(yes/no)\nAnswer: ");
        String answer = scan.nextLine();
        if("yes".equals(answer)){
            resolveIssue(filePath, fileName);
        }else{
            System.out.println("Returning to main menu...");
            start();
        }
    }
    
    public void resolveIssue(Path filePath, String fileName) {
        
        try{
            if (Files.deleteIfExists(filePath)) {
                System.out.println("Issue " + fileName + " resolved and file deleted.");
            } else {
                System.out.println("Issue " + fileName + " not found.");
            }
        }catch(IOException e){
            System.out.println("An error occurred while trying to resolve the issue.");
            e.printStackTrace();
        }
        start();
    }
    
    private String getFileName(int choice) {
        return switch (choice) {
            case 1 -> ACCOUNT_RELATED_PATH;
            case 2 -> TRANSACTION_RELATED_PATH;
            case 3 -> OTHER_ISSUES_PATH;
            default -> "";
        };
    }
}
