package viewpagercards.com.exampleviewpagercards;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private CardPagerAdapter mCardPagerAdapter;
    private  CardTransformer mCardTransformer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.viewPager);

        mCardPagerAdapter = new CardPagerAdapter();
        mCardPagerAdapter.addCardItem(new CardItem(R.string.title,R.string.content));
        mCardPagerAdapter.addCardItem(new CardItem(R.string.title,R.string.content));
        mCardPagerAdapter.addCardItem(new CardItem(R.string.title,R.string.content));
        mCardPagerAdapter.addCardItem(new CardItem(R.string.title,R.string.content));
        mCardPagerAdapter.addCardItem(new CardItem(R.string.title,R.string.content));

        mCardTransformer = new CardTransformer(mViewPager,mCardPagerAdapter);
        mViewPager.setAdapter(mCardPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);


    }
}
