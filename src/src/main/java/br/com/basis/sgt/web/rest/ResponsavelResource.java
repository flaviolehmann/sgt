package br.com.basis.sgt.web.rest;

import br.com.basis.sgt.service.dto.ResponsavelDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responsaveis")
public class ResponsavelResource {
    private final ResponsavelService responsavelService;
    public ResponsavelResource(ResponsavelService responsavelService) {
        this.responsavelService = responsavelService;
    }
    /**
     * Busca todos os setores através
     * @param setor
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ResponsavelDTO>> obterTodos(@RequestParam("setor") String setor) {
        return new ResponseEntity<>(responsavelService.obterTodos(setor), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ResponsavelDTO> criarResponsavel(@RequestBody ResponsavelDTO responsavel) {
        return ResponseEntity.ok(responsavelService.salvar(responsavel));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelDTO> obterPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(responsavelService.obterPorId(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable("id") Long id) {
        responsavelService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
