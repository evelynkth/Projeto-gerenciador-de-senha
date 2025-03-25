package trabalhoFila;

import java.time.LocalTime;
import java.util.Scanner;

public class GerenciarSenhas {
    int contAtendidas = 0;
    int tamanhoTotalP = 0;
    int tamanhoTotalC = 0;
    LocalTime hChegada;
    LocalTime hAtendida;
    int atendidasPreferenciais = 0;
    int atendidasComuns = 0;
	  // Criar as duas filas principais, a fila não atendida, contador de senha para ir criando as senhas
    Fila filaPreferencial = new Fila();
    Fila filaComum = new Fila();
    Fila filaNaoAtendida = new Fila();
    Fila filaHistorico = new Fila();  // Fila para armazenar o histórico de senhas
    private int contadorSenhas = 0;

    // Método adicionar senha preferencial
    public void adicionarSenhaPref() {
        Senha senhaP = new Senha(contadorSenhas++, "p");
        filaPreferencial.adicionar(senhaP);
        filaHistorico.adicionar(senhaP); // Adiciona ao histórico
        tamanhoTotalP++;
        hChegada = LocalTime.now();
    }

    // Método adicionar senha comum
    public void adicionarSenhaComum() {
        Senha senhaC = new Senha(contadorSenhas++, "c");
        filaComum.adicionar(senhaC);
        filaHistorico.adicionar(senhaC); // Adiciona ao histórico
        tamanhoTotalC++;
        hChegada = LocalTime.now();
    }

  
   // Método de chamar a senha
    public void chamarSenha() {
    Senha senhaAtualP = null;
    Senha senhaAtualC = null;
    boolean atendida = false;
    Scanner sc = new Scanner(System.in);

    // Chamar senhas da fila preferencial
    while (!filaPreferencial.estaVazia()) {
        senhaAtualP = filaPreferencial.pegar(0);
        // Loop para chamar 3 vezes
        for (int i = 1; i <= 3; i++) {
            senhaAtualP.incrementarChamadas();
            System.out.println("Chamando senha: " + senhaAtualP.getId() + " (" + senhaAtualP.getTipo() + ") - Tentativa " + senhaAtualP.getChamadas());
            System.out.println("O paciente respondeu a tentativa: \n Responda S, N ou Exit: \n");
            String resposta = sc.nextLine().trim().toUpperCase();

    

            if (resposta.equals("S")) {
                atendida = true;
                contAtendidas++;
                hAtendida = LocalTime.now();
                atendidasPreferenciais++;
                break;  // Se a resposta for 'S', a senha é atendida e o loop é interrompido
            } else if (resposta.equals("EXIT")) {
                System.out.println("Saindo da chamada de senha...");
                return; // Sai do método e retorna para o menu principal
            }
        }

        // Remover a senha da fila preferencial após as tentativas
        filaPreferencial.remover();

        if (!atendida) {
            // Se a senha não foi atendida, movê-la para a fila de não atendidos
            filaNaoAtendida.adicionar(senhaAtualP);
            System.out.println("Senha " + senhaAtualP.getId() + " movida para fila de não atendidos\n");
        } else {
            // Se a senha foi atendida
            System.out.println("Senha " + senhaAtualP.getId() + " foi atendida com sucesso!\n");
        }
        
        // Resetar o status de atendida para a próxima senha
        atendida = false;
    }

    // Chamar senhas da fila comum
    while (!filaComum.estaVazia()) {
        senhaAtualC = filaComum.pegar(0);

        for (int i = 1; i <= 3; i++) {
            senhaAtualC.incrementarChamadas();
            System.out.println("Chamando senha: " + senhaAtualC.getId() + " (" + senhaAtualC.getTipo() + ") - Tentativa " + senhaAtualC.getChamadas());
            System.out.println("O paciente respondeu a tentativa: \n Responda S, N ou Exit: \n");
            String resposta = sc.nextLine().trim().toUpperCase();

          
            if (resposta.equals("S")) {
                atendida = true;
                contAtendidas++;
                hAtendida = LocalTime.now();
                atendidasComuns++;
                break;  // Se a resposta for 'S', a senha é atendida e o loop é interrompido
            }

            else if (resposta.equals("EXIT")) {
                System.out.println("Saindo da chamada de senha...");
                return; // Sai do método e retorna para o menu principal
            }
        }

        // Remover a senha da fila comum após as tentativas
        filaComum.remover();

        if (!atendida) {
            // Se a senha não foi atendida, movê-la para a fila de não atendidos
            filaNaoAtendida.adicionar(senhaAtualC);
            System.out.println("Senha " + senhaAtualC.getId() + " movida para fila de não atendidos\n");
        } else {
            // Se a senha foi atendida
            System.out.println("Senha " + senhaAtualC.getId() + " foi atendida com sucesso!\n");
        }
        
        // Resetar o status de atendida para a próxima senha
        atendida = false;
    }
    
}

    // Método exibir o histórico completo de todas as senhas (atendidas e não atendidas)
    public void exibirTodasSenhas() {
        System.out.println("Exibindo senhas ainda na fila:");

        if(filaPreferencial.tamanho() == 0 && filaComum.tamanho() == 0){
            System.out.println("Sem senha nas filas preferencial e comum.");
        }

        // Verificar senhas na fila preferencial
        for (int i = 0; i < filaPreferencial.tamanho(); i++) {
            Senha senha = filaPreferencial.pegar(i);
            // Mostrar apenas senhas que não foram atendidas e não chegaram a 3 tentativas
            if (senha.getChamadas() < 3) {
                System.out.println("Senha " + senha.getId() + " (" + senha.getTipo() + ") - Tentativas: " + senha.getChamadas());
            }
        }

        // Verificar senhas na fila comum
        for (int i = 0; i < filaComum.tamanho(); i++) {
            Senha senha = filaComum.pegar(i);
            // Mostrar apenas senhas que não foram atendidas e não chegaram a 3 tentativas
            if (senha.getChamadas() < 3) {
                System.out.println("Senha " + senha.getId() + " (" + senha.getTipo() + ") - Tentativas: " + senha.getChamadas());
            }
        }

        // Verificar senhas na fila de não atendidos
        for (int i = 0; i < filaNaoAtendida.tamanho(); i++) {
            Senha senha = filaNaoAtendida.pegar(i);
            // Mostrar apenas senhas que não foram atendidas e não chegaram a 3 tentativas
            if (senha.getChamadas() < 3) {
                System.out.println("Senha " + senha.getId() + " (" + senha.getTipo() + ") - Tentativas: " + senha.getChamadas());
            }
        }
    }
        //Media de Horario
    public static String MediaHorario(LocalTime chegada, LocalTime atendida){
        if (atendida == null){
          System.out.println("Nenhuma senha foi atendida, calculando media de espera...");
          return "0 minutos e 0 segundos";
        }
        long segChegada = chegada.toSecondOfDay();
        long segAtendimento = atendida.toSecondOfDay();


        long diferencaSegundos = segAtendimento - segChegada;

        long minutos = diferencaSegundos / 60;
        long segundos = diferencaSegundos % 60;
    
        return minutos + " minutos e " + segundos + " segundos";
    }

    public void percentualSenha(){
        double percentSenhaP = 0;
        if (tamanhoTotalP > 0) {
            percentSenhaP = ((double) atendidasPreferenciais / tamanhoTotalP) * 100;
        }
    
        // Percentual de senhas comuns atendidas
        double percentSenhaC = 0;
        if (tamanhoTotalC > 0) {
            percentSenhaC = ((double) atendidasComuns / tamanhoTotalC) * 100;
        }
    
        System.out.println("\n Percentual de senhas preferenciais atendidas: " + percentSenhaP + "%");
        System.out.println("\n Percentual de senhas comuns atendidas: " + percentSenhaC + "%");
    }
    

        //Gerar Relatorio
    public void gerarRelatorio(){
      
        System.out.println("\n Número de senhas geradas (c/p): " +(tamanhoTotalC+tamanhoTotalP));

        System.out.println("\n Número de senhas atendidas: " +contAtendidas);

        System.out.println("\n Tempo médio de espera em minutos: " +MediaHorario(hChegada, hAtendida));

        System.out.println("\n Número total de senhas não atendidas: " +filaNaoAtendida.tamanho());

        percentualSenha();
    }
}
