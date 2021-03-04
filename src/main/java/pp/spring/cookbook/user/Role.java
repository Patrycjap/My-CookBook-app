package pp.spring.cookbook.user;

public enum Role {

    ROLE_USER("User"),
    ROLE_ADMIN("Admin");

    private String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}