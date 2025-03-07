package trabalhoFila;

import java.util.Scanner;

public class GerenciarSenhas {
	
	//criar as duas filas principais, a fila nao atendida, contador de senha pra ir criando as senhas
	Fila filaPreferencial = new Fila();
	Fila filaComum = new Fila();
	 Fila filaNaoAtendida = new Fila();
	 private int contadorSenhas = 0;
	
	//metodo adicionar senha preferencial
	public void adicionarSenhaPref() {
		Senha senhaP = new Senha(contadorSenhas++, "p");
		filaPreferencial.adicionar(senhaP);
		
	}
	
	//metodo adicionar senha comum
	public void adicionarSenhaComum() {
		Senha senhaC = new Senha(contadorSenhas++, "c");
		filaComum.adicionar(senhaC);
	}

	//metodo de chamar a senha
	public void chamarSenha() {
		Senha senhaAtualP = null;
		Senha senhaAtualC = null;
		boolean atendida = false;
		Scanner sc = new Scanner (System.in);
		
		while(!filaPreferencial.estaVazia()) {
		//enquanto senha preferencial tiver senha, chamar
			senhaAtualP = filaPreferencial.pegar(0);
		//loop para chamar 3 vezes	 
			for (int i = 1; i <= 3; i++) {
		//cada vez chamada incrementamos um
				senhaAtualP.incrementarChamadas();
				   System.out.println("Chamando senha: " + senhaAtualP.getId() + " (" + senhaAtualP.getTipo() + ") - Tentativa " + senhaAtualP.getChamadas());
		//aqui é pro usuario controlar se o paciente atendeu ou nao o chamado   
				   System.out.println("O paciente respondeu a tentativa: \n Responda S ou N: \n" );
				   String resposta = sc.nextLine().trim().toUpperCase();
		//se foi atendida o booleano vira verdadeiro e damos o break no loop
					   if (resposta.equals("S")) {
						   atendida = true;
						   break;
					   }			    		
			} 
		//removemos da fila
			 filaPreferencial.remover();
		//se não foi atendida, movemos para a fila de nao atendidos
	    	if(!atendida){
				filaNaoAtendida.adicionar(senhaAtualP);
				System.out.println("Senha movida para fila de não atendidos \n");
	    	}
	    	else {
	            System.out.println("Senha " + senhaAtualP.getId() + " foi atendida com sucesso! \n");
	        }
	    	}
			
		
			
		//depois chamar comum
		if (filaComum.tamanho()>=0) {
			senhaAtualC = filaComum.pegar(0);	
			
			 for (int i = 1; i <= 3; i++) {
				senhaAtualC.incrementarChamadas();
			    System.out.println("Chamando senha: " + senhaAtualC.getId() + " (" + senhaAtualC.getTipo() + ") - Tentativa " + senhaAtualC.getChamadas());
				System.out.println("O paciente respondeu a tentativa: \n Responda S ou N: \n" );
		    	String resposta = sc.nextLine().trim().toUpperCase();
		    	if (resposta.equals("S")) {
		    		atendida = true;
		    		break;
		    		}
			} 
    		filaComum.remover();
    			if(!atendida) {
    				filaNaoAtendida.adicionar(senhaAtualC);
    				System.out.println("Senha movida para fila de não atendidos \n");
    			}
    			else {
    	            System.out.println("Senha " + senhaAtualP.getId() + " foi atendida com sucesso!");
    	        }
    	}
    }
		
	
	//criar metodo exibir os nao atendidos, NAO SEI SE TA CERTO
	public void exibirNaoAtendidos() {
		if(!filaNaoAtendida.estaVazia()) {
			for (int i = 0; i < filaNaoAtendida.tamanho(); i++) {
				 System.out.println("Senha " + filaNaoAtendida.pegar(i)+ " não foi atendida.");
			}
		}
	}
	
	
	
	
	
	
}
