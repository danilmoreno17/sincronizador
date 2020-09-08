package com.proffline.sincronizador.bean;

public class BancoPromesa {

    private String idBancoPromesa;
    private String descripcionBancoPromesa;
    private String tipoRecaudo;
    private String depApps;
    
    public String toString() {
        return this.descripcionBancoPromesa;
    }

    public String getIdBancoPromesa() {
        return idBancoPromesa;
    }

    public void setIdBancoPromesa(String idBancoPromesa) {
        this.idBancoPromesa = idBancoPromesa;
    }

    public String getDescripcionBancoPromesa() {
        return descripcionBancoPromesa;
    }

    public void setDescripcionBancoPromesa(String descripcionBancoPromesa) {
        this.descripcionBancoPromesa = descripcionBancoPromesa;
    }

    public String getTipoRecaudo() {
        return tipoRecaudo;
    }

    public void setTipoRecaudo(String tipoRecaudo) {
        this.tipoRecaudo = tipoRecaudo;
    }

	public String getDepApps() {
		return depApps;
	}

	public void setDepApps(String depApps) {
		this.depApps = depApps;
	}

}
