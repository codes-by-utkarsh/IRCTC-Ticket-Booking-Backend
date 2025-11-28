package ticket.booking.services;

import ticket.booking.entities.User;

import java.io.File;

public class UserBookingService
{
    private User user;
    private static final String USER_PATH = "../localDb/users.json";
    public UserBookingService(User user1)
    {
        this.user = user1;
        File users = new File(USER_PATH);
    }
}
