package labirinto;

public enum Cor {
    BRANCO("branco"),
    PRETO("preto"),
    AMARELO("amarelo"),
    AZUL("azul"),
    VERMELHO("vermelho"),
    VERDE("verde"),
    AZULCLARO("azulClaro"),
    MAGENTA("magenta");
	
    private String descricao;

    Cor(String descricao){
            this.descricao = descricao;
    }

    public String getDescricao() { return descricao;}   
}
