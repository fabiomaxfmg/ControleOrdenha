package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Touro;

public class TouroDao {

    public static boolean inserir(String nome, int cod_raca) {
        String sql = "INSERT INTO touro (nome, cod_raca)   VALUES (?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome);
            ps.setInt(2, cod_raca);
            ps.executeUpdate();
            conexao.Conexao.fechaConexao();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean alterar(int codigo, String nome, int cod_raca) {
        String sql = "UPDATE touro SET nome = ?, cod_raca = ? WHERE codigo = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome);
            ps.setInt(2, cod_raca);
            ps.setInt(3, codigo);

            ps.executeUpdate();
            conexao.Conexao.fechaConexao();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean excluir(int codigo) {
        String sql = "DELETE FROM touro WHERE codigo = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.executeUpdate();
            conexao.Conexao.fechaConexao();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static List<Touro> consultar() {
        List<Touro> resultados = new ArrayList<>();
        String sql = "SELECT codigo, nome, cod_raca FROM touro";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Touro linha = new Touro();
                linha.setCod_raca(rs.getInt("cod_raca"));
                linha.setCodigo(rs.getInt("codigo"));
                linha.setNome(rs.getString("nome"));
                resultados.add(linha);
            }
            conexao.Conexao.fechaConexao();
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TouroDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Touro consultar(int pk) {
        Touro resultado = new Touro();
        String sql = "SELECT codigo, nome, cod_raca FROM touro WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, pk);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.setCod_raca(rs.getInt("cod_raca"));
                resultado.setCodigo(rs.getInt("codigo"));
                resultado.setNome(rs.getString("nome"));
            }
            conexao.Conexao.fechaConexao();
            return resultado;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TouroDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static void main(String[] args){
        consultar();
        System.out.println(consultar(4).getNome());
    }
}
