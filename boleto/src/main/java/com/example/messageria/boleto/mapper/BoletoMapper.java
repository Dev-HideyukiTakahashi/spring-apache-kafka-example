package com.example.messageria.boleto.mapper;

import com.example.messageria.avro.Boleto;
import com.example.messageria.boleto.dto.BoletoDTO;
import com.example.messageria.boleto.model.BoletoEntity;
import com.example.messageria.boleto.model.enums.SituacaoBoleto;

public class BoletoMapper {

  public static BoletoDTO toDTO(BoletoEntity boleto) {

    return BoletoDTO.builder()
        .codigoBarras(boleto.getCodigoBarras())
        .situacaoBoleto(boleto.getSituacaoBoleto())
        .dataCriacao(boleto.getDataCriacao())
        .dataAtualizacao(boleto.getDataAtualizacao())
        .build();
  }

  public static Boleto toAvro(BoletoEntity boleto) {

    return Boleto.newBuilder()
        .setCodigoBarras(boleto.getCodigoBarras())
        .setSituacaoBoleto(boleto.getSituacaoBoleto().ordinal())
        .build();
  }

  public static BoletoEntity toEntity(Boleto boleto){
    return BoletoEntity.builder()
            .codigoBarras(boleto.getCodigoBarras().toString())
            .situacaoBoleto(SituacaoBoleto.values()[boleto.getSituacaoBoleto()])
            .build();
  }

}
