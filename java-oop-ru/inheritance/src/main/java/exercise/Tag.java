package exercise;

import java.util.Map;

// BEGIN
public class Tag {

    private final String name;
    private Map<String, String> attributes;

    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    protected final String getName() {
        return name;
    }

    protected final Map<String, String> getAttributes() {
        return attributes;
    }

    protected final void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
// END
