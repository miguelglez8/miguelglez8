package com.plan.igualdad.liferay.generar.informe.dto;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class GraphicDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private BufferedImage graphic;
	private Boolean haveGraphic;
	private String textNoGraphic;

	public BufferedImage getGraphic() {
		return graphic;
	}

	public void setGraphic(BufferedImage graphic) {
		this.graphic = graphic;
	}

	public Boolean getHaveGraphic() {
		return haveGraphic;
	}

	public void setHaveGraphic(Boolean haveGraphic) {
		this.haveGraphic = haveGraphic;
	}

	public String getTextNoGraphic() {
		return textNoGraphic;
	}

	public void setTextNoGraphic(String textNoGraphic) {
		this.textNoGraphic = textNoGraphic;
	}

}
