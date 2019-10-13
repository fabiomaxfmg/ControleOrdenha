package dao;

//import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Producao;

public class ProducaoDao {
    static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat dfsql = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean inserir(String data,int producao_litros,int nr_ordenhas_uteis) {
        String sql = "INSERT INTO producao (data,producao_litros,nr_ordenha_uteis) VALUES (?,?,?)";
        try {
            String date1;      
            date1 = dfsql.format(df.parse(data));
            Date date = Date.valueOf(date1);
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setDate(1, date);
            ps.setInt(2,producao_litros);
            ps.setInt(3, nr_ordenhas_uteis);
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException | ParseException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean alterar(int codigo,String data, int producao_litros, int nr_ordenhas_uteis) {
        String sql = "UPDATE producao SET data = ?, producao_litros = ?,nr_ordenha_uteis = ? WHERE codigo = ?";
        try {
            String date1;      
            date1 = dfsql.format(df.parse(data));
            Date date = Date.valueOf(date1);
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setDate(1,date);
            ps.setInt(2, producao_litros);
            ps.setInt(3, nr_ordenhas_uteis);
            ps.setInt(4, codigo);

            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException | ParseException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean excluir(int codigo) {
        String sql = "DELETE FROM producao WHERE codigo = ?";
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

    public static List<Producao> consultar() {
        List<Producao> resultados = new ArrayList<>();
        String sql = "SELECT codigo,data, producao_litros,mediapervaca,nr_ordenha_uteis FROM producao";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producao linha = new Producao();
                linha.setCodigo(rs.getInt("codigo"));
                linha.setData(rs.getDate("data"));
                linha.setProducao_litros(rs.getInt("producao_litros"));
                linha.setMediapervaca(rs.getDouble("mediapervaca"));
                linha.setNr_ordenha_uteis(rs.getInt("nr_ordenha_uteis"));
                resultados.add(linha);
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProducaoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Producao consultar(int pk) {
        Producao resultado = new Producao();
        String sql = "SELECT codigo, data, producao_litros,mediapervaca,nr_ordenha_uteis FROM producao WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, pk);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.setCodigo(rs.getInt("codigo"));
                resultado.setProducao_litros(rs.getInt("producao_litros"));
                resultado.setData(rs.getDate("data"));
                resultado.setMediapervaca(rs.getDouble("mediapervaca"));
                resultado.setNr_ordenha_uteis(rs.getInt("nr_ordenha_uteis"));
            }
            return resultado;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProducaoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static void main(String[] args){
        
    }
}
