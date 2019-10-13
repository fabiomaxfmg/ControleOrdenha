package dao;

import static dao.InseminacaoDao.dfsql;
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
import model.Vaca;
import model.vaca_situacao;

public class VacaDao {
    static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat dfsql = new SimpleDateFormat("yyyy-MM-dd");
    
    public static boolean inserir(int brinco,String nome, String data_nascimento, int situacao, int brinco_mae, int cod_raca, int cod_touro) {
        String sql = "INSERT INTO vaca (brinco, nome, data_nascimento, cod_situacao,brinco_mae, cod_raca, cod_touro,) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try{
            String date1;
            date1 = dfsql.format(df.parse(data_nascimento));
            Date data = Date.valueOf(date1);
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, brinco);
            ps.setString(2, nome);
            ps.setDate(3, data);
            ps.setInt(4, situacao);
            ps.setInt(5, brinco_mae);
            ps.setInt(6, cod_raca);
            ps.setInt(7, cod_touro);
            ps.executeUpdate();
            return true;
        }catch(ClassNotFoundException | SQLException | ParseException ex){
            System.out.println(ex);
        }
        
      return true;  
    }
    public static boolean inserir(int brinco,String nome, String data_nascimento, int situacao, int cod_raca, int cod_touro) {
        String sql = "INSERT INTO vaca (brinco, nome, data_nascimento, cod_situacao, cod_raca, cod_touro) VALUES (?,?, ?, ?, ?, ?)";
        try{
            String date1;            
            date1 = dfsql.format(df.parse(data_nascimento));
            Date data = Date.valueOf(date1);
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, brinco);
            ps.setString(2, nome);
            ps.setDate(3, data);
            ps.setInt(4, situacao);
            ps.setInt(5, cod_raca);
            ps.setInt(6, cod_touro);
            ps.executeUpdate();
            return true;
        }catch(ClassNotFoundException | SQLException | ParseException ex){
            System.out.println(ex);
        }
        
      return true;  
    }
    

    public static boolean alterar(int brinco, String nome, String data_nascimento, int situacao, int brinco_mae, int cod_raca, int cod_touro) {
        String sql = "UPDATE vaca SET nome = ?, data_nascimento = ?, cod_situacao = ?, brinco_mae = ?, cod_raca = ?, cod_touro = ? WHERE brinco = ?";
        try {
            String date1;            
            date1 = dfsql.format(df.parse(data_nascimento));
            Date data = Date.valueOf(date1);
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome);
            ps.setDate(2, data);
            ps.setInt(3, situacao);
            ps.setInt(4, brinco_mae);
            ps.setInt(5, cod_raca);
            ps.setInt(6, cod_touro);
            ps.setInt(7, brinco);
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException | ParseException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public static boolean alterar(int brinco, String nome, String data_nascimento, int situacao, int cod_raca, int cod_touro) {
        String sql = "UPDATE vaca SET nome = ?, data_nascimento = ?, cod_situacao = ?, cod_raca = ?, cod_touro = ? WHERE brinco = ?";
        try {
            String date1;            
            date1 = dfsql.format(df.parse(data_nascimento));
            Date data = Date.valueOf(date1);
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome);
            ps.setDate(2, data);
            ps.setInt(3, situacao);
            ps.setInt(4, cod_raca);
            ps.setInt(5, cod_touro);
            ps.setInt(6, brinco);
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException | ParseException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean excluir(int brinco) {
        String sql = "DELETE FROM vaca WHERE brinco = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, brinco);
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

        public static List<Vaca> consultar() {
        List<Vaca> resultados = new ArrayList<>();
        String sql = "SELECT brinco, nome, data_nascimento, cod_raca, brinco_mae, cod_touro,cod_situacao FROM vaca";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vaca linha = new Vaca();
                linha.setBrinco(rs.getInt("brinco"));
                linha.setBrinco_mae(rs.getInt("brinco_mae"));
                linha.setCod_raca(rs.getInt("cod_raca"));
                linha.setCod_situacao(rs.getInt("cod_situacao"));
                linha.setCod_touro(rs.getInt("cod_touro"));
                linha.setData_nascimento(rs.getDate("data_nascimento"));
                resultados.add(linha);
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(VacaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
    public static List<Vaca> consultar_obj() {
        List<Vaca> resultados = new ArrayList<>();
        String sql = "SELECT brinco, nome, data_nascimento, cod_situacao, brinco_mae, cod_raca, cod_touro FROM vaca";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vaca v = new Vaca();
                v.setBrinco(rs.getInt("brinco"));
                v.setNome(rs.getString("nome"));
                v.setData_nascimento(rs.getDate("data_nascimento"));
                v.setCod_situacao(rs.getInt("cod_situacao"));
                v.setBrinco_mae(rs.getInt("brinco_mae"));
                v.setCod_raca(rs.getInt("cod_raca"));
                v.setCod_touro(rs.getInt("cod_touro"));
                        
                
                resultados.add(v);
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(VacaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static List<vaca_situacao> consultar_situacao() {
        List<vaca_situacao> resultados = new ArrayList<>();
        String sql = "SELECT codigo, nome FROM vacasituacao";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vaca_situacao v = new vaca_situacao();
                v.setCodigo(rs.getInt("codigo"));
                v.setNome(rs.getString("nome"));

                resultados.add(v);
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(VacaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Vaca consultar(int pk) {
        Vaca resultado = new Vaca();
        String sql = "SELECT brinco, nome, data_nascimento,cod_situacao, brinco_mae, cod_raca, cod_touro FROM vaca WHERE brinco=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, pk);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.setBrinco(rs.getInt("brinco"));
                resultado.setBrinco_mae(rs.getInt("brinco_mae"));
                resultado.setCod_raca(rs.getInt("cod_raca"));
                resultado.setCod_situacao(rs.getInt("cod_situacao"));
                resultado.setCod_touro(rs.getInt("cod_touro"));
                resultado.setData_nascimento(rs.getDate("data_nascimento"));
                resultado.setNome(rs.getString("nome"));
            }
            return resultado;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(VacaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static void main(String[] args){
        //consultar();
        //Vaca nena = consultar(12);
        //System.out.println(nena.getBrinco_mae());
        //consultar_obj();
        //consultar_situacao();
        //alterar(12, "Rohirrim", "10/12/1192", 3, 1, 2);
        //excluir(123);
        //inserir(124, "Alefina", "11/11/2011", 3, 1,1);
    }
}
