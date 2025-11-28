package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.User;
import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

public class UserBookingService
{
    private User user;
    private ObjectMapper objectMapper = new ObjectMapper(); //used to map, serialize and deserialize the objects from json
    private List<User> userList;
    private static final String USER_PATH = "../localDb/users.json";
    public UserBookingService(User user1)
    {
        this.user = user1;
        File users = new File(USER_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() )
        {

        }

    }
}
