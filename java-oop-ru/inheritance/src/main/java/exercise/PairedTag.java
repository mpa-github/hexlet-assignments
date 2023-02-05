package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {

    private String body;
    private List<Tag> childTagList;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> childTagList) {
        super(name, attributes);
        this.body = body;
        this.childTagList = childTagList;
    }

    @Override
    public String toString() {
        StringBuilder attributes = new StringBuilder();
        this.getAttributes()
            .forEach((k, v) -> attributes.append(" ").append(k).append("=").append("\"").append(v).append("\""));
        StringBuilder childTags = new StringBuilder();
        this.childTagList.forEach(tag -> childTags.append(tag.toString()));
        return "<" + this.getName() + attributes + ">" + childTags + body + "</" + this.getName() + ">";
    }
}
// END
