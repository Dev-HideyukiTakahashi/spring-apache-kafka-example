package com.example.consumer.mensageria.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.consumer.mensageria.mapper.BoletoMapper;
import com.example.consumer.mensageria.model.BoletoEntity;
import com.example.consumer.mensageria.model.enums.SituacaoBoleto;
import com.example.consumer.mensageria.repository.BoletoRepository;
import com.example.consumer.mensageria.service.kafka.NotificaoProducer;

import lombok.SneakyThrows;

@Service
public class PagarBoletoService {

  private final BoletoRepository boletoRepository;
  private final NotificaoProducer notificaoProducer;

  public PagarBoletoService(BoletoRepository boletoRepository, NotificaoProducer notificaoProducer) {
    this.boletoRepository = boletoRepository;
    this.notificaoProducer = notificaoProducer;
  }

  @SneakyThrows
  public void pagar(BoletoEntity boletoEntity) {
    Thread.sleep(10000);
    String codigoBarrasNumeros = boletoEntity.getCodigoBarras().replaceAll("[^0-9]", "");
    if (codigoBarrasNumeros.length() > 47) {
      complementarBoletoErro(boletoEntity);
    } else {
      complementarBoletoSucesso(boletoEntity);
    }

    boletoRepository.save(boletoEntity);
    notificaoProducer.enviarMensagem(BoletoMapper.toAvro(boletoEntity));
  }

  private void complementarBoletoErro(BoletoEntity boletoEntity) {
    boletoEntity.setDataAtualizacao(LocalDateTime.now());
    boletoEntity.setSituacaoBoleto(SituacaoBoleto.ERRO_PAGAMENTO);
  }

  private void complementarBoletoSucesso(BoletoEntity boletoEntity) {
    boletoEntity.setDataAtualizacao(LocalDateTime.now());
    boletoEntity.setSituacaoBoleto(SituacaoBoleto.PAGO);
  }
}
