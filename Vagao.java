public class Vagao {
	private int identificador;
	private double capacidadeCarga;
	private Composicao composicao;

	public Vagao(int identificador, double capacidadeCarga) {
		this.identificador = identificador;
		this.capacidadeCarga = capacidadeCarga;
		this.composicao = null;
	}

	public int getIdentificador() {
		return identificador;
	}

	public double getCapacidadeCarga() {
		return capacidadeCarga;
	}

	public Composicao getComposicao() {
		return composicao;
	}

	public boolean livre(){
		return composicao == null;
	}

	public void setComposicao(Composicao composicao) {
		this.composicao = composicao;
	}

	@Override
	public String toString() {
		if (livre()){
			return "Vagao [LIVRE, capacidadeCarga=" + capacidadeCarga + ", identificador=" + identificador + "]";
		}
		return "Vagao [Engatado na composicao: "+composicao.getIdentificador()+", capacidadeCarga=" + capacidadeCarga + ", identificador="+ identificador + "]";
	}
}
