package it.auties.protobuf.base;

public class Protobuf {
    @SuppressWarnings("unchecked")
    public static <T> T readMessage(byte[] message, Class<T> clazz) {
        try {
            var method = clazz.getMethod("ofProtobuf", byte[].class);
            return (T) method.invoke(null, new Object[]{message});
        }catch (Throwable exception){
            throw new ProtobufDeserializationException(exception);
        }
    }

    public static byte[] writeMessage(ProtobufMessage object) {
        try {
            var method = object.getClass().getMethod("toEncodedProtobuf");
            return (byte[]) method.invoke(object);
        }catch (Throwable exception){
            throw new ProtobufSerializationException(exception);
        }
    }
}
