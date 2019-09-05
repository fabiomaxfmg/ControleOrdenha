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

public class InseminadorDao {

    public static boolean inserir(String nome) {
        String sql = "INSERT INTO inseminador (nome) VALUES (?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome);
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean alterar(String codigo, String nome) {
        String sql = "UPDATE touro SET nome = ? WHERE codigo = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, codigo);
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean excluir(String codigo) {
        String sql = "DELETE FROM inseminador WHERE codigo = ?";
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
        String sql = "SELECT codigo, nome FROM inseminador";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] linha = new String[2];
                linha[0] = rs.getString("codigo");
                linha[1] = rs.getString("nome");
                resultados.add(linha);
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(InseminadorDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Map<String, String> consultar(String pk) {
        Map<String, String> resultado = new HashMap<>();
        String sql = "SELECT codigo, nome FROM inseminador WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, pk);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.put("codigo", rs.getString("codigo"));
                resultado.put("nome", rs.getString("nome"));
            }
            return resultado;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(InseminadorDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void main(String[] args) {
        boolean resultado = inserir("Jose");
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
}
