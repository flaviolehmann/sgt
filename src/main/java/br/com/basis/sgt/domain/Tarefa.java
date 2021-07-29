package br.com.basis.sgt.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tarefa")

public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @JoinColumn(name = "id_tipo")
    @ManyToOne
    private TipoTarefa tipoTarefa;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tarefa")
    List<Comentario> comentarios;



}
