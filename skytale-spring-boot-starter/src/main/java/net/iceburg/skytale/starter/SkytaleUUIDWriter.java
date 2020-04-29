package net.iceburg.skytale.starter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.UUID;


public class SkytaleUUIDWriter {
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Returns a Jackson ObjectWriter that appends the 'uuid' field
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public ObjectWriter getUUIDWriter(Serializable obj, UUID uuid) throws JsonProcessingException {
        objectMapper.addMixIn(obj.getClass(), UUIDMixin.class);

        return objectMapper.writerFor(obj.getClass())
                .withAttribute("uuid", uuid);
    }

    @JsonAppend(
            attrs = {
                    @JsonAppend.Attr(value = "uuid")
            }
    )
    public static class UUIDMixin {}
}
