package dxy.springframework.adspringrecipeapp.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author AD
 * @date 2020/10/16
 */
@Data
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNote;

}
