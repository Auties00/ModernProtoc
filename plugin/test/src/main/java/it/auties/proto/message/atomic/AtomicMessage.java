package it.auties.proto.message.atomic;

import it.auties.protobuf.annotation.ProtobufProperty;
import it.auties.protobuf.model.ProtobufMessage;

import java.util.Objects;
import java.util.concurrent.atomic.*;

import static it.auties.protobuf.model.ProtobufType.*;

public record AtomicMessage(
        @ProtobufProperty(index = 1, type = STRING)
        AtomicReference<String> atomicString,
        @ProtobufProperty(index = 2, type = UINT32)
        AtomicInteger atomicInteger,
        @ProtobufProperty(index = 3, type = UINT64)
        AtomicLong atomicLong,
        @ProtobufProperty(index = 4, type = BOOL)
        AtomicBoolean atomicBoolean
) implements ProtobufMessage {
        @Override
        public boolean equals(Object obj) {
                return obj instanceof AtomicMessage other
                        && Objects.equals(atomicString.get(), other.atomicString().get())
                        && Objects.equals(atomicInteger.get(), other.atomicInteger().get())
                        && Objects.equals(atomicLong.get(), other.atomicLong().get())
                        && Objects.equals(atomicBoolean.get(), other.atomicBoolean().get());
        }
}
