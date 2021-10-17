public class Locomotiva extends ElementoDeComposicao{
	private int identificador;
	private double pesoMaximo;
	private double pesoMaximoInicial;
	private double qtdadeMaxVagoes;
	private double qtdadeMaxVagoesInicial;
	private Composicao composicao;


	public Locomotiva(int identificador, double pesoMaximo, int qtdadeVagoes) { 
		super();
		this.identificador = identificador;
		this.pesoMaximo = pesoMaximo;
		this.pesoMaximoInicial = pesoMaximo;
		this.qtdadeMaxVagoes = qtdadeVagoes;			//sim, o parâmetro é inteiro mas o destino é double para evitar erros de cálculo ao reduzir e repor as capacidades
		this.qtdadeMaxVagoesInicial = qtdadeVagoes;		
		this.composicao = null;
	}

	@Override
	public int getIdentificador() {
		return identificador;
	}

	@Override
	public double getCapacidade() {	//retorna a quantidade maxima de vagoes suportados
		return qtdadeMaxVagoes;
	}

	public void setCapacidade(double qtdadeMaxVagoes){
		this.qtdadeMaxVagoes = qtdadeMaxVagoes;
	}

	public double getPesoMaximo(){	//retorna o peso maximo
		return pesoMaximo;
	}

	public void setPesoMaximo(double peso){
		this.pesoMaximo = peso;
	}

	public double getPesoMaximoInicial(){
		return pesoMaximoInicial;
	}

	public double getQtdadeMaxVagoesInicial() {
		return qtdadeMaxVagoesInicial;
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
			return "Locomotiva [LIVRE, identificador=" + identificador + ", pesoMaximo="
			+ pesoMaximo + ", qtdadeMaxVagoes=" + (int)qtdadeMaxVagoes + "]"; //exibe apenas a quantidade inteira (int)
		}
		return "Locomotiva [Engatada na composicao=" + composicao.getIdentificador() + ", identificador=" + identificador + ", pesoMaximo="
			+ pesoMaximo + ", qtdadeMaxVagoes=" + (int)qtdadeMaxVagoes + "]";
	}
}
