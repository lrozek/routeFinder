package pl.lrozek.router.domain.coordinates;

import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;

// south / north (-90 -> 90 decimal degrees) distance away from equator
public record Latitude(@JsonValue BigDecimal value)
{
}
