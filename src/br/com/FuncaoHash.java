/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com;

import java.util.Scanner;

/**
 *
 * Alunos: Fernando Garcia, Felipe Cechin e Bruno Frizzo
 */
public class FuncaoHash {
    
    static public Integer lerMatricula() {
        Integer matricula;
        Scanner ler = new Scanner(System.in);
        System.out.print("\nInforme uma matrícula:");
        try {
            matricula = ler.nextInt();
        } catch (Exception e) {
            System.out.println("O valor informado nao e uma matricula");
            return lerMatricula();
        }
        if (matricula<=0) {
            return lerMatricula();
        }
        return matricula;
    }
    
    static public Integer lerOpcao() {
        Integer opcao;
        Scanner ler = new Scanner(System.in);
        System.out.print("\nMENU");
        System.out.print("\n1-Incluir matricula");
        System.out.print("\n2-Listar matriculas");
        System.out.print("\n0-SAIR");
        System.out.print("\nInforme uma opção:");
        try {
            opcao = ler.nextInt();
        } catch (Exception e) {
            System.out.println("O valor informado nao e uma opção");
            return lerOpcao();
        }
        if (opcao<0 || opcao>2) {
            return lerOpcao();
        }
        return opcao;
    }
    
    static public void inicializarHash(Integer[] hash, int tamanho) {
        for (int i=0;i<tamanho;i++) {
            hash[i] = 0;
        }
    }
    
    static public Boolean tabelaCompleta(Integer[] hash, int tamanho) {
        for (int i=0;i<tamanho;i++) {
            if (hash[i] == 0) {
                return false;
            }
        }
        return true;
    }
    
    static public Integer somaAlgarismos(Integer matricula) {
        String numero = String.valueOf(matricula);
        int contador = 0;
        int soma = 0;
        for (int i=0;i<numero.length();i++) {
            if (i%2 != 0) {
                soma = soma + Integer.parseInt(numero.substring(contador, contador+2));
                contador += 2;
            } else {
                if (contador+1 == numero.length()) {
                    soma = soma + Integer.parseInt(numero.substring(contador));
                }
            }
        }
        return soma;
    }
    
    static public void imprimirHash(Integer[] hash, int tamanho) {
        for (int i=0;i<tamanho;i++) {
            if (hash[i] == 0) {
                System.out.println("Hash["+i+"] = VAZIO");
            } else {
                System.out.println("Hash["+i+"] = "+ hash[i]);
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                
        Integer [] hash = new Integer[21];
        inicializarHash(hash, 21);
        
        Integer opcao = lerOpcao();
        while(opcao>0) {
            if (opcao == 1) {
                //inserir matricula
                if (tabelaCompleta(hash, 21)) {
                    System.out.println("Tabela completa. Nao e possivel inserir mais matriculas");
                } else {
                    Integer matricula = lerMatricula();
                    int resto = somaAlgarismos(matricula)%21;
                    while(hash[resto] != 0) {
                        resto++;
                        if (resto == 21) {
                            resto = 0;
                        }
                    }
                    hash[resto] = matricula;
                }
            } else if (opcao == 2) {
                //listar tabela
                imprimirHash(hash, 21);
            }
            opcao = lerOpcao();
        }
    }
}
