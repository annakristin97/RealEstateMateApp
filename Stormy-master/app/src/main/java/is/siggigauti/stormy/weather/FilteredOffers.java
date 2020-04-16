package is.siggigauti.stormy.weather;

import java.util.List;

public class FilteredOffers {

    private List<Property> offers;

    public FilteredOffers() {

    }

    public FilteredOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setProperties(List<Offer> offers) {
        this.offers = offers;
    }
}
