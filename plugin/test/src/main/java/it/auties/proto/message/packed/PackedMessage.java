package it.auties.proto.message.packed;

import it.auties.protobuf.annotation.ProtobufProperty;
import it.auties.protobuf.model.ProtobufMessage;

import java.util.ArrayList;

import static it.auties.protobuf.model.ProtobufType.UINT32;

public record PackedMessage(
        @ProtobufProperty(index = 1, type = UINT32, repeated = true, packed = true)
        ArrayList<Integer> content
) implements ProtobufMessage {

}
