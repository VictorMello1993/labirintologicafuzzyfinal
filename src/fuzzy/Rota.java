package fuzzy;

public enum Rota {
    SUL("sul"),
    OESTE("oeste"),
    LESTE("leste"),
    NORTE("norte"),
    SEMROTA("semRota");

private String descricao;

Rota(String descricao){
    this.descricao = descricao;
}

public String getDescricao() { return descricao;}

}

