package mjandroiddev.superfastdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by manoj.rawal on 07-Oct-17.
 */

class CityAdapter extends RecyclerView.Adapter<CityAdapter.VH> {
    private final Context context;
    private final ArrayList<City> list;

    public CityAdapter(Context context, ArrayList<City> cityList) {
        this.context = context;
        this.list = cityList;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(context)
                .inflate(android.R.layout.simple_list_item_2, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.textView.setText(list.get(position).toString());
        holder.textView2.setText(String.valueOf(list.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final TextView textView2;

        public VH(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
            textView2 = itemView.findViewById(android.R.id.text2);
        }
    }
}
