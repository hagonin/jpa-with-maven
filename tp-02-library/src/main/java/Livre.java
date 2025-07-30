import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Livre")
public class Livre {
    
    @Id
    private Integer id;
    
    public Livre() {}
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "Livre{id=" + id + "}";
    }
}