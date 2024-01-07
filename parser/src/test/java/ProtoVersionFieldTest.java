import it.auties.protobuf.parser.ProtobufParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Objects;

public class ProtoVersionFieldTest {
    @Test
    public void testProto2() throws URISyntaxException, IOException {
        var proto2Source = ClassLoader.getSystemClassLoader().getResource("proto2.proto");
        Objects.requireNonNull(proto2Source);
        var parser = new ProtobufParser();
        var document = parser.parseOnly(Path.of(proto2Source.toURI()));
        System.out.println(document);
    }

    @Test
    public void testProto3() throws URISyntaxException, IOException {
        var proto2Source = ClassLoader.getSystemClassLoader().getResource("proto3.proto");
        Objects.requireNonNull(proto2Source);
        var parser = new ProtobufParser();
        var document = parser.parseOnly(Path.of(proto2Source.toURI()));
        System.out.println(document);
    }
}
