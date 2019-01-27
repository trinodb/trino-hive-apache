package org.apache.hadoop.hive.ql.io.parquet.timestamp;

import org.apache.hadoop.hive.common.type.Timestamp;

import static java.lang.Math.toIntExact;
import static java.util.concurrent.TimeUnit.SECONDS;

public final class NanoTimeUtils
{
    private NanoTimeUtils() {}

    private static final int JULIAN_EPOCH_OFFSET_DAYS = 2_440_588;

    public static NanoTime getNanoTime(Timestamp timestamp, @SuppressWarnings("unused") boolean ignored)
    {
        int epochDay = toIntExact(SECONDS.toDays(timestamp.toEpochSecond()));
        int julianDay = JULIAN_EPOCH_OFFSET_DAYS + epochDay;

        long timeOfDaySeconds = timestamp.toEpochSecond() % 86400;
        long timeOfDayNanos = SECONDS.toNanos(timeOfDaySeconds) + timestamp.getNanos();

        return new NanoTime(julianDay, timeOfDayNanos);
    }
}
