package mjandroiddev.superfastdemo;

/**
 * Created by manoj.rawal on 07-Oct-17.
 */

class City extends CupModel {

    private String name;

    public City() {
    }

    public City(long aLong, String name) {
        this._id = aLong;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
