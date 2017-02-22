package com.avidos.avidospay;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PaymentMethodFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PaymentMethodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentMethodFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    // UI
    private ImageView mBackButton;
    private ImageView mAddCardButton;

    private ArrayList<Card> cards;

    public PaymentMethodFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PaymentMethodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentMethodFragment newInstance() {
        PaymentMethodFragment fragment = new PaymentMethodFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment_method, container, false);

        mBackButton = (ImageView) view.findViewById(R.id.image_back_arrow_pay_methods);
        mAddCardButton = (ImageView) view.findViewById(R.id.image_add_card);

        RecyclerView rvCards = (RecyclerView) view.findViewById(R.id.cards_view);

        cards = Card.createPaymentMethods(5);
        CardsAdapter adapter = new CardsAdapter(getContext(), cards);
        rvCards.setAdapter(adapter);
        rvCards.setLayoutManager(new LinearLayoutManager(getContext()));

        mBackButton.setOnClickListener(this);
        mAddCardButton.setOnClickListener(this);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {

        FragmentTransaction ft;
        switch (view.getId()) {

            case R.id.image_back_arrow_pay_methods:
                PayFragment payFragment = PayFragment.newInstance();
                ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit, R.anim.enter, R.anim.exit);
                ft.replace(R.id.content, payFragment, "PayFragment").commit();
                break;
            case R.id.image_add_card:
                AddCardFragment addCardFragment = AddCardFragment.newInstance();
                ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                ft.replace(R.id.content, addCardFragment, "PayFragment").commit();
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
