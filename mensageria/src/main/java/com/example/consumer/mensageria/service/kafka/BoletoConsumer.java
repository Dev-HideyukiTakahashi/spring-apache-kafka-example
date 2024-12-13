package com.example.consumer.mensageria.service.kafka;

import java.util.Map;

import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.stereotype.Service;

import com.example.messageria.avro.Boleto;

@Service
public class BoletoConsumer implements ConsumerSeekAware {

  private static final Logger LOGGER = LoggerFactory.getLogger(BoletoConsumer.class);

  @KafkaListener(topics = "${spring.kafka.topico-boleto}", groupId = "${spring.kafka.consumer.group-id}")
  public void consomeBoleto(Boleto boleto) {
    LOGGER.info(String.format("Consumindo mensagem -> %s", boleto));
  }

  // config para rastrear apenas mensagens novas
  @Override
  public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
    assignments.forEach((t, o) -> callback.seekToEnd(t.topic(), t.partition()));
  }

}
