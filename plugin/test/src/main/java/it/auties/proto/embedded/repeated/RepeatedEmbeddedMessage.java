package it.auties.proto.embedded.repeated;

import it.auties.protobuf.annotation.ProtobufProperty;
import it.auties.protobuf.model.ProtobufMessage;
import it.auties.protobuf.model.ProtobufType;

import java.util.ArrayList;

public record RepeatedEmbeddedMessage(
        @ProtobufProperty(index = 1, type = ProtobufType.INT32, repeated = true)
        ArrayList<Integer> content
) implements ProtobufMessage {

}
