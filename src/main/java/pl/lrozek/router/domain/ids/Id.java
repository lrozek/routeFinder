package pl.lrozek.router.domain.ids;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pl.lrozek.router.controller.serializer.IdSerializer;

@JsonSerialize(using = IdSerializer.class)
public record Id(Sequence from, Sequence to)
{
}
