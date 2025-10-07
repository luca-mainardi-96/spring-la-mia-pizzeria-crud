package esercizio.pizzeria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String photo;
    private Double price;
    
    //Getters
    
    public Integer getId(){
        return id;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getPhoto() {
        return photo;
    }
    public Double getPrice() {
        return price;
    }

    //Setters

    public void setId(Integer id){
        this.id = id;
    }

    public void setNome(String name) {
        this.name = name;
    }
    public void setDescrizione(String description) {
        this.description = description;
    }
    public void setFoto(String photo) {
        this.photo = photo;
    }
    public void setPrezzo(Double price) {
        this.price = price;
    }
                
}
