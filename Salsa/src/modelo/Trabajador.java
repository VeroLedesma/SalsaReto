package modelo;

public class Trabajador extends Persona {

	private String nnss;
	private Boolean encargado;

	public String getNnss() {
		return nnss;
	}

	public void setNnss(String nnss) {
		this.nnss = nnss;
	}

	public Boolean isEncargado() {
		return encargado;
	}

	public void setEncargado(Boolean encargado) {
		this.encargado = encargado;
	}

}
