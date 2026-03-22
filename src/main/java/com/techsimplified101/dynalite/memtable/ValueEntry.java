package com.techsimplified101.dynalite.memtable;

/**
 * The type Value entry.
 */
public record ValueEntry(byte[] value, boolean isTombstone) {}
