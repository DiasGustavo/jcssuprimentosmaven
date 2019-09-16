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
import jcssuprimentosmaven.controller.EstoqueDistribuicaoController;
import jcssuprimentosmaven.controller.EstoqueSuprimentoController;
import jcssuprimentosmaven.controller.MateriaPrimaController;
import jcssuprimentosmaven.controller.TransporteController;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.EstoqueDistribuicao;
import jcssuprimentosmaven.domain.EstoqueSuprimento;
import jcssuprimentosmaven.domain.MateriaPrima;
import jcssuprimentosmaven.domain.Transporte;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewDadosTransporte;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewDadosTransporte {

    ViewDadosTransporte viewDadosTransporte;

    public OuvinteViewDadosTransporte(ViewDadosTransporte view) {
        this.viewDadosTransporte = view;
        viewDadosTransporte.bGravarAddActionListener(new OuvinteGravarTransporte());
        viewDadosTransporte.bEditarAddActionListener(new OuvinteEditarTransporte());
        viewDadosTransporte.bExcluirAddActionListener(new OuvinteExcluirTransporte());
    }

    class OuvinteGravarTransporte implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Transporte transporte;
            String destino = viewDadosTransporte.getDestino();
            if (!viewDadosTransporte.getErro()) {
                /*TransporteController transporteController = new TransporteController();
                transporteController.setTransporteCadastro(transporte);
                transporteController.salvar();*/

                transporte = viewDadosTransporte.preencherTransporte();
                this.comprarEstoqueSuprimento(transporte);

                viewDadosTransporte.limparCampos();
            } else {
                viewDadosTransporte.setError(false);
                ViewUtil.addMsgErro("Verifique os dados da quantidade!");
            }
        }

        public boolean validar() {
            MateriaPrima materia = (MateriaPrima) viewDadosTransporte.getCbMateriaPrima().getSelectedItem();
            int quantidade = Integer.valueOf(materia.getQuantidade());
            int solicitada = Integer.valueOf(viewDadosTransporte.getJtxQuantidade().getText());
            boolean permite = false;
            if (quantidade >= solicitada) {
                permite = true;
            }

            return permite;
        }

        public void comprarMateriaPrima(Transporte transporte) {
            TransporteController transporteController = new TransporteController();
            transporteController.setTransporteCadastro(transporte);
            transporteController.salvar();
            //alterar a quantidade de materia prima em estoque
            MateriaPrima materiaAlterar = (MateriaPrima) viewDadosTransporte.getCbMateriaPrima().getSelectedItem();
            MateriaPrimaController materiaPrimaController = new MateriaPrimaController();
            BigDecimal quantidadeAtual = new BigDecimal(materiaAlterar.getQuantidade());
            BigDecimal quantidadePedido = new BigDecimal(viewDadosTransporte.getJtxQuantidade().getText());
            //compara as quantidades se forem iguais as quantidades retorna 0 
            //se a quantidade atual for maior do que a do pedido retorna 1
            if (quantidadeAtual.compareTo(quantidadePedido) == 0 || quantidadeAtual.compareTo(quantidadePedido) == 1) {
                BigDecimal valor = quantidadeAtual.subtract(quantidadePedido);
                materiaAlterar.setQuantidade(valor.toEngineeringString());
                materiaPrimaController.setMateriaPrimaCadastro(materiaAlterar);
                materiaPrimaController.editar();
            }
            //alterar o capital
            /*Empresa empresaAlterar = (Empresa) viewDadosTransporte.getCbEmpresa().getSelectedItem();
            EmpresaController empresaController = new EmpresaController();
            empresaAlterar.setCapitalAtual(empresaAlterar.getCapitalAtual().subtract(transporteController.getTransporteCadastro().getValor()));
            empresaAlterar.setCapitalAtual(empresaAlterar.getCapitalAtual().subtract(materiaAlterar.getPreco().multiply(quantidadePedido)));
            empresaController.setEmpresaCadastro(empresaAlterar);
            empresaController.editar();*/
        }

        public void comprarEstoqueSuprimento(Transporte transporte) {
            TransporteController transporteController = new TransporteController();
            transporteController.setTransporteCadastro(transporte);
            transporteController.salvar();
            //alterar a quantidade de materia prima em estoque de suprimento
            EstoqueSuprimento materiaAlterar = (EstoqueSuprimento) viewDadosTransporte.getCbMateriaPrima().getSelectedItem();
            EstoqueSuprimentoController estoqueSuprimentoController = new EstoqueSuprimentoController();
            BigDecimal quantidadeAtual = new BigDecimal(materiaAlterar.getQuantidade().toEngineeringString());
            BigDecimal quantidadePedido = new BigDecimal(viewDadosTransporte.getJtxQuantidade().getText());
            if (quantidadeAtual.compareTo(quantidadePedido) == 0 || quantidadeAtual.compareTo(quantidadePedido) == 1) {
                BigDecimal valor = quantidadeAtual.subtract(quantidadePedido);
                materiaAlterar.setQuantidade(valor);
                estoqueSuprimentoController.setEstoqueCadastro(materiaAlterar);
                estoqueSuprimentoController.editar();
            }
            //alterar o capital
            /*Empresa empresaAlterar = (Empresa) viewDadosTransporte.getCbEmpresa().getSelectedItem();
            EmpresaController empresaController = new EmpresaController();
            empresaAlterar.setCapitalAtual(empresaAlterar.getCapitalAtual().subtract(transporteController.getTransporteCadastro().getValor()));
            //empresaAlterar.setCapitalAtual(empresaAlterar.getCapitalAtual().subtract(materiaAlterar.getPreco().multiply(quantidadePedido)));
            empresaController.setEmpresaCadastro(empresaAlterar);
            empresaController.editar();*/
        }

        public void comprarEstoqueDistribuicao(Transporte transporte) {
            TransporteController transporteController = new TransporteController();
            transporteController.setTransporteCadastro(transporte);
            transporteController.salvar();
            //alterar a quantidade de materia prima em estoque de suprimento
            EstoqueDistribuicao materiaAlterar = (EstoqueDistribuicao) viewDadosTransporte.getCbMateriaPrima().getSelectedItem();
            EstoqueDistribuicaoController estoqueDistribuicaoController = new EstoqueDistribuicaoController();
            BigDecimal quantidadeAtual = new BigDecimal(materiaAlterar.getQuantidade().toEngineeringString());
            BigDecimal quantidadePedido = new BigDecimal(viewDadosTransporte.getJtxQuantidade().getText());
            if (quantidadeAtual.compareTo(quantidadePedido) == 0 || quantidadeAtual.compareTo(quantidadePedido) == 1) {
                BigDecimal valor = quantidadeAtual.subtract(quantidadePedido);
                materiaAlterar.setQuantidade(valor);
                estoqueDistribuicaoController.setEstoqueCadastro(materiaAlterar);
                estoqueDistribuicaoController.editar();
            }
            //alterar o capital
            /*Empresa empresaAlterar = (Empresa) viewDadosTransporte.getCbEmpresa().getSelectedItem();
            EmpresaController empresaController = new EmpresaController();
            empresaAlterar.setCapitalAtual(empresaAlterar.getCapitalAtual().subtract(transporteController.getTransporteCadastro().getValor()));
            //empresaAlterar.setCapitalAtual(empresaAlterar.getCapitalAtual().subtract(materiaAlterar.getPreco().multiply(quantidadePedido)));
            empresaController.setEmpresaCadastro(empresaAlterar);
            empresaController.editar();*/
        }

    }

    class OuvinteEditarTransporte implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Transporte transporte = viewDadosTransporte.preencherTransporte();
            if (!viewDadosTransporte.getErro()) {
                TransporteController transporteController = new TransporteController();
                transporteController.setTransporteCadastro(transporte);
                transporteController.editar();
                transporteController.listar();
            } else {
                viewDadosTransporte.setError(false);
            }
        }

    }

    class OuvinteExcluirTransporte implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Transporte transporte = viewDadosTransporte.getTransporte();
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão do Transporte abaixo: ");
            mensagem.append("\ncodigo: " + transporte.getId());
            mensagem.append("\nnome: " + transporte.getArmazemFabrica().getFabrica().getNomeFantasia());
            int resposta = viewDadosTransporte.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                TransporteController transporteController = new TransporteController();
                transporteController.setTransporteCadastro(transporte);
                transporteController.excluir();
                viewDadosTransporte.limparCampos();
            }
        }

    }
}
