package br.fiap.ana561460.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PainelItemDTO {
    private String logradouro;
    private String tipoCalcada;
    private Integer altitude;
    private Integer ultimaLamina;
    private String ultimoStatus;
}
