package Manutencao;

import Listagem.ListagemVaca;
import Views.ListagemVacaView;
import dao.RacaDao;
import dao.TouroDao;
import dao.VacaDao;
import dao.Vaca_situacaoDao;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.JOptionPane;
import model.Raca;
import model.Touro;
import model.vaca_situacao;

public class ManutencaoVaca extends javax.swing.JDialog {

    private ListagemVaca listagem;
    private ListagemVacaView test;

    public ManutencaoVaca(javax.swing.JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        populateSit();
        populateTour();
        //System.out.println(parent.getClass());
        populateRaca();
//        System.out.println(parent.getClass().getName());
 //       if (parent.getClass().getName() == "Views.ListagemVacaView"){
  //          System.out.println("E igual");
            
  //      }
    }

    public ManutencaoVaca(Frame owner, boolean modal) {
        super(owner, modal);
        initComponents();
    }

    
    
    
    
    public ManutencaoVaca(java.awt.Frame parent, boolean modal, ListagemVaca listagem) {
        super(parent, modal);
        initComponents();
        jtfCodigoMae1.setText("");
        populateTour();
        populateSit();
        populateRaca();
        this.listagem = listagem;

        btnAlterar1.setEnabled(false);
        btnExcluir1.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtfNome = new javax.swing.JTextField();
        jtfCodigo = new javax.swing.JTextField();
        jtfDataNascimento = new javax.swing.JTextField();
        btnExcluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jtfNome1 = new javax.swing.JTextField();
        jtfCodigo1 = new javax.swing.JTextField();
        jtfDataNascimento1 = new javax.swing.JTextField();
        btnExcluir1 = new javax.swing.JButton();
        btnAlterar1 = new javax.swing.JButton();
        btnAdicionar1 = new javax.swing.JButton();
        btnCancelar1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jtfCodigoMae1 = new javax.swing.JTextField();
        situacao = new javax.swing.JLabel();
        jcbSituacao = new javax.swing.JComboBox<>();
        jcbTouro = new javax.swing.JComboBox<>();
        jcbRaca = new javax.swing.JComboBox<>();

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jFrame1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jFrame1.getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(501, 92, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("Cadastro Vaca");
        jFrame1.getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Nome:");
        jFrame1.getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 202, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Código:");
        jFrame1.getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 265, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Data de Nascimento:");
        jFrame1.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 338, -1, -1));
        jFrame1.getContentPane().add(jtfNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 204, 396, -1));
        jFrame1.getContentPane().add(jtfCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 267, 388, -1));
        jFrame1.getContentPane().add(jtfDataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 340, 267, -1));

        btnExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExcluir.setText("Excluir");
        jFrame1.getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(374, 401, -1, -1));

        btnAlterar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAlterar.setText("Alterar");
        jFrame1.getContentPane().add(btnAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 401, -1, -1));

        btnAdicionar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdicionar.setText("Adicionar");
        jFrame1.getContentPane().add(btnAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 401, -1, -1));

        btnCancelar.setText("Cancelar");
        jFrame1.getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(496, 401, -1, -1));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel6.setText("Cadastro Vaca");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Nome:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Brinco:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Data de Nascimento:");

        btnExcluir1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExcluir1.setText("Excluir");
        btnExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluir1ActionPerformed(evt);
            }
        });

        btnAlterar1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAlterar1.setText("Alterar");
        btnAlterar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterar1ActionPerformed(evt);
            }
        });

        btnAdicionar1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdicionar1.setText("Adicionar");
        btnAdicionar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionar1ActionPerformed(evt);
            }
        });

        btnCancelar1.setText("Cancelar");
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Código Mãe:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Código Raça:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Código Touro:");

        situacao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        situacao.setText("Codigo Situacao");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jLabel6))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(120, 120, 120)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtfCodigoMae1))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtfDataNascimento1))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jtfNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(13, 13, 13)
                                        .addComponent(jtfCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11)
                                .addComponent(situacao)
                                .addComponent(jLabel12))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jcbTouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jcbRaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jcbSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(248, 248, 248)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(btnAdicionar1)
                        .addGap(37, 37, 37)
                        .addComponent(btnAlterar1)
                        .addGap(47, 47, 47)
                        .addComponent(btnExcluir1)
                        .addGap(35, 35, 35)
                        .addComponent(btnCancelar1)))
                .addContainerGap(167, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel6)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfNome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jtfDataNascimento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCodigoMae1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jcbRaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jcbTouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(situacao)
                    .addComponent(jcbSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar1)
                    .addComponent(btnAlterar1)
                    .addComponent(btnExcluir1)
                    .addComponent(btnCancelar1))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void jtfCodigoActionPerformed(java.awt.event.ActionEvent evt) {

    }
    private void btnAlterar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterar1ActionPerformed
/*       boolean resultado = VacaDao.alterar(jtfCodigo1.getText(), jtfNome1.getText(), jtfDataNascimento1.getText(), jtfCodigoMae1.getText(), jtfCodigoRaca1.getText(), jtfCodigoTouro1.getText());
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");

            if (listagem != null) {
                listagem.atualizarTabela();
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
        */
    }//GEN-LAST:event_btnAlterar1ActionPerformed

    private void btnAdicionar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionar1ActionPerformed
        boolean resultado;
        System.out.println("AA"+jtfCodigoMae1.getText());
        if(jtfCodigoMae1.getText().equals("")){
            resultado = VacaDao.inserir(Integer.parseInt(jtfCodigo1.getText()), jtfNome1.getText(), jtfDataNascimento1.getText(),jcbSituacao.getItemAt(jcbSituacao.getSelectedIndex()).getCodigo(), jcbRaca.getItemAt(jcbRaca.getSelectedIndex()).getCodigo(), jcbTouro.getItemAt(jcbTouro.getSelectedIndex()).getCodigo());
        }else{
            resultado = VacaDao.inserir(Integer.parseInt(jtfCodigo1.getText()), jtfNome1.getText(), jtfDataNascimento1.getText(),jcbSituacao.getItemAt(jcbSituacao.getSelectedIndex()).getCodigo(),Integer.parseInt(jtfCodigoMae1.getText()) ,jcbRaca.getItemAt(jcbRaca.getSelectedIndex()).getCodigo(), jcbTouro.getItemAt(jcbTouro.getSelectedIndex()).getCodigo());
        }
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");

            if (listagem != null) {
                listagem.atualizarTabela();
            }
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
     //   if(parent.getClass() == Views.ListagemVacaView)
    }//GEN-LAST:event_btnAdicionar1ActionPerformed
    private void limparCampos() {
        jtfCodigo1.setText("");
        jtfNome1.setText("");
        jtfDataNascimento1.setText("");
        jtfCodigoMae1.setText("");
        //jtfCodigoRaca1.setText("");
        //jtfCodigoTouro1.setText("");

        }                     
    
    private void btnExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluir1ActionPerformed
        boolean resultado = VacaDao.excluir(Integer.parseInt(jtfCodigo.getText()));
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

            if (listagem != null) {
                listagem.atualizarTabela();
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
        
    }//GEN-LAST:event_btnExcluir1ActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
       dispose();
    }//GEN-LAST:event_btnCancelar1ActionPerformed
                         
    public static void main(String args[]) {
     
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManutencaoVaca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManutencaoVaca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManutencaoVaca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManutencaoVaca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
       

    
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ManutencaoVaca dialog = new ManutencaoVaca(null, true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    public void populateSit(){
        for(vaca_situacao s : Vaca_situacaoDao.consultar()){
            jcbSituacao.addItem(s);
        }
    }
    public void populateTour(){
        for(Touro s : TouroDao.consultar()){
            jcbTouro.addItem(s);
        }
    }
    public void populateRaca(){
        for(Raca r : RacaDao.consultar()){
            jcbRaca.addItem(r);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAdicionar1;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnAlterar1;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnExcluir1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox<model.Raca> jcbRaca;
    private javax.swing.JComboBox<vaca_situacao> jcbSituacao;
    private javax.swing.JComboBox<model.Touro> jcbTouro;
    private javax.swing.JTextField jtfCodigo;
    private javax.swing.JTextField jtfCodigo1;
    private javax.swing.JTextField jtfCodigoMae1;
    private javax.swing.JTextField jtfDataNascimento;
    private javax.swing.JTextField jtfDataNascimento1;
    private javax.swing.JTextField jtfNome;
    private javax.swing.JTextField jtfNome1;
    private javax.swing.JLabel situacao;
    // End of variables declaration//GEN-END:variables
}
