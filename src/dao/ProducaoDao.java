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

public class ProducaoDao {

    public static boolean inserir(String producao_em_litros) {
        String sql = "INSERT INTO producao (producao_em_litros) VALUES (?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(producao_em_litros));
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean alterar(String data, String producao_em_litros) {
        String sql = "UPDATE producao SET producao_em_litros = ? WHERE data = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, producao_em_litros);
            ps.setString(2, data);

            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean excluir(String data) {
        String sql = "DELETE FROM producao WHERE data = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, data);
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static List<String[]> consultar() {
        List<String[]> resultados = new ArrayList<>();
        String sql = "SELECT data, producao_em_litros FROM producao";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] linha = new String[2];
                linha[0] = rs.getString("data");
                linha[1] = rs.getString("producao_em_litros");
                resultados.add(linha);
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProducaoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Map<String, String> consultar(String pk) {
        Map<String, String> resultado = new HashMap<>();
        String sql = "SELECT data, producao_em_litros FROM producao WHERE data=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, pk);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.put("data", rs.getString("data"));
                resultado.put("producao_em_litros", rs.getString("producao_em_litros"));
            }
            return resultado;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProducaoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
