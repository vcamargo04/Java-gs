package br.com.fiap.rapidmed.model.service;

import java.util.ArrayList;

import br.com.fiap.rapidmed.model.entity.Paciente;
import br.com.fiap.rapidmed.model.repository.PacienteRepository;

public class PacienteService {

    public Paciente findById(Long id) {
        return PacienteRepository.findById(id);
    }

    public ArrayList<Paciente> findAll() {
        return PacienteRepository.findAll();
    }

    public Paciente save(Paciente paciente) {
        return PacienteRepository.save(paciente);
    }

    public boolean delete(Long id) {
        return PacienteRepository.delete(id);
    }

    public Paciente update(Paciente paciente) {
        return PacienteRepository.update(paciente);
    }
}
