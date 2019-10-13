/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.vaca_situacao;

/**
 *
 * @author fabio
 */
public class Vaca_situacaoDao {
        public static boolean inserir(String nome) {
        String sql = "INSERT INTO vacasituacao (nome) VALUES (?)";
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

    public static boolean alterar(int codigo, String nome) {
        String sql = "UPDATE vacasituacao SET nome = ? WHERE codigo = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome);
            ps.setInt(2, codigo);
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean excluir(int codigo) {
        String sql = "DELETE FROM vacasituacao WHERE codigo = ?";
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

    public static List<vaca_situacao> consultar_obj() {
        List<vaca_situacao> resultados = new ArrayList<>();
        String sql = "SELECT codigo, nome FROM vacasituacao";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vaca_situacao i = new vaca_situacao();
                i.setCodigo(rs.getInt("codigo"));
                i.setNome(rs.getString("nome"));
                resultados.add(i);
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(InseminadorDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

        public static List<vaca_situacao> consultar() {
        List<vaca_situacao> resultados = new ArrayList<>();
        String sql = "SELECT codigo, nome FROM vacasituacao";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vaca_situacao linha = new vaca_situacao();
                linha.setCodigo(rs.getInt("codigo"));
                linha.setNome(rs.getString("nome"));
                resultados.add(linha);
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(InseminadorDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


    
    public static vaca_situacao consultar(int pk) {
        vaca_situacao resultado = new vaca_situacao();
        String sql = "SELECT codigo, nome FROM vacasituacao WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, pk);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.setCodigo(rs.getInt("codigo"));
                resultado.setNome(rs.getString("nome"));
            }
            return resultado;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(InseminadorDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static void main(String[] args){

    }
    
}
