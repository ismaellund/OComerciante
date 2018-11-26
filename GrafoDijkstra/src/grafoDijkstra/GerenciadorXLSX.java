package grafoDijkstra;

/**
 * Classe que gerencia a leitura de arquivo XLSX convertendo-o para uma matriz
 * de adjacência.
 *
 * @version 1.00.03
 *
 * @authors Ismael Lund & Fernando Moosher
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GerenciadorXLSX {

    //Variavel global que delimita o número de linhas da matriz
    private int linha = 0;
    //Variável global que delimita o número de colunas da matriz
    private int coluna = 0;

    /**
     * Gera uma matriz de adjacencia com base em uma planilha xlsx em:
     * ./matriz/matriz.xlsx.
     *
     * @return Uma matriz de adjacencia a preenchida
     */
    public int[][] matrizGrafoXLSX() {
        //pega as dimensões da planilha xlsx
        getTamanhoXLSX();
        //instancia uma matriz adjacente com as dimensões
        int[][] matrizGrafo = new int[linha][coluna];
        //preenche a matriz com os dados da planilha xlsx
        carregaTabelaXLSX(matrizGrafo);

        return matrizGrafo;

    }

    /**
     * Preenche a matriz de adjacencia com os dados da planilha xlsx.
     *
     * @param matrizGrafo matriz de adjacencia a ser preenchida
     */
    private void carregaTabelaXLSX(int[][] matrizGrafo) {

        FileInputStream fisTabela = null;
        int i = 0;
        int j = 0;

        try {
            File file = new File("./matriz/matriz.xlsx");
            fisTabela = new FileInputStream(file);

            //cria um workbook = planilha toda com todas as abas
            XSSFWorkbook workbook = new XSSFWorkbook(fisTabela);

            //recupera apenas a primeira aba ou primeira planilha
            XSSFSheet sheet = workbook.getSheetAt(0);

            //retorna todas as linhas da planilha 0 (aba 1)
            Iterator<Row> rowIterator = sheet.iterator();

            //varre todas as linhas da planilha 0
            while (i < linha) {

                //recebe cada linha da planilha
                Row row = rowIterator.next();

                //pega todas as celulas desta linha
                Iterator<Cell> cellIterator = row.iterator();

                //varre todas as celulas da linha atual
                while (j < coluna) {
                    //cria uma celula
                    Cell cell = cellIterator.next();
                    matrizGrafo[i][j] = (int) cell.getNumericCellValue();
                    j++;
                }
                j = 0;
                i++;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GerenciadorXLSX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorXLSX.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fisTabela.close();
            } catch (IOException ex) {
                Logger.getLogger(GerenciadorXLSX.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Pega as dimensões de uma planilha xlsx e preenche as variáveis globais.
     */
    private void getTamanhoXLSX() {

        FileInputStream fisTabela = null;
        boolean percorreUmaVez = true;

        try {
            File file = new File("./matriz/matriz.xlsx");
            fisTabela = new FileInputStream(file);

            //cria um workbook = planilha toda com todas as abas
            XSSFWorkbook workbook = new XSSFWorkbook(fisTabela);

            //recupera apenas a primeira aba ou primeira planilha
            XSSFSheet sheet = workbook.getSheetAt(0);

            //retorna todas as linhas da planilha 0 (aba 1)
            Iterator<Row> rowIterator = sheet.iterator();

            //varre todas as linhas da planilha 0
            while (rowIterator.hasNext()) {

                linha++;
                //recebe cada linha da planilha
                Row row = rowIterator.next();

                //pega todas as celulas desta linha
                Iterator<Cell> cellIterator = row.iterator();
                //varre todas as celulas da linha atual
                while (cellIterator.hasNext() && percorreUmaVez) {

                    coluna++;

                    //cria uma celula
                    Cell cell = cellIterator.next();

                }
                percorreUmaVez = false;

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GerenciadorXLSX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorXLSX.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fisTabela.close();
            } catch (IOException ex) {
                Logger.getLogger(GerenciadorXLSX.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
