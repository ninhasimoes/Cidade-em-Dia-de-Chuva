package br.fiap.ana561460.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LeituraDTO {
    private Long id;
    @NotNull
    private Long trechoId;
    private String trechoDescricao;
    @NotNull @PastOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataHora;
    @NotNull  @Min(0)  @Max(300)
    private Integer laminaCm;
    private String status;
}
