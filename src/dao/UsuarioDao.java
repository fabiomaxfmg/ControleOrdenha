package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UsuarioDao {

    public static boolean inserir(String senha) {
        String sql = "INSERT INTO usuario (senha) VALUES (?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(senha));
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean alterar(String login, String senha) {
        String sql = "UPDATE usuario SET senha = ? WHERE login = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, senha);
            ps.setString(2, login);

            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean excluir(String login) {
        String sql = "DELETE FROM usuario WHERE login = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, login);
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static List<String[]> consultar() {
        List<String[]> resultados = new ArrayList<>();
        String sql = "SELECT login, senha FROM usuario";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] linha = new String[2];
                linha[1] = rs.getString("login");
                linha[2] = rs.getString("senha");

                resultados.add(linha);
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Map<String, String> consultar(String pk) {
        Map<String, String> resultado = new HashMap<>();
        String sql = "SELECT login, senha FROM usuario WHERE login=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, pk);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.put("login", rs.getString("login"));
                resultado.put("senha", rs.getString("senha"));
            }
            return resultado;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
