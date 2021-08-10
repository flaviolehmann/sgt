package br.com.basis.sgt.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class ResponsavelDTO {

    private Long id;
    private String setor;

    private List<ResponsavelDTO> responsavel;

}