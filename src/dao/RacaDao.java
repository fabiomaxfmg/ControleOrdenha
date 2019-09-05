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

    public static boolean alterar(String codigo, String descricao) {
        String sql = "UPDATE raca SET descricao = ? WHERE codigo = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, descricao);
            ps.setString(2, codigo);
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean excluir(String codigo) {
        String sql = "DELETE FROM raca WHERE codigo = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, codigo);
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static List<String[]> consultar() {
        List<String[]> resultados = new ArrayList<>();
        String sql = "SELECT codigo, descricao FROM raca";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] linha = new String[2];
                linha[0] = rs.getString("codigo");
                linha[1] = rs.getString("descricao");
                resultados.add(linha);
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RacaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Map<String, String> consultar(String pk) {
        Map<String, String> resultado = new HashMap<>();
        String sql = "SELECT codigo, descricao FROM raca WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, pk);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.put("codigo", rs.getString("codigo"));
                resultado.put("descricao", rs.getString("descricao"));
            }
            return resultado;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RacaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
