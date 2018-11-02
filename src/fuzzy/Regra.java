package fuzzy;

public enum Regra {
        INICIO("inicio"),
	AOSUL("aoSul"),
	DOLESTEAOSUL("doLesteAoSul"),
	DOOESTEAOSUL("doOesteAoSul"),
	AOOESTE("aoOeste"),
	DOSULAOOESTE("doSulAoOeste"),
	DOLESTEAOOESTE("doLesteAoOeste"),
	AOLESTE("aoLeste"),
	DOSULAOLESTE("doSulAoLeste"),
	DOOESTEAOLESTE("doOesteAoLeste"),
	DOLESTEAONORTE("doLesteAoNorte"),
	AONORTE("aoNorte"),
	DONORTEAOOESTE("doNorteAoOeste"),
	DONORTEAOLESTE("doNorteAoleste"),
	PARA("para");
    
    private String descricao;
	
	Regra(String descricao){
		this.descricao = descricao;
	}
	
    public String getDescricao() { return descricao;}
}
