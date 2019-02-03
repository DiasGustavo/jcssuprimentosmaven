/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import jcssuprimentosmaven.controller.EmpresaController;
import jcssuprimentosmaven.controller.EstoqueSuprimentoController;
import jcssuprimentosmaven.controller.PedidoController;
import jcssuprimentosmaven.dao.ArmazemSuprimentoDAO;
import jcssuprimentosmaven.dao.EmpresaDAO;
import jcssuprimentosmaven.dao.FabricaDAO;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.domain.ArmazemSuprimento;
import jcssuprimentosmaven.domain.EstoqueSuprimento;
import jcssuprimentosmaven.domain.Pedido;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaPedido;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaPedido {

    ViewPesquisaPedido viewPesquisaPedido;
    Jogador jogador;

    public OuvinteViewPesquisaPedido(ViewPesquisaPedido view, Jogador jogador) {
        this.viewPesquisaPedido = view;
        this.jogador = jogador;
        viewPesquisaPedido.bPesquisarTodosAddActionListener(new OuvintePesquisaTodosPedidos(this.jogador));
        viewPesquisaPedido.bPesquisarAddActionListener(new OuvintePesquisaPedido());
        viewPesquisaPedido.bExcluirAddActionListener(new OuvinteExcluirPedido());
        viewPesquisaPedido.bFinalizarPedidoAddActionListener(new OuvinteFinalizarPedido(this.jogador));
    }

    class OuvintePesquisaTodosPedidos implements ActionListener {

        Jogador jogadorUser;

        public OuvintePesquisaTodosPedidos(Jogador jogador) {
            this.jogadorUser = jogador;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            PedidoController pedidoController = new PedidoController();
            try {
                //funcao de administrador
                if (this.jogadorUser.getFuncao() == 2) {

                    pedidoController.listar();
                    List pedidos = pedidoController.getListaPedidos();
                    viewPesquisaPedido.listar(pedidos);
                } else {
                    //selecionar apenas os pedidos da empresa logada
                    EmpresaDAO edao = new EmpresaDAO();
                    FabricaDAO fdao = new FabricaDAO();
                    Empresa empresa = (Empresa) edao.buscarPorJogador(this.jogadorUser).get(0);
                    Fabrica fabrica = (Fabrica) fdao.buscarPorEmpresa(empresa).get(0);
                    ArmazemSuprimentoDAO asdao = new ArmazemSuprimentoDAO();
                    ArmazemSuprimento armazemSuprimento = (ArmazemSuprimento) asdao.buscarPorFabrica(fabrica).get(0);
                    List pedidos = pedidoController.buscarPorArmazemSuprimentoStatus(armazemSuprimento);
                    viewPesquisaPedido.listar(pedidos);
                }
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Ocorreu um erro ao listar os pedidos " + ex.getMessage());
            }
        }

    }

    class OuvintePesquisaPedido implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                PedidoController pedidoController = new PedidoController();
                Long codigo = viewPesquisaPedido.getIdPesquisar();                
                pedidoController.setCodigo(codigo);
                List pedidos = pedidoController.buscarPedidoPorCodigo(pedidoController.getCodigo());
                
                viewPesquisaPedido.listar(pedidos);

            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Erro ao pesquisar o pedido " + ex.getMessage());
            }
        }

    }

    class OuvinteExcluirPedido implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Pedido pedido = viewPesquisaPedido.getPedido();
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Produção abaixo: ");
                mensagem.append("\ncodigo: " + pedido.getId());
                mensagem.append("\nQuantidade: " + pedido.getQuantidade());
                mensagem.append("\nMateria: " + pedido.getArmazemSuprimento().getNomeFantasia());
                int resposta = viewPesquisaPedido.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    PedidoController pedidoController = new PedidoController();
                    pedidoController.setPedidoCadastro(pedido);
                    pedidoController.excluir();
                    pedidoController.listar();
                    List pedidos = pedidoController.getListaPedidos();
                    viewPesquisaPedido.listar(pedidos);
                }
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Ocorreu um erro ao excluir o pedido " + ex.getMessage());
            }
        }

    }

    class OuvinteFinalizarPedido implements ActionListener {
        
        Jogador jogador;
        public OuvinteFinalizarPedido(Jogador jogador){
            this.jogador = jogador;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Pedido pedido = viewPesquisaPedido.getPedido();
                PedidoController pedidoController = new PedidoController();
                //transportando as materias primas para o estoque suprimento
                EstoqueSuprimentoController estoqueSuprimentoController = new EstoqueSuprimentoController();
                EstoqueSuprimento estoque = new EstoqueSuprimento();
                estoque.setArmazemSuprimento(pedido.getArmazemSuprimento());
                estoque.setDescricao(pedido.getDescricao());
                estoque.setMateriaPrima(pedido.getMateriPrima());
                estoque.setQuantidade(pedido.getQuantidade());
                //atualizar o pedido
                pedido.setStatus("Entregue");
                pedidoController.setPedidoCadastro(pedido);
                pedidoController.editar();
                pedidoController.listar();
                List pedidos = pedidoController.getListaPedidos();
                viewPesquisaPedido.listar(pedidos);
                
                List estoques = estoqueSuprimentoController.buscarPorMateriaArmazem(pedido.getMateriPrima(), pedido.getArmazemSuprimento());
                if(estoques.size()>0){
                    EstoqueSuprimento estoqueTemp = (EstoqueSuprimento) estoques.get(0);
                    estoqueTemp.setQuantidade(estoqueTemp.getQuantidade().add(pedido.getQuantidade()));
                    estoqueSuprimentoController.setEstoqueCadastro(estoqueTemp);
                    estoqueSuprimentoController.editar();
                }else{
                    estoqueSuprimentoController.setEstoqueCadastro(estoque);
                    estoqueSuprimentoController.salvar();
                }
                
                EmpresaController empresaController = new EmpresaController();
                List empresas = empresaController.buscarPorJogador(jogador);
                Empresa empresa = (Empresa) empresas.get(0);
                empresa.setCapitalAtual(empresa.getCapitalAtual().subtract(pedido.getValor()));
                empresaController.setEmpresaCadastro(empresa);
                empresaController.editar();
                
            }catch(RuntimeException ex){
                ViewUtil.addMsgErro("Ocorreu um erro ao finalizar o pedido " + ex.getMessage());
            }
        }

    }

}
