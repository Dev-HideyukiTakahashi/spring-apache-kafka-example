package com.example.messageria.boleto.controller;

import com.example.messageria.boleto.dto.BoletoDTO;
import com.example.messageria.boleto.dto.BoletoRequestDTO;
import com.example.messageria.boleto.service.BoletoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/boleto")
public class BoletoController {

    private final BoletoService boletoService;

    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @GetMapping(path = "/{codigoBarras}")
    public ResponseEntity<BoletoDTO> buscarBoletoPorCodigoBarras(@PathVariable String codigoBarras){
        var boletoDTO = boletoService.buscarBoletoPorCodigoBarras(codigoBarras);
        return ResponseEntity.ok(boletoDTO);
    }

    @PostMapping

    public ResponseEntity<BoletoDTO> salvar(@Valid @RequestBody BoletoRequestDTO boletoRequestDTO) {
        var boleto = boletoService.salvar(boletoRequestDTO.getCodigoBarras());
        return ResponseEntity.status(HttpStatus.CREATED).body(boleto);
    }

}
