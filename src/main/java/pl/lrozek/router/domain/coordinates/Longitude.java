package pl.lrozek.router.domain.coordinates;

import java.math.BigDecimal;

// east / west (-180 -> 180 decimal degrees)
public record Longitude(BigDecimal value)
{
}
