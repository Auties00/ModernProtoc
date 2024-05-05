package it.auties.proto.object.enumeration.defaultValue;

import it.auties.protobuf.annotation.ProtobufMessage;
import it.auties.protobuf.annotation.ProtobufProperty;
import it.auties.protobuf.model.ProtobufType;

@ProtobufMessage
public record WrapperMessage(
        @ProtobufProperty(index = 1, type = ProtobufType.OBJECT)
        EnumType enumType
) {

}
