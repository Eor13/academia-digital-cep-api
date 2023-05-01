package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired //chama o sevice
    private AlunoServiceImpl service;


    @PostMapping
    public Aluno create( @RequestBody AlunoForm form){ //é RequestBody pois estará passando a requisicao no corpo de aluno
        return service.create(form);
    }//precisa do @Valid para as validações funcionarem

    @GetMapping("/avaliacoes/{id}")
    public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(@PathVariable Long id){
        return service.getAllAvailacaoFisicaId(id);
    }

    @GetMapping
    public List<Aluno> getAll(
            @RequestParam(value = "dataDeNascimento", required =false) String dataDeNascimento){
        return service.getAll(dataDeNascimento);
    }

    @GetMapping("/{id}")
    public Aluno get(@PathVariable("id") Long id){
        return service.get(id);
    }

    @PutMapping("/{id}")
    public void update(
            @RequestBody AlunoUpdateForm form, @PathVariable Long id){
        service.update(id,form);
    }

    //=============== Errado
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }

}
