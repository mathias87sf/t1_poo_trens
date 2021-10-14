public class VagaoPassageiro extends Vagao{

    private double capacidadePassageiros;

    public VagaoPassageiro(int identificador, int capacidadePassageiros){
        super(identificador, capacidadePassageiros * 70);
        this.capacidadePassageiros = capacidadePassageiros;
    }

    @Override
    public String toString(){
        return "" + super.toString() + " [Capacidade de passageiros=" + (int)capacidadePassageiros + "]";
    }
}
