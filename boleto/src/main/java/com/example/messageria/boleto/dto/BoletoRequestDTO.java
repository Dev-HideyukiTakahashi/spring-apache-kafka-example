package com.example.messageria.boleto.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoletoRequestDTO {

  @NotNull(message = "não pode ser nulo")
  @NotEmpty(message = "não pode ser vazio")
  String codigoBarras;
}
