package br.fiap.ana561460.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BairroDTO {
    private Long id;
    @NotBlank @Size(max = 80)
    private String nome;
    @NotBlank
    private String zona;
    private Boolean possuiPiscinao;

}


