package com.techsimplified101.dynalite.engine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LsmKVStoreTest {

    @Test
    void shouldPutAndGetValue() {
        LsmKVStore store = new LsmKVStore();

        String key = "key1";
        byte[] value = "value1".getBytes();

        store.put(key, value);

        byte[] result = store.get(key);

        assertNotNull(result);
        assertArrayEquals(value, result);
    }

    @Test
    void shouldReturnNullForMissingKey() {
        LsmKVStore store = new LsmKVStore();

        assertNull(store.get("unknown"));
    }

    @Test
    void shouldOverwriteValue() {
        LsmKVStore store = new LsmKVStore();

        String key = "key1";
        byte[] value1 = "value1".getBytes();
        byte[] value2 = "value2".getBytes();

        store.put(key, value1);
        store.put(key, value2);

        byte[] result = store.get(key);

        assertArrayEquals(value2, result);
    }

    @Test
    void shouldDeleteKey() {
        LsmKVStore store = new LsmKVStore();

        String key = "key1";
        byte[] value = "value1".getBytes();

        store.put(key, value);
        store.delete(key);

        assertNull(store.get(key));
    }

    @Test
    void shouldAllowReinsertAfterDelete() {
        LsmKVStore store = new LsmKVStore();

        String key = "key1";
        byte[] value1 = "value1".getBytes();
        byte[] value2 = "value2".getBytes();

        store.put(key, value1);
        store.delete(key);
        store.put(key, value2);

        byte[] result = store.get(key);

        assertArrayEquals(value2, result);
    }

    @Test
    void shouldHandleEmptyValue() {
        LsmKVStore store = new LsmKVStore();

        String key = "key1";
        byte[] value = new byte[0];

        store.put(key, value);

        byte[] result = store.get(key);

        assertNotNull(result);
        assertEquals(0, result.length);
    }
}