/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Felipe
 */
public class Cifra {
    
    static String alfabetoOriginal = "abcdefghijklmnopqrstuvwxyz";
    
    static int repetido (String s) {
        int i = 0, j = 0, cont = 0;
        char c,d;
        char[] letra;
        boolean encontrou = false;
        letra = new char[s.length()];        
        for (j = 0; j < s.length(); j++)
        {
            for(i = j + 1 ; i < s.length();i++)
            {
                c = s.charAt(i);                
                d = s.charAt(j);
                for (int l=0; l < s.length(); l++) {
                    if (d==letra[l]) {
                        encontrou=true;                 
                        break;
                    }
                }
                if ((c == d) && !encontrou) {
                    cont++;                                        
                    letra[j] = d;
                }
                encontrou=false;
            }
        }
        return cont;
    }
    
    static public String lerMensagem() {
        String entrada;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Digite uma frase a ser cifrada:");
        try {
            entrada = in.readLine();
        } catch (Exception e) {
            return lerMensagem();
        }
        if (entrada.length()==0 || entrada.length()>200) {
            return lerMensagem();
        }
        return entrada;
    }

    static public String lerChave(String cifra) {
        String entrada;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Digite uma chave para a cifra de " + cifra+ ":");
        try {
            entrada = in.readLine();
        } catch (Exception e) {
            return lerChave(cifra);
        }
        if (entrada.length()==0) {
            return lerChave(cifra);
        }
        if (repetido(entrada) > 0) {
            System.out.println("A chave nao pode conter letras iguais");
            return lerChave(cifra);
        }
        return entrada;
    }
    
    static public void imprimirCifra(String cifra) {
        for (int i=1;i<=cifra.length();i++) {
            System.out.print(cifra.charAt(i-1));
            if (i%5==0) {
                System.out.print(" ");
            }
        }
    }
    
    static public String decodificarMensagemSubstituicao(String mensagemCodificadaSubstituicao, String chaveSubstDecodificacao) {
        String alfabetoSubst = "abcdefghijklmnopqrstuvwxyz";
        String novoAlfabetoSubst;
        chaveSubstDecodificacao = chaveSubstDecodificacao.toLowerCase();
        for (int i=0;i<chaveSubstDecodificacao.length();i++) {
            String letra = ""+chaveSubstDecodificacao.charAt(i);
            alfabetoSubst = alfabetoSubst.replace(letra, "");
        }
        novoAlfabetoSubst = chaveSubstDecodificacao+alfabetoSubst;
        String mensagemDecodSubst = "";
        for (int i=0;i<mensagemCodificadaSubstituicao.length();i++) {
            int indice = novoAlfabetoSubst.indexOf(mensagemCodificadaSubstituicao.charAt(i));
            if (indice >= 0) {
                mensagemDecodSubst += alfabetoOriginal.charAt(indice);
            }
        }
        return mensagemDecodSubst;
    }
    
    static public String decodificarMensagemTransposicao(String mensagemCodificadaTransposicao, String chaveTranspDecodificacao) {
        String[] vetorString = new String[chaveTranspDecodificacao.length()];
        char[] chaveTranspDecodChar = chaveTranspDecodificacao.toCharArray();
        Arrays.sort(chaveTranspDecodChar);
        String concatenacao2 = mensagemCodificadaTransposicao + chaveTranspDecodificacao;
        int linha = (concatenacao2.length()/chaveTranspDecodificacao.length())-1;
       
        for (int i=0;i<chaveTranspDecodificacao.length();i++) {
            for (int j=0; j<chaveTranspDecodificacao.length();j++){
                if (chaveTranspDecodChar[i] == chaveTranspDecodificacao.charAt(j)) {
                    if (mensagemCodificadaTransposicao.length() < linha) {
                        vetorString[j] = mensagemCodificadaTransposicao;
                    } else {
                        vetorString[j] = mensagemCodificadaTransposicao.substring(0, linha);
                        mensagemCodificadaTransposicao = mensagemCodificadaTransposicao.substring(linha);
                    }
                }
            }
        }
        String mensagemDecodificada = "";
        Boolean parou = false;
        for (int i=0; i<linha; i++) {
            for (int j=0;j<vetorString.length;j++){
                try {
                    vetorString[j].charAt(i);
                } catch (Exception e) {
                    parou = true;
                    break;
                }
                mensagemDecodificada += "" + vetorString[j].charAt(i);
            }
            if (parou) {
                break;
            }
        }
        return mensagemDecodificada;
    }
    
    static public String codificarMensagemTransposicao(String mensagem, String chaveTransposicao) {
        char[] chaveTransposicaoChar = chaveTransposicao.toCharArray();
        String concatenacao = chaveTransposicao+mensagem;
        while(concatenacao.length()%chaveTransposicao.length()!= 0) {
            Random generator = new Random();
            char letraNova = (char) (generator.nextInt(25) + 97);
            concatenacao += letraNova;
        }
        String mensagemCodificadaTransposicao = "";
        Arrays.sort(chaveTransposicaoChar);
        int linhas = concatenacao.length()/chaveTransposicao.length();
        for(int i=0;i<chaveTransposicao.length();i++) {
            for (int j=0;j<chaveTransposicao.length();j++) {
                if (chaveTransposicaoChar[i] == chaveTransposicao.charAt(j)) {
                    for (int k=1;k<linhas;k++) {
                        mensagemCodificadaTransposicao += concatenacao.charAt(j+(chaveTransposicao.length()*k));
                    }
                }
            }
        }
        return mensagemCodificadaTransposicao;
    }
    
    static public String codificarMensagemSubstituicao(String mensagem, String chaveSubstCodificacao) {
        String alfabetoSubst = "abcdefghijklmnopqrstuvwxyz";
        String novoAlfabetoSubst;
        chaveSubstCodificacao = chaveSubstCodificacao.toLowerCase();
        for (int i=0;i<chaveSubstCodificacao.length();i++) {
            String letra = ""+chaveSubstCodificacao.charAt(i);
            alfabetoSubst = alfabetoSubst.replace(letra, "");
        }
        novoAlfabetoSubst = chaveSubstCodificacao+alfabetoSubst;
        String mensagemCodSubst = "";
        for (int i=0;i<mensagem.length();i++) {
            int indice = alfabetoOriginal.indexOf(mensagem.charAt(i));
            if (indice >= 0) {
                mensagemCodSubst += novoAlfabetoSubst.charAt(indice);
            }
        }
        return mensagemCodSubst;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Codificação
        String mensagem = lerMensagem();
        System.out.println("CHAVES DE CODIFICAÇÃO");
        String chaveTransposicao = lerChave("transposição");
        String chaveSubstituicao = lerChave("substituição");
        
        mensagem = mensagem.replace(" ","");
        mensagem = mensagem.replace(",","");
        mensagem = mensagem.replace(".","");
        mensagem = mensagem.replace("!","");
        mensagem = mensagem.toLowerCase();
        
        //Cifra de transposição
        System.out.println("\nMensagem codificada pela cifra de transposição: ");
        String mensagemCodificadaTransposicao = codificarMensagemTransposicao(mensagem, chaveTransposicao);
        imprimirCifra(mensagemCodificadaTransposicao);
        
        //Cifra de substituição
        System.out.println("\nMensagem codificada pela cifra de substituição: ");
        String mensagemCodificadaSubstituicao = codificarMensagemSubstituicao(mensagem, chaveSubstituicao);
        imprimirCifra(mensagemCodificadaSubstituicao);
        
        
        
        //Decodificação
        System.out.println("\n\n\nCHAVES DE DECODIFICAÇÃO");
        String chaveTranspDecodificacao = lerChave("transposição");
        String chaveSubstDecodificacao = lerChave("substituição");
        
        
        //Cifra de transposição
        System.out.println("\nA mensagem decodificada pela cifra de transposição pode conter lixo ao final.");
        System.out.println("Mensagem decodificada pela cifra de transposição: \n" + decodificarMensagemTransposicao(mensagemCodificadaTransposicao, chaveTranspDecodificacao));
        
        //Cifra de substituição
        System.out.print("Mensagem decodificada pela cifra de substituição: \n" + decodificarMensagemSubstituicao(mensagemCodificadaSubstituicao, chaveSubstDecodificacao));
    }
}
