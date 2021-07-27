package br.com.basis.sgt.repository;


import br.com.basis.sgt.domain.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Classe responsável por realizar as consultas no banco
 */
@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {

    /**
     * Encontra todas os responsaveis por Setor
     * * @param setor
     * @return
     */
    List<Responsavel> findAllBySetor(String setor);

    /**
     * Encontra todos os responsaveis por Setor
     * Também captura responsaveis que se responsabilize por alguns setores
     * Isso ocorre graças ao uso do LIKE juntamente com os %%
     * @param setor
     * @return
     */
    @Query("FROM Responsavel WHERE setor LIKE %:setor%")
    List<Responsavel> encontarTodosPorSetor(@Param("setor") String setor);


}
