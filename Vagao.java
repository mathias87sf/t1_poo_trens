public class Vagao extends ElementoDeComposicao{
	private int identificador;
	private double capacidadeCarga;
	private Composicao composicao;

	public Vagao(int identificador, double capacidadeCarga) {
		super();
		this.identificador = identificador;
		this.capacidadeCarga = capacidadeCarga;
		this.composicao = null;
	}

	@Override
	public int getIdentificador() {
		return identificador;
	}

	@Override
	public double getCapacidade() { //retorna o peso maximo suportado pelo vagao
		return capacidadeCarga;
	}

	public void setCapacidade(double peso){
		this.capacidadeCarga = peso;
	}

	@Override
	public Composicao getComposicao() {
		return composicao;
	}

	@Override
	public void setComposicao(Composicao composicao) {
		this.composicao = composicao;
	}

	public boolean livre(){
		return composicao == null;
	}

	@Override
	public String toString() {
		if (livre()){
			return "Vagao [LIVRE, capacidadeCarga=" + capacidadeCarga + ", identificador=" + identificador + "]";
		}
		return "Vagao [Engatado na composicao: "+composicao.getIdentificador()+", capacidadeCarga=" + capacidadeCarga + ", identificador="+ identificador + "]";
	}
}
