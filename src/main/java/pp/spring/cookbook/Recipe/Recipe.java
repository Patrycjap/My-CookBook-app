package pp.spring.cookbook.Recipe;

import pp.spring.cookbook.Category.Category;
import pp.spring.cookbook.Ingredient.Ingredient;

import javax.persistence.*;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "varchar(500)")
    private String description;
    private String preparationTime;
    @Column(columnDefinition = "boolean default false")
    private Boolean favorite = false;
    private int portions;
    @Enumerated(EnumType.STRING)
    private Category category;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE)
    private List<Ingredient> ingredients;
    private int countLike = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getCountLike() {
        return countLike;
    }

    public void setCountLike(int like) {
        this.countLike = like;
    }
}