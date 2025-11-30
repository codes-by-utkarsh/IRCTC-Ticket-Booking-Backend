package ticket.booking.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import ticket.booking.services.UserBookingService;


import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.utils.UserServiceUtils;

public class UserBookingService
{
    private User user;
    private final ObjectMapper objectMapper; //used to map, serialize and deserialize the objects from json
    private List<User> userList;
    private static final String USER_PATH = System.getProperty("user.dir") + "/app/src/main/java/ticket/booking/localDb/users.json";
    
    {
        objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE);
    }


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
        if(!file.exists())
        {
            // If file doesn't exist, create an empty list
            userList = new ArrayList<>();
            // Create the file with empty array
            objectMapper.writeValue(file, userList);
            return userList;
        }
        if(file.length() == 0)
        {
            // If file is empty, initialize with empty list
            userList = new ArrayList<>();
            return userList;
        }
        userList = objectMapper.readValue(file, new TypeReference<List<User>>() {});
        if(userList == null)
        {
            userList = new ArrayList<>();
        }
        return userList;
    }

    public Boolean loginUser() {
        // Using stream to find a matching user by username
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getUsername().equalsIgnoreCase(user.getUsername()) && UserServiceUtils.checkPassword(user1.getHashedPassword(), user.getPassword());}).findFirst();
        if(foundUser.isPresent())
        {
            this.user = foundUser.get();
            return true;
        }
        return false;
    }
    
    public Boolean loginUser(String loginMethod, String loginIdentifier, String password) {
        // Using stream to find a matching user based on login method
        Optional<User> foundUser = Optional.empty();
        
        if(loginMethod.equals("1")) // Login via Phone
        {
            foundUser = userList.stream().filter(user1 -> {
                return user1.getPhone().equals(loginIdentifier) && UserServiceUtils.checkPassword(user1.getHashedPassword(), password);
            }).findFirst();
        }
        else if(loginMethod.equals("2")) // Login via Email
        {
            foundUser = userList.stream().filter(user1 -> {
                return user1.getEmail().equalsIgnoreCase(loginIdentifier) && UserServiceUtils.checkPassword(user1.getHashedPassword(), password);
            }).findFirst();
        }
        else if(loginMethod.equals("3")) // Login via Username
        {
            foundUser = userList.stream().filter(user1 -> {
                return user1.getUsername().equalsIgnoreCase(loginIdentifier) && UserServiceUtils.checkPassword(user1.getHashedPassword(), password);
            }).findFirst();
        }
        
        if(foundUser.isPresent())
        {
            this.user = foundUser.get();
            return true;
        }
        return false;
    }
    
    public User getCurrentUser()
    {
        return this.user;
    }
    public Boolean signUpUser(User user)
    {
        Optional<User> foundUser = userList.stream()
                .filter(user1 -> user1.getUsername().equalsIgnoreCase(user.getUsername()))
                .findFirst();
        if(foundUser.isPresent())
        {
            System.out.println("Username already exists. Please choose a different username.");
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
    public List<Train> getTrains(String sourceOfTheTrain, String destinationOfTheTrain)
    {
        try
        {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(sourceOfTheTrain, destinationOfTheTrain);
        }
        catch (IOException e)
        {
            System.out.println("Error loading train service: " + e.getMessage());
            return new ArrayList<>();
        }
    }

}
