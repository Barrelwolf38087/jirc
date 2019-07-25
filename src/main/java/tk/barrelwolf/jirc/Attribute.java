package tk.barrelwolf.jirc;

import org.jetbrains.annotations.Nullable;

public enum Attribute {
    NICK(null);

    private String defaultValue;

    private Attribute(@Nullable String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() { return defaultValue; }
}
