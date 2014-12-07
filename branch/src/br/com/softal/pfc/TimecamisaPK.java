package br.com.softal.pfc;
 
@SuppressWarnings("serial")
public class TimecamisaPK extends EntidadePK<Timecamisa> {
 
    private Integer cdTime;
 
    public Integer getCdTime() {
        return this.cdTime;
    }
 
    public void setCdTime(Integer newCdTime) {
        this.cdTime = newCdTime;
    }
 
}
