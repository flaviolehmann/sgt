package br.com.basis.sgt.service;
;
import br.com.basis.sgt.domain.Comentario;
import br.com.basis.sgt.repository.ComentarioRepository;
import br.com.basis.sgt.service.dto.ComentarioDTO;
import br.com.basis.sgt.service.error.ComentarioNaoEncontradaException;
import br.com.basis.sgt.service.mapper.ComentarioMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class ComentarioService {
    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;
    public ComentarioService(ComentarioRepository comentarioRepository, ComentarioMapper comentarioMapper) {
        this.comentarioRepository = comentarioRepository;
        this.comentarioMapper = comentarioMapper;
    }
    public List<ComentarioDTO> obterTodos(String descricao) {
        // Caso o título seja passado, realiza o filtro por título
        if (descricao != null && !descricao.isEmpty()) {
            return comentarioMapper.toDto(comentarioRepository.encontarTodosPorDescricao(descricao));
        }
        // Caso não, retorna todos as comentarios no banco
        return comentarioMapper.toDto(comentarioRepository.findAll());
    }
    public ComentarioDTO obterPorId(Long id) {
        Comentario comentario = comentarioRepository.findById(id).orElseThrow(ComentarioNaoEncontradaException::new);
        return comentarioMapper.toDto(comentario);
    }
    public ComentarioDTO salvar(ComentarioDTO comentarioDTO) {
        Comentario comentario = comentarioMapper.toEntity(comentarioDTO);
        Comentario comentarioSalva = comentarioRepository.save(comentario);
        return comentarioMapper.toDto(comentarioSalva);
    }
    public void deletarPorId(Long id) {
        comentarioRepository.deleteById(id);
    }
}