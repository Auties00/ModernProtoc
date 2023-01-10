open module it.auties.protobuf.serializer.jackson {
    requires static lombok;
    requires static java.logging;
    requires it.auties.protobuf.base;
    requires it.auties.protobuf.serializer.base;
    requires com.fasterxml.jackson.datatype.jdk8;
    requires com.fasterxml.jackson.databind;
    requires jdk.unsupported;

    exports it.auties.protobuf.serialization.jackson;
}