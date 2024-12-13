package com.example.messageria.boleto.service.kafka;

import com.example.messageria.avro.Boleto;
import com.example.messageria.boleto.mapper.BoletoMapper;
import com.example.messageria.boleto.service.BoletoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoConsumer {

    private Logger LOGGER = LoggerFactory.getLogger(Boleto.class);
    private final BoletoService boletoService;

    public NotificacaoConsumer(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @KafkaListener(topics = "${spring.kafka.topico-notificacao}")
    public void consumer(@Payload Boleto boleto) {
        LOGGER.info(String.format("Consumindo notificacao -> %s", boleto));
        boletoService.atualizar(BoletoMapper.toEntity(boleto));
    }

}
