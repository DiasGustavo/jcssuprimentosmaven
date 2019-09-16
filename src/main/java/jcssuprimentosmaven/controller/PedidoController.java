/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;
import java.util.List;
import jcssuprimentosmaven.dao.PedidoDAO;
import jcssuprimentosmaven.domain.Pedido;
import jcssuprimentosmaven.domain.ArmazemSuprimento;
import jcssuprimentosmaven.util.ViewUtil;
/**
 *
 * @author Gustavo
 */
public class PedidoController {
    private Pedido pedidoCadastro;
    
    private List<Pedido> listaPedidos;
    private List<Pedido> listaPedidosFiltrados;
    
    private Long codigo;

    public Pedido getPedidoCadastro() {
        return pedidoCadastro;
    }

    public void setPedidoCadastro(Pedido pedidoCadastro) {
        this.pedidoCadastro = pedidoCadastro;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public List<Pedido> getListaPedidosFiltrados() {
        return listaPedidosFiltrados;
    }

    public void setListaPedidosFiltrados(List<Pedido> listaPedidosFiltrados) {
        this.listaPedidosFiltrados = listaPedidosFiltrados;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public void salvar(){
        try{
            PedidoDAO pdao = new PedidoDAO();
            pdao.salvar(pedidoCadastro);
            
            ViewUtil.addMsgInfo("Pedido cadastrado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao cadastrar o pedido! " + ex.getMessage());
        }
    }
    
    public void listar(){
        try{
            PedidoDAO pdao = new PedidoDAO();
            listaPedidos = pdao.listar();
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao listar os pedidos " + ex.getMessage());
        }
    }
    
    public List<Pedido> buscarPorArmazemSuprimento(ArmazemSuprimento armazem){
        List<Pedido> pedidos = null;
        try{
            PedidoDAO pdao = new PedidoDAO();
            pedidos = pdao.buscarPorArmazemSuprimento(armazem);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar os pedidos " + ex.getMessage());
        }
        return pedidos;
    }
    
    public List<Pedido> buscarPorArmazemSuprimentoStatus(ArmazemSuprimento armazem){
        List<Pedido> pedidos = null;
        try{
            PedidoDAO pdao = new PedidoDAO();
            pedidos = pdao.buscarPorArmazemSuprimentoStatus(armazem);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar os pedidos " + ex.getMessage());
        }
        return pedidos;
    }
    
    public List<Pedido> buscarPedidoPorCodigo(Long codigo){
        List<Pedido> pedidos = null;
        try{
            PedidoDAO pdao = new PedidoDAO();
            pedidos = pdao.buscarPeidoPorCodigo(codigo);
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao pesquisar os pedidos " + ex.getMessage());
        }
        return pedidos;
    }
       
    
    public void carregarDados(){
        try{
            if(codigo != null){
                PedidoDAO pdao = new PedidoDAO();
                pedidoCadastro = pdao.buscarPorCodigo(codigo);
            }else{
                pedidoCadastro = new Pedido();
            }
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao carregar os dados do Pedido " + ex.getMessage());
        }
    }
    
    public void editar(){
        try{
            PedidoDAO pdao = new PedidoDAO();
            pdao.editar(pedidoCadastro);
            
            ViewUtil.addMsgInfo("Pedido editado com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao editar o pedido! " + ex.getMessage());
        }
    }
    
    public void excluir(){
        try{
            PedidoDAO pdao = new PedidoDAO();
            pdao.excluir(pedidoCadastro);
            
            ViewUtil.addMsgInfo("Pedido excluído com sucesso!");
        }catch(RuntimeException ex){
            ViewUtil.addMsgErro("Ocorreu um erro ao excluir o pedido! " + ex.getMessage());
        }
    }
}
