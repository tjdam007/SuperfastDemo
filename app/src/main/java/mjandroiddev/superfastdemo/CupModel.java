package mjandroiddev.superfastdemo;

/**
 * Created by manoj.rawal on 05-Oct-17.
 */

public abstract class CupModel {
    public Long _id = -1L;

    CupModel(){}

    public long getId() {
        return _id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CupModel cupModel = (CupModel) o;

        return _id.equals(cupModel._id);

    }

    @Override
    public int hashCode() {
        return _id.hashCode();
    }

    public void setId(Long id) {
        this._id = _id;
    }
}
