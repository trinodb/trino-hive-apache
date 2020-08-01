package org.apache.hadoop.hive.metastore;

/**
 * Coral depends on a different version of Hive, which has the MetaStoreUtils to a different location. This change
 * allows Coral to work across Hive versions.
 */
public class MetaStoreUtils extends HiveMetaStoreUtils {}
