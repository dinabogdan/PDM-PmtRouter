package org.pdm.ib.pmt.router.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;

@Converter
public class BigDecimalConverter implements AttributeConverter<BigDecimal, Long> {
    @Override
    public Long convertToDatabaseColumn(BigDecimal value) {
        if (value == null) {
            return null;
        } else {
            return value.multiply(BigDecimal.valueOf(100)).longValue();
        }
    }

    @Override
    public BigDecimal convertToEntityAttribute(Long dbData) {
        if(dbData == null){
            return null;
        } else {
            return new BigDecimal(dbData).divide(BigDecimal.valueOf(100));
        }
    }
}
