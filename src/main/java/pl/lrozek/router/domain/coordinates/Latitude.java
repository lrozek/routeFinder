package pl.lrozek.router.domain.coordinates;

import java.math.BigDecimal;

// south / north (-90 -> 90 decimal degrees) distance away from equator
public record Latitude(BigDecimal value)
{
}
