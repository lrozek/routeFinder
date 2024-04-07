package pl.lrozek.router.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;
import pl.lrozek.router.domain.VesselRoute;
import pl.lrozek.router.domain.destination.Port;
import pl.lrozek.router.domain.destination.Route;
import pl.lrozek.router.domain.ids.Sequence;
import pl.lrozek.router.domain.ids.VesselId;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CsvParser
{
    public List<VesselRoute> parse(InputStream inputStream)
    {
        Reader reader = new InputStreamReader(inputStream);
        try (CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build())
        {
            List<String[]> rawData = csvReader.readAll();
            return rawData.stream().map(CsvParser::mapper).collect(Collectors.toList());
        } catch (IOException | CsvException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static VesselRoute mapper(String[] rawData)
    {
        VesselId vesselId = new VesselId(rawData[0]);
        Sequence fromSeq = new Sequence(Integer.valueOf(rawData[1]));
        Sequence toSeq = new Sequence(Integer.valueOf(rawData[2]));
        Port fromPort = new Port(rawData[3]);
        Port toPort = new Port(rawData[4]);
        Route route = new Route(fromPort, toPort);
        return new VesselRoute(vesselId, fromSeq, toSeq, route, new ArrayList<>());
    }
}
