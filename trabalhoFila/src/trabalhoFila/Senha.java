package trabalhoFila;
import java.time.LocalTime;
public class Senha {

	  //atributos
		private int id; //id da senha
		private String tipo; //preferencial ou nao
		private int chamadas; //tentativa de chamada
		private LocalTime horaChegada; //horaChegada fazer o metodo que registra
		private LocalTime horaAtendida; //hora que foi atendida
		private boolean status; //se foi ou nao atendida, dps jogamos na fila nao atendida
		
		public Senha(int numero, String tipo) {
	        this.id = numero;
	        this.tipo = tipo;
	        this.chamadas = 0;
	        this.horaChegada = LocalTime.now();
	    }
		

		//métodos get e incrementar
		public int getId() {
			return id;
		}
		
		public String getTipo() {
			return tipo;
		}
		
		public int getChamadas() {
			return chamadas;
		}
		
		public void incrementarChamadas() {
			this.chamadas++;
		}
		
		public LocalTime getHoraChegada() {
			return horaChegada;
		}

		public LocalTime getHoraAtenida() {
			return horaAtendida;
		}

		public boolean isStatus() {
			return status;
		}
		
		//metodo registrar hora da chegada
		public void registrarChegada() {
			this.horaChegada = LocalTime.now();
		}
		
		//metodo registrar hora do atendimento
		public void registrarAtendimento() {
			this.horaAtendida = LocalTime.now();
		}

	}


