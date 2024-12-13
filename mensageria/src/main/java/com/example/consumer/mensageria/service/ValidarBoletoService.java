package com.example.consumer.mensageria.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.consumer.mensageria.mapper.BoletoMapper;
import com.example.consumer.mensageria.model.BoletoEntity;
import com.example.consumer.mensageria.model.enums.SituacaoBoleto;
import com.example.consumer.mensageria.repository.BoletoRepository;
import com.example.consumer.mensageria.service.kafka.NotificaoProducer;

@Service
public class ValidarBoletoService {

  private final BoletoRepository boletoRepository;
  private final NotificaoProducer notificaoProducer;
  private final PagarBoletoService pagarBoletoService;

  public ValidarBoletoService(BoletoRepository boletoRepository, NotificaoProducer notificaoProducer,
      PagarBoletoService pagarBoletoService) {
    this.boletoRepository = boletoRepository;
    this.notificaoProducer = notificaoProducer;
    this.pagarBoletoService = pagarBoletoService;
  }

  public void validar(BoletoEntity boletoEntity) {
    var codigo = Integer.parseInt(boletoEntity.getCodigoBarras().substring(0, 1));
    if (codigo % 2 == 0) {
      complementarBoletoErro(boletoEntity);
      boletoRepository.save(boletoEntity);
      // notificando
      notificaoProducer.enviarMensagem(BoletoMapper.toAvro(boletoEntity));
    } else {
      complementarBoletoSucesso(boletoEntity);
      boletoRepository.save(boletoEntity);
      // notificando
      notificaoProducer.enviarMensagem(BoletoMapper.toAvro(boletoEntity));
      // seguindo com pagamento
      pagarBoletoService.pagar(boletoEntity);
    }
  }

  private void complementarBoletoErro(BoletoEntity boleto) {
    boleto.setDataCriacao(LocalDateTime.now());
    boleto.setDataAtualizacao(LocalDateTime.now());
    boleto.setSituacaoBoleto(SituacaoBoleto.ERRO_VALIDACAO);
  }

  private void complementarBoletoSucesso(BoletoEntity boleto) {
    boleto.setDataCriacao(LocalDateTime.now());
    boleto.setDataAtualizacao(LocalDateTime.now());
    boleto.setSituacaoBoleto(SituacaoBoleto.VALIDADO);
  }
}
