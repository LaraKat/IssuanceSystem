/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package issuancesystem.Objects;

/**
 *
 * @author Lara
 */
public class Issuance {
    private String IssuanceCode;
    private int AccountNo;
    private String FullName;
    private String Type;
    private String Description;

    public Issuance() {
        this.IssuanceCode = null;
        this.AccountNo = 0;
        this.FullName = null;
        this.Type = null;
        this.Description = null;
    }

    public String getIssuanceCode() {
        return IssuanceCode;
    }

    public int getAccountNo() {
        return AccountNo;
    }

    public String getFullName() {
        return FullName;
    }

    public String getType() {
        return Type;
    }

    public String getDescription() {
        return Description;
    }
}
