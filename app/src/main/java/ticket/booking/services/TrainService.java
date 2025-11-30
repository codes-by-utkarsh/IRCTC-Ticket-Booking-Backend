package ticket.booking.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;

public class TrainService
{
    private User user;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Train> trainList;
    private static final String TRAIN_PATH = "app/src/main/java/ticket/booking/localDb/trains.json";

    public TrainService() throws IOException
    {
        loadTrains();
    }

    public TrainService(User user1) throws IOException
    {
        this.user = user1;
        loadTrains();
    }

    public List<Train> loadTrains() throws IOException
    {
        File file = new File(TRAIN_PATH);
        trainList = objectMapper.readValue(file, new TypeReference<List<Train>>() {});
        return trainList;
    }

    public List<Train> searchTrains(String sourceOfTheTrain, String destinationOfTheTrain)
    {
        if(trainList == null)
        {
            try {
                loadTrains();
            } catch (IOException e) {
                System.out.println("Error loading trains: " + e.getMessage());
                return new ArrayList<>();
            }
        }

        return trainList.stream()
                .filter(train -> {
                    List<String> stations = train.getStations();
                    int sourceIndex = -1;
                    int destIndex = -1;
                    
                    for(int i = 0; i < stations.size(); i++)
                    {
                        if(stations.get(i).equalsIgnoreCase(sourceOfTheTrain))
                        {
                            sourceIndex = i;
                        }
                        if(stations.get(i).equalsIgnoreCase(destinationOfTheTrain))
                        {
                            destIndex = i;
                        }
                    }
                    
                    // Check if source comes before destination in the route
                    return sourceIndex != -1 && destIndex != -1 && sourceIndex < destIndex;
                })
                .collect(Collectors.toList());
    }
}
