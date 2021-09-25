public class Locomotiva {
	private int identificador;
	private double pesoMaximo;
	private double pesoMaximoInicial;
	private double qtdadeMaxVagoes;
	private double qtdadeMaxVagoesInicial;
	private Composicao composicao;


	public Locomotiva(int identificador, double pesoMaximo, int qtdadeVagoes) { 
		this.identificador = identificador;
		this.pesoMaximo = pesoMaximo;
		this.pesoMaximoInicial = pesoMaximo;
		this.qtdadeMaxVagoes = qtdadeVagoes;			//sim, o parâmetro é inteiro mas o destino é double para evitar erros de cálculo ao reduzir e repor as capacidades
		this.qtdadeMaxVagoesInicial = qtdadeVagoes;		
		this.composicao = null;
	}

	public int getIdentificador() {
		return identificador;
	}

	public double getPesoMaximo() {
		return pesoMaximo;
	}

	public void setPesoMaximo(double peso){
		this.pesoMaximo = peso;
	}

	public double getPesoMaximoInicial(){
		return pesoMaximoInicial;
	}

	public double getQtdadeMaxVagoes() {
		return qtdadeMaxVagoes;
	}

	public void setMaxVagoes(double max){
		this.qtdadeMaxVagoes = max;
	}

	public double getQtdadeMaxVagoesInicial() {
		return qtdadeMaxVagoesInicial;
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
			return "Locomotiva [LIVRE, identificador=" + identificador + ", pesoMaximo="
			+ pesoMaximo + ", qtdadeMaxVagoes=" + (int)qtdadeMaxVagoes + "]"; //exibe apenas a quantidade inteira (int)
		}
		return "Locomotiva [Engatada na composicao=" + composicao.getIdentificador() + ", identificador=" + identificador + ", pesoMaximo="
			+ pesoMaximo + ", qtdadeMaxVagoes=" + (int)qtdadeMaxVagoes + "]";
	}
}
