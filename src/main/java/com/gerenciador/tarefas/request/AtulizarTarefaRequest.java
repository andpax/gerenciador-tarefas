package com.gerenciador.tarefas.request;

import com.gerenciador.tarefas.entity.Usuario;
import com.gerenciador.tarefas.status.TarefasStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalTime;

@Getter
@Setter
public class AtulizarTarefaRequest {

    private String titulo;
    private String descricao;
    private TarefasStatusEnum status;
    private Long responsavelId;
    private Integer quantidadeHorasEstimadas;
    private Integer quantidadeHorasRealizadas;

}
