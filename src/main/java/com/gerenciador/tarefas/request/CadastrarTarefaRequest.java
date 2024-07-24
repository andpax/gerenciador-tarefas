package com.gerenciador.tarefas.request;

import com.gerenciador.tarefas.status.TarefasStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarTarefaRequest {
    private String titulo;
    private String descricao;
    private Long criadorId;
    private int quantidadeHorasEstimadas;

}
