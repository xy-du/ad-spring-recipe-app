package dxy.springframework.adspringrecipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author AD
 * @date 2020/10/17
 */
@Data
@Entity
@EqualsAndHashCode(exclude = {"recipes"}) //add this since there're some circular issue with the equalsandhadhashcode methos
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes=new HashSet<>();

}
