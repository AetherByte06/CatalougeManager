import java.time.LocalDate;

public class Equipment {
    private String itemName;
    private String manufacturer;
    private LocalDate dateOfAcquisition;
    private String type;
    private boolean isAvailable;

    public Equipment(String itemName, String manufacturer, LocalDate dateOfAcquisition, String type, boolean isAvailable) {
        this.itemName = itemName;
        this.manufacturer = manufacturer;
        this.dateOfAcquisition = dateOfAcquisition;
        this.type = type;
        this.isAvailable = isAvailable;
    }

    public String getItemName(){
        return this.itemName;
    }
    public String getManufacturer(){
        return this.manufacturer;
    }
    public LocalDate getDateOfAcquisition(){
        return this.dateOfAcquisition;
    }
    public String getType(){
        return this.type;
    }
    public boolean getAvailability(){
        return this.isAvailable;
    }
    public String getInformation(){
        return "Name: " + itemName + ", Manufacturer: " + manufacturer + ", Date of Acquisition: "
                + dateOfAcquisition + ", Type of Equipment: " + type +
                ", Available?: " + isAvailable;
    }
    public void setAvailability(boolean available){
        this.isAvailable = available;
    }
}
