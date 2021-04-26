package pp.spring.cookbook.ingredient;

import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public void save(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }
}