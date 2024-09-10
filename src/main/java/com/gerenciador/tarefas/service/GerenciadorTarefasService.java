package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.entity.Tarefa;
import com.gerenciador.tarefas.excecoes.NaoPermitirAlterarStatusException;
import com.gerenciador.tarefas.excecoes.NaoPermitirExcluirException;
import com.gerenciador.tarefas.excecoes.TarefaExistenteException;
import com.gerenciador.tarefas.repository.GerenciadorTarefasRepository;
import com.gerenciador.tarefas.request.AtulizarTarefaRequest;
import com.gerenciador.tarefas.request.CadastrarTarefaRequest;
import com.gerenciador.tarefas.status.TarefasStatusEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GerenciadorTarefasService {

    @Autowired
    private GerenciadorTarefasRepository gerenciadorTarefasRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Tarefa salvarTarefa(CadastrarTarefaRequest request) {

        Tarefa tarefaValidacao = gerenciadorTarefasRepository.findByTituloOrDescricao(request.getTitulo(), request.getDescricao());

        if (tarefaValidacao != null) {
            throw new TarefaExistenteException("Já existe uma tarefa com o mesmo título ou descrição");
        }

        Tarefa tarefa = Tarefa.builder()
                .quantidadeHorasEstimadas(request.getQuantidadeHorasEstimadas())
                .status(TarefasStatusEnum.CRIADA)
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .criador(usuarioService.obterUsuarioId(request.getCriadorId()).get())
                .build();

        return gerenciadorTarefasRepository.save(tarefa);
    }

    public Page<Tarefa> obtemTarefasPorTitulo(String titulo, Pageable pageable) {
        return this.gerenciadorTarefasRepository.findByTituloContaining(titulo, pageable);
    }

    public Page<Tarefa> obtemTodasTarefas(Pageable pageable) {
        return this.gerenciadorTarefasRepository.findAll(pageable);
    }

    public Tarefa atualizarTarefa(Long id, AtulizarTarefaRequest request) {
        Tarefa tarefa = this.gerenciadorTarefasRepository.findById(id).get();

        if (tarefa.getStatus().equals(TarefasStatusEnum.FINALIZADA)) {
            throw  new NaoPermitirAlterarStatusException("Não permitido mover a tarefa que está FINALIZADA");
        }

        if (tarefa.getStatus().equals(TarefasStatusEnum.CRIADA) && request.getStatus().equals(TarefasStatusEnum.FINALIZADA)) {
            throw  new NaoPermitirAlterarStatusException("Não permitido mover a tarefa para FINALIZADA se a mesma estiver com o status de CRIADA");
        }

        if (tarefa.getStatus().equals(TarefasStatusEnum.BLOQUEADA) && request.getStatus().equals(TarefasStatusEnum.FINALIZADA)) {
            throw  new NaoPermitirAlterarStatusException("Não permitido mover a tarefa para FINALIZADA se a mesma estiver com o status de BLOQUEADA");
        }

        tarefa.setQuantidadeHorasEstimadas(request.getQuantidadeHorasEstimadas());
        tarefa.setStatus(request.getStatus());
        tarefa.setTitulo(request.getTitulo());
        tarefa.setDescricao(request.getDescricao());
        tarefa.setResponsavel(usuarioService.obterUsuarioId(request.getResponsavelId()).get()); //erro
        tarefa.setQuantidadeHorasRealizadas(request.getQuantidadeHorasRealizadas());

        this.gerenciadorTarefasRepository.save(tarefa);

        return tarefa;
    }

    public void excluirTarefa(Long id) {
        Tarefa tarefa = this.gerenciadorTarefasRepository.findById(id).get();

        if (!TarefasStatusEnum.CRIADA.equals(tarefa.getStatus())) {
            throw new NaoPermitirExcluirException();
        }

        this.gerenciadorTarefasRepository.deleteById(id);
    }

}
