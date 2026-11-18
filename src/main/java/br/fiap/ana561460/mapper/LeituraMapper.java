package br.fiap.ana561460.mapper;

import br.fiap.ana561460.domain.Leitura;
import br.fiap.ana561460.domain.Trecho;
import br.fiap.ana561460.dto.LeituraDTO;
import org.springframework.stereotype.Component;


@Component
public class LeituraMapper {
    public Leitura toEntity(LeituraDTO dto, Trecho trecho, String status) {
        Leitura leitura = new Leitura();
        leitura.setTrecho(trecho);
        leitura.setStatus(status);
        leitura.setId(dto.getId());
        leitura.setDataHora(dto.getDataHora());
        leitura.setLaminaCm(dto.getLaminaCm());
        return leitura;
    }

    public LeituraDTO toDTO(Leitura leitura){
        LeituraDTO dto = new LeituraDTO();
        dto.setId(leitura.getId());
        dto.setDataHora(leitura.getDataHora());
        dto.setLaminaCm(leitura.getLaminaCm());
        dto.setStatus(leitura.getStatus());
        String logradouro = leitura.getTrecho().getLogradouro();
        String bairro = leitura.getTrecho().getBairro().getNome();
        dto.setTrechoDescricao(logradouro+ " (" +bairro+ ")");
        dto.setTrechoId(leitura.getTrecho().getId());

        return dto;
    }
}
