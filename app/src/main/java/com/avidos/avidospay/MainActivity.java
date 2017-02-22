package com.avidos.avidospay;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements PayFragment.OnFragmentInteractionListener,
        PaymentMethodFragment.OnFragmentInteractionListener, AddCardFragment.OnFragmentInteractionListener,
        EstablishedPaymentMethodFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PayFragment payFragment = PayFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.content, payFragment, "PayFragment").commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
