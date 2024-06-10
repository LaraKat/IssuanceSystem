/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package issuancesystem.Objects;

/**
 *
 * @author Lara
 */
public class Account {
    private final String AccountNo;
    private final String Password;
    private final String Fullname;
    

    public Account(String AccountNo, String Password, String Fullname) {
        this.AccountNo = AccountNo;
        this.Password = Password;
        this.Fullname = Fullname;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public String getPassword() {
        return Password;
    }

    public String getFullname() {
        return Fullname;
    }
}
