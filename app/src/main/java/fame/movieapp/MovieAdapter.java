package fame.movieapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovVH> {

    ArrayList<Model> list;
    Context context;

    public MovieAdapter(ArrayList<Model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapter.MovVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movitem, viewGroup, false);
        MovVH rcv = new MovVH(layoutView);
        return rcv;    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovVH movVH, int i) {
        movVH.title.setText(list.get(i).name);
        movVH.rat.setText(list.get(i).rating+"/10");
        movVH.ovr.setText(list.get(i).desc);

        String picurl="http://image.tmdb.org/t/p/w185//"+list.get(i).pic;

        Picasso.with(context).load(picurl).into(movVH.imgx);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MovVH extends RecyclerView.ViewHolder {
        TextView title, rat,ovr;
        ImageView imgx;
        public MovVH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            rat = itemView.findViewById(R.id.rating);
            ovr = itemView.findViewById(R.id.ovr);

            imgx = itemView.findViewById(R.id.insight_imgv);

        }
    }
}
