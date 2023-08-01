package it.auties.protobuf.model;

public interface ProtobufMessage extends ProtobufObject {
    default byte[] toEncodedProtobuf(@SuppressWarnings("unused") ProtobufVersion version) {
        throw new UnsupportedOperationException("stub");
    }
}
