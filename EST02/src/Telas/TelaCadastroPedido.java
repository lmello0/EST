/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Telas;

import DAO.Comandos;
import Classes.*;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mello
 */
public class TelaCadastroPedido extends javax.swing.JDialog {
    private Comandos comandos;
    private ArrayList<Produto> itens;
    private ArrayList<String> nomesProdutos = new ArrayList<>();
    private ArrayList<Cliente> clientes;
    private ArrayList<String> nomesClientes = new ArrayList<>();
    private boolean stateChange = true;
    private float totalPedido = 0;
    private Pedido pedido = new Pedido();
    
    public TelaCadastroPedido(java.awt.Frame parent, boolean modal, Comandos comandos, Funcionario funcionario) {
        super(parent, modal);
        this.comandos = comandos;
        
        try {
            initComponents();
            
            // Pega o cargo do funcionario para verificar se sera possivel registrar o pedido no nome de outros vendedores
            String cargo = funcionario.getCargo();
            
            if (!cargo.equals("GERENTE")){
                cbVendedor.setEnabled(false);
            }
            
            // Pega todos os produtos para popular as variaveis e disponibilizar nos comboBoxes
            itens = comandos.getProduto();
            clientes = comandos.getCliente();
            
            // Para cada produto em 'itens' adiciona o nome a variavel 'nomesProdutos'
            for (Produto produtos : itens) {
                nomesProdutos.add(produtos.getNome());
            }
            
            for (Cliente cliente : clientes) {
                nomesClientes.add(cliente.getNome());
            }
            
            // popula todas as comboBoxes
            popularComboBox(cbProduto, nomesProdutos);
            popularComboBox(cbCliente, nomesClientes);
            popularComboBox(cbVendedor, comandos.getFuncionario());
            
            txtItensEstoque.setText(String.valueOf(itens.get(cbProduto.getSelectedIndex()).getQuantidade()-1));
            
            // seleciona o funcionario com base no nome
            cbVendedor.setSelectedItem(funcionario.getNome());
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroPedido.class.getName()).log(Level.SEVERE, null, ex);
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

        scrollGeral = new javax.swing.JScrollPane();
        panelGeral = new javax.swing.JPanel();
        panelPedido = new javax.swing.JPanel();
        scrollTablePedido = new javax.swing.JScrollPane();
        tablePedido = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        lblPrecoTotal = new javax.swing.JLabel();
        panelProduto = new javax.swing.JPanel();
        lblCodigoProduto = new javax.swing.JLabel();
        txtCodigoProduto = new javax.swing.JTextField();
        lblProduto = new javax.swing.JLabel();
        cbProduto = new javax.swing.JComboBox<>();
        lblQuantidade = new javax.swing.JLabel();
        spinQuantidade = new javax.swing.JSpinner();
        lblCliente = new javax.swing.JLabel();
        cbCliente = new javax.swing.JComboBox<>();
        lblVendedor = new javax.swing.JLabel();
        cbVendedor = new javax.swing.JComboBox<>();
        lblValor = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        lblItensEstoque = new javax.swing.JLabel();
        txtItensEstoque = new javax.swing.JTextField();
        btnLimparPedido = new javax.swing.JButton();
        btnFecharPedido = new javax.swing.JButton();
        btnRemoverItem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo pedido");
        setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        setIconImage(null);
        setIconImages(null);
        setMinimumSize(new java.awt.Dimension(1300, 750));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        scrollGeral.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N

        panelPedido.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablePedido.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        tablePedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Produto", "Quantidade", "Cliente", "Vendedor", "Valor unitário", "Valor total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollTablePedido.setViewportView(tablePedido);

        lblTotal.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblTotal.setText("Total.:");

        lblPrecoTotal.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblPrecoTotal.setText("R$ " + totalPedido);

        javax.swing.GroupLayout panelPedidoLayout = new javax.swing.GroupLayout(panelPedido);
        panelPedido.setLayout(panelPedidoLayout);
        panelPedidoLayout.setHorizontalGroup(
            panelPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTablePedido, javax.swing.GroupLayout.DEFAULT_SIZE, 1193, Short.MAX_VALUE)
                    .addGroup(panelPedidoLayout.createSequentialGroup()
                        .addComponent(lblTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPrecoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelPedidoLayout.setVerticalGroup(
            panelPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollTablePedido, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPrecoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelProduto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCodigoProduto.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCodigoProduto.setText("Código do produto.:");

        txtCodigoProduto.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        txtCodigoProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoProdutoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoProdutoKeyTyped(evt);
            }
        });

        lblProduto.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblProduto.setText("Produto.:");

        cbProduto.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        cbProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        cbProduto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbProdutoItemStateChanged(evt);
            }
        });

        lblQuantidade.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblQuantidade.setText("Quantidade.:");

        spinQuantidade.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        lblCliente.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCliente.setText("Cliente.:");

        cbCliente.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N

        lblVendedor.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblVendedor.setText("Vendedor.:");

        cbVendedor.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N

        lblValor.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblValor.setText("Valor.:");

        txtValor.setEditable(false);
        txtValor.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        txtValor.setToolTipText("");

        btnAdicionar.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnLimpar.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        lblItensEstoque.setText("Itens em estoque.:");

        txtItensEstoque.setEditable(false);

        javax.swing.GroupLayout panelProdutoLayout = new javax.swing.GroupLayout(panelProduto);
        panelProduto.setLayout(panelProdutoLayout);
        panelProdutoLayout.setHorizontalGroup(
            panelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProdutoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAdicionar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpar))
                    .addGroup(panelProdutoLayout.createSequentialGroup()
                        .addGroup(panelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCodigoProduto)
                            .addComponent(lblCliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbCliente, 0, 120, Short.MAX_VALUE)
                            .addComponent(txtCodigoProduto))
                        .addGroup(panelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelProdutoLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblProduto))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProdutoLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblVendedor)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbProduto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbVendedor, 0, 120, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblValor, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblQuantidade, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spinQuantidade)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblItensEstoque)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtItensEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelProdutoLayout.setVerticalGroup(
            panelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigoProduto)
                    .addComponent(txtCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProduto)
                    .addComponent(lblQuantidade)
                    .addComponent(spinQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblItensEstoque)
                    .addComponent(txtItensEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliente)
                    .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVendedor)
                    .addComponent(cbVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValor)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLimpar)
                    .addComponent(btnAdicionar))
                .addContainerGap())
        );

        btnLimparPedido.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        btnLimparPedido.setText("Limpar pedido");
        btnLimparPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparPedidoActionPerformed(evt);
            }
        });

        btnFecharPedido.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        btnFecharPedido.setText("Fechar pedido");
        btnFecharPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharPedidoActionPerformed(evt);
            }
        });

        btnRemoverItem.setText("Remover item");
        btnRemoverItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelGeralLayout = new javax.swing.GroupLayout(panelGeral);
        panelGeral.setLayout(panelGeralLayout);
        panelGeralLayout.setHorizontalGroup(
            panelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGeralLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRemoverItem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFecharPedido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimparPedido)))
                .addContainerGap())
        );
        panelGeralLayout.setVerticalGroup(
            panelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimparPedido)
                    .addComponent(btnFecharPedido)
                    .addComponent(btnRemoverItem))
                .addGap(12, 12, 12))
        );

        scrollGeral.setViewportView(panelGeral);

        getContentPane().add(scrollGeral);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbProdutoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProdutoItemStateChanged
        // se um novo produto for selecionado, muda o campo de codigo do produto e valor
        if(evt.getStateChange() == ItemEvent.SELECTED && stateChange){
            txtCodigoProduto.setText(String.valueOf(itens.get(cbProduto.getSelectedIndex()).getCodigo()));
            txtValor.setText("R$ " + itens.get(cbProduto.getSelectedIndex()).getValor());
            txtItensEstoque.setText(String.valueOf(itens.get(cbProduto.getSelectedIndex()).getQuantidade()-1));
        }
    }//GEN-LAST:event_cbProdutoItemStateChanged

    private void txtCodigoProdutoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProdutoKeyTyped
        // metodo para bloquear a entrada de caracteres nao numericos no campo de id do produto
        char letra = evt.getKeyChar();
        if (((letra < '0') || (letra > '9')) && (letra != KeyEvent.VK_BACK_SPACE)){
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoProdutoKeyTyped

    private void txtCodigoProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProdutoKeyReleased
        // metodo para limitar a lista de produto de acordo com o id que esta sendo digitado
        String id = txtCodigoProduto.getText();
        Dimension tamanho = cbProduto.getSize();
        
        if (id != null){
            stateChange = !stateChange;
            ArrayList<String> itensFiltrados = new ArrayList<>();

            for (Produto item : itens){
                if (String.valueOf(item.getCodigo()).startsWith(id)){
                    itensFiltrados.add(item.getNome());
                }
            }
            
            popularComboBox(cbProduto, itensFiltrados);
            cbProduto.setPreferredSize(tamanho);
            
            for (Produto produto : itens){
                if (produto.getNome().equals(cbProduto.getSelectedItem())){
                    txtValor.setText("R$ " + produto.getValor());
                    txtItensEstoque.setText(String.valueOf(produto.getQuantidade()-1));
                    break;
                }
            }
            
        } else {
            stateChange = !stateChange;
            popularComboBox(cbProduto, nomesProdutos);
            cbProduto.setSelectedIndex(0);
        }
        stateChange = !stateChange;
    }//GEN-LAST:event_txtCodigoProdutoKeyReleased

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // metodo para adicionar produtos ao carrinho
        
        // coleta das informacoes do pedido
        String id = txtCodigoProduto.getText();
        String nomeProduto = cbProduto.getSelectedItem().toString();
        int quantidade = Integer.parseInt(spinQuantidade.getValue().toString());
        String cliente = cbCliente.getSelectedItem().toString();
        String vendedor = cbVendedor.getSelectedItem().toString();
        double valorUnit = Double.parseDouble(txtValor.getText().substring(2).strip());
        double valorTot = valorUnit * quantidade;
        
        // adiciona produto na tabela
        DefaultTableModel modelo = (DefaultTableModel) tablePedido.getModel();
        modelo.addRow(new Object[]{id, nomeProduto, quantidade, cliente, vendedor, valorUnit, valorTot});

        // desabilita a escolha do cliente a partir de um item adicionado
        cbCliente.setEnabled(false);
        
        // soma o valor total do pedido
        totalPedido += valorTot;
        lblPrecoTotal.setText("R$ " + totalPedido);

        // adiciona o produto ao pedido
        Produto produto = new Produto(Integer.parseInt(id), nomeProduto, quantidade, valorUnit, null);
        pedido.addProduto(produto);
        
        // seta as configuracoes do pedido
        pedido.setNewCodPedido();
        pedido.setValor(totalPedido);
        pedido.setCliente(cliente);
        pedido.setVendedor(vendedor);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnLimparPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparPedidoActionPerformed
        // metodo para limpar o carrinho e a busca
        DefaultTableModel modelo = (DefaultTableModel) tablePedido.getModel();
        modelo.setRowCount(0);
        
        totalPedido = 0;
        lblPrecoTotal.setText("R$ " + totalPedido);
        cbCliente.setEnabled(true);
        
        btnLimpar.doClick();
    }//GEN-LAST:event_btnLimparPedidoActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // metodo para limpar a busca
        txtCodigoProduto.setText(null);
        popularComboBox(cbProduto, nomesProdutos);
        spinQuantidade.setValue(1);
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnFecharPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharPedidoActionPerformed
        // metodo para fechar o pedido e consequentemente registrar o pedido no banco
                
        if (tablePedido.getRowCount() != 0){
            try {
                comandos.insertPedido(pedido);
                JOptionPane.showMessageDialog(null, "Pedido registrado!", null, JOptionPane.INFORMATION_MESSAGE);
                
                btnLimparPedido.doClick();
                btnLimpar.doClick();
                pedido = new Pedido();
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 2290){
                    JOptionPane.showMessageDialog(null, "O produto não está disponível nessa quantidade", null, JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "O carrinho está vazio!", null, JOptionPane.WARNING_MESSAGE);
        }    
    }//GEN-LAST:event_btnFecharPedidoActionPerformed

    private void btnRemoverItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverItemActionPerformed
        int row = tablePedido.getSelectedRow();
        float valor = Float.parseFloat(tablePedido.getValueAt(row, 5).toString());
        
        totalPedido -= valor;
        lblPrecoTotal.setText("R$ " + totalPedido);
        pedido.setValor(totalPedido);
        
        DefaultTableModel modelo = (DefaultTableModel) tablePedido.getModel();
        modelo.removeRow(row);
        pedido.removeProduto(Integer.parseInt(tablePedido.getValueAt(row, 0).toString()));
        
        if (tablePedido.getRowCount() == 0) {
            cbCliente.setEnabled(true);
        }
    }//GEN-LAST:event_btnRemoverItemActionPerformed

    private void popularComboBox(javax.swing.JComboBox<String> comboBox, ArrayList<String> itens){
        // metodo que popula as comboBoxes com base numa lista
        comboBox.removeAllItems();
        for (String item : itens){
            comboBox.addItem(item);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnFecharPedido;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnLimparPedido;
    private javax.swing.JButton btnRemoverItem;
    private javax.swing.JComboBox<String> cbCliente;
    private javax.swing.JComboBox<String> cbProduto;
    private javax.swing.JComboBox<String> cbVendedor;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblCodigoProduto;
    private javax.swing.JLabel lblItensEstoque;
    private javax.swing.JLabel lblPrecoTotal;
    private javax.swing.JLabel lblProduto;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblValor;
    private javax.swing.JLabel lblVendedor;
    private javax.swing.JPanel panelGeral;
    private javax.swing.JPanel panelPedido;
    private javax.swing.JPanel panelProduto;
    private javax.swing.JScrollPane scrollGeral;
    private javax.swing.JScrollPane scrollTablePedido;
    private javax.swing.JSpinner spinQuantidade;
    private javax.swing.JTable tablePedido;
    private javax.swing.JTextField txtCodigoProduto;
    private javax.swing.JTextField txtItensEstoque;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
