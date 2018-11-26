package grafoDijkstra;

/**
 * Classe de abstração de um Grafo.
 *
 * Nela contém uma matriz de adjacência e a descrição dos vértices referente ao
 * problema proposto.
 *
 * @version 1.00.02
 *
 * @authors Ismael Piloto Lund & Fernando Moosher Felix
 */

public class Grafo {

    //Matriz de adjacência
    int[][] G;
    //Descrição dos vértices
    String[] descricao;

    /**
     * Construtor do grafo, onde gera a matriz de adjacência com base no arquivo
     * XLSX.
     */
    public Grafo() {
        //Instancia a matriz de adjacência com base no arquivo XLSX.
        G = new GerenciadorXLSX().matrizGrafoXLSX();
        //Instancia o vetor de descrições
        descricao = new String[G.length];
        //Preenche as descrições com base no problema
        preencherDescDefault();
    }

    /**
     * Preenche as descrições com base no nome das cidades do problema proposto.
     */
    public void preencherDescDefault() {

        descricao[0] = "1 - Cabana das Fadas dos Dentes";
        descricao[1] = "2 - Grána";
        descricao[2] = "3 - Antiga Arvore da Sabedoria";
        descricao[3] = "4 - Trent";
        descricao[4] = "5 - Behr";
        descricao[5] = "6 - Calpheon";
        descricao[6] = "7 - Porto de Ephéria";
        descricao[7] = "8 - Plantação de Trigo do Norte";
        descricao[8] = "9 - Florin";
        descricao[9] = "10 - Olvia";
        descricao[10] = "11 - Acampamento da Guarda Oeste";
        descricao[11] = "12 - Velia";
        descricao[12] = "13 - Heidel";
        descricao[13] = "14 - Glish";
        descricao[14] = "15 - Keplan";
        descricao[15] = "16 - Duvencrune";
        descricao[16] = "17 - Tarif";
        descricao[17] = "18 - Abun";
        descricao[18] = "19 - Altinova";
        descricao[19] = "20 - Kusha";
        descricao[20] = "21 - Posto do Corvo Noturno";
    }

    /**
     * @return G - Matriz de adjacência do Grafo.
     */
    public int[][] getG() {
        return this.G;
    }

    /**
     * @return Vetor de descrição dos vértices.
     */
    public String[] getDescricao() {
        return this.descricao;
    }
}
