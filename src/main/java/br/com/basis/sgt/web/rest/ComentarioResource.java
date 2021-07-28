package br.com.basis.sgt.web.rest;

import br.com.basis.sgt.service.ComentarioService;
import br.com.basis.sgt.service.dto.ComentarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



    @RestController
    @RequestMapping("/api/comentario")
    public class ComentarioResource {
        private final ComentarioService comentarioService;
        public ComentarioResource(ComentarioService comentarioService) {
            this.comentarioService = comentarioService;
        }
        /**
         * Busca todos os comentarios através
         * @param descricao
         * @return
         */
        @GetMapping
        public ResponseEntity<List<ComentarioDTO>> obterTodos(@RequestParam("descricao") String descricao) {
            return new ResponseEntity<>(comentarioService.obterTodos(descricao), HttpStatus.OK);
        }
        @PostMapping
        public ResponseEntity<ComentarioDTO> criarComentario(@RequestBody ComentarioDTO comentario) {
            return ResponseEntity.ok(comentarioService.salvar(comentario));
        }
        @GetMapping("/{id}")
        public ResponseEntity<ComentarioDTO> obterPorId(@PathVariable("id") Long id) {
            return new ResponseEntity<>(comentarioService.obterPorId(id), HttpStatus.OK);
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletarPorId(@PathVariable("id") Long id) {
            comentarioService.deletarPorId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }



