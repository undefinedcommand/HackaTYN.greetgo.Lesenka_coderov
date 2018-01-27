package bus.notifier.busnotifier.Models;

public class Bus {

    private String number;
    private float longitude;
    private float latitude;
    private String from_address;
    private String to_address;

    public Bus(String number, float longitude, float latitude, String from_address, String to_address) {
        this.number = number;
        this.longitude = longitude;
        this.latitude = latitude;
        this.from_address = from_address;
        this.to_address = to_address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getFrom_address() {
        return from_address;
    }

    public void setFrom_address(String from_address) {
        this.from_address = from_address;
    }

    public String getTo_address() {
        return to_address;
    }

    public void setTo_address(String to_address) {
        this.to_address = to_address;
    }
}
