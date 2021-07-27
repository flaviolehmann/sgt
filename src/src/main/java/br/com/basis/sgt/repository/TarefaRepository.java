package br.com.basis.sgt.repository;


import br.com.basis.sgt.domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Classe responsável por realizar as consultas no banco
 */
@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    /**
     * Encontra todas as tarefas por Título
     * @param titulo
     * @return
     */
    List<Tarefa> findAllByTitulo(String titulo);

    /**
     * Encontra todos as tarefas por Título
     * Também captura tarefas que contenham somente parte dos títulos
     * Isso ocorre graças ao uso do LIKE juntamente com os %%
     * @param titulo
     * @return
     */
    @Query("FROM Tarefa WHERE titulo LIKE %:titulo%")
    List<Tarefa> encontarTodosPorTitulo(@Param("titulo") String titulo);


}
