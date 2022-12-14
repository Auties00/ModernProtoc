package it.auties.protobuf.parser.statement;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public abstract sealed class ProtobufObject<T extends ProtobufStatement> extends ProtobufStatement
        permits ProtobufDocument, ProtobufReservable, ProtobufOneOfStatement {
    private final Map<String, T> statements;
    public ProtobufObject(String name, String packageName, ProtobufObject<?> parent){
        super(name, packageName, parent);
        this.statements = new LinkedHashMap<>();
    }

    public Collection<T> statements() {
        return statements.values();
    }

    public ProtobufObject<T> addStatement(T statement){
        statements.put(statement.name(), statement);
        return this;
    }

    public Optional<T> getStatement(String name){
        return name == null ? Optional.empty()
                : Optional.ofNullable(statements.get(name));
    }

    @SuppressWarnings("unchecked")
    public <V extends ProtobufStatement> Optional<? extends V> getStatement(String name, Class<? extends V> clazz){
        return getStatement(name)
                .filter(entry -> clazz.isAssignableFrom(entry.getClass()))
                .map(entry -> (V) entry);
    }

    @SuppressWarnings("unused")
    public <V extends ProtobufStatement> Optional<? extends V> getStatementRecursive(String name, Class<? extends V> clazz){
        var child = getStatement(name, clazz);
        return child.isPresent() ? child : statements().stream()
                .filter(entry -> ProtobufObject.class.isAssignableFrom(entry.getClass()))
                .map(entry -> (ProtobufObject<?>) entry)
                .flatMap(entry -> entry.getStatement(name, clazz).stream())
                .findFirst();
    }
}
