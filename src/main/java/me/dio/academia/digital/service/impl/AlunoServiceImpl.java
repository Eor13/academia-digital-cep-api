package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.Endereco;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.infra.utils.JavaTimeUtils;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.EnderecoRepository;
import me.dio.academia.digital.service.IAlunoService;
import me.dio.academia.digital.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlunoServiceImpl implements IAlunoService {

    @Autowired //Retornará o repostirio
    private AlunoRepository alunoRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Aluno create(AlunoForm form) {
        Aluno aluno = new Aluno();
        aluno.setNome(form.getNome());
        aluno.setCpf(form.getCpf());
        aluno.setBairro(form.getBairro());
        aluno.setDataDeNascimento(form.getDataDeNascimento());

        String cep = aluno.getEndereco().getCep();
        Endereco endereco = EnderecoRepository.findById(cep).orElseGet(() -> {
            // Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        aluno.setEndereco(endereco);
        // Inserir Cliente, vinculando o Endereco (novo ou existente).
        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno get(Long id) {
        return (alunoRepository.getById(id));
    }


    @Override
    public List<Aluno> getAll(String dataDeNascimento) {
        if (dataDeNascimento == null) {
            return alunoRepository.findAll();
        } else {
            LocalDate localDate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
            return alunoRepository.findByDataDeNascimento(localDate);
        }
    }

    @Override
    public Aluno update(Long id, AlunoUpdateForm updateForm) {
        Aluno aluno = alunoRepository.findById(id).get();
        aluno.setNome(updateForm.getNome());
        aluno.setBairro(updateForm.getBairro());
        aluno.setDataDeNascimento(updateForm.getDataDeNascimento());
        return alunoRepository.save(aluno);
    }

    @Override
    public List<AvaliacaoFisica> getAllAvailacaoFisicaId(Long id) {
        Aluno aluno = alunoRepository.findById(id).get();
        return aluno.getAvaliacoes();
    }


    @Override
    public void delete(Long id) {
        alunoRepository.deleteById(id);
    }

}
