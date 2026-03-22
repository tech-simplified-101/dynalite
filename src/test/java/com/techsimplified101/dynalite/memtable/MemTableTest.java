package com.techsimplified101.dynalite.memtable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemTableTest {

    private MemTable memTable;

    @BeforeEach
    void setUp() {
        memTable = new MemTable();
    }

    @Test
    void shouldPutAndGetValue() {
        String key = "key1";
        byte[] value = "value1".getBytes();

        memTable.put(key, value);

        byte[] result = memTable.get(key);

        assertNotNull(result);
        assertArrayEquals(value, result);
    }

    @Test
    void shouldReturnNullForNonExistingKey() {
        assertNull(memTable.get("unknown"));
    }

    @Test
    void shouldOverwriteExistingKey() {
        String key = "key1";
        byte[] value1 = "value1".getBytes();
        byte[] value2 = "value2".getBytes();

        memTable.put(key, value1);
        memTable.put(key, value2);

        byte[] result = memTable.get(key);

        assertArrayEquals(value2, result);
    }

    @Test
    void shouldDeleteKeyAndReturnNull() {
        String key = "key1";
        byte[] value = "value1".getBytes();

        memTable.put(key, value);
        memTable.delete(key);

        assertNull(memTable.get(key));
    }

    @Test
    void shouldAllowReinsertAfterDelete() {
        String key = "key1";
        byte[] value1 = "value1".getBytes();
        byte[] value2 = "value2".getBytes();

        memTable.put(key, value1);
        memTable.delete(key);
        memTable.put(key, value2);

        byte[] result = memTable.get(key);

        assertArrayEquals(value2, result);
    }

    @Test
    void shouldTrackSizeBytesForPut() {
        String key = "key";
        byte[] value = "value".getBytes();

        memTable.put(key, value);

        long expectedSize = key.length() + value.length;
        assertEquals(expectedSize, memTable.getSizeBytes());
    }

    @Test
    void shouldTrackSizeBytesForDelete() {
        String key = "key";

        memTable.delete(key);

        long expectedSize = key.length();
        assertEquals(expectedSize, memTable.getSizeBytes());
    }

    @Test
    void shouldAccumulateSizeBytesAcrossOperations() {
        String key1 = "k1";
        byte[] value1 = "v1".getBytes();

        String key2 = "k2";
        byte[] value2 = "v2".getBytes();

        memTable.put(key1, value1); // k1 + v1
        memTable.put(key2, value2); // k2 + v2
        memTable.delete(key1);      // k1

        long expectedSize =
                key1.length() + value1.length +
                        key2.length() + value2.length +
                        key1.length();

        assertEquals(expectedSize, memTable.getSizeBytes());
    }

    @Test
    void shouldHandleEmptyValue() {
        String key = "key";
        byte[] value = new byte[0];

        memTable.put(key, value);

        byte[] result = memTable.get(key);

        assertNotNull(result);
        assertEquals(0, result.length);
    }
}