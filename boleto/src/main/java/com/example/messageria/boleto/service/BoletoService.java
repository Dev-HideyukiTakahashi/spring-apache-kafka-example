package com.example.messageria.boleto.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.messageria.boleto.controller.exception.ApplicationException;
import com.example.messageria.boleto.dto.BoletoDTO;
import com.example.messageria.boleto.mapper.BoletoMapper;
import com.example.messageria.boleto.model.Boleto;
import com.example.messageria.boleto.model.enums.SituacaoBoleto;
import com.example.messageria.boleto.repository.BoletoRepository;

@Service
public class BoletoService {

  private final BoletoRepository boletoRepository;

  public BoletoService(BoletoRepository boletoRepository) {
    this.boletoRepository = boletoRepository;
  }

  public BoletoDTO salvar(String codigoBarras) {
    var boletoOptional = boletoRepository.findByCodigoBarras(codigoBarras);
    if (boletoOptional.isPresent()) {
      throw new ApplicationException("Já existe uma solicitação de pagamento para esse boleto");
    }

    var boletoEntity = Boleto.builder()
        .codigoBarras(codigoBarras)
        .situacaoBoleto(SituacaoBoleto.INICIALIZADO)
        .dataCriacao(LocalDateTime.now())
        .dataAtualizacao(LocalDateTime.now())
        .build();

    boletoRepository.save(boletoEntity);
    return BoletoMapper.toDTO(boletoEntity);
  }
}
