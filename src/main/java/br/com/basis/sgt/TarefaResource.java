package br.com.basis.sgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaResource {

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping
    public ResponseEntity<List<Tarefa>> obterTodos() {
        return new ResponseEntity<>(tarefaRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        tarefaRepository.save(tarefa);
        return ResponseEntity.ok(tarefa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> obterPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(tarefaRepository.findById(id).orElseThrow(TarefaNaoEncontradaException::new), HttpStatus.OK);
    }

}
