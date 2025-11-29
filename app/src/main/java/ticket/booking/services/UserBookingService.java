package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.User;
import ticket.booking.utils.UserServiceUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class UserBookingService
{
    private User user;
    private final ObjectMapper objectMapper = new ObjectMapper(); //used to map, serialize and deserialize the objects from json
    private List<User> userList;
    private static final String USER_PATH = "app/src/main/java/ticket/booking/localDb/users.json";


    public UserBookingService() throws IOException
    {
        loadUsers();
    }

    public UserBookingService(User user1) throws Exception
    {
        this.user = user1;
        loadUsers();
    }

    public List<User> loadUsers() throws IOException
    {
        File file = new File(USER_PATH);
        return objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    public Boolean loginUser() {
        // Using stream to find a matching user
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equalsIgnoreCase(user.getName()) && UserServiceUtils.checkPassword(user1.getHashedPassword(), user.getPassword());}).findFirst();
        // Return true if user exists, false otherwise
        return foundUser.isPresent();
    }
    public Boolean signUpUser(User user)
    {
        Optional<User> foundUser = userList.stream().filter(user1 -> {return user1.getUserId().equalsIgnoreCase(user.getUserId())}).findFirst();
        if(foundUser.isPresent())
        {
            System.out.println("User already exists. Try Another");
            return false;
        }
        try{
            userList.add(user);
            saveuserListtoFile();
            return true;
        } catch (IOException e) {
            System.out.println("Internal System Error");
            return false;
        }
    }

    private void saveuserListtoFile() throws IOException {
        File usersFile = new File(USER_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    public void fetchBooking()
    {
        user.printTickets();
    }
    public Boolean cancelBooking(String ticketId) {

        if(ticketId.isEmpty())
        {
            System.out.println("Ticket Id is Empty");
            return false;
        }
        boolean isRemoved = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equalsIgnoreCase(ticketId));
        if(isRemoved)
        {
            System.out.println("Ticket with Ticket ID - "+ticketId+ " has been Cancelled");
            try
            {
                saveuserListtoFile();
            } catch (IOException e) {
                throw new RuntimeException("Unable to save booking to file");
            }
            return true;
        }
        else if(!isRemoved)
        {
            System.out.println("Ticket with Ticket ID - "+ticketId+ " has NOT been Cancelled");
            return false;
        }
        return true;
    }

}
