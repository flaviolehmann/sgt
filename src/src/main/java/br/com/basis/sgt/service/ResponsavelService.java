package br.com.basis.sgt.service;
import br.com.basis.sgt.domain.Responsavel;
import br.com.basis.sgt.repository.ResponsavelRepository;
import br.com.basis.sgt.service.dto.ResponsavelDTO;
import br.com.basis.sgt.service.error.ResponsavelNaoEncontradaException;
import br.com.basis.sgt.service.mapper.ResponsavelMapper;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ResponsavelService {
    private final ResponsavelRepository responsavelRepository;
    private final ResponsavelMapper responsavelMapper;
    public ResponsavelService(ResponsavelRepository responsavelRepository, ResponsavelMapper responsavelMapper) {
        this.responsavelRepository = responsavelRepository;
        this.responsavelMapper = responsavelMapper;
    }
    public List<ResponsavelDTO> obterTodos(String setor) {
        // Caso o título seja passado, realiza o filtro por título
        if (setor != null && !setor.isEmpty()) {
            return responsavelMapper.toDto(responsavelRepository.encontarTodosPorSetor(setor));
        }
        // Caso não, retorna todos as responsavels no banco
        return responsavelMapper.toDto(responsavelRepository.findAll());
    }
    public ResponsavelDTO obterPorId(Long id) {
        Responsavel responsavel = responsavelRepository.findById(id).orElseThrow(ResponsavelNaoEncontradaException::new);
        return responsavelMapper.toDto(responsavel);
    }
    public ResponsavelDTO salvar(ResponsavelDTO responsavelDTO) {
        Responsavel responsavel = responsavelMapper.toEntity(responsavelDTO);
        Responsavel responsavelSalva = responsavelRepository.save(responsavel);
        return responsavelMapper.toDto(responsavelSalva);
    }
    public void deletarPorId(Long id) {
        responsavelRepository.deleteById(id);
    }
}
