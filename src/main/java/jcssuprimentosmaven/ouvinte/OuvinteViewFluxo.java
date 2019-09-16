/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.ouvinte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import jcssuprimentosmaven.view.ViewDadosFornecedor;
import jcssuprimentosmaven.view.ViewFluxo;

/**
 *
 * @author Gustavo
 */
public class OuvinteViewFluxo {
    private ViewFluxo viewFluxo;
    private ViewDadosFornecedor viewDadosFornecedor;
    
    public OuvinteViewFluxo (ViewFluxo view){
        this.viewFluxo = view;
        //viewFluxo.bFornecedorAddActionListener(new OuvinteFornecedor());
    }
    
    class OuvinteFornecedor implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            viewDadosFornecedor = new ViewDadosFornecedor();
            OuvinteViewDadosFornecedor ouvinte = new OuvinteViewDadosFornecedor(viewDadosFornecedor);
            viewFluxo.getParent().add(viewDadosFornecedor);
            viewDadosFornecedor.setVisible(true);
            viewDadosFornecedor.setPosicao();
        }
        
    }
}
