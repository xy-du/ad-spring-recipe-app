package dxy.springframework.adspringrecipeapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @author AD
 * @date 2020/10/17
 */
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
