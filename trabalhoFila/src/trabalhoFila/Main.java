package trabalhoFila;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		GerenciarSenhas GS = new GerenciarSenhas();
		int opcao = 0;
		Scanner sc = new Scanner(System.in);
		
		while(opcao != 6) {
		System.out.println("Bem-vindo ao gerenciador de senha \n Escolha a opção: \n 1- Gerar Senha Normal \n 2 - Gerar Senha Preferencial \n 3- Chamar Senha \n 4- Listar Senhas na Fila \n 5- Gerar Relatório \n 6- Sair do Programa \n");		
		opcao = sc.nextInt();
		

		switch(opcao) {
		case 1: 
			GS.adicionarSenhaComum();
			break;
		case 2:
			GS.adicionarSenhaPref();
			break;
		case 3:
			GS.chamarSenha();
			break;
		case 4:
			GS.exibirTodasSenhas();
		}
		
		
	}
	System.out.println("Saindo do programa...");
    }
}
