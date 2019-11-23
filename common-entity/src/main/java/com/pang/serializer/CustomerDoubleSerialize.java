package com.pang.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * @author pang
 * @version V1.0
 * @ClassName: CustomerDoubleSerialize
 * @Package com.pang.mall.common.serializer
 * @description:
 * @date 2019/11/17 15:38
 */
public class CustomerDoubleSerialize extends JsonSerializer<Double> {
    private DecimalFormat df = new DecimalFormat("0.00");
    @Override
    public void serialize(Double aDouble, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(aDouble != null) {
            jsonGenerator.writeString(df.format(aDouble));
        }
    }
}
