package plan.igualdad.evaluacion.dto;

import java.io.Serializable;

public class GeneralDataDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String generalDataType;
	private String generalDataDateFrom;
	private String generalDataDateUntil;
	private String generalDataTextarea;
	private String generalDataRadio;

	public String getGeneralDataType() {
		return generalDataType;
	}

	public void setGeneralDataType(String generalDataType) {
		this.generalDataType = generalDataType;
	}

	public String getGeneralDataDateFrom() {
		return generalDataDateFrom;
	}

	public void setGeneralDataDateFrom(String generalDataDateFrom) {
		this.generalDataDateFrom = generalDataDateFrom;
	}

	public String getGeneralDataDateUntil() {
		return generalDataDateUntil;
	}

	public void setGeneralDataDateUntil(String generalDataDateUntil) {
		this.generalDataDateUntil = generalDataDateUntil;
	}

	public String getGeneralDataTextarea() {
		return generalDataTextarea;
	}

	public void setGeneralDataTextarea(String generalDataTextarea) {
		this.generalDataTextarea = generalDataTextarea;
	}

	public String getGeneralDataRadio() {
		return generalDataRadio;
	}

	public void setGeneralDataRadio(String generalDataRadio) {
		this.generalDataRadio = generalDataRadio;
	}

}
