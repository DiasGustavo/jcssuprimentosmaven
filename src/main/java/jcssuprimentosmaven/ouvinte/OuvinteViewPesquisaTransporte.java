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
import jcssuprimentosmaven.controller.ArmazemFabricaController;
import jcssuprimentosmaven.controller.ArmazemSuprimentoController;
import jcssuprimentosmaven.controller.EmpresaController;
import jcssuprimentosmaven.controller.EstoqueSuprimentoController;
import jcssuprimentosmaven.controller.FabricaController;
import jcssuprimentosmaven.controller.TransporteController;
import jcssuprimentosmaven.domain.ArmazemFabrica;
import jcssuprimentosmaven.domain.ArmazemSuprimento;
import jcssuprimentosmaven.domain.Empresa;
import jcssuprimentosmaven.domain.EstoqueSuprimento;
import jcssuprimentosmaven.domain.Fabrica;
import jcssuprimentosmaven.domain.Jogador;
import jcssuprimentosmaven.domain.Transporte;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.view.ViewPesquisaTransporte;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewPesquisaTransporte {

    ViewPesquisaTransporte viewPesquisaTransporte;
    Jogador jogador;

    public OuvinteViewPesquisaTransporte(ViewPesquisaTransporte view, Jogador usuario) {
        this.viewPesquisaTransporte = view;
        this.jogador = usuario;
        viewPesquisaTransporte.bPesquisarTodosAddActionListener(new OuvintePesquisarTodasTransporte(this.jogador));
        viewPesquisaTransporte.bPesquisarAddActionListener(new OuvintePesquisarTransporte());
        viewPesquisaTransporte.bExcluirAddActionListener(new OuvinteExcluirTransporte());
        viewPesquisaTransporte.bEntregarAddActionListener(new OuvinteEntregarTransporte(this.jogador));
    }

    class OuvintePesquisarTodasTransporte implements ActionListener {

        Jogador jogadorTeste;

        public OuvintePesquisarTodasTransporte(Jogador usuario) {
            this.jogadorTeste = usuario;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (this.jogadorTeste.getFuncao() == 2) {
                    TransporteController transporteController = new TransporteController();
                    transporteController.listar();
                    List transportes = transporteController.getListaTransportes();
                    viewPesquisaTransporte.listar(transportes);
                } else {
                    TransporteController transporteController = new TransporteController();
                    FabricaController fabricaController = new FabricaController();
                    EmpresaController empresaController = new EmpresaController();
                    ArmazemFabricaController armazemFabricaController = new ArmazemFabricaController();
                    List empresas = empresaController.buscarPorJogador(this.jogadorTeste);
                    Empresa empresa = (Empresa) empresas.get(0);
                    //List transportes = transporteController.buscarPorEmpresa(empresa);
                    List fabricas = fabricaController.buscarPorEmpresa(empresa);
                    Fabrica fabrica = (Fabrica) fabricas.get(0);
                    List armazens = armazemFabricaController.buscarPorFabrica(fabrica);
                    ArmazemFabrica armazem = (ArmazemFabrica) armazens.get(0);
                    List transportes = transporteController.buscarPorArmazemFabricaStatus(armazem, "Pendente");

                    if (transportes.size() > 0) {
                        viewPesquisaTransporte.listar(transportes);
                    } else {
                        ViewUtil.addMsgInfo("Não existe Transportes para esta empresa a listar ");
                    }
                }
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Não existe Transportes para listar " + ex.getMessage());
            }
        }

    }

    class OuvintePesquisarTransporte implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                TransporteController transporteController = new TransporteController();
                String nome = viewPesquisaTransporte.getNomePesquisar();
                List transportes;
                transportes = transporteController.buscarPorDestino(nome);
                viewPesquisaTransporte.listar(transportes);
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Erro ao pesquisar as transportes " + ex.getMessage());
            }
        }

    }

    class OuvinteExcluirTransporte implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Transporte transporte = viewPesquisaTransporte.getTransporte();
                StringBuffer mensagem = new StringBuffer("Confirma a Exclusão da Transporte abaixo: ");
                mensagem.append("\ncodigo: " + transporte.getId());
                mensagem.append("\n:valor" + transporte.getValor().toEngineeringString());
                int resposta = viewPesquisaTransporte.pedirConfirmacao(mensagem.toString(), "Exclusão de Registro", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.OK_OPTION) {
                    TransporteController transporteController = new TransporteController();
                    transporteController.setTransporteCadastro(transporte);
                    transporteController.excluir();
                    transporteController.listar();
                    List transportes = transporteController.getListaTransportes();
                    viewPesquisaTransporte.listar(transportes);
                }
            } catch (RuntimeException ex) {

            }
        }

    }

    class OuvinteEntregarTransporte implements ActionListener {

        Jogador jogador;

        public OuvinteEntregarTransporte(Jogador jogador) {
            this.jogador = jogador;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //capturar a fábrica da empresa do usuário
                FabricaController fabricaController = new FabricaController();
                EmpresaController empresaController = new EmpresaController();
                ArmazemFabricaController armazemFabricaController = new ArmazemFabricaController();
                List empresas = empresaController.buscarPorJogador(this.jogador);
                Empresa empresa = (Empresa) empresas.get(0);
                //List transportes = transporteController.buscarPorEmpresa(empresa);
                List fabricas = fabricaController.buscarPorEmpresa(empresa);
                Fabrica fabrica = (Fabrica) fabricas.get(0);

                //gravar a materia prima no armazem da fabrica
                Transporte transporte = viewPesquisaTransporte.getTransporte();
                TransporteController transporteController = new TransporteController();
                transporte.setStatus("Entregue");
                transporteController.setTransporteCadastro(transporte);
                transporteController.editar();
                List materias = armazemFabricaController.buscarPorFabricaMateriaPrima(fabrica, transporte.getMateriaPrima());
                ArmazemFabrica armazemFabrica;
                if (materias.size() > 0) {
                    armazemFabrica = (ArmazemFabrica) materias.get(0);
                    armazemFabrica.setQuantidade(armazemFabrica.getQuantidade().add(transporte.getQuantidade()));
                    armazemFabricaController.setArmazemCadastro(armazemFabrica);
                    armazemFabricaController.editar();
                } else {
                    armazemFabrica = new ArmazemFabrica();
                    armazemFabrica.setFabrica(fabrica);
                    armazemFabrica.setMateriaPrima(transporte.getMateriaPrima());
                    armazemFabrica.setQuantidade(transporte.getQuantidade());
                    armazemFabricaController.setArmazemCadastro(armazemFabrica);
                    armazemFabricaController.salvar();
                }

                //atualizar o estoque suprimento
                ArmazemSuprimentoController armazemSuprimentoController = new ArmazemSuprimentoController();
                List armazens = armazemSuprimentoController.buscarPorFabrica(fabrica);
                ArmazemSuprimento armazem = (ArmazemSuprimento) armazens.get(0);

                EstoqueSuprimentoController estoqueSuprimentoController = new EstoqueSuprimentoController();
                List estoques = estoqueSuprimentoController.buscarPorMateriaArmazem(transporte.getMateriaPrima(), armazem);
                EstoqueSuprimento estoque = (EstoqueSuprimento) estoques.get(0);
                estoque.setQuantidade(estoque.getQuantidade().subtract(transporte.getQuantidade()));
                estoqueSuprimentoController.setEstoqueCadastro(estoque);
                estoqueSuprimentoController.editar();
                
                empresa.setCapitalAtual(empresa.getCapitalAtual().subtract(transporte.getValor()));
                empresaController.setEmpresaCadastro(empresa);
                empresaController.editar();
                
                if (this.jogador.getFuncao() == 2) {
                    transporteController.listar();
                    viewPesquisaTransporte.listar(transporteController.getListaTransportes());
                } else {
                    transporteController.buscarPorArmazemFabricaStatus(armazemFabrica, "Pendente");
                    viewPesquisaTransporte.listar(transporteController.getListaTransportes());
                }
                
                
            } catch (RuntimeException ex) {
                ViewUtil.addMsgErro("Ocorreu um erro ao transportar o estoque." + ex.getMessage());
            }
        }

    }

}
