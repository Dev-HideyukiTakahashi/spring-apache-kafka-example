package com.example.messageria.boleto.service.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.messageria.avro.Boleto;

@Component
public class BoletoProducer {

  @Value("${spring.kafka.topico-boleto}")
  public String topico;

  // a classe Boleto vem de import com.example.messageria.avro.Boleto e não do
  // model
  private final KafkaTemplate<String, Boleto> kafkaTemplate;

  public BoletoProducer(KafkaTemplate<String, Boleto> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void enviarMensagem(Boleto dto) {
    kafkaTemplate.send(topico, dto);
  }
}
