package com.example.messageria.boleto.mapper;

import com.example.messageria.boleto.dto.BoletoDTO;
import com.example.messageria.boleto.model.Boleto;

public class BoletoMapper {

  public static BoletoDTO toDTO(Boleto boleto) {

    return BoletoDTO.builder()
        .codigoBarras(boleto.getCodigoBarras())
        .situacaoBoleto(boleto.getSituacaoBoleto())
        .dataCriacao(boleto.getDataCriacao())
        .dataAtualizacao(boleto.getDataAtualizacao())
        .build();
  }

}
