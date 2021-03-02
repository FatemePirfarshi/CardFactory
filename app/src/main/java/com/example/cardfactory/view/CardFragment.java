package com.example.cardfactory.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cardfactory.R;
import com.example.cardfactory.data.model.Card;
import com.example.cardfactory.databinding.FragmentCardBinding;
import com.example.cardfactory.viewmodel.CardViewModel;
import com.squareup.picasso.Picasso;

public class CardFragment extends Fragment {

    private FragmentCardBinding mBinding;
    private CardViewModel mCardViewModel;

    public CardFragment() {
        // Required empty public constructor
    }

    public static CardFragment newInstance() {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCardViewModel = new ViewModelProvider(this).get(CardViewModel.class);

        mCardViewModel.fetchCardItems();
        setLiveDataObservers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_card,
                container,
                false);

        mBinding.setCardViewModel(mCardViewModel);

        return mBinding.getRoot();
    }

    private void setLiveDataObservers() {
        mCardViewModel.getCurrentCardLiveData().observe(this, new Observer<Card>() {
            @Override
            public void onChanged(Card card) {

                    Picasso.get()
                            .load(card.getImageLink())
                            .placeholder(mCardViewModel.setIconCards())
                            .into(mBinding.ivImage);

                mBinding.cardBackground.setBackgroundResource(mCardViewModel.setBackgroundTheme());
            }
        });

        mCardViewModel.getRandomIndexLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mBinding.setPosition(integer);
            }
        });
    }
}