package is.siggigauti.stormy.weather;

public class PropertyFilterRequestWrapper {
    private String propertyTown;
    private String propertyZip;
    private String propertyBedrooms;
    private String propertyPrice;
    private String propertySize;
    private String propertyCategory;
    private String propertyBathrooms;

    public PropertyFilterRequestWrapper(String propertyTown, String propertyZip, String propertyBedrooms, String propertyPrice, String propertySize, String propertyCategory, String propertyBathrooms) {
        this.propertyTown = propertyTown;
        this.propertyZip = propertyZip;
        this.propertyBedrooms = propertyBedrooms;
        this.propertyPrice = propertyPrice;
        this.propertySize = propertySize;
        this.propertyCategory = propertyCategory;
        this.propertyBathrooms = propertyBathrooms;
    }

    public PropertyFilterRequestWrapper(){

    }

    public String getPropertyTown() {
        return propertyTown;
    }

    public void setPropertyTown(String propertyTown) {
        this.propertyTown = propertyTown;
    }

    public String getPropertyZip() {
        return propertyZip;
    }

    public void setPropertyZip(String propertyZip) {
        this.propertyZip = propertyZip;
    }

    public String getPropertyBedrooms() {
        return propertyBedrooms;
    }

    public void setPropertyBedrooms(String propertyBedrooms) {
        this.propertyBedrooms = propertyBedrooms;
    }

    public String getPropertyPrice() {
        return propertyPrice;
    }

    public void setPropertyPrice(String propertyPrice) {
        this.propertyPrice = propertyPrice;
    }

    public String getPropertySize() {
        return propertySize;
    }

    public void setPropertySize(String propertySize) {
        this.propertySize = propertySize;
    }

    public String getPropertyCategory() {
        return propertyCategory;
    }

    public void setPropertyCategory(String propertyCategory) {
        this.propertyCategory = propertyCategory;
    }

    public String getPropertyBathrooms() {
        return propertyBathrooms;
    }

    public void setPropertyBathrooms(String propertyBathrooms) {
        this.propertyBathrooms = propertyBathrooms;
    }

}