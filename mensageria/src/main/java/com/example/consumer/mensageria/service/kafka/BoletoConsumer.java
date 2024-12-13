package com.example.consumer.mensageria.service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.example.consumer.mensageria.mapper.BoletoMapper;
import com.example.consumer.mensageria.service.ValidarBoletoService;
import com.example.messageria.avro.Boleto;

@Service
public class BoletoConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(BoletoConsumer.class);
  private final ValidarBoletoService validarBoletoService;

  public BoletoConsumer(ValidarBoletoService validarBoletoService) {
    this.validarBoletoService = validarBoletoService;
  }

  @KafkaListener(topics = "${spring.kafka.topico-boleto}", groupId = "${spring.kafka.consumer.group-id}")
  public void consomeBoleto(Boleto boleto, Acknowledgment acknowledgment) {
    LOGGER.info(String.format("Consumindo mensagem -> %s", boleto));
    validarBoletoService.validar(BoletoMapper.toEntity(boleto));
    acknowledgment.acknowledge();
  }

}
