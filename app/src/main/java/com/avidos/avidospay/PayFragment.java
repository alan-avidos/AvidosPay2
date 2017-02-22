package com.avidos.avidospay;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PayFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    // UI
    private FrameLayout mEstablishedPayMethodView;
    private FrameLayout mManagePayMethodView;
    private FrameLayout mCurrentPaymentPlanView;
    private FrameLayout mManagePaymentPlan;
    private FrameLayout mPayView;
    private FrameLayout mAccountStatusView;

    public PayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment PayFragment.
     */
    public static PayFragment newInstance() {
        PayFragment fragment = new PayFragment();
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
        View view = inflater.inflate(R.layout.fragment_pay, container, false);

        mManagePayMethodView = (FrameLayout) view.findViewById(R.id.layout_manage_pay_method);
        mEstablishedPayMethodView = (FrameLayout) view.findViewById(R.id.layout_established_payment_method);

        mManagePayMethodView.setOnClickListener(this);
        mEstablishedPayMethodView.setOnClickListener(this);

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

            case R.id.layout_manage_pay_method:
                PaymentMethodFragment paymentMethodFragment = PaymentMethodFragment.newInstance();
                ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                ft.replace(R.id.content, paymentMethodFragment, "PaymentMethodFragment").commit();
                break;
            case R.id.layout_established_payment_method:
                EstablishedPaymentMethodFragment establishedPaymentMethodFragment = EstablishedPaymentMethodFragment.newInstance();
                ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                ft.replace(R.id.content, establishedPaymentMethodFragment, "EstablishedPaymentMethodFragment").commit();
                break;
            default:
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
