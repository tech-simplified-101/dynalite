package com.techsimplified101.dynalite.engine;

import com.techsimplified101.dynalite.memtable.MemTable;

/**
 * The type Lsm kv store.
 */
public class LsmKVStore implements KVStore {

    private final MemTable memTable;

    /**
     * Instantiates a new Lsm kv store.
     */
    public LsmKVStore() {
        this.memTable = new MemTable();
    }


    @Override
    public void put(String key, byte[] value) {
        memTable.put(key, value);
    }

    @Override
    public byte[] get(String key) {
        return memTable.get(key);
    }

    @Override
    public void delete(String key) {
        memTable.delete(key);
    }
}
