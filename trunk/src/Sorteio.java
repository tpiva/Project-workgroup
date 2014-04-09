import java.util.Date;

/** Classe que representa um Sorteio da Mega Sena*/
public class Sorteio {
	
	public Integer numeroSorteio;
	
	public Boolean acumulado;
	
	public Date dataSorteio;
	
	public Integer dezenaUm;
	public Integer dezenaDois;
	public Integer dezenaTres;
	public Integer dezenaQuarto;
	public Integer dezenaCinco;
	public Integer dezenaSeis;
	
	public Double arrecadacaoTotal;
	
	public Integer ganhadoresMega;	
	public Integer ganhadoresQuina;	
	public Integer ganhadoresQuadra;

	public Double rateioMega;	
	public Double rateioQuina;	
	public Double rateioQuadra;
		
	public Double valorAcumulado;	
	public Double estimativaPremio;	
	
	
	public Integer getNumeroSorteio() {
		return numeroSorteio;
	}
	public void setNumeroSorteio(Integer numeroSorteio) {
		this.numeroSorteio = numeroSorteio;
	}
	public Boolean getAcumulado() {
		return acumulado;
	}
	public void setAcumulado(Boolean acumulado) {
		this.acumulado = acumulado;
	}
	public Date getDataSorteio() {
		return dataSorteio;
	}
	public void setDataSorteio(Date dataSorteio) {
		this.dataSorteio = dataSorteio;
	}
	public Integer getDezenaUm() {
		return dezenaUm;
	}
	public void setDezenaUm(Integer dezenaUm) {
		this.dezenaUm = dezenaUm;
	}
	public Integer getDezenaDois() {
		return dezenaDois;
	}
	public void setDezenaDois(Integer dezenaDois) {
		this.dezenaDois = dezenaDois;
	}
	public Integer getDezenaTres() {
		return dezenaTres;
	}
	public void setDezenaTres(Integer dezenaTres) {
		this.dezenaTres = dezenaTres;
	}
	public Integer getDezenaQuarto() {
		return dezenaQuarto;
	}
	public void setDezenaQuatro(Integer dezenaQuatro) {
		this.dezenaQuarto = dezenaQuatro;
	}
	public Integer getDezenaCinco() {
		return dezenaCinco;
	}
	public void setDezenaCinco(Integer dezenaCinco) {
		this.dezenaCinco = dezenaCinco;
	}
	public Integer getDezenaSeis() {
		return dezenaSeis;
	}
	public void setDezenaSeis(Integer dezenaSeis) {
		this.dezenaSeis = dezenaSeis;
	}
	public Double getArrecadacaoTotal() {
		return arrecadacaoTotal;
	}
	public void setArrecadacaoTotal(Double arrecadacaoTotal) {
		this.arrecadacaoTotal = arrecadacaoTotal;
	}
	public Integer getGanhadoresMega() {
		return ganhadoresMega;
	}
	public void setGanhadoresMega(Integer ganhadoresMega) {
		this.ganhadoresMega = ganhadoresMega;
	}
	public Integer getGanhadoresQuina() {
		return ganhadoresQuina;
	}
	public void setGanhadoresQuina(Integer ganhadoresQuina) {
		this.ganhadoresQuina = ganhadoresQuina;
	}
	public Integer getGanhadoresQuadra() {
		return ganhadoresQuadra;
	}
	public void setGanhadoresQuadra(Integer ganhadoresQuadra) {
		this.ganhadoresQuadra = ganhadoresQuadra;
	}
	public Double getRateioMega() {
		return rateioMega;
	}
	public void setRateioMega(Double rateioMega) {
		this.rateioMega = rateioMega;
	}
	public Double getRateioQuina() {
		return rateioQuina;
	}
	public void setRateioQuina(Double rateioQuina) {
		this.rateioQuina = rateioQuina;
	}
	public Double getRateioQuadra() {
		return rateioQuadra;
	}
	public void setRateioQuadra(Double rateioQuadra) {
		this.rateioQuadra = rateioQuadra;
	}
	public Double getValorAcumulado() {
		return valorAcumulado;
	}
	public void setValorAcumulado(Double valorAcumulado) {
		this.valorAcumulado = valorAcumulado;
	}
	public Double getEstimativaPremio() {
		return estimativaPremio;
	}
	public void setEstimativaPremio(Double estimativaPremio) {
		this.estimativaPremio = estimativaPremio;
	}
	public Double getAcumuladoMegaVirada() {
		return acumuladoMegaVirada;
	}
	public void setAcumuladoMegaVirada(Double acumuladoMegaVirada) {
		this.acumuladoMegaVirada = acumuladoMegaVirada;
	}
	public Double acumuladoMegaVirada;
	
	@Override
	public String toString() {
		
		return "Sorteio nº = " + this.getNumeroSorteio() + " / Acumulado = " + (this.getAcumulado() ? "Sim" : "Não")
				+ " / Data = " + Utils.dateToString(this.getDataSorteio())
				+ " / Dezenas = " + this.getDezenaUm() + "-" + this.getDezenaDois()+ "-" + this.getDezenaTres()+ "-"
				+ this.getDezenaQuarto()+ "-" + this.getDezenaCinco()+ "-" + this.getDezenaSeis()
				+ " / Arrecadação Total = "	+ Utils.formataDoubleMoeda(this.getArrecadacaoTotal())
				+ " / Ganhadores Mega = " + this.getGanhadoresMega() + " / Rateio Mega = " + Utils.formataDoubleMoeda(this.getRateioMega()) 
				+ " / Ganhadores Quinta = " + this.getGanhadoresQuina() + " / Rateio Quina = " + Utils.formataDoubleMoeda(this.getRateioQuina())
				+ " / Ganhadores Quadra = " + this.getGanhadoresQuadra() + " / Rateio Quadra = " + Utils.formataDoubleMoeda(this.getRateioQuadra())
				+ " / Valor Acumulado = " + Utils.formataDoubleMoeda(this.getValorAcumulado()) + " / Estimativa Premio = " + Utils.formataDoubleMoeda(this.getEstimativaPremio());
	}

}
