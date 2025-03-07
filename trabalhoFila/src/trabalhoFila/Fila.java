package trabalhoFila;

public class Fila {
    
    private ListaLigada fila;

    public Fila(){
        this.fila = new ListaLigada();
    }

    public void adicionar(Senha dado){
        fila.adicionarNoFinal(dado);
    }

    public void remover(){
        if (fila.tamanho() == 0){
            throw new IllegalStateException("Fila Vazia");
        }
        fila.removerNoInicio();
    }

    public boolean contem(Senha dadoBuscado){
        return fila.contem(dadoBuscado);
    }
    
    public Senha pegar(int posicao) {
    	return fila.pegarNaPosicao(posicao);
    }

    public int tamanho(){
        return fila.tamanho();
    }

    public boolean estaVazia(){
        return (fila.tamanho() == 0);
    }

    @Override
    public String toString(){
        return fila.toString();
    }
}
