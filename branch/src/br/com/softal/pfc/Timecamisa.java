package br.com.softal.pfc;
 
@SuppressWarnings("serial")
public class Timecamisa extends Entidade {
 
    
	public static final int TIME_CAMISA_A = 1;
	public static final int TIME_CAMISA_B = 2;
	
	private TimecamisaPK timecamisaPK;
    private String nmTime;
    private Integer flForauso;
 
    public Timecamisa() {
        setTimecamisaPK(new TimecamisaPK());
    }
 
    public TimecamisaPK getTimecamisaPK() {
        return this.timecamisaPK;
    }
 
    public void setTimecamisaPK(TimecamisaPK newtimecamisaPK) {
        this.timecamisaPK = newtimecamisaPK;
    }
 
    public String getNmTime() {
        return this.nmTime;
    }
 
    public void setNmTime(String newNmTime) {
        this.nmTime = newNmTime;
    }
 
    public Integer getFlForauso() {
        return this.flForauso;
    }
 
    public void setFlForauso(Integer newFlForauso) {
        this.flForauso = newFlForauso;
    }
    
    @Override
    public EntidadePK<Timecamisa> getPK() {
    	return getTimecamisaPK();
    }
    
}
