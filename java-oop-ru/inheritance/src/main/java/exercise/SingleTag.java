package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {

    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }

    @Override
    public String toString() {
        StringBuilder attributes = new StringBuilder();
        this.getAttributes()
            .forEach((k, v) -> attributes.append(" ").append(k).append("=").append("\"").append(v).append("\""));
        return "<" + this.getName() + attributes + ">";
    }
}
// END
