package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Inseminacao;

public class InseminacaoDao {
    static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat dfsql = new SimpleDateFormat("yyyy-MM-dd");
    
    public static boolean inserir(String data, int situacao, String observacao, int cod_inseminador, int cod_vaca) {
        String sql = "INSERT INTO inseminacao (data, cod_situacao, observacao, cod_inseminador, brinco_vaca) VALUES (?, ?, ?, ?, ?)";
        try {
            
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            String date1;      
            date1 = dfsql.format(df.parse(data));
            Date date = Date.valueOf(date1);
            ps.setDate(1, date);
            ps.setInt(2, situacao);
            ps.setString(3, observacao);
            ps.setInt(4, cod_inseminador);
            ps.setInt(5, cod_vaca);
            ps.executeUpdate();

            return true;
        } catch (SQLException | ClassNotFoundException | ParseException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean alterar(int codigo, String data, int situacao, String observacao, int cod_inseminador, int cod_vaca) {
        String sql = "UPDATE inseminacao SET data = ?, cod_situacao  = ?, observacao = ?, cod_inseminador = ?, brinco_vaca = ?  WHERE codigo = ?";

        try {
            String date1;
            
            
            date1 = dfsql.format(df.parse(data));
            Date date = Date.valueOf(date1);
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);

            ps.setDate(1, date);
            ps.setInt(2, situacao);
            ps.setString(3, observacao);
            ps.setInt(4, cod_inseminador);
            ps.setInt(5, cod_vaca);
            ps.setInt(6, codigo);
            ps.executeUpdate();

            return true;
        } catch (SQLException | ClassNotFoundException | ParseException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean excluir(int codigo) {
        String sql = "DELETE FROM inseminacao WHERE codigo = ?";
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

    public static List<Inseminacao> consultar() {
        List<Inseminacao> resultados = new ArrayList<>();
        String sql = "SELECT codigo, data, cod_situacao, observacao, cod_inseminador, brinco_vaca FROM inseminacao";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inseminacao ins = new Inseminacao();
                ins.setBrinco_vaca(rs.getInt("brinco_vaca"));
                ins.setCodigo(rs.getInt("codigo"));
                ins.setCod_inseminador(rs.getInt("cod_inseminador"));
                ins.setObservacao(rs.getString("observacao"));
                ins.setSituacao(rs.getInt("cod_situacao"));
                ins.setData(rs.getDate("data"));
                resultados.add(ins);
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(InseminacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

        /*public static List<String[]> consultar_situacao() {
        List<String[]> resultados = new ArrayList<>();
        String sql = "SELECT codigo, nome FROM inssituacao";
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
            Logger.getLogger(VacaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
*/
    
    public static Inseminacao consultar(int pk) {
        Inseminacao resultado = new Inseminacao();
        String sql = "SELECT codigo, data, cod_situacao, observacao, cod_inseminador, brinco_vaca FROM inseminacao WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, pk);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.setCodigo(rs.getInt("codigo"));
                resultado.setData(rs.getDate("data"));
                resultado.setSituacao(rs.getInt("cod_situacao"));
                resultado.setObservacao(rs.getString("observacao"));
                resultado.setCod_inseminador(rs.getInt("cod_inseminador"));
                resultado.setBrinco_vaca(rs.getInt("brinco_vaca"));

            }
            return resultado;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(InseminacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

   
    
    public static void main(String[] args){
        System.out.println(consultar(3).getObservacao());
    }
}
