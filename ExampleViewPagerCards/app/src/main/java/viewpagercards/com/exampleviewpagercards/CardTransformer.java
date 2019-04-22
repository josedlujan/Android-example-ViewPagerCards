package viewpagercards.com.exampleviewpagercards;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;

public class CardTransformer  implements  ViewPager.OnPageChangeListener, ViewPager.PageTransformer{
    private ViewPager mViewPager;
    private CardAdapter mAdapter;
    private float mLastOffset;
    private boolean mScalingEnabled;

    public CardTransformer(ViewPager mViewPager, CardAdapter mAdapter){
        this.mViewPager = mViewPager;
        mViewPager.addOnPageChangeListener(this);
        this.mAdapter = mAdapter;
    }

    public void enableScaling(boolean enable){
        if (mScalingEnabled && !enable){
            CardView currentCard = mAdapter.getCardViewAt(mViewPager.getCurrentItem());
            if (currentCard!=null){
                currentCard.animate().scaleX(1);
                currentCard.animate().scaleY(1);
            }
        }else if (!mScalingEnabled && enable){
            CardView currentCard = mAdapter.getCardViewAt(mViewPager.getCurrentItem());
            if(currentCard != null){
                currentCard.animate().scaleX(1.1f);
                currentCard.animate().scaleY(1.1f);
            }
        }
        mScalingEnabled= enable;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPizels) {
        int realCurrentPosition;
        int nextPosition;
        float baseElevation = mAdapter.getBaseElevation();
        float realOffset;
        boolean goingLeft = mLastOffset > positionOffset;

        if (goingLeft) {
            realCurrentPosition = position + 1;
            nextPosition = position;
            realOffset = 1 - positionOffset;
        }else {
            nextPosition = position + 1;
            realCurrentPosition = position;
            realOffset = positionOffset;
        }

        if(nextPosition > mAdapter.getCount()-1 || realCurrentPosition > mAdapter.getCount() -1){
            return;
        }

        CardView currentCard = mAdapter.getCardViewAt(realCurrentPosition);

        if(currentCard != null){
            if(mScalingEnabled){
                currentCard.setScaleX((float)(1 + 0.1 * (1 - realOffset)));
                currentCard.setScaleY((float) (1 + 0.1 * (1 -realOffset)));
            }
            currentCard.setCardElevation((baseElevation + baseElevation * (CardAdapter.MAX_ELEVATION - 1 ) * (1 - realOffset)));
        }
        CardView nextCard = mAdapter.getCardViewAt(nextPosition);

        if(nextCard != null){
            if(mScalingEnabled){
                nextCard.setScaleX((float) (1 + 0.1 * (realOffset)));
                nextCard.setScaleY((float) (1 + 0.1 * (realOffset)));
            }
            nextCard.setCardElevation((baseElevation + baseElevation * (CardAdapter.MAX_ELEVATION - 1 * (realOffset))));
        }
        mLastOffset = positionOffset;
    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void transformPage(@NonNull View view, float v) {

    }
}
