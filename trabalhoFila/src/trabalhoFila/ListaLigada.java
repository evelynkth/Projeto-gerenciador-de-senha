package trabalhoFila;

public class ListaLigada {
    private No cabeca;
    private No cauda;
    private int tamanho;

    public void ListaLigada(){
        this.cabeca = null;
        this.cauda = null;
        this.tamanho = 0;
    }

    public void adicionarNoInicio(Senha dado){

        No noNovo = new No(dado);
        if (cabeca == null){
            cabeca = noNovo;
            cauda = noNovo;
        }
        else {
            noNovo.proximo = cabeca;
            cabeca.anterior = noNovo;
            cabeca = noNovo;
        }
        tamanho++;
    }

    public void adicionarNoFinal(Senha dado){
        No noNovo = new No(dado);
        if (cabeca == null){
            cabeca = noNovo;
            cauda = noNovo;
        }
        else {
            noNovo.anterior = cauda;
            cauda.proximo = noNovo;
            cauda = noNovo;
        }
        tamanho++;
    }

    public void removerNoInicio(){

        if (cabeca == null){
            return;
        }
        if (cabeca == cauda){
            cabeca = null;
            cauda = null;
        }
        else{
            cabeca = cabeca.proximo;
            cabeca.anterior = null;
        }
        tamanho--;
    }

    public void removerNoFinal(){
        if (cabeca == null){
            return;
        }
        if (cabeca == cauda){
            cabeca = null;
            cauda = null;
        }
        else{
            cauda = cauda.anterior;
            cauda.proximo = null;
        }
        tamanho--;
    }

    public void adicionarNaPosicao (Senha dado, int posicaoAdicionar){
        if (posicaoAdicionar < 0 || posicaoAdicionar > tamanho){
            throw new IndexOutOfBoundsException("Posicao invalida: " + posicaoAdicionar);
        }

        if (posicaoAdicionar == 0){
            adicionarNoInicio(dado);
            return;
        }

        if (posicaoAdicionar == tamanho){
            adicionarNoFinal(dado);
            return;
        }

        No novoNo = new No(dado);
        
        No atual = cabeca;
        for (int i=0; i < posicaoAdicionar; i++){
            atual = atual.proximo;
        }
        novoNo.proximo = atual;
        novoNo.anterior = atual.anterior;
        atual.anterior.proximo = novoNo;
        atual.anterior = novoNo;
        tamanho++;
    }

    public void removerNaPosicao(int posicaoRemover){

        if (posicaoRemover < 0 || posicaoRemover >= tamanho){
            throw new IndexOutOfBoundsException("Posicao invalida: " + posicaoRemover);
        }
        if (posicaoRemover == 0){
            removerNoInicio();
            return;
        }
        if (posicaoRemover == (tamanho - 1)){
            removerNoFinal();
            return;
        }
        No atual = cabeca;
        for (int i=0; i < posicaoRemover; i++){
            atual = atual.proximo;
        }
        atual.anterior.proximo = atual.proximo;
        atual.proximo.anterior = atual.anterior;
        tamanho--;
       
    }
    
    public boolean contem(Senha dadoBuscado){
        No atual = cabeca;
        while (atual != null) {
           if (atual.dado == dadoBuscado) {
                return true;
           }
           atual = atual.proximo;
        }
        return false;
    }

    public Senha pegarNaPosicao(int posicao){
        if (posicao < 0 || posicao >= tamanho){
            throw new IndexOutOfBoundsException("Posicao invalida: " + posicao);
        }
        No atual = cabeca;
        for (int i=0; i<posicao; i++){
            atual = atual.proximo;
        }
        return atual.dado;
    }

    public int tamanho(){
        return this.tamanho;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        No atual = cabeca;
        while (atual != null) {
            sb.append(atual.dado);
            if (atual.proximo != null) {
                sb.append(", ");
            }
            atual = atual.proximo;
        }
        sb.append("]");
        return sb.toString();
    }


}

