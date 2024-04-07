package pl.lrozek.router.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lrozek.router.domain.VesselRoute;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CsvParser
{

    @Autowired
    private VesselRouteMapper vesselRouteMapper;

    public List<VesselRoute> parse(InputStream inputStream)
    {
        Reader reader = new InputStreamReader(inputStream);
        try (CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build())
        {
            List<String[]> rawData = csvReader.readAll();
            return rawData.stream().map(vesselRouteMapper::map).collect(Collectors.toList());
        } catch (IOException | CsvException e)
        {
            throw new RuntimeException(e);
        }
    }


}
