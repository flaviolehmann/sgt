package br.com.basis.sgt.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class ComentarioDTO {

    private Long id;
    private String descricao;
    private LocalDate dataReferencia;

    private List<ComentarioDTO> comentarios;
}
