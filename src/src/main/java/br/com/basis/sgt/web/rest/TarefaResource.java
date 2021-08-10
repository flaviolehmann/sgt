package br.com.basis.sgt.web.rest;

import br.com.basis.sgt.service.TarefaService;
import br.com.basis.sgt.service.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")

public class TarefaResource {

    private final TarefaService tarefaService;

    public TarefaResource(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    /**
     * Busca todos os títulos através
     * @param titulo
     * @return
     */
    @GetMapping
    public ResponseEntity<List<TarefaDTO>> obterTodos(@RequestParam("titulo") String titulo) {
        return new ResponseEntity<>(tarefaService.obterTodos(titulo), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> criarTarefa(@RequestBody TarefaDTO tarefa) {
        return ResponseEntity.ok(tarefaService.salvar(tarefa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> obterPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(tarefaService.obterPorId(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable("id") Long id) {
        tarefaService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
