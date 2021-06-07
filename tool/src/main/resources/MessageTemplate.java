<% if(!pack.empty && imports) { %>
package ${pack};
<% } %>

<% if(imports) { %>
import com.fasterxml.jackson.annotation.*;
import it.auties.protobuf.model.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.*;
<% } %>

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Accessors(fluent = true)
public class ${message.name} implements ProtobufMessage {
    <%
        def types = [:]
        def data = []
        for(statement in message.statements) {
            if(statement instanceof it.auties.protobuf.ast.FieldStatement) {
                data.add("""
                    @JsonProperty(value = "${statement.index}", required = ${statement.required})
                    ${statement.repeated ? "@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)" : ""}
                    private ${statement.type} ${it.auties.protobuf.utils.ProtobufUtils.toValidIdentifier(statement.name)};
                """)
            types[statement.index] = statement.rawType
            } else if(statement instanceof it.auties.protobuf.ast.OneOfStatement) {
                for(oneOf in statement.statements) {
                    data.add("""
                        @JsonProperty(value = "${oneOf.index}", required = false)
                        private ${oneOf.type} ${oneOf.name};
                    """)
                    types[oneOf.index] = oneOf.rawType
                }

                data.push("""
                    public ${statement.name} ${statement.nameAsField}Case() {
                        ${it.auties.protobuf.utils.ProtobufUtils.generateCondition(statement.name, statement.statements.iterator())}
                    }
                """)

                data.push(new it.auties.protobuf.schema.OneOfSchemaCreator(statement, pack, false).createSchema())
            } else if(statement instanceof it.auties.protobuf.ast.MessageStatement) {
                data.push(new it.auties.protobuf.schema.MessageSchemaCreator(statement, pack, false).createSchema())
            } else if(statement instanceof it.auties.protobuf.ast.EnumStatement) {
                data.push(new it.auties.protobuf.schema.EnumSchemaCreator(statement, pack, false).createSchema())
            }
        }
    %>

    <% for(statement in data.reverse()){ %>
        ${statement}
    <% } %>

    @Override
    public Map<Integer, Class<?>> types(){
        return Map.ofEntries(${types.collect{'Map.entry(' + it.key + ', ' + it.value + '.class)'}.collect().join(', ')});
    }
}
