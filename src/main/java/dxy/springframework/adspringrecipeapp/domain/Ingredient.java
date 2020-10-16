package dxy.springframework.adspringrecipeapp.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author AD
 * @date 2020/10/16
 */
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;

    //private UnitOfMeasure uom;

    @ManyToOne
    private Recipe recipe;

    //EAGER here is the default behavior for OneToOne, but it's good for showing the intent
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public UnitOfMeasure getUom() {
        return uom;
    }

    public void setUom(UnitOfMeasure uom) {
        this.uom = uom;
    }
}
