package mjandroiddev.superfastdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CityAdapter cityAdapter;
    private ArrayList<City> cityList;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        cityList = new ArrayList<>();
        cityList = DbHelper
                .getInstance(MainActivity.this)
                .getCities();
        cityAdapter = new CityAdapter(this, cityList);
        recyclerView.setAdapter(cityAdapter);
        recyclerView.addOnScrollListener(scrollListener);

        if (cityList.isEmpty()) {
            Sdk.getCities(this, max, skip);
        }
    }

    //Scroll Listener
    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (layoutManager.findLastCompletelyVisibleItemPosition() == (cityList.size() - 1)) {
                Sdk.getCities(MainActivity.this, max, cityList.size());
            }
        }
    };

    private int max = 150;
    private int skip = 0;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(sticky = false, threadMode = ThreadMode.MAIN)
    public void onEvent(CityEvent eventObj) {
        if (!eventObj.getList().isEmpty()) {
            cityList.addAll(eventObj.getList());
            cityAdapter.notifyDataSetChanged();
        }
        if (!eventObj.isHasMore()) {
            recyclerView.removeOnScrollListener(scrollListener);
            Toast.makeText(this, "No More Data", Toast.LENGTH_SHORT).show();
        }

    }
}
