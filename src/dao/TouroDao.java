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

public class TouroDao {

    public static boolean inserir(String nome, String cod_raca) {
        String sql = "INSERT INTO touro (nome, cod_raca)   VALUES (?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome);
            ps.setInt(2, Integer.parseInt(cod_raca));
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean alterar(String codigo, String nome, String cod_raca) {
        String sql = "UPDATE touro SET nome = ?, cod_raca = ? WHERE codigo = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, cod_raca);
            ps.setString(3, codigo);

            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean excluir(String codigo) {
        String sql = "DELETE FROM touro WHERE codigo = ?";
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
        String sql = "SELECT codigo, nome, cod_raca FROM touro";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] linha = new String[3];
                linha[0] = rs.getString("codigo");
                linha[1] = rs.getString("nome");
                linha[2] = rs.getString("cod_raca");
                resultados.add(linha);
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TouroDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Map<String, String> consultar(String pk) {
        Map<String, String> resultado = new HashMap<>();
        String sql = "SELECT codigo, nome, cod_raca FROM touro WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, pk);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.put("codigo", rs.getString("codigo"));
                resultado.put("nome", rs.getString("nome"));
                resultado.put("cod_raca", rs.getString("cod_raca"));
            }
            return resultado;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TouroDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
