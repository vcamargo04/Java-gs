package br.com.fiap.rapidmed.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.rapidmed.model.entity.Medico;

public class MedicoRepository extends Repository {

	public static ArrayList<Medico> findAll() {
        ArrayList<Medico> medicos = new ArrayList<>();
        String sql = "SELECT * FROM t_rapidmed_medico";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
        	while (rs.next()) {
        	    Medico medico = new Medico(
        	            rs.getLong("ID_MEDICO"),
        	            rs.getString("NM_MEDICO"),
        	            rs.getString("NR_CRM"),
        	            rs.getString("DS_ESPECIALIDADE")
        	    );
        	    medicos.add(medico); // Add the created Medico object to the list
        	}
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        } finally {
        	closeConnection(connection);
        }
        return medicos;
    }
		
    public static Medico save(Medico medico) {
        String sql = "INSERT INTO t_rapidmed_medico" + "(ID_MEDICO, NM_MEDICO, NR_CRM, DS_ESPECIALIDADE)" + " VALUES (SQ_RAPIDMED_MEDICO.NEXTVAL, ?, ?, ?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            
            ps.setString(1, medico.getNomeMedico());
            ps.setString(2, medico.getNumeroCrm());
            ps.setString(3, medico.getEspecialidade());
            if (ps.executeUpdate() > 0) {
                return medico;
            } else {
                // Log an error or handle appropriately
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
        	closeConnection(connection);
        }
        return null;
    }
		
    public static boolean delete(Long id) {
        String sql = "DELETE FROM t_rapidmed_medico WHERE ID_MEDICO=?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
        } finally {
        	closeConnection(connection);
        }
        return false;
    }


    public static Medico update(Medico medico) {
        String sql = "UPDATE t_rapidmed_medico SET NM_MEDICO=?, NR_CRM=?, DS_ESPECIALIDADE=? WHERE ID_MEDICO=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, medico.getNomeMedico());
            ps.setString(2, medico.getNumeroCrm());
            ps.setString(3, medico.getEspecialidade());
            ps.setLong(4, medico.getId());
            if (ps.executeUpdate() > 0) {
                return medico;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
        	closeConnection(connection);
        }
        return null;
    }

	
    public static Medico findById(long id) {
        String sql = "SELECT * FROM t_rapidmed_medico WHERE ID_MEDICO=?";
        Medico medico = new Medico(id, sql, sql, sql);
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {                
                medico.setId(rs.getLong("ID_MEDICO"));
                medico.setNomeMedico(rs.getString("NM_MEDICO"));
                medico.setNumeroCrm(rs.getString("NR_CRM"));
                medico.setEspecialidade(rs.getString("DS_ESPECIALIDADE"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar por ID: " + e.getMessage());
        } finally {
        	closeConnection(connection);
        }
        return medico;
    }
}

