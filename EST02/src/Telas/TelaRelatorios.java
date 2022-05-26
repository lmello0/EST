/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Telas;

import DAO.Comandos;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;

/**
 *
 * @author mello
 */
public class TelaRelatorios extends javax.swing.JDialog {
    Comandos comandos;
    Funcionario funcionario;
    String[] colunas;
    /**
     * Creates new form TelaRelatorios
     * @param parent
     * @param modal
     * @param comandos
     * @param funcionario
     */
    public TelaRelatorios(java.awt.Frame parent, boolean modal, Comandos comandos, Funcionario funcionario) {
        super(parent, modal);
        this.comandos = comandos;
        this.funcionario = funcionario;
        initComponents();
        
        switch(funcionario.getCargo()){
            case "GERENTE":
                this.colunas = new String[]{"PEDIDO", "PRODUTO", "QUANTIDADE", "CLIENTE", "DATA DO PEDIDO", "VALOR", "VALOR TOTAL", "VENDEDOR"};
                break;
            default:
                this.colunas = new String[]{"PEDIDO", "PRODUTO", "QUANTIDADE", "CLIENTE", "DATA DO PEDIDO", "VALOR", "VALOR TOTAL"};
                break;
        }
        
        try {
            switch(funcionario.getCargo()){
                case "GERENTE":
                    tablePedidos.setModel(comandos.getPedidosVendedor(null, null, null, null, null, null, tablePedidos, colunas));
                    break;
                default:
                    tablePedidos.setModel(comandos.getPedidosVendedor(funcionario.getCpf(), null, null, null, null, null, tablePedidos, colunas));
                    break;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, null, JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPaneGeral = new javax.swing.JScrollPane();
        panelGeral = new javax.swing.JPanel();
        lblDtIni = new javax.swing.JLabel();
        txtDtIni = new javax.swing.JFormattedTextField();
        lblDtFim = new javax.swing.JLabel();
        txtDtFim = new javax.swing.JFormattedTextField();
        btnBuscar = new javax.swing.JButton();
        btnDownload = new javax.swing.JButton();
        scrollPaneTablePedidos = new javax.swing.JScrollPane();
        tablePedidos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Extrair relatório");
        setIconImage(null);
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        scrollPaneGeral.setMinimumSize(new java.awt.Dimension(858, 560));

        panelGeral.setMinimumSize(new java.awt.Dimension(858, 560));

        lblDtIni.setText("Data inicial.:");

        txtDtIni.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        txtDtIni.setToolTipText("DD/MM/YYYY");
        txtDtIni.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        lblDtFim.setText("Data final.:");

        txtDtFim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        txtDtFim.setToolTipText("DD/MM/YYYY");
        txtDtFim.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnDownload.setText("Downlod relatório");
        btnDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownloadActionPerformed(evt);
            }
        });

        tablePedidos.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        tablePedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "PRODUTO", "QUANTIDADE", "CLIENTE", "DATA DO PEDIDO", "VALOR", "VALOR TOTAL", "VENDEDOR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollPaneTablePedidos.setViewportView(tablePedidos);

        javax.swing.GroupLayout panelGeralLayout = new javax.swing.GroupLayout(panelGeral);
        panelGeral.setLayout(panelGeralLayout);
        panelGeralLayout.setHorizontalGroup(
            panelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneTablePedidos)
                    .addGroup(panelGeralLayout.createSequentialGroup()
                        .addComponent(lblDtIni)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDtIni, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDtFim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDtFim, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDownload)
                        .addGap(0, 413, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelGeralLayout.setVerticalGroup(
            panelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeralLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDtIni)
                    .addComponent(txtDtIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDtFim)
                    .addComponent(txtDtFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(btnDownload))
                .addGap(18, 18, 18)
                .addComponent(scrollPaneTablePedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                .addContainerGap())
        );

        scrollPaneGeral.setViewportView(panelGeral);

        getContentPane().add(scrollPaneGeral);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // coleta as variaveis de data e valida-as
        String dtIni = txtDtIni.getText();
        String dtFim = txtDtFim.getText();

        if(!dtIni.equals("") && !dtIni.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$")){
            JOptionPane.showMessageDialog(null, "O formato da data está errado!", "Erro na data inicial", JOptionPane.ERROR_MESSAGE);
        }

        if(!dtFim.equals("") && !dtFim.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$")){
            JOptionPane.showMessageDialog(null, "O formato da data está errado!", "Erro na data final", JOptionPane.ERROR_MESSAGE);
        }

        if (dtIni.equals("")) dtIni = null;
        if (dtFim.equals("")) dtFim = null;

        // busca o historico de pedidos de acordo com a data passada
        try {
            if (funcionario.getCargo().equals("GERENTE")){
                tablePedidos.setModel(comandos.getPedidosVendedor(null, null, null, null, dtIni, dtFim, tablePedidos, colunas));
            } else {
                tablePedidos.setModel(comandos.getPedidosVendedor(funcionario.getCpf(), null, null, null, dtIni, dtFim, tablePedidos, colunas));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadActionPerformed
        // faz o download do resultado da tabela
        if (tablePedidos.getRowCount() != 0){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmm");
            String filename = "EXTRACAO_EST_" + formatter.format(new Date()) + ".csv";

            // janela para selecao do local de salvamento
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Comma Separated Values (.csv)", "csv");
            chooser.addChoosableFileFilter(filtro);

            filename = chooser.getCurrentDirectory() + "\\" + filename;
            chooser.setSelectedFile(new File(filename));

            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
                if (!chooser.getSelectedFile().isDirectory()){
                    filename = chooser.getSelectedFile().getAbsolutePath() + ".csv";
                }

                // exporta o resultado da pesquisa num arquivo
                try {
                    exportToCsv(tablePedidos, filename);

                    JOptionPane.showMessageDialog(null, "Relatorio exportado para\n" + filename, null, JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    Logger.getLogger(TelaRelatorios.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Faca uma pesquisa antes de gerar um relatorio!", null, JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnDownloadActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDownload;
    private javax.swing.JLabel lblDtFim;
    private javax.swing.JLabel lblDtIni;
    private javax.swing.JPanel panelGeral;
    private javax.swing.JScrollPane scrollPaneGeral;
    private javax.swing.JScrollPane scrollPaneTablePedidos;
    private static javax.swing.JTable tablePedidos;
    private javax.swing.JFormattedTextField txtDtFim;
    private javax.swing.JFormattedTextField txtDtIni;
    // End of variables declaration//GEN-END:variables

    private void exportToCsv(javax.swing.JTable table, String path) throws IOException{
        // exporta a tabela em formato csv para o path passado
        TableModel model = table.getModel();
        try (FileWriter csv = new FileWriter(new File(path))) {
            csv.write("CODIGO PEDIDO;PRODUTO;QUANTIDADE;CLIENTE;DATA DO PEDIDO;VALOR;VALOR TOTAL;VENDEDOR;\n");
            
            for (int i = 0; i < model.getRowCount(); i++){
                for (int j = 0; j < model.getColumnCount(); j++){
                    csv.write(model.getValueAt(i, j).toString() + ";");
                }
                csv.write("\n");
            }
        }
    }
}