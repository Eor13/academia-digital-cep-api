package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.service.impl.AvaliacaoFisicaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {
    @Autowired
    private AvaliacaoFisicaServiceImpl service;

    @PostMapping
    public AvaliacaoFisica create(@RequestBody AvaliacaoFisicaForm form){
        return service.create(form);
    }

    @GetMapping
    public List<AvaliacaoFisica> getAll(){ return service.getAll(); }

    @GetMapping("/{id}")
    public AvaliacaoFisica get(@PathVariable("id") Long id){ return service.get(id);}

    @PutMapping("/{id}")
    public void update(
            @RequestBody AvaliacaoFisicaUpdateForm form, @PathVariable Long id){
        service.update(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){ service.delete(id);}
}
