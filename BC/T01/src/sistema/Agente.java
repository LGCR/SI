package sistema;

import problema.*;
import ambiente.*;
import arvore.TreeNode;
import arvore.fnComparator;
import comuns.*;
import static comuns.PontosCardeais.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author tacla
 */
public class Agente implements PontosCardeais {
    /* referência ao ambiente para poder atuar no mesmo*/
    Model model;
    Estado estAtu; // guarda o estado atual (posição atual do agente)
    int plan[];
    double custo;
    int lastAction = -1;
    static int ct = -1;
           
    public Agente(Model m) {
        this.model = m; 
        
        // Posiciona o agente fisicamente no ambiente na posicao inicial
        model.setPos(8,0);
    }
    
     /**
     * Agente escolhe qual acao será executada em um ciclo de raciocinio.
     * Observar que o agente executa somente uma acao por ciclo.
     */
    public int deliberar() {               
        //  contador de acoes
        ct++;
        // @todo T1: perceber por meio do sensor a posicao atual e imprimir
        // @todo T1: a cada acao escolher uma acao {N, NE, L, SE, S, SO, O, NO}
        int ret = 0;
        int action = N;
        int op = -1;
        estAtu = sensorPosicao();
        while(ret != 1){//executar a acao escolhida
            System.out.println("POSITION: " + estAtu.getString());
            //System.out.println("ACTION(N=0, NE=1, L=2, SE=3, S=4, SO=5, O=6, NO=7)");
            //Scanner cmd = new Scanner(System.in);
            ///cmd.nextLine();
            //action = cmd.nextInt();

            ret = executarIr(action);
            if (estAtu.igualAo(new Estado(2,8))){
                return -1;
            }
            if (action == N){
                op = 0;
            }
            else if (action == NO){
                op = 1;
            }

            if (op == 0){
                action++;
            }
            else{
                action --;
            }
        }
        
        return 1; // Se retornar -1, encerra o agente
    }

    /**
    * Atuador: executa 'fisicamente' a acao Ir
    * @param direcao um dos pontos cardeais
    */
    public int executarIr(int direcao) {
        //@todo T1 - invocar metodo do Model - atuar no ambiente
        model.ir(direcao);
        if(estAtu.igualAo(sensorPosicao())){
            return 0;
        }
        estAtu = sensorPosicao();
        return 1; // deu certo
    }

    /**
     * Simula um sensor que realiza a leitura da posição atual no ambiente e
     * traduz para um par de coordenadas armazenadas em uma instância da classe
     * Estado.
     * @return Estado contendo a posição atual do agente no labirinto 
     */
    public Estado sensorPosicao() {
        //@todo T1 - sensor deve ler a posicao do agente no labirinto (environment)
        int pos[] = model.lerPos();
        return new Estado(pos[0],pos[1]);
    }
    
}
