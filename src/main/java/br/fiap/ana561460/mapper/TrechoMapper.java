package br.fiap.ana561460.mapper;

import br.fiap.ana561460.domain.Bairro;
import br.fiap.ana561460.domain.Trecho;
import br.fiap.ana561460.dto.TrechoDTO;
import org.springframework.stereotype.Component;

@Component
public class TrechoMapper {
    public Trecho toEntity(TrechoDTO dto, Bairro bairro){
        Trecho trecho = new Trecho();
        trecho.setBairro(bairro);
        trecho.setId(dto.getId());
        trecho.setTipoCalcada(dto.getTipoCalcada());
        trecho.setLogradouro(dto.getLogradouro());
        trecho.setAltitude(dto.getAltitude());
        return trecho;
    }


    public TrechoDTO toDTO (Trecho trecho){
        TrechoDTO dto = new TrechoDTO();
        dto.setId(trecho.getId());
        dto.setTipoCalcada(trecho.getTipoCalcada());
        dto.setLogradouro(trecho.getLogradouro());
        dto.setAltitude(trecho.getAltitude());
        dto.setBairroId(trecho.getBairro().getId());
        dto.setBairroNome(trecho.getBairro().getNome());
        return dto;
    }
}
