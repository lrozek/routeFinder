package pl.lrozek.router.controller.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import pl.lrozek.router.domain.ids.Id;

import java.io.IOException;

public class IdSerializer extends StdSerializer<Id>
{
    protected IdSerializer()
    {
        super(Id.class);
    }

    @Override
    public void serialize(Id id, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException
    {
        jsonGenerator.writeString(String.format("%s_%s", id.from().value(), id.to().value()));
    }
}
