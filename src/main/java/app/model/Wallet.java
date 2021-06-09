package app.model;


import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "wallet")
public class Wallet {
    public Wallet(Integer id, BigDecimal balance, String name) {
        this.id = id;
        this.balance = balance;
        this.name = name;
    }

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(0)
    @Column(name = "balance",nullable = false)
    @NotNull(message = "Wallet balance must be provided")
    private BigDecimal balance;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Transaction> transaction;

    @Transient
    public BigDecimal currentBalance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wallet() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    @JsonGetter(value = "currentBalance")
    public BigDecimal getCurrentBalance() {
        BigDecimal tmpBalance = new BigDecimal(0);
        for(Transaction t : this.getTransaction())
            tmpBalance = tmpBalance.add(t.getDebitAmount());
         this.setCurrentBalance(this.balance.subtract(tmpBalance));
        return this.currentBalance;
    }

}
