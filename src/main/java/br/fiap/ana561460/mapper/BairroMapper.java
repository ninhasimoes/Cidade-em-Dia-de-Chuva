package br.fiap.ana561460.mapper;

import br.fiap.ana561460.domain.Bairro;
import br.fiap.ana561460.dto.BairroDTO;
import org.springframework.stereotype.Component;

@Component
public class BairroMapper {
    public Bairro toEntity(BairroDTO dto){
        Bairro bairro = new Bairro();
        bairro.setNome(dto.getNome());
        bairro.setId(dto.getId());
        bairro.setZona(dto.getZona());
        bairro.setPossuiPiscinao(dto.getPossuiPiscinao());
        return bairro;
    }


    public BairroDTO toDTO (Bairro bairro){
        BairroDTO dto = new BairroDTO();
        dto.setId(bairro.getId());
        dto.setZona(bairro.getZona());
        dto.setPossuiPiscinao(bairro.getPossuiPiscinao());
        dto.setNome(bairro.getNome());
        return dto;
    }
}
