package ticket.booking.entities;

import java.util.List;

public class User
{
    private String name;
    private String username;
    private String password;
    private String hashedPassword;
    private String email;
    private String phone;
    private List<Ticket> ticketsBooked;


    public User(String name, String username, String password, String hashedPassword, String email, String phone, List<Ticket> ticketsBooked)
    {
        this.name = name;
        this.username = username;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.phone = phone;
        this.ticketsBooked = ticketsBooked;
    }

    public User()
    {}
    public String getName()
    {
        return name;
    }
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public  String getHashedPassword()
    {
        return hashedPassword;
    }
    public List<Ticket> getTicketsBooked()
    {
        return ticketsBooked;
    }
    public String getEmail()
    {
        return email;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public void setTicketsBooked(List<Ticket> ticketsBooked)
    {
        this.ticketsBooked = ticketsBooked;
    }
    public void printTickets()
    {
        for (Ticket ticket : ticketsBooked)
        {
            System.out.println(ticket);
        }
    }
}
