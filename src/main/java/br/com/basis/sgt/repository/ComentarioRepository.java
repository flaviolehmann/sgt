package br.com.basis.sgt.repository;

import br.com.basis.sgt.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Classe responsável por realizar as consultas no banco
 */
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    /**
     * Encontra todas os responsaveis por Setor
     * * @param descricao
     *
     * @return
     */
    List<Comentario> findAllByDescricao(String descricao);

    /**
     * Encontra todos os responsaveis por Setor
     * Também captura responsaveis que se responsabilize por alguns descricaoes
     * Isso ocorre graças ao uso do LIKE juntamente com os %%
     *
     * @param descricao
     * @return
     */
    @Query("FROM Comentario WHERE descricao LIKE %:descricao%")
    List<Comentario> encontarTodosPorDescricao(@Param("descricao") String descricao);


}