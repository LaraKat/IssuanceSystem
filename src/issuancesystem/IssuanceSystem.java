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
        
        Scanner scanner = new Scanner(System.in);
        LoginSystem loginSystem = new LoginSystem();
        ResolverIssueEnd resolverSystem = new ResolverIssueEnd();
        
        while(true){
            try{
                System.out.println("----SELECT-A-SYSTEM----");
                System.out.println("A. Compliant User End\nB. Resolver User End\nC. Exit");
                System.out.print("Selection: ");
                String choice = scanner.nextLine();

                if(choice.isBlank()){
                    throw new NullPointerException();
                }
                    
                switch(choice.toLowerCase().charAt(0)){
                    case 'a' -> loginSystem.login();
                    case 'b' -> resolverSystem.start();
                    case 'c' -> {
                        System.out.println("System shutdown!");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid input!");
                } 
            }catch(InputMismatchException ex){
                System.out.println("Please try again.");
                scanner.nextLine();
            }catch(NullPointerException ex){
                System.out.println("Invalid input!" + ex.getMessage());
                scanner.nextLine();
            }
        }
    }
}
 
    
