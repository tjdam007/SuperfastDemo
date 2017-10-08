package mjandroiddev.superfastdemo;

/**
 * Created by manoj.rawal on 05-Oct-17.
 */

public class DataModel extends CupModel {
    private String city;

    public DataModel(String city) {
        this.city = city;
    }

    public DataModel(String city, long id) {
        this.city = city;
        this._id = id;
    }


    public DataModel() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
