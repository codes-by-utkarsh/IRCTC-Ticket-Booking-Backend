package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.User;
import java.io.File;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class UserBookingService
{
    private User user;
    private ObjectMapper objectMapper = new ObjectMapper(); //used to map, serialize and deserialize the objects from json
    private List<User> userList;
    private static final String USER_PATH = "app/src/main/java/ticket/booking/localDb/users.json";
    public UserBookingService(User user1) throws Exception
    {
        this.user = user1;
        File users = new File(USER_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }
    public Boolean loginUser() {
        // Using stream to find a matching user
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && user1.getPassword().equals(user.getPassword())
                })as
                .findFirst();

        // Return true if user exists, false otherwise
        return foundUser.isPresent();
    }

}
