package com.example.messageria.boleto.dto;

import java.time.LocalDateTime;

import com.example.messageria.boleto.model.enums.SituacaoBoleto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoletoDTO {

  private String codigoBarras;
  private SituacaoBoleto situacaoBoleto;
  private LocalDateTime dataCriacao;
  private LocalDateTime dataAtualizacao;
}
