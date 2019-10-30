package entity;

public class MovieTicket {
    private float price;
    private AgeGroup ageGroup;
    private String TID;

    public MovieTicket(float price, AgeGroup ageGroup) {
        this.price = price;
        this.ageGroup = ageGroup;
        this.TID = "";
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }
}
