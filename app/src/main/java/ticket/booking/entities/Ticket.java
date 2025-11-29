package ticket.booking.entities;

import java.util.Date;

public class Ticket
{
    private String ticketId;
    private String userId;
    private String PNR;
    private String dest;
    private String source;
    private Date dateTime;
    private Train train;

    public Ticket(String ticketId, String userId, String PNR, String dest, String source, Date dateTime, Train train)
    {
        this.ticketId = ticketId;
        this.userId = userId;
        this.PNR = PNR;
        this.dest = dest;
        this.source = source;
        this.dateTime = dateTime;
        this.train = train;
    }

    public void setUserId(String userId) {
            this.userId = userId;
    }
    public String getUserId() {
        return userId;
    }
    public void setPNR(String PNR) {
        this.PNR = PNR;
    }
    public String getPNR() {
        return PNR;
    }
    public void setDest(String dest) {
        this.dest = dest;
    }
    public String getDest() {
        return dest;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getSource() {
        return source;
    }
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    public Date getDateTime() {
        return dateTime;
    }
    public void setTrain(Train train) {
        this.train = train;
    }
    public Train getTrain() {
        return train;
    }
    public String getTicketId() {
        return ticketId;
    }
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
    public String getTicketInfo()
    {
        return String.format("Ticket ID: %s belongs to user %s from %s to %s on %s",ticketId,userId,source,dest,dateTime);
    }
}
