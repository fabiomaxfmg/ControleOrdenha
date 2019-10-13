package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Raca;

public class RacaDao {

    public static boolean inserir(String descricao) {
        String sql = "INSERT INTO raca (descricao) VALUES (?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, descricao);
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean alterar(int codigo, String descricao) {
        String sql = "UPDATE raca SET descricao = ? WHERE codigo = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, descricao);
            ps.setInt(2, codigo);
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean excluir(int codigo) {
        String sql = "DELETE FROM raca WHERE codigo = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static List<Raca> consultar() {
        List<Raca> resultados = new ArrayList<>();
        String sql = "SELECT codigo, descricao FROM raca";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Raca linha = new Raca();
                linha.setCodigo(rs.getInt("codigo"));
                linha.setDescricao(rs.getString("descricao"));
                resultados.add(linha);
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RacaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Raca consultar(int pk) {
        Raca resultado = new Raca();
        String sql = "SELECT codigo, descricao FROM raca WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, pk);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.setCodigo(rs.getInt("codigo"));
                resultado.setDescricao(rs.getString("descricao"));
            }
            return resultado;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RacaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static void main(String[] args){
        consultar();
        System.out.println(consultar(3).getDescricao());
    }
}
