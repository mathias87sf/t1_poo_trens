import java.util.ArrayList;

public class GaragemVagoes {
    
    private ArrayList<Vagao> garagem;

    public GaragemVagoes() {
        garagem = new ArrayList<>();
    }

    public void estacionaVagao(Vagao vagao){
        if(vagao != null) garagem.add(vagao);
    }

    public Vagao getVagao(int id){
        if (garagem.isEmpty()) return null;
        
        for (Vagao vagao : garagem) {
            if(id == vagao.getIdentificador()) {
                garagem.remove(vagao);
                return vagao;
            }
        }
        return null;
    }

    public boolean idVagaoValido(int id){
        if (garagem.isEmpty()) return false;

        for (Vagao vagao : garagem) {
            if(vagao.getIdentificador() == id) return true;
        }
        return false;
    }

    public String toString(){
        String aux = "";
        if (garagem.isEmpty()) return "Garagem de vagoes vazia!";

        for (Vagao vagao : garagem) {
            if(vagao.livre()) aux += vagao.toString() + "\n"; //concatena apenas vagoes livres
        }
        return aux;
    }
    
}
