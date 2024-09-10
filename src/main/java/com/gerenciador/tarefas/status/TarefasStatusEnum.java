package com.gerenciador.tarefas.status;

/*
1 - Não permitir deletar tarrefa se o status for diferente de CRIADA
2 - Não deixar mover a tarefa para FINALIZADA se a mesma estiver em CRIADA
3 - Não deixar mover a tarefa para FINALIZADA se a mesma estiver em PROGRESSO
4 - Uma vez finalizada a tarefa não pode mudar de status e nem ser deletada
5 - Se uma tarefa tiver a mesma descrição ou título informar que já existe uma tarefa criada
*/

public enum TarefasStatusEnum {
    CRIADA, PROGRESSO, BLOQUEADA, FINALIZADA
}
