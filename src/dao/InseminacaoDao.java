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

public class InseminacaoDao {

    public static boolean inserir(String data, String situacao, String observacao, String cod_inseminador, String cod_vaca) {
        String sql = "INSERT INTO inseminacao (data, situacao, observacao, cod_inseminador, cod_vaca) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, data);
            ps.setInt(2, Integer.parseInt(situacao));
            ps.setString(3, observacao);
            ps.setInt(4, Integer.parseInt(cod_inseminador));
            ps.setInt(5, Integer.parseInt(cod_vaca));
            ps.executeUpdate();

            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean alterar(String codigo, String data, String situacao, String observacao, String cod_inseminador, String cod_vaca) {
        String sql = "UPDATE inseminacao SET data = ?, situacao  = ?, observacao = ?, cod_inseminador = ?, cod_vaca = ?  WHERE codigo = ?";

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);

            ps.setString(1, data);
            ps.setString(2, situacao);
            ps.setString(3, observacao);
            ps.setString(4, cod_inseminador);
            ps.setString(5, cod_vaca);
            ps.setString(6, codigo);
            ps.executeUpdate();

            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean excluir(String codigo) {
        String sql = "DELETE FROM inseminacao WHERE codigo = ?";
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
        String sql = "SELECT codigo, data, situacao, observacao, cod_inseminador, cod_vaca FROM inseminacao";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] linha = new String[2];
                linha[0] = rs.getString("codigo");
                linha[1] = rs.getString("data");
                linha[2] = rs.getString("situacao");
                linha[3] = rs.getString("observacao");
                linha[4] = rs.getString("cod_inseminador");
                linha[5] = rs.getString("cod_vaca");
                resultados.add(linha);
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(InseminacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Map<String, String> consultar(String pk) {
        Map<String, String> resultado = new HashMap<>();
        String sql = "SELECT codigo, data, situacao, observacao, cod_inseminador, cod_vaca FROM inseminacao WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, pk);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.put("codigo", rs.getString("codigo"));
                resultado.put("data", rs.getString("data"));
                resultado.put("situacao", rs.getString("situacao"));
                resultado.put("observacao", rs.getString("observacao"));
                resultado.put("cod_inseminador", rs.getString("cod_inseminador"));
                resultado.put("cod_vaca", rs.getString("cod_vaca"));

            }
            return resultado;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(InseminacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static boolean alterar(String text, String text0, String text1, String text2, String text3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static boolean inserir(String text, String text0, String text1, String text2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
