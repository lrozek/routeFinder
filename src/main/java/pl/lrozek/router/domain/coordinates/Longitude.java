package pl.lrozek.router.domain.coordinates;

import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;

// east / west (-180 -> 180 decimal degrees)
public record Longitude(@JsonValue BigDecimal value)
{
}
