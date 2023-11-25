package br.com.fiap.rapidmed.model.service;

import java.util.ArrayList;

import br.com.fiap.rapidmed.model.entity.Consulta;
import br.com.fiap.rapidmed.model.repository.ConsultaRepository;

public class ConsultaService {

    public Consulta findById(Long id) {
        return ConsultaRepository.findById(id);
    }

    public ArrayList<Consulta> findAll() {
        return ConsultaRepository.findAll();
    }

    public Consulta save(Consulta consulta) {
        return ConsultaRepository.save(consulta);
    }

    public boolean delete(Long id) {
        return ConsultaRepository.delete(id);
    }

    public Consulta update(Consulta consulta) {
        return ConsultaRepository.update(consulta);
    }
}
