/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com;

import java.util.Scanner;

public class CodigoHamming {
    public String converteDecimalEmBinario(int i) {
        String binario = Integer.toBinaryString(i);
        while (binario.length()<4) {
            binario = "0"+binario;
        }
        return binario;
    }
    
    public void popularMatrizGeradora(Integer [][]matriz) {
        matriz[0][0] = 1;
        matriz[0][1] = 1;
        matriz[0][2] = 0;
        matriz[0][3] = 1;
        
        matriz[1][0] = 1;
        matriz[1][1] = 0;
        matriz[1][2] = 1;
        matriz[1][3] = 1;
        
        matriz[2][0] = 1;
        matriz[2][1] = 0;
        matriz[2][2] = 0;
        matriz[2][3] = 0;
        
        matriz[3][0] = 0;
        matriz[3][1] = 1;
        matriz[3][2] = 1;
        matriz[3][3] = 1;
        
        matriz[4][0] = 0;
        matriz[4][1] = 1;
        matriz[4][2] = 0;
        matriz[4][3] = 0;
        
        matriz[5][0] = 0;
        matriz[5][1] = 0;
        matriz[5][2] = 1;
        matriz[5][3] = 0;
        
        matriz[6][0] = 0;
        matriz[6][1] = 0;
        matriz[6][2] = 0;
        matriz[6][3] = 1;
    }
    
    public void popularMatrizParidade(Integer [][]matriz) {
        matriz[0][0] = 1;
        matriz[0][1] = 0;
        matriz[0][2] = 1;
        matriz[0][3] = 0;
        matriz[0][4] = 1;
        matriz[0][5] = 0;
        matriz[0][6] = 1;
        
        matriz[1][0] = 0;
        matriz[1][1] = 1;
        matriz[1][2] = 1;
        matriz[1][3] = 0;
        matriz[1][4] = 0;
        matriz[1][5] = 1;
        matriz[1][6] = 1;
        
        matriz[2][0] = 0;
        matriz[2][1] = 0;
        matriz[2][2] = 0;
        matriz[2][3] = 1;
        matriz[2][4] = 1;
        matriz[2][5] = 1;
        matriz[2][6] = 1;
    }
    
    public Integer[] popularVetorParidade(Integer []vetorCodigoHamming,Integer [][]matrizParidade) {
        Integer [] vetorParidade = new Integer[3];
        for (int i=0;i<3;i++) {
            Integer somaLinha = 0;
            for (int j=0;j<7;j++) {
                somaLinha = somaLinha + (matrizParidade[i][j] & vetorCodigoHamming[j]);
            }
            vetorParidade[i] = somaLinha%2;
        }
        return vetorParidade;
    }
    public Integer lerPosicao() {
        Integer posicao = 0;
        Scanner ler = new Scanner(System.in);
        System.out.print("\nInforme uma posição do codigo para alterar seu valor.");
        System.out.print("\nDigite -1 para deixar de alterar o codigo gerado.");
        System.out.print("\nPosicao: ");
        try {
            posicao = ler.nextInt();
        } catch (Exception e) {
            System.out.println("O valor informado nao e um numero");
            return this.lerPosicao();
        }
        if (posicao<-1 || posicao>7) {
            return this.lerPosicao();
        }
        return posicao;
    }
    public void alterarVetorPosicao(Integer []vetorCodigoHamming,Integer posicao) {
        if (vetorCodigoHamming[posicao]==0) {
            vetorCodigoHamming[posicao] = 1;
        } else {
            vetorCodigoHamming[posicao] = 0;
        }
    }
    public void imprimirVetor(Integer []vetor,int tamanho) {
        for (int i=0;i<tamanho;i++) {
            System.out.print(vetor[i]);
        }
    }
    public Boolean paridadeIncorreta(Integer []vetorParidade) {
        for (int i=0;i<3;i++) {
            if (vetorParidade[i] == 1) {
                return true;
            }
        }
        return false;
    }
    public Boolean verificarCodigo(Integer []vetorCodigo,Integer []vetorCorreto) {
        for (int i=0;i<7;i++) {
            if (vetorCodigo[i] != vetorCorreto[i]) {
                return false;
            }
        }
        return true;
    }
    public int quantidadeBitsAlterados(Integer []vetorCodigo,Integer []vetorCorreto) {
        int cont = 0;
        for (int i=0;i<7;i++) {
            if (vetorCodigo[i] != vetorCorreto[i]) {
                cont++;
            }
        }
        return cont;
    }
    public Integer lerNumero() {
        Integer numero = 0;
        Scanner ler = new Scanner(System.in);
        System.out.print("\nInforme um numero em decimal de 0 a 15: ");
        try {
            numero = ler.nextInt();
        } catch (Exception e) {
            System.out.println("O valor informado nao e um numero.");
            return this.lerNumero();
        }
        if (numero<0 || numero>15) {
            return this.lerNumero();
        }
        return numero;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CodigoHamming codigoHamming = new CodigoHamming();
        Integer numeroDecimal = codigoHamming.lerNumero();
        String numero = codigoHamming.converteDecimalEmBinario(numeroDecimal);
        Integer [] numeroBinario = new Integer[4];
        for (int i=0;i<4;i++) {
            numeroBinario[i] = Integer.parseInt(numero.substring(i, i+1));
        }
        
        Integer [][] matrizGeradora = new Integer[7][4];
        codigoHamming.popularMatrizGeradora(matrizGeradora);
        
        Integer [][] matrizParidade = new Integer[3][7];
        codigoHamming.popularMatrizParidade(matrizParidade);
        
        Integer [] vetorCodigoHamming = new Integer[7];
        for (int i=0;i<7;i++) {
            Integer somaLinha = 0;
            for (int j=0;j<4;j++) {
                somaLinha = somaLinha + (matrizGeradora[i][j] & numeroBinario[j]);
            }
            vetorCodigoHamming[i] = somaLinha%2;
        }
        
        Integer [] vetorCodigoInicial = new Integer[7];
        vetorCodigoInicial = vetorCodigoHamming.clone();
        System.out.print("Código de Hamming gerado: ");
        codigoHamming.imprimirVetor(vetorCodigoHamming, 7);
        Integer posicao = codigoHamming.lerPosicao();
        int cont = 0;
        while(posicao>0 && cont<3) {
            posicao--;
            codigoHamming.alterarVetorPosicao(vetorCodigoHamming,posicao);
            cont = codigoHamming.quantidadeBitsAlterados(vetorCodigoHamming,vetorCodigoInicial);
            if (cont != 3) {
                System.out.print("Você alterou "+(cont)+" bit(s) do codigo gerado. Máximo:3 alterações");
                posicao = codigoHamming.lerPosicao();
            }
        }
        System.out.print("Você alterou "+(cont)+" bit(s) do codigo gerado.");        
        
        Integer []vetorParidade = codigoHamming.popularVetorParidade(vetorCodigoHamming, matrizParidade);
        System.out.println("\nVetor paridade:");
        codigoHamming.imprimirVetor(vetorParidade, 3);
        
        if (codigoHamming.paridadeIncorreta(vetorParidade)) {
            System.out.print("\nO codigo de Hamming alterado ficou assim: ");
            codigoHamming.imprimirVetor(vetorCodigoHamming, 7);
        }
        
        while(codigoHamming.paridadeIncorreta(vetorParidade)) {
            String binario = "";
            for (int i=2;i>=0;i--) {
                binario = binario + vetorParidade[i];
            }
            Integer numeroInteiro = Integer.parseInt(binario, 2);
            System.out.println("\nO erro esta na posicao "+numeroInteiro+" do codigo.");
            codigoHamming.alterarVetorPosicao(vetorCodigoHamming,numeroInteiro-1);
            System.out.print("\nAgora o codigo de Hamming esta assim: ");
            codigoHamming.imprimirVetor(vetorCodigoHamming, 7);
            vetorParidade = codigoHamming.popularVetorParidade(vetorCodigoHamming, matrizParidade);
            System.out.println("\nVetor paridade:");
            codigoHamming.imprimirVetor(vetorParidade, 3);
        }
        if (cont==0) {
            System.out.print("\nO codigo de Hamming nao foi alterado:");
            codigoHamming.imprimirVetor(vetorCodigoHamming, 7);
            System.out.println();
        } else {
            if (!codigoHamming.verificarCodigo(vetorCodigoHamming,vetorCodigoInicial)) {
                System.out.print("\nO codigo de Hamming nao foi corrigido");
            } else {
                System.out.print("\nO codigo de Hamming foi corrigido");
            }
            System.out.print("\nCodigo de Hamming gerado inicialmente: ");
            codigoHamming.imprimirVetor(vetorCodigoInicial, 7);
            System.out.print("\nCodigo de Hamming apos os testes: ");
            codigoHamming.imprimirVetor(vetorCodigoHamming, 7);
            System.out.println();
        }
    }
    
}
