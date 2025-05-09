package com.marcos.financial_test.service;

import static com.marcos.financial_test.constants.ConstantsMessage.CALCULATED_FEE;
import static com.marcos.financial_test.constants.ConstantsMessage.NAO_EXISTEM_TAXAS_PARA_TRANSFERENCIA;
import static com.marcos.financial_test.constants.ConstantsMessage.TRANSFERENCIA_MAIOR_QUE_ZERO;
import static com.marcos.financial_test.constants.ConstantsMessage.TRANSFERENCIA_NAO_PODE_SER_ANTES_DATA_ATUAL;
import static com.marcos.financial_test.constants.ConstantsMessage.TRANSFER_SCHEDULED_SUCCESSFULY;
import static com.marcos.financial_test.constants.ConstantsNumeric.DAYS_RANGES;
import static com.marcos.financial_test.constants.ConstantsNumeric.TARGED_ACCOUNT;
import static com.marcos.financial_test.constants.ConstantsNumeric.TAX_RATES;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.marcos.financial_test.dto.TransferDTO;
import com.marcos.financial_test.entity.Transfer;
import com.marcos.financial_test.exception.TransferValidationException;
import com.marcos.financial_test.repository.TransferRepository;

@Service
public class TransferService {
    private static final Logger logger = LoggerFactory.getLogger(TransferService.class);
    
//    private static final ZoneOffset zoneOffset = ZoneOffset.of("America/Sao_Paulo");
    
    private static final ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

    @Autowired
    private TransferRepository repository;

    public List<TransferDTO> findAllTransfer() {
        List<Transfer> result = repository.findAll(Sort.by(TARGED_ACCOUNT));
        return result.stream().map(x -> new TransferDTO(x)).collect(Collectors.toList());
    }

    public Transfer scheduleTransfer(Transfer transfer) {
        validateTransfer(transfer);

        double rate = calculateRate(transfer.getAppointmentDate(), transfer.getAmount());
        double valueWithRate = transfer.getAmount() - (transfer.getAmount() * rate / 100);

        transfer.setRate(rate);
        transfer.setTransferAmount(valueWithRate);
        LocalDateTime currentDate = LocalDateTime.now();
        transfer.setDateTransfer(currentDate);
        transfer.setAppointmentDate(transfer.getAppointmentDate().withHour(23).withMinute(59).withSecond(59));

        Transfer savedTransfer = repository.save(transfer);

        logger.info(TRANSFER_SCHEDULED_SUCCESSFULY, savedTransfer);

        return savedTransfer;
    }

    void validateTransfer(Transfer transfer) {
        if (transfer.getAmount() <= 0) {
            throw new TransferValidationException(TRANSFERENCIA_MAIOR_QUE_ZERO, HttpStatus.BAD_REQUEST);
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate transferLocalDate = transfer.getDateTransfer().atZone(zoneId).toInstant().atZone(zoneId).toLocalDate();

        if (transferLocalDate.isBefore(currentDate)) {
            throw new TransferValidationException(TRANSFERENCIA_NAO_PODE_SER_ANTES_DATA_ATUAL, HttpStatus.BAD_REQUEST);
        }
    }

    private double calculateRate(LocalDateTime appointmentDate, double amount) {
        LocalDate currentDate = LocalDate.now();
        LocalDate transferLocalDate = appointmentDate.atZone(zoneId).toInstant().atZone(zoneId).toLocalDate();

        long days = Math.abs(ChronoUnit.DAYS.between(currentDate, transferLocalDate));

        int index = 0;
        while (index < DAYS_RANGES.length && days > DAYS_RANGES[index]) {
            index++;
        }

        if (index < TAX_RATES.length) {
            double fee = amount > 0 ? TAX_RATES[index] : 0.0;
            logger.info(CALCULATED_FEE, fee);
            return fee;
        } else {
            throw new TransferValidationException(NAO_EXISTEM_TAXAS_PARA_TRANSFERENCIA, HttpStatus.BAD_REQUEST);
        }
    }
}
