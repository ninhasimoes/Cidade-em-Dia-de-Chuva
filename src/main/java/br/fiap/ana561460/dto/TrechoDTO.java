package br.fiap.ana561460.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TrechoDTO {
    private Long id;
    @NotBlank @Size(max = 120)
    private String logradouro;
    @NotBlank
    private String tipoCalcada;
    @NotNull @Min(0) @Max(2000)
    private Integer altitude;
    @NotNull
    private Long bairroId;
    private String bairroNome;
}
