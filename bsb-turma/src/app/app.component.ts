import { Component } from '@angular/core';
import {Tarefa} from './model/tarefa.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  tituloPai: string = 'Titulo PAiAAAAAAAAAa';

  title = 'bsb-turma';

  tarefaSalva = new Tarefa();

  mostrarTarefaCriada(event: Tarefa): void{
    this.tarefaSalva = event;
  }
}
