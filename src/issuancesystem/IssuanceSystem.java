/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package issuancesystem;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *11
 * @author Lara
 */
public class IssuanceSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        IssuanceSystem system = new IssuanceSystem();
        system.startIssuanceSystem();
    }
    
    public void startIssuanceSystem(){
    
        Scanner scanner = new Scanner(System.in);
        LoginSystem loginSystem = new LoginSystem();
        ResolverIssueEnd resolverSystem = new ResolverIssueEnd();
        
        while(true){
            try{
                System.out.println("----SELECT-A-SYSTEM----");
                System.out.println("1. Compliant User End\n2. Resolver User End\n3. Exit");
                System.out.print("Selection: ");
                int choice = scanner.nextInt();

                switch(choice){
                    case 1 -> loginSystem.login();
                    case 2 -> resolverSystem.start();
                    case 3 -> {
                        System.out.println("System shutdown!");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid input!");
                } 
            }catch(InputMismatchException ex){
                System.out.println("Please try again.");
                scanner.nextLine();
            }
        }
    }
}
 
    
