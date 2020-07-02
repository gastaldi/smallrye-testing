package io.smallrye.testing.logging;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import io.smallrye.common.constraint.Assert;

public class InMemoryLogHandler extends Handler {

    public InMemoryLogHandler(Predicate<LogRecord> predicate) {
        Assert.checkNotNullParam("predicate", predicate);
        setFilter(predicate::test);
        setLevel(Level.FINE);
    }

    final List<LogRecord> records = new ArrayList<>();

    @Override
    public void publish(LogRecord record) {
        if (!isLoggable(record)) {
            return;
        }

        records.add(record);
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() throws SecurityException {
        records.clear();
    }
}
