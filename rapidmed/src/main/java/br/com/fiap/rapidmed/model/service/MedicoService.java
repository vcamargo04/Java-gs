package br.com.fiap.rapidmed.model.service;
import java.util.ArrayList;

import br.com.fiap.rapidmed.model.entity.Medico;
import br.com.fiap.rapidmed.model.repository.MedicoRepository;

public class MedicoService {

    public Medico findById(Long id) {
        return MedicoRepository.findById(id);
    }

    public ArrayList<Medico> findAll() {
        return MedicoRepository.findAll();
    }

    public Medico save(Medico medico) {
        return MedicoRepository.save(medico);
    }

    public boolean delete(Long id) {
        return MedicoRepository.delete(id);
    }

    public Medico update(Medico medico) {
        return MedicoRepository.update(medico);
    }
}
