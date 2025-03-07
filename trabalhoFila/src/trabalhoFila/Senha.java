package trabalhoFila;

public class Senha {

	//atributos
	private int id; //id da senha
	private String tipo; //preferencial ou nao
	private int chamadas; //tentativa de chamada
	private String horaChegada; //horaChegada fazer o metodo que registra
	private String horaAtenida; //hora que foi atendida
	private boolean status; //se foi ou nao atendida, dps jogamos na fila nao atendida
	
	public Senha(int numero, String tipo) {
        this.id = numero;
        this.tipo = tipo;
        this.chamadas = 0;
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
	
	public String getHoraChegada() {
		return horaChegada;
	}

	public String getHoraAtenida() {
		return horaAtenida;
	}

	public boolean isStatus() {
		return status;
	}


	}


