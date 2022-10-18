package HibernateCodeFirst.BillsPaymentSystem;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "credit_card")
public class CreditCard extends BillingDetail {

    @Column(name = "card_type", nullable = false)
    private String cardType;

    @Column(name = "expiration_month", nullable = false)
    private String expirationMonth;

    @Column(name = "expiration_year", nullable = false)
    private String expirationYear;

    public CreditCard() {
        super();
    }

    public CreditCard(int id, int number, User owner, String cardType, String expirationMonth, String expirationYear) {
        super(id, number, owner);
        this.cardType = cardType;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }
}
