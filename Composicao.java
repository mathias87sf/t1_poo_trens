import java.util.ArrayList;

public class Composicao {
	private ArrayList<Vagao> vagoes;
	private ArrayList<Locomotiva> locomotivas;
	private int identificador;

	public Composicao(int identificador){
		this.identificador = identificador;
		vagoes = new ArrayList<>();
		locomotivas = new ArrayList<>();
	}

	public int getIdentificador() {
		return identificador;
	}

	public int getQtdadeLocomotivas() {
		return locomotivas.size();
	}

	public Locomotiva getLocomotiva(int posicao) {
		if (posicao >= 0 && posicao < locomotivas.size()) {
			return locomotivas.get(posicao);
		} else {
			return null;
		}
	}

	public int getQtdadeVagoes() {
		return vagoes.size();
	}

	public Vagao getVagao(int posicao) {
		if (posicao >= 0 && posicao < vagoes.size()) {
			return vagoes.get(posicao);
		} else {
			return null;
		}
	}

	public boolean engataLocomotiva(Locomotiva locomotiva) {
		//só é possível engatar locomotivas antes dos vagões
		if (!vagoes.isEmpty()) { //se há vagoes, não pode engatar locomotivas
			System.out.println(">>> ERRO: Nao eh possivel engatar LOCOMOTIVAS APOS VAGOES!");
			return false;
		}

		locomotivas.add(locomotiva);
		locomotiva.setComposicao(this);
		
		if (locomotivas.size() > 1){
			for (Locomotiva l : locomotivas) {
				l.setPesoMaximo(l.getPesoMaximo() * 0.9); //reduz 10% da capacidade de peso de cada locomotiva, pois nao faz sentido reduzir apenas o numero de vagoes
				l.setMaxVagoes((l.getQtdadeMaxVagoes() * 0.9)); //reduz 10% da capacidade de vagoes cada locomotiva
			}
		}

		//System.out.println("DEBUG: Adicionei a locomotiva " + locomotiva.getIdentificador());
		return true;
	}

	public boolean engataVagao(Vagao vagao) {
		//se não há locomotivas, não há como engatar vagões
		if (locomotivas.isEmpty()) { 
			System.out.println(">>> ERRO: Nao eh possivel engatar VAGOES SEM LOCOMOTIVA!");
			return false;
		}
	
		//calcula a quantidade e o peso maximos suportado pelo conjunto de locomotivas
		double pesoMaxLocomotivas = 0;
		int qtdVagoesSuportados = 0;
		for (Locomotiva locomotiva : locomotivas) {
			pesoMaxLocomotivas += locomotiva.getPesoMaximo();
			qtdVagoesSuportados += locomotiva.getQtdadeMaxVagoes();
		}															
		
		double pesoVagao = vagao.getCapacidadeCarga();

		/*
		System.out.println("DEBUG: PESO MAXIMO que as locomotivas suportam: " + pesoMaxLocomotivas);
		System.out.println("DEBUG: QUANTIDADE MAXIMA de vagoes que as locomotivas suportam: " + qtdVagoesSuportados);
		System.out.println("DEBUG: Ha " + vagoes.size() + " vagoes no trem");
		System.out.println("DEBUG: Tentando inserir o vagao " + vagao.getIdentificador() + " de peso " + pesoVagao);
		*/

		if (pesoVagao <= (pesoMaxLocomotivas - pesoVagao)){ //suporta o peso de mais um vagao?
			if (vagoes.size() < qtdVagoesSuportados){		//suporta mais um vagão?
				vagoes.add(vagao);
				vagao.setComposicao(this);
				//System.out.println("DEBUG: Adicionei o vagao " + vagao.getIdentificador());
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
		if (locomotivas.isEmpty()) return null; //se nao ha locomotivas na composicao, retorna null
		
		Locomotiva locomotiva = locomotivas.get(locomotivas.size() - 1); //pega a ultima locomotiva
		
		locomotivas.remove(locomotiva);
		locomotiva.setComposicao(null);
		locomotiva.setMaxVagoes(locomotiva.getQtdadeMaxVagoesInicial()); //volta para a garagem com a quantidade maxima de vagoes suportados inicial
		locomotiva.setPesoMaximo(locomotiva.getPesoMaximoInicial()); //volta para a garagem com o peso maximo suportado inicial

		for (Locomotiva l : locomotivas) {
			l.setPesoMaximo(l.getPesoMaximo() / 0.9); //repoe 10% do peso maximo suportado em cada locomotiva que restou
			l.setMaxVagoes((l.getQtdadeMaxVagoes() / 0.9)); //repõe 10% da capacidade max de vagoes em cada locomotiva que restou
		}

		//System.out.println("DEBUG: DESENGATEI locomotiva " + locomotiva.getIdentificador());

		return locomotiva;
	}

	public Vagao desengataVagao() {
		if (vagoes.isEmpty()) return null; //se nao ha vagoes na composicao, retorna null

		Vagao vagao = vagoes.get(vagoes.size() - 1);
		vagoes.remove(vagao);
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
	
		for(int i=0;i<this.getQtdadeVagoes();i++){
			aux += "    "+this.getVagao(i).toString()+"\n";
		}
		return aux;
	}
}
