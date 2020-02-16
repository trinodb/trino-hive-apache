package org.apache.parquet.format.converter;

import org.apache.parquet.format.ConvertedType;
import org.apache.parquet.format.SchemaElement;
import org.apache.parquet.schema.LogicalTypeAnnotation;

public final class ParquetMetadataConverterUtil
{
    private ParquetMetadataConverterUtil() {}

    public static LogicalTypeAnnotation getLogicalTypeAnnotation(ParquetMetadataConverter parquetMetadataConverter, ConvertedType convertedType, SchemaElement schemaElement)
    {
        return parquetMetadataConverter.getLogicalTypeAnnotation(convertedType, schemaElement);
    }
}
