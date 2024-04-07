package pl.lrozek.router.domain.destination;

import com.fasterxml.jackson.annotation.JsonValue;

public record Port(@JsonValue String name)
{
}
