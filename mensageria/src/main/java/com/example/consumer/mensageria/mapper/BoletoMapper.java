package com.example.consumer.mensageria.mapper;

import com.example.consumer.mensageria.model.BoletoEntity;
import com.example.consumer.mensageria.model.enums.SituacaoBoleto;
import com.example.messageria.avro.Boleto;

public class BoletoMapper {

  public static BoletoEntity toEntity(Boleto boleto) {
    return BoletoEntity.builder()
        .codigoBarras(boleto.getCodigoBarras().toString())
        .situacaoBoleto(SituacaoBoleto.values()[boleto.getSituacaoBoleto()])
        .build();

  }
}
