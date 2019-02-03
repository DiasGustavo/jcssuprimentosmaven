/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;

import java.util.List;
import jcssuprimentosmaven.dao.EstoqueSuprimentoDAO;
import jcssuprimentosmaven.domain.ArmazemSuprimento;
import jcssuprimentosmaven.domain.EstoqueSuprimento;
import jcssuprimentosmaven.domain.MateriaPrima;
import jcssuprimentosmaven.util.ViewUtil;

/**
 *
 * @author Gustavo
 */
public class EstoqueSuprimentoController {

    private EstoqueSuprimento estoqueCadastro;

    private List<EstoqueSuprimento> listaEstoques;
    private List<EstoqueSuprimento> listaEstoquesFiltrados;

    private Long codigo;

    public EstoqueSuprimento getEstoqueCadastro() {
        return estoqueCadastro;
    }

    public void setEstoqueCadastro(EstoqueSuprimento estoqueCadastro) {
        this.estoqueCadastro = estoqueCadastro;
    }

    public List<EstoqueSuprimento> getListaEstoques() {
        return listaEstoques;
    }

    public void setListaEstoques(List<EstoqueSuprimento> listaEstoques) {
        this.listaEstoques = listaEstoques;
    }

    public List<EstoqueSuprimento> getListaEstoquesFiltrados() {
        return listaEstoquesFiltrados;
    }

    public void setListaEstoquesFiltrados(List<EstoqueSuprimento> listaEstoquesFiltrados) {
        this.listaEstoquesFiltrados = listaEstoquesFiltrados;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public void salvar() {
        try {
            EstoqueSuprimentoDAO esdao = new EstoqueSuprimentoDAO();
            esdao.salvar(estoqueCadastro);

            ViewUtil.addMsgInfo("Estoque Cadastrado com Sucesso!");
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao cadastrar o estoque " + ex.getMessage());
        }
    }

    public void listar() {
        try {
            EstoqueSuprimentoDAO esdao = new EstoqueSuprimentoDAO();
            listaEstoques = esdao.listar();

        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao listar os estoques " + ex.getMessage());
        }
    }

    public List<EstoqueSuprimento> buscarPorArmazem(ArmazemSuprimento armazem) {
        List<EstoqueSuprimento> estoques = null;
        try {
            EstoqueSuprimentoDAO esdao = new EstoqueSuprimentoDAO();
            estoques = esdao.buscarPorArmazem(armazem);
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o estoque " + ex.getMessage());
        }
        return estoques;
    }
    
     public List<EstoqueSuprimento> buscarPorMateriaArmazem(MateriaPrima materiaPrima, ArmazemSuprimento armazem) {
        List<EstoqueSuprimento> estoques = null;
        try {
            EstoqueSuprimentoDAO esdao = new EstoqueSuprimentoDAO();
            estoques = esdao.buscarPorMateriaArmazem(materiaPrima, armazem);
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o estoque " + ex.getMessage());
        }
        return estoques;
    }
    
    /*public List<EstoqueSuprimento> buscarPorEmpresaEstoque(Empresa empresa, String estoque) {
        List<EstoqueSuprimento> estoques = null;
        try {
            EstoqueSuprimentoDAO esdao = new EstoqueSuprimentoDAO();
            estoques = esdao.buscarPorEmpresaEstoque(empresa,estoque);
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o estoque " + ex.getMessage());
        }
        return estoques;
    }
    
    public List<EstoqueSuprimento> buscarPorNomeEmpresa(String nomeInformado) {
        List<EstoqueSuprimento> estoques = null;
        try {
            EstoqueSuprimentoDAO esdao = new EstoqueSuprimentoDAO();
            estoques = esdao.buscarPorNomeEmpresa(nomeInformado);
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar o estoque " + ex.getMessage());
        }
        return estoques;
    }*/

    public void carregarDados() {
        try {
            if (codigo != null) {
                EstoqueSuprimentoDAO esdao = new EstoqueSuprimentoDAO();
                estoqueCadastro = esdao.buscarPorCodigo(codigo);
            } else {
                estoqueCadastro = new EstoqueSuprimento();
            }
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu ao carregar os dados do estoque " + ex.getMessage());
        }
    }

    public void editar() {
        try {
            EstoqueSuprimentoDAO esdao = new EstoqueSuprimentoDAO();
            esdao.editar(estoqueCadastro);

            ViewUtil.addMsgInfo("Estoque Editado com Sucesso!");
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao editar o estoque " + ex.getMessage());
        }
    }

    public void excluir() {
        try {
            EstoqueSuprimentoDAO esdao = new EstoqueSuprimentoDAO();
            esdao.excluir(estoqueCadastro);

            ViewUtil.addMsgInfo("Estoque Excluído com Sucesso!");
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu um erro ao excluir o estoque " + ex.getMessage());
        }
    }
}
