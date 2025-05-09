package com.marcos.financial_test.controller;

import com.marcos.financial_test.dto.TransferDTO;
import com.marcos.financial_test.entity.Transfer;
import com.marcos.financial_test.exception.TransferValidationException;
import com.marcos.financial_test.service.TransferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @GetMapping
    public ResponseEntity<List<TransferDTO>> findAllTransfer() {
        List<TransferDTO> list = transferService.findAllTransfer();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<?> createFinancial(@Valid @RequestBody Transfer transfer) {
        try {
            Transfer savedTransfer = transferService.scheduleTransfer(transfer);
            return ResponseEntity.ok(savedTransfer);
        } catch (TransferValidationException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }
}
