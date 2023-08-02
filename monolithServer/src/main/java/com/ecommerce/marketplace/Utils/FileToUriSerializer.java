package com.ecommerce.marketplace.Utils;

import com.ecommerce.marketplace.Entity.ProductImageFile;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

public class FileToUriSerializer extends JsonSerializer<ProductImageFile> {
    /**
     * @param productImageFile Value to serialize; cannot be null.
     * @param jsonGenerator    Generator used to output resulting Json content
     * @param serializers      Provider that can be used to get serializers for
     *                         serializing Objects value contains, if any.
     * @throws IOException
     */
    @Override
    public void serialize(ProductImageFile productImageFile, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(String.valueOf(productImageFile.getId()))
                .toUriString();
        jsonGenerator.writeString(fileDownloadUri);
    }
}
