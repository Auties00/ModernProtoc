import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
<% } %>

@AllArgsConstructor
@Accessors(fluent = true)
public enum ${enm.name} {
    ${enm.statements.collect{ it.name + '(' + it.index + ')'}.join(', ')};

    @Getter
    private final int index;

    @JsonCreator
    public static ${enm.name} forIndex(int index){
        return Arrays.stream(values()).filter(entry -> entry.index() == index).findFirst().orElse(null);
    }
}
