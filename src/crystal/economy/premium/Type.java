package crystal.economy.premium;

public enum Type {
	
	Vip("vip"),
	Svip("svip"),;
	
	private String node;
	
	Type(String type) {
		this.node = type;
	}
	
	public String toString()
	{
		return node;
	}
}
