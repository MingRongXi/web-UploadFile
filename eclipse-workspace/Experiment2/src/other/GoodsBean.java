package other;
//将类的各种信息封装到javabean里
public class GoodsBean {
//	public byte[] image;
	private String path;
	private String id;
	private String name;
	private String product;
	private String type;
	private String typeNumber;
	private String description;
	
//	public byte[] getImage() {
//		return image;
//	}
//	public void setImage(String path) {
//		//FileOutputStream
//	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeNumber() {
		return typeNumber;
	}
	public void setTypeNumber(String typeNumber) {
		this.typeNumber = typeNumber;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
