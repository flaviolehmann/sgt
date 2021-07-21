package br.com.basis.sgt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaResource {

    List<TarefaDTO> tarefas = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> obterTodos() {
        return new ResponseEntity<>(tarefas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> criarTarefa(@RequestBody TarefaDTO tarefa) {
        tarefas.add(tarefa);
        return ResponseEntity.ok(tarefa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> obterPorId(@PathVariable("id") Long id) {
        TarefaDTO tarefaDTO = new TarefaDTO();
        for (TarefaDTO tarefa : tarefas) {
            if (tarefa.getId().equals(id)) {
                tarefaDTO = tarefa;
            }
        }
        if (tarefaDTO.getId() == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(tarefaDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        TarefaDTO tarefaDTO = new TarefaDTO(id);
        tarefas.remove(tarefaDTO);
        return ResponseEntity.noContent().build();
    }

}
