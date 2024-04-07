package pl.lrozek.router.domain.geo;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.lrozek.router.domain.destination.Port;
import pl.lrozek.router.domain.ids.Id;
import pl.lrozek.router.domain.ids.VesselId;

public record Properties(Id id, Port from, Port to, VesselId vesselId, String stroke,
                         @JsonProperty("stroke-opacity") double strokeOpacity)
{
}
