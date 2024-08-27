package com.gerenciador.tarefas.request;

import com.gerenciador.tarefas.status.TarefasStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class AtulizarTarefaRequest {

    @NotBlank(message = "{atualizar.tarefa.request.titulo.obrigatorio}")
    private String titulo;

    @Length(max = 150, message = "{atualizar.tarefa.request.descricao.limite}")
    private String descricao;

    private TarefasStatusEnum status;

    private Long responsavelId;

    @NotNull(message = "{atualizar.tarefa.request.quantidadeHorasEstimadas}")
    private Integer quantidadeHorasEstimadas;

    private Integer quantidadeHorasRealizadas;

}
