/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.EmpresaController;
import jcssuprimentosmaven.controller.MateriaPrimaController;
import jcssuprimentosmaven.controller.PedidoController;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.domain.MateriaPrima;
import jcssuprimentosmaven.domain.Pedido;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewDadosPedido;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosPedido {
    ViewDadosPedido viewDadosPedido;
    Jogador jogador;
    public OuvinteViewDadosPedido(ViewDadosPedido view, Jogador jogador){
        this.viewDadosPedido = view;
        this.jogador = jogador;
        viewDadosPedido.bEditarProducaoAddActionListener(new OuvinteEditarPedido());
        viewDadosPedido.bExcluirProducaoAddActionListener(new OuvinteExcluirPedido());
        viewDadosPedido.bGravarProducaoAddActionListener(new OuvinteGravarPedido(this.jogador));
    }
    
    class OuvinteGravarPedido implements ActionListener{
        
        Jogador jogadorUser;
        public OuvinteGravarPedido(Jogador jogador){
            this.jogadorUser = jogador;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            Pedido pedido = viewDadosPedido.preencherPedido();
            try{
                if(!viewDadosPedido.getErro()){
                    realizarPedido(pedido);
                    viewDadosPedido.limparCampos();
                }else{
                    viewDadosPedido.setError(false);
                }
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu erro ao gravar o pedido " + ex.getMessage());
            }
        }
        /**
         * Executa a ação de realizar o pedido de matéria-prima no estoque padrão
         * @param pedido 
         */
        public void realizarPedido(Pedido pedido){
            PedidoController pedidoController = new PedidoController();
            pedidoController.setPedidoCadastro(pedido);
            pedidoController.salvar();
            
            //alterar a quantidade de materia prima do fornecedor
            MateriaPrima materiaAlterar = (MateriaPrima) viewDadosPedido.getCbMateriaPrima().getSelectedItem();
            MateriaPrimaController materiaPrimaController = new MateriaPrimaController();
            BigDecimal quantidadeAtual = new BigDecimal(materiaAlterar.getQuantidade());
            BigDecimal quantidadePedido = new BigDecimal(viewDadosPedido.getJtxQuantidade().getText());
            //compara as quantidades se forem iguais as quantidades retorna 0 
            //se a quantidade atual for maior do que a do pedido retorna 1
            if (quantidadeAtual.compareTo(quantidadePedido) == 0 || quantidadeAtual.compareTo(quantidadePedido) == 1) {
                BigDecimal valor = quantidadeAtual.subtract(quantidadePedido);
                materiaAlterar.setQuantidade(valor.toEngineeringString());
                materiaPrimaController.setMateriaPrimaCadastro(materiaAlterar);
                materiaPrimaController.editar();
            }
            EmpresaController empresaController = new EmpresaController();
            Empresa empresaAlterar = (Empresa) empresaController.buscarPorJogador(this.jogadorUser).get(0);
            empresaAlterar.setCapitalAtual(empresaAlterar.getCapitalAtual().subtract(pedido.getValor()));            
            empresaController.setEmpresaCadastro(empresaAlterar);
            empresaController.editar();
        }
       
    }
    
    class OuvinteEditarPedido implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Pedido pedido = viewDadosPedido.preencherPedido();
            try{
                if(!viewDadosPedido.getErro()){
                    PedidoController pedidoController = new PedidoController();
                    pedidoController.setPedidoCadastro(pedido);
                    pedidoController.editar();
                    pedidoController.listar();
                }else{
                    viewDadosPedido.setError(false);
                }
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu erro ao editar o pedido " + ex.getMessage());
            }
        }
        
    }
    
    class OuvinteExcluirPedido implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Pedido pedido = viewDadosPedido.getPedido();
            try{
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Producao abaixo: ");
                mensagem.append("\ncodigo: " + pedido.getId());
                mensagem.append("\nQuantidade: " + pedido.getQuantidade());
                mensagem.append("\nArmazém: " + pedido.getArmazemSuprimento().getNomeFantasia());
                int resposta = viewDadosPedido.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    PedidoController pedidoController = new PedidoController();
                    pedidoController.setPedidoCadastro(pedido);
                    pedidoController.excluir();
                    pedidoController.listar();
                }                
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu erro ao excluir o pedido " + ex.getMessage());
            }
        }
        
    }
}
