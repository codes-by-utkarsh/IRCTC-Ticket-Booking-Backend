package ticket.booking.entities;

import java.util.List;
import java.util.Map;

public class Train
{
    private String trainId;
    private String trainNo;
    private List<List<Integer>> seats;
    private Map<String, String> stationTime;
    private List<String> Stations;

    public Train(String trainId, String trainNo, List<List<Integer>> seats, Map<String, String> stationTime, List<String> Stations)
        {
        this.trainId = trainId;
        this.trainNo = trainNo;
        this.seats = seats;
        this.stationTime = stationTime;
        this.Stations = Stations;
        }

        public String getTrainId() {
        return trainId;
        }
        public void setTrainId(String trainId) {
        this.trainId = trainId;
        }
        public String getTrainNo() {
        return trainNo;
        }
        public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
        }
        public List<List<Integer>> getSeats() {
        return seats;
        }
        public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
        }
        public Map<String, String> getStationTime() {
        return stationTime;
        }
        public void setStationTime(Map<String, String> stationTime) {
        this.stationTime = stationTime;
        }
        public List<String> getStations() {
        return Stations;
        }
        public void setStations(List<String> Stations) {
        this.Stations = Stations;
        }
        public String getTrainInfo()
        {
            return String.format("Train ID: "+trainId+" Train Number: "+trainNo);
        }
}
