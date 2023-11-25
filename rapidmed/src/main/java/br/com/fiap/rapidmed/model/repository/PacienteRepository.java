package br.com.fiap.rapidmed.model.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.rapidmed.model.entity.Paciente;

public class PacienteRepository extends Repository{

		
	public static ArrayList<Paciente> findAll() {
	    ArrayList<Paciente> pacientes = new ArrayList<>();
	    String sql = "SELECT * FROM T_RAPIDMED_PACIENTE_LOGIN";
	    try {
	        PreparedStatement ps = getConnection().prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        if (rs != null) {
	            while (rs.next()) {
	                Paciente paciente = new Paciente();
	                paciente.setId(rs.getLong("ID_PACIENTE"));
	                paciente.setIdLogin(rs.getLong("ID_LOGIN"));
	                paciente.setUsername(rs.getString("USER_NAME"));
	                paciente.setSenha(rs.getString("PASSWORD"));
	                
	                pacientes.add(paciente);
	            }
	        } else {
	            return null;
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao listar: " + e.getMessage());
	    } finally {
	    	closeConnection(connection);
	    }
	    return pacientes;
	}

		
		
	public static Paciente save(Paciente paciente) {
		if (paciente == null) {
	        return null;
	    }
	    String sqlPaciente = "INSERT INTO T_RAPIDMED_PACIENTE (ID_PACIENTE, NM_PACIENTE, NR_CPF) VALUES (SQ_RAPIDMED_PACIENTE.NEXTVAL, ?, ?)";
	    String sqlLogin = "INSERT INTO T_RAPIDMED_PACIENTE_LOGIN (ID_PACIENTE, ID_LOGIN, USER_NAME, PASSWORD) VALUES (SQ_RAPIDMED_PACIENTE.CURRVAL, SQ_RAPIDMED_PACIENTE_LOGIN.NEXTVAL, ?, ?)";

	    Connection connection = null;
	    PreparedStatement ps = null;
	    PreparedStatement pl = null;

	    try {
	        connection = getConnection();
	        connection.setAutoCommit(false); // Inicia a transação

	        ps = connection.prepareStatement(sqlPaciente);
	        pl = connection.prepareStatement(sqlLogin);

	        ps.setString(1, paciente.getNomeDoPaciente());
	        ps.setString(2, String.valueOf(paciente.getCpf()));

	        int psResult = ps.executeUpdate();

	        if (psResult > 0) {
	            pl.setString(1, paciente.getUsername());
	            pl.setString(2, paciente.getSenha());
	            int plResult = pl.executeUpdate();

	            if (plResult > 0) {
	                connection.commit(); // Confirma a transação se ambos os inserts foram bem-sucedidos
	            } else {
	                connection.rollback(); // Desfaz a transação em caso de falha no segundo insert
	                return null;
	            }
	        } else {
	            connection.rollback(); // Desfaz a transação em caso de falha no primeiro insert
	            return null;
	        }
	    } catch (SQLException e) {
	        try {
	            if (connection != null) {
	                connection.rollback(); // Desfaz a transação em caso de exceção
	            }
	        } catch (SQLException rollbackException) {
	            rollbackException.printStackTrace();
	        }
	        System.out.println("Erro ao salvar: " + e.getMessage());
	    } finally {
	        closeConnection(connection);
	    }
	    return paciente;
	}
	        
	        
	    

		
	public static boolean delete(Long id) {
	    String sql = "DELETE FROM T_RAPIDMED_PACIENTE_LOGIN WHERE ID_LOGIN=?";
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

	public static Paciente update(Paciente paciente) {
	    String sql = "UPDATE T_RAPIDMED_PACIENTE SET ID_PACIENTE=?, NM_PACIENTE=?, NR_CPF=? WHERE ID_PACIENTE=?";
	    try {
	        PreparedStatement ps = getConnection().prepareStatement(sql);	        
	        ps.setLong(1, paciente.getId());
	        ps.setString(2, paciente.getNomeDoPaciente());
	        ps.setLong(3, paciente.getCpf());
	        
	        if (ps.executeUpdate() > 0) {
	            return paciente;
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


		
		
		
	public static Paciente findById(Long id) {
	    String sql = "SELECT * FROM T_RAPIDMED_PACIENTE_LOGIN WHERE ID_LOGIN=?";
	    Paciente paciente = new Paciente();
	    try {
	        PreparedStatement ps = getConnection().prepareStatement(sql);
	        ps.setLong(1, id);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {              
	        	paciente.setId(rs.getLong("ID_PACIENTE"));
                paciente.setIdLogin(rs.getLong("ID_LOGIN"));
                paciente.setUsername(rs.getString("USER_NAME"));
                paciente.setSenha(rs.getString("PASSWORD"));
	        } else {
	            return null;
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao buscar por ID: " + e.getMessage());
	    } finally {
	        closeConnection(connection);
	    }
	    return paciente;
	}



	public static boolean existsByCpf(long cpf) {
	    String sql = "SELECT COUNT(*) FROM T_RAPIDMED_PACIENTE WHERE NR_CPF=?";
	    try {
	        PreparedStatement ps = getConnection().prepareStatement(sql);
	        ps.setLong(1, cpf);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0; // Retorna true se o CPF já existe, false caso contrário
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao verificar existência por CPF: " + e.getMessage());
	    } finally {
	        closeConnection(connection);
	    }
	    return false;
	}
}
