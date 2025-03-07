package plan.igualdad.evaluacion.dto;

import java.io.Serializable;

public class PlanIgualdadEvaluacionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long evaluationId;
	private String dateEvaluation;
	private Long userId;
	private String userName;
	private Long versionId;
	private GeneralDataDTO datosGenerales;
	private EvaluationResultDTO informacionResultados;
	private EvaluationImplantationDTO informacionImplantacion;
	private EvaluationImpactDTO informacionImpacto;
	private String conclusions;
	private String observations;
	private int versionEvaluacion;

	public Long getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(Long evaluationId) {
		this.evaluationId = evaluationId;
	}

	public String getDateEvaluation() {
		return dateEvaluation;
	}

	public void setDateEvaluation(String dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Long getVersionId() {
		return versionId;
	}

	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}

	public GeneralDataDTO getDatosGenerales() {
		return datosGenerales;
	}

	public void setDatosGenerales(GeneralDataDTO datosGenerales) {
		this.datosGenerales = datosGenerales;
	}

	public EvaluationResultDTO getInformacionResultados() {
		return informacionResultados;
	}

	public void setInformacionResultados(EvaluationResultDTO informacionResultados) {
		this.informacionResultados = informacionResultados;
	}

	public EvaluationImplantationDTO getInformacionImplantacion() {
		return informacionImplantacion;
	}

	public void setInformacionImplantacion(EvaluationImplantationDTO informacionImplantacion) {
		this.informacionImplantacion = informacionImplantacion;
	}

	public EvaluationImpactDTO getInformacionImpacto() {
		return informacionImpacto;
	}

	public void setInformacionImpacto(EvaluationImpactDTO informacionImpacto) {
		this.informacionImpacto = informacionImpacto;
	}

	public String getConclusions() {
		return conclusions;
	}

	public void setConclusions(String conclusions) {
		this.conclusions = conclusions;
	}

	public int getVersionEvaluacion() {
		return versionEvaluacion;
	}

	public void setVersionEvaluacion(int versionEvaluacion) {
		this.versionEvaluacion = versionEvaluacion;
	}

}
