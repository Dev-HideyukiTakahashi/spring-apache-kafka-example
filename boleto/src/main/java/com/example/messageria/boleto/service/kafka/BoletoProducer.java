package com.example.messageria.boleto.service.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.messageria.boleto.dto.BoletoDTO;

@Component
public class BoletoProducer {

  @Value("${spring.kafka.topico-boleto}")
  public String topico;

  private final KafkaTemplate<String, BoletoDTO> kafkaTemplate;

  public BoletoProducer(KafkaTemplate<String, BoletoDTO> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void enviarMensagem(BoletoDTO dto) {
    kafkaTemplate.send(topico, dto);
  }
}
