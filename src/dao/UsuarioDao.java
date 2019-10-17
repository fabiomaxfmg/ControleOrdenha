package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;

public class UsuarioDao {

    public static boolean inserir(String nome, String login, String senha) {
        String sql = "INSERT INTO usuario (nome, login, senha) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, login);
            ps.setString(3, senha);
            ps.executeUpdate();
            //ps = conexao.Conexao.getConexao().prepareStatement("CREATE USER "+ login +" WITH PASSWORD ?");
            Statement s = conexao.Conexao.getConexao().createStatement();
            s.executeUpdate("CREATE USER " + login+" WITH PASSWORD '"+ senha+"'");
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /*public static boolean alterar(String codigo, String nome, String login, String senha) {
        String sql = "UPDATE usuario SET nome = ?, login = ?, senha = ? WHERE codigo = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, login);
            ps.setString(3, senha);
            ps.setInt(4, Integer.parseInt(codigo));

            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }*/

    public static boolean excluir(String codigo) {
        String sql = "DELETE FROM usuario WHERE codigo = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(codigo));
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static List<Usuario> consultar() {
        List<Usuario> resultados = new ArrayList<>();
        String sql = "SELECT codigo, nome, login, senha FROM usuario";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setLogin(rs.getString("login"));
                user.setCodigo(rs.getInt("codigo"));
                user.setNome(rs.getString("nome"));
                user.setSenha(rs.getString("senha"));

                resultados.add(user);
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Usuario consultarLogin(String pk) {
        Usuario resultado = new Usuario();
        String sql = "SELECT codigo, nome, login, senha FROM usuario WHERE login=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, pk);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.setCodigo(rs.getInt("codigo"));
                resultado.setLogin(rs.getString("login"));
                resultado.setNome(rs.getString("nome"));
                resultado.setSenha(rs.getString("senha"));
            }
            return resultado;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static void main(String[] args){
        inserir("Jonas", "Raaaaiz", "asas|");
    }
}
