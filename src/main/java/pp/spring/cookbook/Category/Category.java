package pp.spring.cookbook.Category;

public enum Category {
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    SUPPER("Supper"),
    DESSERT("Desser"),
    DRINK("Drink");

    private String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}