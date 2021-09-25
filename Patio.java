import java.util.ArrayList;
import java.util.Scanner;

public class Patio {

    private ArrayList<Composicao> trens = new ArrayList<Composicao>();
    private GaragemLocomotivas garagemLocomotivas = new GaragemLocomotivas();
    private GaragemVagoes garagemVagoes = new GaragemVagoes();

    public static void main(String[] args){

        Patio patio = new Patio();

        //populando garagem de locomotivas
        patio.garagemLocomotivas.estacionaLocomotiva(new Locomotiva(123, 1000, 10)); //(id, peso suportado, numero de locomotivas suportado)
        patio.garagemLocomotivas.estacionaLocomotiva(new Locomotiva(456, 2000, 20));
        patio.garagemLocomotivas.estacionaLocomotiva(new Locomotiva(789, 3000, 30));
        patio.garagemLocomotivas.estacionaLocomotiva(new Locomotiva(321, 4000, 40));
        patio.garagemLocomotivas.estacionaLocomotiva(new Locomotiva(654, 5000, 50));
        patio.garagemLocomotivas.estacionaLocomotiva(new Locomotiva(987, 6000, 60));

        //populando garagem da vagoes
        patio.garagemVagoes.estacionaVagao(new Vagao(111, 100)); // (id, peso suportado)
        patio.garagemVagoes.estacionaVagao(new Vagao(222, 200));
        patio.garagemVagoes.estacionaVagao(new Vagao(333, 300));
        patio.garagemVagoes.estacionaVagao(new Vagao(444, 400));
        patio.garagemVagoes.estacionaVagao(new Vagao(555, 500));
        patio.garagemVagoes.estacionaVagao(new Vagao(666, 600));
        patio.garagemVagoes.estacionaVagao(new Vagao(777, 700));
        patio.garagemVagoes.estacionaVagao(new Vagao(888, 800));
        patio.garagemVagoes.estacionaVagao(new Vagao(999, 900));

        patio.menu();     
    }


    // métodos de apoio

    //busca um trem no patio
    public Composicao getTrem(int id){ 
        if (trens.isEmpty()) return null;

        for (Composicao trem : trens) {
            if (trem.getIdentificador() == id) return trem;
        }
        return null;
    }
    
    //verifica se o trem existe na composicao
    //meio supérfluo/redundante mas ajuda na legibilidade
    public boolean idTremValido(int id){ 
        if (trens.isEmpty()) return false;

        for (Composicao trem : trens) {
            if (trem.getIdentificador() == id) return true;
        }
        return false;
    }

    // MENU //
    public void menu(){

        Scanner scan = new Scanner(System.in);
        int opt = 0;
        int id = 0;
        Composicao trem;

        System.out.println("\n\nBem vindo ao patio central do sistema ferroviario!");
        System.out.println("por Mathias Silveira de Freitas @ PUCRS 21280225-0");

        while(true){
            System.out.println("\n# Menu #");
            System.out.println("1 - Criar um novo trem");
            System.out.println("2 - Editar um trem existente");
            System.out.println("3 - Listar todos os trens");
            System.out.println("4 - Desfazer um trem");
            System.out.println("5 - Sair");

            opt = scan.nextInt();

            //1 - Criar um novo trem
            if (opt == 1){
                System.out.println("\n> Digite um identificador para o trem");
                id = scan.nextInt();
                if (id > 0){ //aceita qualquer ID positivo, exceto 0
                    trens.add(new Composicao(id)); //cria um novo trem com id
                    System.out.println(">>> Trem ID [" + id + "] criado!");
                }
            }
            
            //2 - Editar um trem existente
            if (opt == 2){
                System.out.println("\n> Digite o identificador");
                id = scan.nextInt();
                if (idTremValido(id)) { //verifica se o trem existe no patio //poderia ser tb if (getTrem(id) != null)
                    trem = getTrem(id);
                    System.out.println(trem);
                } else {
                    System.out.println(">>> Trem [" + id + "] não existe");
                    trem = null; //pra apaziguar a ira do compilador
                    opt = 0;
                }

                while(opt == 2){
                    System.out.println("\n# Editando trem [" + id + "] #");
                    
                    System.out.println("1 - Inserir uma locomotiva");
                    System.out.println("2 - Inserir um vagao");
                    System.out.println("3 - Remover elemento");
                    System.out.println("4 - Listar locomotivas livres");
                    System.out.println("5 - Listar vagoes livres");
                    System.out.println("6 - Encerrar edicao");
                    
                    int optE = 6;
                    optE = scan.nextInt();
                    switch (optE){
                        case 1: //inserir locomotiva
                            System.out.println("\n- Locomotivas livres -");
                            System.out.println(garagemLocomotivas);
                        
                            System.out.println("\n> Digite o identificador da locomotiva que voce quer inserir");
                            int idLocomotiva = scan.nextInt();
                            if (garagemLocomotivas.idLocomotivaValido(idLocomotiva)) { //verifica se a locomotiva esta na garagem //ou if(garagemLocomotivas.getLocomotiva(idLocomotiva) != null)
                                trem.engataLocomotiva(garagemLocomotivas.getLocomotiva(idLocomotiva));
                                break;
                            } else {
                                System.out.println(">>> A locomotiva ID [" + idLocomotiva + "] nao existe!");
                                break;
                            }
                        case 2: //inserir vagao
                            System.out.println("\n- Vagoes livres - ");
                            System.out.println(garagemVagoes);
                        
                            System.out.println("\n> Digite o identificador do vagao que voce quer inserir");
                            int idVagao = scan.nextInt();
                            if (garagemVagoes.idVagaoValido(idVagao)) { //ou if(garagemVagoes.getVagao(idVagao) != null)
                                trem.engataVagao(garagemVagoes.getVagao(idVagao));
                                break;                                
                            } else {
                                System.out.println(">>> O vagao ID [" + idVagao + "] nao existe!");
                                break;
                            }
                        case 3: //remover elemento
                            if (trem.getQtdadeVagoes() > 0) { //ainda ha vagoes para remover
                                garagemVagoes.estacionaVagao(trem.desengataVagao()); //desengata o vagao e estaciona na garagem
                                break;
                            }
                            if (trem.getQtdadeVagoes() == 0 && trem.getQtdadeLocomotivas() > 1) { //nao ha mais vagoes mas ha locomotivas para remover
                                garagemLocomotivas.estacionaLocomotiva(trem.desengataLocomotiva()); //desengata a locomotiva e estaciona na garagem
                                break;
                            }
                            if (trem.getQtdadeVagoes() == 0 && trem.getQtdadeLocomotivas() == 1){ //ultima locomotiva destroi tb o trem
                                garagemLocomotivas.estacionaLocomotiva(trem.desengataLocomotiva()); //desengata a locomotiva e estaciona na garagem
                                trens.remove(trem);                                                 //remove a locomotiva da composicao
                                trem = null;
                                opt = 0;
                                System.out.println("Trem ID = " + id + " removido");
                                break;
                            }
                            break;
                        case 4: //listar locomotivas livres
                            System.out.println("\n- Locomotivas livres -");
                            System.out.println(garagemLocomotivas);
                            break;
                        case 5: //listar vagoes livres
                            System.out.println("\n- Vagoes livres - ");
                            System.out.println(garagemVagoes);
                            break;
                        case 6: //encerrar edicao
                            opt = 0;
                            break;
                        default:
                            break;
                    }
                }
                
                trem = null;
            }

            //Listar todos os trens ja criados
            if (opt == 3){
                for (Composicao auxTrem : trens) {
                    System.out.println(auxTrem);                         
                }
            }

            //Desfazer um trem
            if (opt == 4){
                System.out.println("\n> Digite o identificador do trem que deseja desfazer");
                id = scan.nextInt();
                if (idTremValido(id)){ //verifica se o trem existe no patio // if (getTrem(id) != null)
                    trem = getTrem(id); //busca o trem que esta no patio para desfaze-lo

                    //estaciona todos os vagoes
                    while (trem.getQtdadeVagoes() > 0){
                        garagemVagoes.estacionaVagao(trem.desengataVagao());
                    }
                    
                    //estaciona todas as locomotivas
                    while (trem.getQtdadeLocomotivas() > 0){
                        garagemLocomotivas.estacionaLocomotiva(trem.desengataLocomotiva());
                    }
                    
                    //tira o trem da lista
                    trens.remove(trem);
                } else {
                    System.out.println(">>> Trem [" + id + "] não existe");
                    opt = 0;
                }
            }

            //Sair
            if (opt == 5){
                System.out.println("\nHasta la vista, baby.");
                System.out.println("\nMathias Silveira de Freitas @ PUCRS 21280225-0\n");
                scan.close();
                System.exit(0);
            }

            trem = null;
        }
       
    }
}
