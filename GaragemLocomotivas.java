import java.util.ArrayList;

public class GaragemLocomotivas {
    
    private ArrayList<Locomotiva> garagem;

    public GaragemLocomotivas() {
        garagem = new ArrayList<>();
    }

    public void estacionaLocomotiva(Locomotiva locomotiva){
        if(locomotiva != null) garagem.add(locomotiva);
    }

    public Locomotiva getLocomotiva(int id){
        if (garagem.isEmpty()) return null;
        
        for (Locomotiva locomotiva : garagem) {
            if(id == locomotiva.getIdentificador()) {
                garagem.remove(locomotiva);
                return locomotiva;
            }
        }
        return null;
    }

    public boolean idLocomotivaValido(int id){
        if(garagem.isEmpty()) return false;
        
        for (Locomotiva locomotiva : garagem) {
            if (locomotiva.getIdentificador() == id) return true;
        }
        return false;
    }

    public String toString(){
        String aux = "";
        if (garagem.isEmpty()) return "Garagem de locomotivas vazia!";

        for (Locomotiva locomotiva : garagem) {
            if(locomotiva.livre()) aux += locomotiva.toString() + "\n"; //concatena apenas as locomotivas livres
        }
        return aux;
    }
}
