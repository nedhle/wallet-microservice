package app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
@Table(name = "transaction")
public class Transaction {

    public Transaction() {}

    public Transaction(Integer id, String unqTransactionId, BigDecimal debitAmount, Wallet wallet) {
        this.id = id;
        this.unqTransactionId = unqTransactionId;
        this.debitAmount = debitAmount;
        this.wallet = wallet;
    }

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "unq_transaction_id is not blank")
    @NotNull(message = "unq_transaction_id is provided")
    @Column(name = "unq_transaction_id", unique = true, nullable = false)
    private String unqTransactionId;

    @NotNull(message = "Transaction debit_amount is provided")
    @Column(name = "debit_amount", nullable = false)
    private BigDecimal debitAmount;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id",nullable=false)
    private Wallet wallet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnqTransactionId() {
        return unqTransactionId;
    }

    public void setUnqTransactionId(String unqTransactionId) {
        this.unqTransactionId = unqTransactionId;
    }

    public BigDecimal getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(BigDecimal debitAmount) {
        this.debitAmount = debitAmount;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

}
