package pl.lrozek.router.domain.coordinates;

/**
 * New York in decimal degrees (40.714 N, -74.006 E)
 * Latitude: 40.714
 * Longitude: -74.006
 * <p>
 * London  in decimal degrees (51.5072 N, 0.1276 W)
 * Latitude: 51.5072
 * Longitude: 0.1276
 */
public record Coordinates(Longitude longitude, Latitude latitude)
{
}
