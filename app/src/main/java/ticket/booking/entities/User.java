package ticket.booking.entities;

import java.util.List;

public class User
{
    private String name;
    private String userId;
    private String password;
    private String hashedPassword;
    private String email;
    private String phone;
    private List<Ticket> ticketsBooked;
}
