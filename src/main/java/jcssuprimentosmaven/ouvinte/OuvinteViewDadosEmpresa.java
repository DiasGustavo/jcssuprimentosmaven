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
import jcssuprimentosmaven.controller.ArmazemDistribuicaoController;
import jcssuprimentosmaven.controller.ArmazemSuprimentoController;
import jcssuprimentosmaven.controller.EmpresaController;
import jcssuprimentosmaven.controller.FabricaController;
import jcssuprimentosmaven.domain.ArmazemDistribuicao;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.domain.ArmazemSuprimento;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewDadosEmpresa;

/**
 * Data de Criação: 01/05/2018
 * Data da última modificação: 24/10/2018
 * Observação: 
 * @author Gustavo
 */
public class OuvinteViewDadosEmpresa {

    ViewDadosEmpresa viewDadosEmpresa;
    Jogador usuario;

    public OuvinteViewDadosEmpresa(ViewDadosEmpresa view, Jogador user) {
        this.viewDadosEmpresa = view;
        this.usuario = user;
        viewDadosEmpresa.bGravarAddActionListener(new OuvinteGravarEmpresa(usuario));
        viewDadosEmpresa.bEditarAddActionListener(new OuvinteEditarEmpresa());
        viewDadosEmpresa.bExcluirAddActionListener(new OuvinteExcluirEmpresa());
    }

    class OuvinteGravarEmpresa implements ActionListener {
        
        Jogador userUtilizado;
        
        OuvinteGravarEmpresa(Jogador usuarioTest){
            this.userUtilizado = usuarioTest;
        }
        /**
         * Cadastra a empresa, a fábrica da empresa, armazém de distribuição e 
         * armazém  suprimento
         * @param ActionEvent 
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Empresa empresa = viewDadosEmpresa.preencherEmpresa();
            if (!viewDadosEmpresa.getErro()) {
                //cria a empresa propriamente dita
                EmpresaController empresaController = new EmpresaController();
                empresaController.setEmpresaCadastro(empresa);
                Long id = empresaController.salvarSemAviso();
                               
                empresaController.setCodigo(id);
                empresaController.carregarDados();                
                if (empresaController.getEmpresaCadastro() != null && empresaController.getEmpresaCadastro().getId() != 1 ) {
                    Empresa empresaFK = empresaController.getEmpresaCadastro();
                    
                    //criar a fábrica do jogador
                    FabricaController fabricaController = new FabricaController();
                    fabricaController.setCodigo(1L);
                    fabricaController.carregarDados();
                    Fabrica fabricaPadrao = fabricaController.getArmazemCadastro();
                    Fabrica fabricaNova = new Fabrica();
                    fabricaNova.setNomeFantasia("Fabrica- " + empresa.getNomeFantasia());
                    fabricaNova.setTempoTransformacao(fabricaPadrao.getTempoTransformacao());
                    fabricaNova.setCustoTransformacao(fabricaPadrao.getCustoTransformacao());
                    fabricaNova.setFatorTransformacao(fabricaPadrao.getFatorTransformacao());
                    fabricaNova.setTaxaServicoCliente(fabricaPadrao.getTaxaServicoCliente());
                    fabricaNova.setPesquisa(fabricaPadrao.getPesquisa());
                    fabricaNova.setDemanda(fabricaPadrao.getDemanda());
                    fabricaNova.setEmpresa(empresaController.getEmpresaCadastro());
                    fabricaController.setArmazemCadastro(fabricaNova);
                    fabricaController.salvarSemAviso();
                    
                    //cadastrar o Armazém de suprimento da Fábrica
                    ArmazemSuprimentoController armazemSuprimentoController = new ArmazemSuprimentoController();
                    armazemSuprimentoController.setCodigo(1L);
                    armazemSuprimentoController.carregarDados();
                    ArmazemSuprimento armazemSuprimentoPadrao = armazemSuprimentoController.getArmazemCadastro();
                    ArmazemSuprimento armazemSuprimentoNovo = new ArmazemSuprimento();
                    armazemSuprimentoNovo.setNomeFantasia("Armazém Suprimento - " + empresa.getNomeFantasia());
                    armazemSuprimentoNovo.setTaxaDiaria(armazemSuprimentoPadrao.getTaxaDiaria());
                    armazemSuprimentoNovo.setTaxaSeguro(armazemSuprimentoPadrao.getTaxaSeguro());
                    List fabricas = fabricaController.buscarPorEmpresa(empresaFK);
                    Fabrica fabricaFK = (Fabrica) fabricas.get(0);
                    armazemSuprimentoNovo.setFabrica(fabricaFK);
                    armazemSuprimentoController.setArmazemCadastro(armazemSuprimentoNovo);
                    armazemSuprimentoController.salvarSemAviso();
                    
                    //Cadastrar o Armazém de distribuição da Fábrica
                    ArmazemDistribuicaoController armazemDistribuicaoController = new ArmazemDistribuicaoController();
                    armazemDistribuicaoController.setCodigo(1L);
                    armazemDistribuicaoController.carregarDados();
                    ArmazemDistribuicao armazemDistribuicaoPadrao = armazemDistribuicaoController.getArmazemCadastro();
                    ArmazemDistribuicao armazemDistribuicaoNovo = new ArmazemDistribuicao();
                    armazemDistribuicaoNovo.setNomeFantasia("Armazém Distribuição - " + empresa.getNomeFantasia());
                    armazemDistribuicaoNovo.setTaxaDiaria(armazemDistribuicaoPadrao.getTaxaDiaria());
                    armazemDistribuicaoNovo.setTaxaSeguro(armazemDistribuicaoPadrao.getTaxaSeguro());
                    List fabricasDistribuicao = fabricaController.buscarPorEmpresa(empresaFK);
                    Fabrica fabricaDistribuicaoFK = (Fabrica) fabricasDistribuicao.get(0);
                    armazemDistribuicaoNovo.setFabrica(fabricaDistribuicaoFK);
                    armazemDistribuicaoController.setArmazemCadastro(armazemDistribuicaoNovo);
                    armazemDistribuicaoController.salvarSemAviso();
                    
                    //criar as materias-primas
                    /*MateriaPrimaController materiaPrimaController = new MateriaPrimaController();
                    List materias = materiaPrimaController.buscarPorFornecedor(padrao);
                    MateriaPrima materiaCad;
                    List fornecedores = fornecedorController.buscarPorNome("Fornecedor - "+ empresa.getNomeFantasia());
                    Fornecedor fornecedorFK = (Fornecedor) fornecedores.get(0);
                    int i = 0;
                    while (i < materias.size()){
                        materiaCad = (MateriaPrima) materias.get(i);
                        //materiaCad.setFornecedor(fornecedorFK);
                        materiaPrimaController.setMateriaPrimaCadastro(materiaCad);
                        materiaPrimaController.salvarSemAviso();
                        i = i+1;
                    }*/
                            
                    ViewUtil.addMsgInfo("Empresa Cadastrado com sucesso!");
                }else{
                    ViewUtil.addMsgInfo("Empresa Cadastrado com sucesso! \n Cadastre Individualmente os demais módulos: \n"
                            + " Fábrica, \n Armazém de Suprimento, \n Armazém de Distribuição, \n e Distribuidor ");
                }
            } else {
                viewDadosEmpresa.setError(false);
            }
        }

    }

    class OuvinteEditarEmpresa implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Empresa empresa = viewDadosEmpresa.preencherEmpresa();
            if (!viewDadosEmpresa.getErro()) {
                EmpresaController empresaController = new EmpresaController();
                empresaController.setEmpresaCadastro(empresa);
                empresaController.editar();
                empresaController.listar();
            } else {
                viewDadosEmpresa.setError(false);
            }
        }

    }

    class OuvinteExcluirEmpresa implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Empresa empresa = viewDadosEmpresa.getEmpresa();
            StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Empresa abaixo: ");
            mensagem.append("\ncodigo: " + empresa.getId());
            mensagem.append("\nnome: " + empresa.getNomeFantasia());
            int resposta = viewDadosEmpresa.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.OK_OPTION) {
                EmpresaController empresaController = new EmpresaController();
                empresaController.setEmpresaCadastro(empresa);
                empresaController.excluir();
                viewDadosEmpresa.limparCampos();
            }
        }

    }
}
