import java.util.ArrayList;

public class Composicao {
	private int identificador;
	private ArrayList<ElementoDeComposicao> composicao;
	private int qtdLocomotivas;
	private int qtdVagoes;

	public Composicao(int identificador){
		this.identificador = identificador;
		this.composicao = new ArrayList<>();
	}

	public int getIdentificador() {
		return identificador;
	}

	public int getQtdadeLocomotivas() {
		return qtdLocomotivas;
	}

	public Locomotiva getLocomotiva(int posicao) {
		if (posicao >= 0 && posicao < qtdLocomotivas) {
			return (Locomotiva)composicao.get(posicao);
		} else {
			return null;
		}
	}
	
	public int getQtdadeVagoes() {
		return qtdVagoes;
	}

	public Vagao getVagao(int posicao) {
		if (posicao >= qtdLocomotivas) { //se está no dominio dos vagoes (após locomotivas)
			return (Vagao)composicao.get(posicao);
		} else {
			return null;
		}
	}
	
	public boolean engataLocomotiva(Locomotiva locomotiva){
		//se não há elementos na composicao, apenas insere a locomotiva
		if(composicao.isEmpty()){
			composicao.add(locomotiva);
			locomotiva.setComposicao(this);
			qtdLocomotivas++;
			return true;
		}
		
		//se há elementos na composição
		//só é possível engatar locomotivas antes dos vagões
		for (ElementoDeComposicao elementoDeComposicao : composicao) {
			if(elementoDeComposicao instanceof Vagao) { //se há uma instância de vagão na composição, então não pode mais engatar locomotivas
				System.out.println(">>> ERRO: Nao eh possivel engatar LOCOMOTIVAS APOS VAGOES!");	
				return false;
			}
		}

		composicao.add(locomotiva);
		locomotiva.setComposicao(this);
		qtdLocomotivas++;

		Locomotiva locomotivaAux = null;
		if(qtdLocomotivas > 1){
			for (ElementoDeComposicao elementoDeComposicao : composicao) {
				if(elementoDeComposicao instanceof Locomotiva){
					locomotivaAux = (Locomotiva)elementoDeComposicao;
					locomotivaAux.setPesoMaximo(locomotivaAux.getPesoMaximo() * 0.9); //cada vez que eu engato uma locomotiva, passo em todas as locomotivas retirando 10% da sua capacidade de carga
					locomotivaAux.setCapacidade(locomotivaAux.getCapacidade() * 0.9); //reduz 10% da capacidade de vagoes cada locomotiva
				}
			}
		}

		//System.out.println("DEBUG: Adicionei a locomotiva " + locomotiva.getIdentificador());   /////////
		return true;
	}

	public boolean engataVagao(Vagao vagao) {
		//se não há locomotivas, não há como engatar vagões
		if (qtdLocomotivas < 1) { 
			System.out.println(">>> ERRO: Nao eh possivel engatar VAGOES SEM LOCOMOTIVA!");
			return false;
		}
	
		//calcula a quantidade e o peso maximos suportado pelo conjunto de locomotivas
		double pesoMaxLocomotivas = 0;
		int qtdVagoesSuportados = 0;
		Locomotiva locomotivaAux = null;
		for (ElementoDeComposicao elementoDeComposicao : composicao) {
			if(elementoDeComposicao instanceof Locomotiva){
				locomotivaAux = (Locomotiva)elementoDeComposicao;
				pesoMaxLocomotivas += locomotivaAux.getPesoMaximo();
				qtdVagoesSuportados += locomotivaAux.getCapacidade();
			}
		}

		double pesoVagao = vagao.getCapacidade();
		
		//System.out.println("DEBUG: PESO MAXIMO que as locomotivas suportam: " + pesoMaxLocomotivas);
		//System.out.println("DEBUG: QUANTIDADE MAXIMA de vagoes que as locomotivas suportam: " + qtdVagoesSuportados);
		//System.out.println("DEBUG: Ha " + qtdVagoes + " vagoes no trem");
		//System.out.println("DEBUG: Tentando inserir o vagao " + vagao.getIdentificador() + " de peso " + pesoVagao);
		
		if (pesoVagao <= (pesoMaxLocomotivas - pesoVagao)){ //suporta o peso de mais um vagao?
			if (qtdVagoes < qtdVagoesSuportados){			//suporta mais um vagão?
				composicao.add(vagao);
				qtdVagoes++;
				vagao.setComposicao(this);
				
				//System.out.println("DEBUG: Adicionei o vagao " + vagao.getIdentificador()); /////////
				
				return true;
			} else {
				System.out.println(">>> ERRO: Quantidade limite de vagoes atingida!");
			}
		} else {
			System.out.println(">>> ERRO: Peso limite atingido!");
		}

		System.out.println(">>> ERRO: FALHEI em adicionar um vagao!");
		return false;
	}
	
	public Locomotiva desengataLocomotiva() {
		if (qtdLocomotivas < 1) return null; //se nao ha locomotivas na composicao, retorna null
		
		Locomotiva locomotiva = null;
		for (ElementoDeComposicao elementoDeComposicao : composicao) {
			if(elementoDeComposicao instanceof Locomotiva){
				locomotiva = (Locomotiva)elementoDeComposicao;
			}
		} //ao final do laço, 'locomotiva' terá a última locomotiva

		composicao.remove(locomotiva);
		qtdLocomotivas--;
		locomotiva.setComposicao(null);
		locomotiva.setCapacidade(locomotiva.getQtdadeMaxVagoesInicial()); //volta para a garagem com a quantidade maxima de vagoes suportados inicial
		locomotiva.setPesoMaximo(locomotiva.getPesoMaximoInicial()); //volta para a garagem com o peso maximo suportado inicial

		Locomotiva locomotivaAux = null;
		for (ElementoDeComposicao elementoDeComposicao : composicao) {
			if(elementoDeComposicao instanceof Locomotiva){
				locomotivaAux = (Locomotiva)elementoDeComposicao;
				locomotivaAux.setPesoMaximo(locomotivaAux.getPesoMaximo() / 0.9); //repoe 10% do peso maximo suportado em cada locomotiva que restou
				locomotivaAux.setCapacidade(locomotivaAux.getCapacidade() / 0.9); //repõe 10% da capacidade max de vagoes em cada locomotiva que restou
			}
		}

		//System.out.println("DEBUG: DESENGATEI locomotiva " + locomotiva.getIdentificador());

		return locomotiva;
	}

	public Vagao desengataVagao() {
		if (qtdVagoes < 1) return null; //se nao ha vagoes na composicao, retorna null

		Vagao vagao = (Vagao)composicao.get((qtdLocomotivas + qtdVagoes) - 1); //pega o ultimo vagão

		composicao.remove(vagao);
		qtdVagoes--;
		vagao.setComposicao(null);

		//System.out.println("DEBUG: DESENGATEI vagao " + vagao.getIdentificador());

		return vagao;
	}

	public String toString(){
		String aux = "Composicao: ";
		aux += this.getIdentificador() +"\n";
		aux += "Locomotivas:\n";
		
		for(int i=0;i<this.getQtdadeLocomotivas();i++){
			aux += "    "+this.getLocomotiva(i).toString()+"\n";
		}
		
		aux += "Vagoes:\n";

		for(int i=qtdLocomotivas;i<=this.getQtdadeVagoes();i++){ //<=
			aux += "    "+this.getVagao(i).toString()+"\n";
		}
		return aux;
	}
}
