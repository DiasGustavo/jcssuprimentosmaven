/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.constantes;

/**
 *
 * @author Gustavo
 */
public enum FuncaoEnum {
        Participante("Participante"),Juiz("Juiz");
	
	private String funcao;
	
	private FuncaoEnum (String funcao){
		this.funcao = funcao;
	}
	
	public String getFuncao(){
		return this.funcao;
	}
}
