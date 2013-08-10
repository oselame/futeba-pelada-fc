package br.com.softal.pfc;

 
@SuppressWarnings("serial")
public class PartidaPK extends EntidadePK<Partida> {
 
    private Integer cdPartida;
 
    public Integer getCdPartida() {
        return this.cdPartida;
    }
 
    public void setCdPartida(Integer newCdPartida) {
        this.cdPartida = newCdPartida;
    }
 
}
