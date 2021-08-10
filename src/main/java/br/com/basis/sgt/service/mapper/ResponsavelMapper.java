package br.com.basis.sgt.service.mapper;

import br.com.basis.sgt.domain.Responsavel;
import br.com.basis.sgt.service.dto.ResponsavelDTO;
import org.mapstruct.Mapper;

/**
 * Faz a conversão de DTO para entidade
 * o 'uses' é usado devido à RepomsavelDTO possuir um DTO dentro dela
 */
@Mapper(componentModel = "spring", uses = ComentarioMapper.class)
public interface ResponsavelMapper extends EntityMapper<ResponsavelDTO, Responsavel> {
}
