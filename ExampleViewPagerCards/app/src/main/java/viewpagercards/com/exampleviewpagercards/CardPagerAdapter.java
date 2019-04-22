package viewpagercards.com.exampleviewpagercards;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {
    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;

    public CardPagerAdapter(){
        mViews = new ArrayList<>();
        mData = new ArrayList<>();
    }

    public void addCardItem(CardItem item){
        mViews.add(null);
        mData.add(item);
    }

    @Override
    public float getBaseElevation() {
      return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.cardlayout,container,false);
        container.addView(view);
        bind(mData.get(position), view);
        CardView cardView = view.findViewById(R.id.cardView);
        if(mBaseElevation == 0){
            mBaseElevation = cardView.getCardElevation();
        }
        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION);
        mViews.set(position,cardView);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View) object);
       mViews.set(position,null);
    }

    private void bind(CardItem item, View view){
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView contentTextView = view.findViewById(R.id.contentTextView);
        Button button = view.findViewById(R.id.buttonCard);

        titleTextView.setText(item.getTitle());
        contentTextView.setText(item.getText());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Click on Button",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
