package fuzzy;

public enum Distancia {

		BLOQUEADA("bloqueada"),
		MUITOPERTO("muitoPerto"),
		PERTO("perto"),
		MEDIA("media"),
		LONGE("longe"),
		MUITOLONGE("muitoLonge");
    	
    	private String descricao;
    	
    	Distancia(String descricao){
    		this.descricao = descricao;
    	}
    	
    	public String getDescrocao() { return descricao;}
}
