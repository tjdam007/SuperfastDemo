package mjandroiddev.superfastdemo;

import java.util.ArrayList;

/**
 * Created by manoj.rawal on 07-Oct-17.
 */

class CityEvent {
    private final ArrayList<City> list;
    private final boolean hasMore;

    public CityEvent(ArrayList<City> cities, boolean hasMore) {
        this.list = cities;
        this.hasMore = hasMore;
    }

    public ArrayList<City> getList() {
        return list;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    //Event for EventBus
}
