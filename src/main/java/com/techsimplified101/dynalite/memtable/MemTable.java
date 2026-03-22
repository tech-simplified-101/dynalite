package com.techsimplified101.dynalite.memtable;

import java.util.TreeMap;

/**
 * The type Mem table.
 */
public class MemTable {
    private final TreeMap<String, ValueEntry> store;
    private long sizeBytes;

    /**
     * Instantiates a new Mem table.
     */
    public MemTable() {
        this.store = new TreeMap<>();
        this.sizeBytes = 0;
    }

    /**
     * Put.
     *
     * @param key   the key
     * @param value the value
     */
    public void put(final String key, final byte[] value) {
        this.store.put(key, new ValueEntry(value, false));
        sizeBytes += key.length() + value.length;
    }

    /**
     * Delete.
     *
     * @param key the key
     */
    public void delete(final String key) {
        this.store.put(key, new ValueEntry(null, true));
        sizeBytes += key.length();
    }

    /**
     * Get byte [ ].
     *
     * @param key the key
     * @return the byte [ ]
     */
    public byte[] get(final String key) {
        var valueEntry = this.store.get(key);
        if(valueEntry == null || valueEntry.isTombstone()) {
            return null;
        }
        return valueEntry.value();
    }

    /**
     * Gets size bytes.
     *
     * @return the size bytes
     */
    public long getSizeBytes() {
        return this.sizeBytes;
    }
}
