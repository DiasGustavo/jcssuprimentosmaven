/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcssuprimentosmaven.controller;

import java.util.Calendar;
import java.util.List;
import jcssuprimentosmaven.dao.RodadaDAO;
import jcssuprimentosmaven.util.ViewUtil;
import jcssuprimentosmaven.domain.Rodada;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Gustavo
 */
public class AvisoController {

    private Rodada rodada;
    private String mediaDemanda;
    private String variacaoRodada;
    private String situacaoRodada;

    private Long codigo;

    public Rodada getRodada() {
        return rodada;
    }

    public void setRodada(Rodada rodada) {
        this.rodada = rodada;
    }

    public String getMediaDemanda() {
        return mediaDemanda;
    }

    public void setMediaDemanda(String mediaDemanda) {
        this.mediaDemanda = mediaDemanda;
    }

    public String getVariacaoRodada() {
        return variacaoRodada;
    }

    public void setVariacaoRodada(String variacaoRodada) {
        this.variacaoRodada = variacaoRodada;
    }

    public String getSituacaoRodada() {
        return situacaoRodada;
    }

    public void setSituacaoRodada(String situacaoRodada) {
        this.situacaoRodada = situacaoRodada;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public void carregarDados() {
        try {
            if (codigo != null) {
                RodadaDAO rdao = new RodadaDAO();
                rodada = rdao.buscarPorCodigo(codigo);
            } else {
                rodada = new Rodada();
            }
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu erro ao carregar os dados dos avisos! " + ex.getMessage());
        }
    }

    public Long CodigoRecenteRodada() {
        Long ultima = null;
        List rodadas;
        try {
            RodadaDAO rdao = new RodadaDAO();
            rodadas = rdao.listar();
            Rodada rodadaTemp = (Rodada) rodadas.get(rodadas.size() - 1);
            ultima = rodadaTemp.getId();
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu erro ao carregar os dados dos avisos! " + ex.getMessage());
        }
        return ultima;
    }

    public Rodada penultimaRodada() {
        Rodada penultima = null;
        List rodadas;
        try {
            RodadaDAO rdao = new RodadaDAO();
            rodadas = rdao.listar();
            Rodada rodadaTemp = (Rodada) rodadas.get(rodadas.size() - 2);
            penultima = rodadaTemp;
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu erro ao carregar os dados dos avisos! " + ex.getMessage());
        }
        return penultima;
    }
    
    public Rodada ultimaRodada() {
        Rodada ultima = null;
        List rodadas;
        try {
            RodadaDAO rdao = new RodadaDAO();
            rodadas = rdao.listar();
            Rodada rodadaTemp = (Rodada) rodadas.get(rodadas.size() - 1);
            ultima = rodadaTemp;
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu erro ao carregar os dados dos avisos! " + ex.getMessage());
        }
        return ultima;
    }

    public Long mediaDemanda() {
        Long media = null;
        List rodadas;
        try {
            RodadaDAO rdao = new RodadaDAO();
            rodadas = rdao.listar();
            Long valor = 0L;
            for (int i = 0; i < rodadas.size(); i++) {
                Rodada rodadaTemp = (Rodada) rodadas.get(i);
                valor = valor + Long.parseLong(rodadaTemp.getDemanda());
            }
            media = valor / rodadas.size();
        } catch (RuntimeException ex) {
            ViewUtil.addMsgErro("Ocorreu erro ao carregar os dados dos avisos! " + ex.getMessage());
        }
        return media;
    }

    public Date getPegaDataAtual() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        return calendar.getTime();
    }
    
    public boolean isRodadaExpirada(){
        boolean expirada = false;
        
        return expirada;
    }
}
