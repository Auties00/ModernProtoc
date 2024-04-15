package it.auties.protobuf.serialization.property;

import it.auties.protobuf.annotation.ProtobufProperty;

import javax.lang.model.element.Element;

public record ProtobufPropertyStub(int index, String name, Element accessor, ProtobufPropertyType type, boolean required, boolean packed) {
    public ProtobufPropertyStub(String name, Element accessor, ProtobufPropertyType type, ProtobufProperty annotation) {
        this(
                annotation.index(),
                name,
                accessor,
                type,
                annotation.required(),
                annotation.packed()
        );
    }
}
