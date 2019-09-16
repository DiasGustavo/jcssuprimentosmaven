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
public enum StatusEnum {
        Ativo("Ativo"),Inativo("Inativo");
	
	private String status;
	
	private StatusEnum (String status){
		this.status = status;
	}
	
	public String getStatus(){
		return this.status;
	}
}
