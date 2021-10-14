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

	public int getIdentificador() {
		return identificador;
	}

	//redundancia
	public double getCapacidade() { //retorna o peso maximo suportado pelo vagao
		return capacidadeCarga;
	}

	//redundancia
	public void setCapacidade(double peso){
		this.capacidadeCarga = peso;
	}

	public Composicao getComposicao() {
		return composicao;
	}

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
