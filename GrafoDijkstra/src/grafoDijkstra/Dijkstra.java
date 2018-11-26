package grafoDijkstra;

/*
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * Programa de Pós-Graduação em Ciências da Computação - PROPG
 * Disciplina: Projeto e Análise de Algoritmos
 * Prof Alexandre Gonçalves da Silva 
 *
 * Baseado nos slides 63 da aula do dia 27/10/2017 
 *
 * Página 479 Thomas H. Cormen 3a Ed 
 *
 * Caminho mínimos de fonte única, Algoritmo de Dijkstra
 *
 * O algoritmo de Dijkstra recebe um grafo orientado ponderado(G,w) (sem 
 * arestas de peso negativo) e um vértice s de G. Menor caminho de um vértice,
 * para todos os outros vértices.
 */

/**
 * @author Osmar de Oliveira Braz Junior
 */

import java.util.LinkedList;
import java.util.List;

public class Dijkstra {
    
    private final  int BRANCO = 0;//Vértice não visitado. Inicialmente todos os vértices são brancos
    private final  int CINZA = 1; //Vértice visitado 
    
    //Vetor dos pais de um vértice
    public int[] pi;
    //Vetor das distâncias
    public int[] d;
    //Armazena os vértices visitados
    public int[] cor;
    
    /**
     * Gera um vetor de arestas e pesos.
     *
     * @param G Matriz de adjacência do grafo
     * @return Um vetor de arestas e pesos.
     */
    private List getMatrizVertices(int[][] G) {
        int n = G.length;
        List vertices = new LinkedList();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {                
                if (G[i][j] != 0) {
                    //Cria um vetor de 3 elementos para conter                     
                    //[0]=u(origem), [1]=v(destino), [2]=w(peso)
                    vertices.add(new int[]{i, j, G[i][j]});
                }
            }
        }
        return vertices;
    }
    
    /**
     * Retorna o índice do vértice com o menor peso da aresta ainda não visitado.
     *
     * Complexidade O(V log V)
     * 
     * @param n Quantidade de vértices a ser pesquisados
     * @return O índice do menor vértice
     */
    private int extrairMenor(int n) {
        int menorValor = Integer.MAX_VALUE;
        int indiceMenor = -1;
        for (int i = 0; i < n; i++) {
            if (cor[i] == BRANCO && d[i] < menorValor) {
                indiceMenor = i;
                menorValor = d[i];
            }
        }
        return indiceMenor;
    }    

    /**
     * Inicializa as estimativas de caminhos mínimos e predecessores.
     *
     * @param G Grafo a ser inicializado
     * @param s Vértice inicial
     */
    private void inicializaFonteUnica(int[][] G, int s) {
        //Quantidade de vértices do grafo G
        int V = G.length;
        //Instancia os vetores
        d = new int[V];
        pi = new int[V];
        cor = new int[V];
        for (int v = 0; v < G.length; v++) {
            d[v] = Integer.MAX_VALUE;
            pi[v] = -1;
            cor[v] = BRANCO;            
        }
        d[s] = 0;
        pi[s] = 0;               
    }

    /**
     * Teste se pode ser melhorado o caminho mínimo de u até v.
     *
     * @param u Vértice de origem.
     * @param v Vértice de destino
     * @param w Peso do caminho u até v.
     */
    private void relaxamento(int u, int v, int w) {        
        if (d[v] > d[u] + w) {
            d[v] = d[u] + w;
            pi[v] = u;
        }
    }
    
    /**
     * Executa o algoritmo de Dijkstra para Caminhos Mínimos de fonte única.
     *
     * Encontra a distância mais curta de s para todos os outros vértices.   
     * 
     * Complexidade: O(V log V + E)
     *
     * @param G Matriz de adjacência da árvore
     * @param s Vértice de início
     * @return Vetor com a lista das arestas de menor custo
     */
    public int[] algoritmoDijkstra(int[][] G, int s) {

        //Quantidade de vértices do grafo G
        int V = G.length;

        //Instância o vetor de retorno
        int[] S = new int[V];
        
        //Converte a matriz em uma lista de arestas
        List arestas = getMatrizVertices(G);

        //Quantidade de arestas do grafo
        int E = arestas.size();

        //Realiza a inicialização das estimativas
        inicializaFonteUnica(G, s);

        //Percorre todos os vértice do grafo
        for (int i = 0; i < V; i++) {
            //extranctMin remove o vértice com a menor distância de Q
            int x = extrairMenor(V);
            //Marca como visitado
            cor[x] = CINZA;
            S[i]=x;                        
            //Percorre todas as arestas do grafo
            for (int j = 0; j < E; j++) {
                int[] vertice = (int[]) arestas.get(j);
                int u = vertice[0];
                int v = vertice[1];
                int w = vertice[2];
                //Faz p relaxamento para o vertice retirado de V
                if (u == x) {                
                    relaxamento(u, v, w);
                }
            }
        }  
        return S;
    }
}
