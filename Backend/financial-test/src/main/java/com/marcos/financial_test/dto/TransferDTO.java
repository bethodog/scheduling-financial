package com.marcos.financial_test.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marcos.financial_test.entity.Transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferDTO {

    @NotBlank(message = "Campo requerido")
    @Size(max = 10, message = "O campo [targetAccount] precisa de 10 caracteres")
    private String targetAccount;
    @NotBlank(message = "Campo requerido")
    @Size(max = 10, message = "O campo [sourceAccount] precisa de 10 caracteres")
    private String sourceAccount;
    @NotBlank(message = "Campo requerido")
    @Positive(message = "O campo [amount] deve ser positivo")
    private Double amount;
    @NotBlank(message = "Campo requerido")
    @Positive(message = "O campo [transferAmount] deve ser positivo")
    private Double transferAmount;
    @NotBlank(message = "Campo requerido")
    @Positive(message = "O campo [rate] deve ser positivo")
    private Double rate;
    @NotBlank(message = "Campo requerido")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
    private LocalDateTime dateTransfer;
    @NotBlank(message = "Campo requerido")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
    private LocalDateTime appointmentDate;

    public TransferDTO(Transfer entity) {
        this.targetAccount = entity.getTargetAccount();
        this.sourceAccount = entity.getSourceAccount();
        this.amount = entity.getAmount();
        this.transferAmount = entity.getTransferAmount();
        this.rate = entity.getRate();
        this.dateTransfer = entity.getDateTransfer();
        this.appointmentDate = entity.getAppointmentDate();
    }

}
