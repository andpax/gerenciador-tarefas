package com.gerenciador.tarefas.repository;

import com.gerenciador.tarefas.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GerenciadorTarefasRepository extends JpaRepository<Tarefa, Long> {
}
