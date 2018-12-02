package pc.dto;

public class CheckDTO {
	private String name;
	private String pas;
	public CheckDTO(String name, String pas) {
		super();
		this.name = name;
		this.pas = pas;
	}
	public CheckDTO(){}
	@Override
	public String toString() {
		return "CheckDTO [name=" + name + ", pas=" + pas + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPas() {
		return pas;
	}
	public void setPas(String pas) {
		this.pas = pas;
	}
}
