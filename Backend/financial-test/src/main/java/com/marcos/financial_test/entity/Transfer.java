package com.marcos.financial_test.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_transfer")
@JsonIgnoreProperties(value = "createdAt, createUserId, updatedAt, lastUserId",
allowGetters = true)
public class Transfer extends AbstractEntity<Transfer> {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "source_account")
    private String sourceAccount;
    @Column(name = "target_account")
    private String targetAccount;
    private Double amount;
    @Column(name = "transfer_amount")
    private Double transferAmount;
    private Double rate;
    @Column(name = "date_transfer")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "America/Sao_Paulo")
    private LocalDateTime dateTransfer;
    @Column(name = "appointment_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "America/Sao_Paulo")
    private LocalDateTime appointmentDate;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        if (sourceAccount == null || sourceAccount.trim().isEmpty()) {
            throw new RuntimeException("A conta de origem não pode ser nula ou vazia.");
        }
        this.sourceAccount = sourceAccount;
    }

    public String getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(String targetAccount) {
        if (targetAccount == null || targetAccount.trim().isEmpty()) {
            throw new RuntimeException("A conta de destino não pode ser nula ou vazia");
        }
        this.targetAccount = targetAccount;
    }
    
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        if (amount < 0) {
            throw new RuntimeException("O valor da transferência não pode ser negativo;");
        }
        this.amount = amount;
    }

    public Double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Double transferAmount) {
//        if (transferAmount < 0) {
//            throw new RuntimeException("O valor da transferência não pode ser negativo;");
//        }
        this.transferAmount = transferAmount;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        if (rate < 0) {
            throw new RuntimeException("A taxa da transferência não pode ser negativa.");
        }
        this.rate = rate;
    }

    public LocalDateTime getDateTransfer() {
        return dateTransfer;
    }

    public void setDateTransfer(LocalDateTime dateTransfer) {
        this.dateTransfer = dateTransfer;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", sourceAccount='" + sourceAccount + '\'' +
                ", targetAccount='" + targetAccount + '\'' +
                ", amount=" + amount +
                ", transferAmount=" + transferAmount +
                ", rate=" + rate +
                ", dataTransfer=" + dateTransfer +
                ", appointmentDate=" + appointmentDate +
                '}';
    }
}
