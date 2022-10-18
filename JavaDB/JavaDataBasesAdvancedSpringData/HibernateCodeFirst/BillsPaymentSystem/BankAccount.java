package HibernateCodeFirst.BillsPaymentSystem;

import javax.persistence.*;

@Entity(name = "bank_account")
public class BankAccount extends BillingDetail {

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "SWIFT_code", unique = true, nullable = false)
    private String SWIFTCode;

    public BankAccount() {
        super();
    }

    public BankAccount(int id, int number, User owner, String bankName, String SWIFTCode) {
        super(id, number, owner);
        this.bankName = bankName;
        this.SWIFTCode = SWIFTCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSWIFTCode() {
        return SWIFTCode;
    }

    public void setSWIFTCode(String SWIFTCode) {
        this.SWIFTCode = SWIFTCode;
    }
}
