import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Tarefa} from '../model/tarefa.model';
import {TarefaService} from '../service/tarefa.service';

@Component({
  selector: 'app-tarefa-crud',
  templateUrl: './tarefa-crud.component.html',
  styleUrls: ['./tarefa-crud.component.css']
})
export class TarefaCrudComponent implements OnInit {

  @Output() criarTarefa = new EventEmitter<Tarefa>();
  @Input() tituloForm: string;

  tarefa: Tarefa = new Tarefa();

  constructor(private tarefaService: TarefaService) { }

  ngOnInit(): void {
  }

  click(): void {
    this.tarefaService.salvarTarefa(this.tarefa)
      .subscribe( retorno => this.criarTarefa.emit(retorno));
  }
}
