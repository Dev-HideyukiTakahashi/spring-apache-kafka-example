package com.example.consumer.mensageria.service.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.messageria.avro.Boleto;

@Service
public class NotificaoProducer {

  private final KafkaTemplate<String, Boleto> kafkaTemplate;

  @Value("${spring.kafka.topico-notificacao}")
  public String topico;

  public NotificaoProducer(KafkaTemplate<String, Boleto> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void enviarMensagem(Boleto boleto) {
    kafkaTemplate.send(topico, boleto);
  }
}
