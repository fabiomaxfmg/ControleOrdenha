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

public class VacaDao {

    public static boolean inserir(String nome, String data_nascimento, String situacao, String brinco_mae, String cod_raca, String cod_touro) {
        String sql = "INSERT INTO vaca (nome, data_nascimento, situacao, brinco_mae, cod_raca, cod_touro) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, data_nascimento);
            ps.setInt(3, Integer.parseInt(situacao));
            ps.setInt(4, Integer.parseInt(brinco_mae));
            ps.setInt(5, Integer.parseInt(cod_raca));
            ps.setInt(6, Integer.parseInt(cod_touro));
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean alterar(String brinco, String nome, String data_nascimento, String situacao, String brinco_mae, String cod_raca, String cod_touro) {
        String sql = "UPDATE vaca SET nome = ?, data_nascimento = ?, situacao = ?, brinco_mae = ?, cod_raca = ?, cod_touro = ? WHERE brinco = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, data_nascimento);
            ps.setInt(3, Integer.parseInt(situacao));
            ps.setInt(4, Integer.parseInt(brinco_mae));
            ps.setInt(5, Integer.parseInt(cod_raca));
            ps.setInt(6, Integer.parseInt(cod_touro));
            ps.setInt(7, Integer.parseInt(brinco));
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean excluir(String brinco) {
        String sql = "DELETE FROM vaca WHERE brinco = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(brinco));
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static List<String[]> consultar() {
        List<String[]> resultados = new ArrayList<>();
        String sql = "SELECT brinco, nome, data_nascimento, situacao, brinco_mae, cod_raca, cod_touro FROM vaca";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] linha = new String[3];
                linha[0] = rs.getString("brinco");
                linha[1] = rs.getString("nome");
                linha[2] = rs.getString("data_nascimento");
                linha[3] = rs.getString("situacao");
                linha[4] = rs.getString("brinco_mae");
                linha[5] = rs.getString("cod_raca");
                linha[6] = rs.getString("cod_touro");

                resultados.add(linha);
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(VacaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Map<String, String> consultar(String pk) {
        Map<String, String> resultado = new HashMap<>();
        String sql = "SELECT brinco, nome, data_nascimento, situacao, brinco_mae, cod_raca, cod_touro FROM vaca WHERE brinco=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, pk);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.put("brinco", rs.getString("brinco"));
                resultado.put("nome", rs.getString("nome"));
                resultado.put("data_nascimento", rs.getString("data_nascimento"));
                resultado.put("situacao", rs.getString("situacao"));
                resultado.put("brinco_mae", rs.getString("brinco_mae"));
                resultado.put("cod_raca", rs.getString("cod_raca"));
                resultado.put("cod_touro", rs.getString("cod_touro"));
            }
            return resultado;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(VacaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
