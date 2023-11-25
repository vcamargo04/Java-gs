package br.com.fiap.rapidmed.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import br.com.fiap.rapidmed.model.entity.Consulta;

public class ConsultaRepository extends Repository {

    public static ArrayList<Consulta> findAll() {
        ArrayList<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM T_RAPIDMED_CONSULTA_ONLINE";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(rs.getLong("ID_CONSULTA_ON"));
                consulta.setIdPaciente(rs.getLong("ID_PACIENTE"));
                consulta.setIdMedico(rs.getLong("ID_MEDICO"));
                consulta.setDataHoraConsulta(rs.getString("DT_HR_CONSULTA"));
                consulta.setNumeroConsultorio(rs.getString("NR_CONSULTORIO"));
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar: " + e.getMessage(), e);
        } finally {
            closeConnection(connection);
        }
        return consultas;
    }

    public static Consulta save(Consulta consulta) {
        String sql = "INSERT INTO T_RAPIDMED_CONSULTA_ONLINE (ID_CONSULTA_ON, ID_PACIENTE, ID_MEDICO, DT_HR_CONSULTA, NR_CONSULTORIO) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setLong(1, consulta.getId());
            ps.setLong(2, consulta.getIdPaciente());
            ps.setLong(3, consulta.getIdMedico());

            // Utilize setTimestamp para a coluna de data e hora
            Timestamp timestamp = Timestamp.valueOf(consulta.getDataHoraConsulta());
            ps.setTimestamp(4, timestamp);

            ps.setString(5, consulta.getNumeroConsultorio());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return consulta;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
        	closeConnection(connection);
        }
        return null;
    }

    public static boolean delete(long id) {
        String sql = "DELETE FROM T_RAPIDMED_CONSULTA_ONLINE WHERE ID_CONSULTA_ON = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir: " + e.getMessage(), e);
        } finally {
        	closeConnection(connection);
        }
    }

    public static Consulta update(Consulta consulta) {
        String sql = "UPDATE T_RAPIDMED_CONSULTA_ONLINE SET ID_PACIENTE = ?, ID_MEDICO = ?, DT_HR_CONSULTA = ?, NR_CONSULTORIO = ? WHERE ID_CONSULTA_ON = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, consulta.getIdPaciente());
            ps.setLong(2, consulta.getIdMedico());
            ps.setString(3, consulta.getDataHoraConsulta());
            ps.setString(4, consulta.getNumeroConsultorio());
            ps.setLong(5, consulta.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return consulta;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar: " + e.getMessage(), e);
        } finally {
        	closeConnection(connection);
        }
        return null;
    }

    public static Consulta findById(Long id) {
        String sql = "SELECT * FROM T_RAPIDMED_CONSULTA_ONLINE WHERE ID_CONSULTA_ON=?";
        Consulta consulta = new Consulta();
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                consulta.setId(rs.getLong("ID_CONSULTA_ON"));
                consulta.setIdPaciente(rs.getLong("ID_PACIENTE"));
                consulta.setIdMedico(rs.getLong("ID_MEDICO"));
                consulta.setDataHoraConsulta(rs.getString("DT_HR_CONSULTA"));
                consulta.setNumeroConsultorio(rs.getString("NR_CONSULTORIO"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar por ID: " + e.getMessage(), e);
        } finally {
        	closeConnection(connection);
        }
        return consulta;
    }
}
