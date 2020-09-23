package org.apache.hadoop.hive.ql.io.parquet.timestamp;

import org.apache.hadoop.hive.common.type.Timestamp;

import static java.lang.Math.floorDiv;
import static java.lang.Math.floorMod;
import static java.lang.Math.toIntExact;
import static java.util.concurrent.TimeUnit.SECONDS;

public final class NanoTimeUtils
{
    private NanoTimeUtils() {}

    private static final int JULIAN_EPOCH_OFFSET_DAYS = 2_440_588;
    private static final long SECONDS_PER_DAY = 86400L;

    public static NanoTime getNanoTime(Timestamp timestamp, @SuppressWarnings("unused") boolean ignored)
    {
        long epochSeconds = timestamp.toEpochSecond();
        int epochDay = toIntExact(floorDiv(epochSeconds, SECONDS_PER_DAY));
        int julianDay = JULIAN_EPOCH_OFFSET_DAYS + epochDay;

        long timeOfDaySeconds = floorMod(epochSeconds, SECONDS_PER_DAY);
        long timeOfDayNanos = SECONDS.toNanos(timeOfDaySeconds) + timestamp.getNanos();

        return new NanoTime(julianDay, timeOfDayNanos);
    }
}
