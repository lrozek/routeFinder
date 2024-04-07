package pl.lrozek.router.csv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lrozek.router.domain.VesselRoute;
import pl.lrozek.router.domain.coordinates.Coordinates;
import pl.lrozek.router.domain.coordinates.Latitude;
import pl.lrozek.router.domain.coordinates.Longitude;
import pl.lrozek.router.domain.destination.Port;
import pl.lrozek.router.domain.destination.Route;
import pl.lrozek.router.domain.ids.Sequence;
import pl.lrozek.router.domain.ids.VesselId;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VesselRouteMapper
{

    private TypeReference<List<List<BigDecimal>>> typeReference = new TypeReference<List<List<BigDecimal>>>()
    {
    };

    @Autowired
    private ObjectMapper objectMapper;

    public VesselRoute map(String[] rawData)
    {
        VesselId vesselId = new VesselId(rawData[0]);
        Sequence fromSeq = new Sequence(Integer.valueOf(rawData[1]));
        Sequence toSeq = new Sequence(Integer.valueOf(rawData[2]));
        Port fromPort = new Port(rawData[3]);
        Port toPort = new Port(rawData[4]);
        Route route = new Route(fromPort, toPort);
        String points = rawData[7];
        List<Coordinates> coordinates = parsePoints(points);
        return new VesselRoute(vesselId, fromSeq, toSeq, route, coordinates);
    }

    private List<Coordinates> parsePoints(String points)
    {
        try
        {
            List<List<BigDecimal>> coordinates = objectMapper.readValue(points, typeReference);
            return coordinates.stream().map(point -> new Coordinates(new Longitude(point.get(0)), new Latitude(point.get(1)))).collect(Collectors.toList());
        } catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }
    }
}
