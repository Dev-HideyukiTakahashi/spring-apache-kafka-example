package com.example.messageria.boleto.service;

import java.time.LocalDateTime;

import com.example.messageria.boleto.controller.exception.CustomizedNotFoundException;
import org.springframework.stereotype.Service;

import com.example.messageria.boleto.controller.exception.ApplicationException;
import com.example.messageria.boleto.dto.BoletoDTO;
import com.example.messageria.boleto.mapper.BoletoMapper;
import com.example.messageria.boleto.model.BoletoEntity;
import com.example.messageria.boleto.model.enums.SituacaoBoleto;
import com.example.messageria.boleto.repository.BoletoRepository;
import com.example.messageria.boleto.service.kafka.BoletoProducer;

@Service
public class BoletoService {

  private final BoletoRepository boletoRepository;
  private final BoletoProducer boletoProducer;

  public BoletoService(BoletoRepository boletoRepository, BoletoProducer boletoProducer) {
    this.boletoRepository = boletoRepository;
    this.boletoProducer = boletoProducer;
  }

  public BoletoDTO salvar(String codigoBarras) {
    var boletoOptional = boletoRepository.findByCodigoBarras(codigoBarras);
    if (boletoOptional.isPresent()) {
      throw new ApplicationException("Já existe uma solicitação de pagamento para esse boleto");
    }

    var boletoEntity = BoletoEntity.builder()
        .codigoBarras(codigoBarras)
        .situacaoBoleto(SituacaoBoleto.INICIALIZADO)
        .dataCriacao(LocalDateTime.now())
        .dataAtualizacao(LocalDateTime.now())
        .build();

    boletoRepository.save(boletoEntity);
    // envia mensagem para kafka
    boletoProducer.enviarMensagem(BoletoMapper.toAvro(boletoEntity));
    return BoletoMapper.toDTO(boletoEntity);
  }

  public void atualizar(BoletoEntity boleto){
    var boletoAtual = recuperaBoleto(boleto.getCodigoBarras());

    boletoAtual.setSituacaoBoleto(boleto.getSituacaoBoleto());
    boletoAtual.setDataAtualizacao(LocalDateTime.now());
    boletoRepository.save(boletoAtual);
  }

  private BoletoEntity recuperaBoleto(String codigoBarras){
    return boletoRepository.findByCodigoBarras(codigoBarras)
            .orElseThrow(() -> new CustomizedNotFoundException("Boleto não encontrado"));
  }
}
