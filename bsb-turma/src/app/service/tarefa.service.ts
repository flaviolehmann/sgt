import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Tarefa} from '../model/tarefa.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TarefaService {

  constructor(private http: HttpClient) { }

  salvarTarefa(tarefa: Tarefa): Observable<Tarefa> {
    return this.http.post<Tarefa>('http://localhost:8080/api/tarefas', tarefa);
  }
}
