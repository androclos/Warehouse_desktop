package Helper;

/**
 *Termeket reprezentalo objektum
 */
public class ProductContainer {
    
    private int id;
    private String name;
    private String type;
    private String comment;
    private int amount;

    public ProductContainer(int id, String name, String type, String comment, int amount) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.comment = comment;
        this.amount = amount;
    }
    
    @Override
    public int hashCode(){
    
        return id*name.hashCode()*type.hashCode();
    
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductContainer other = (ProductContainer) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    public ProductContainer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String tname) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
       
    
}
