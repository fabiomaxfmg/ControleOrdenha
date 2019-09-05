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

    public static boolean inserir(String nome, String data_nascimento, String cod_raca, String cod_mae, String cod_touro) {
        String sql = "INSERT INTO vaca (nome, data_nascimento, cod_raca, cod_mae, cod_touro) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, data_nascimento);
            ps.setInt(3, Integer.parseInt(cod_raca));
            ps.executeUpdate();
            ps.setInt(4, Integer.parseInt(cod_mae));
            ps.executeUpdate();
            ps.setInt(5, Integer.parseInt(cod_touro));
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean alterar(String codigo, String nome, String data_nascimento, String cod_mae, String cod_raca, String cod_touro) {
        String sql = "UPDATE vaca SET nome = ?, data_nascimento = ?, cod_raca = ?, cod_mae = ?, cod_touro = ? WHERE codigo = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, data_nascimento);
            ps.setString(3, cod_raca);
            ps.setString(4, cod_mae);
            ps.setString(5, cod_touro);
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean excluir(String codigo) {
        String sql = "DELETE FROM vaca WHERE codigo = ?";
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
        String sql = "SELECT codigo, nome, data_nascimento, cod_raca, cod_mae, cod_touro FROM vaca";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] linha = new String[3];
                linha[0] = rs.getString("codigo");
                linha[1] = rs.getString("nome");
                linha[2] = rs.getString("data_nascimento");
                linha[3] = rs.getString("cod_raca");
                linha[4] = rs.getString("cod_mae");
                linha[5] = rs.getString("cod_touro");

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
        String sql = "SELECT codigo, nome, data_nascimento, cod_raca, cod_mae, cod_touro FROM vaca WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, pk);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.put("codigo", rs.getString("codigo"));
                resultado.put("nome", rs.getString("nome"));
                resultado.put("data_nascimento", rs.getString("data_nascimento"));
                resultado.put("cod_raca", rs.getString("cod_raca"));
                resultado.put("cod_mae", rs.getString("cod_mae"));
                resultado.put("cod_touro", rs.getString("cod_touro"));
            }
            return resultado;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(VacaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
