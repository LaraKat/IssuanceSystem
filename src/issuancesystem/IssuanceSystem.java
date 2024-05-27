/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package issuancesystem;

import java.util.Scanner;

/**
 *
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
        
        System.out.println("SELECT-A-SYSTEM");
        System.out.println("1. Compliant User End\n2. Resolver User End");
        System.out.print("Selection: ");
        int choice = scanner.nextInt();
        
        switch(choice){
            case 1 -> loginSystem.login();
            case 2 -> resolverSystem.start();
            default -> {
                System.out.println("System shutdown.");
                System.exit(0);
            }
        }
    }
}