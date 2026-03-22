package com.techsimplified101.dynalite.engine;

public interface KVStore {
    void put(String key, byte[] value);

    byte[] get(String key);

    void delete(String key);

}
