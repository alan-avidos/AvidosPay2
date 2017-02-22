package com.avidos.avidospay;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddCardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCardFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    // UI
    private EditText mCardNumberText;
    private EditText mMonthYearText;
    private ImageView mBackButton;

    public String date;

    public AddCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddCardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCardFragment newInstance() {
        AddCardFragment fragment = new AddCardFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_card, container, false);

        mCardNumberText = (EditText) view.findViewById(R.id.text_card_number);
        mMonthYearText = (EditText) view.findViewById(R.id.text_month_year);
        mBackButton = (ImageView) view.findViewById(R.id.image_back_arrow_add_card);

        mCardNumberText.addTextChangedListener(new FourDigitCardFormatWatcher());
        mMonthYearText.addTextChangedListener(ExpiryDateTextWatcher);

        mBackButton.setOnClickListener(this);

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

    TextWatcher ExpiryDateTextWatcher = new TextWatcher() {
        //Make sure for mExpiryDate to be accepting Numbers only
        boolean isSlash = false; //class level initialization
        private void formatCardExpiringDate(Editable s){
            String input = s.toString();
            String mLastInput = "";

            SimpleDateFormat formatter = new SimpleDateFormat("MM/yy",     Locale.ENGLISH);
            Calendar expiryDateDate = Calendar.getInstance();

            try {
                expiryDateDate.setTime(formatter.parse(input));
            } catch (java.text.ParseException e) {
                if (s.length() == 2 && !mLastInput.endsWith("/") && isSlash) {
                    isSlash = false;
                    int month = Integer.parseInt(input);
                    if (month <= 12) {
                        mMonthYearText.setText(mMonthYearText.getText().toString().substring(0, 1));
                        mMonthYearText.setSelection(mMonthYearText.getText().toString().length());
                    } else {
                        mMonthYearText.setText(mMonthYearText.getText().toString().substring(0,1));
                        mMonthYearText.setSelection(mMonthYearText.getText().toString().length());
                    }
                }else if (s.length() == 2 && !mLastInput.endsWith("/") && !isSlash) {
                    isSlash = true;
                    int month = Integer.parseInt(input);
                    if (month <= 12) {
                        mMonthYearText.setText(mMonthYearText.getText().toString() + "/");
                        mMonthYearText.setSelection(mMonthYearText.getText().toString().length());
                    }else if(month > 12){
                        mMonthYearText.setText(mMonthYearText.getText().toString().substring(0,1));
                        s.clear();
                        mMonthYearText.setSelection(mMonthYearText.getText().toString().length());
                    }


                } else if (s.length() == 1) {

                    int month = Integer.parseInt(input);
                    if (month > 1 && month < 12) {
                        isSlash = true;
                        mMonthYearText.setText("0" + mMonthYearText.getText().toString() + "/");
                        mMonthYearText.setSelection(mMonthYearText.getText().toString().length());
                    }
                }

                mLastInput = mMonthYearText.getText().toString();
                return;
            }
        }
        //wrap method formatCardExpiringDate around try catch or wrap the entire code in try catch, catching NumberFormateException. To take care of situations when s.length() == 2 and there is a a number in from of the slash
        @Override
        public void afterTextChanged(Editable s) {
            try{
                formatCardExpiringDate(s);
            }catch(NumberFormatException e){
                s.clear();
                //Toast message here.. Wrong date formate

            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
    };

    @Override
    public void onClick(View view) {
        FragmentTransaction ft;
        switch (view.getId()) {

            case R.id.image_back_arrow_add_card:
                PaymentMethodFragment paymentMethodFragment = PaymentMethodFragment.newInstance();
                ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit, R.anim.enter, R.anim.exit);
                ft.replace(R.id.content, paymentMethodFragment, "PaymentMethodFragment").commit();
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

    /**
     * Formats the watched EditText to a credit card number
     */
    public static class FourDigitCardFormatWatcher implements TextWatcher {

        private static final int TOTAL_SYMBOLS = 19; // size of pattern 0000-0000-0000-0000
        private static final int TOTAL_DIGITS = 16; // max numbers of digits in pattern: 0000 x 4
        private static final int DIVIDER_MODULO = 5; // means divider position is every 5th symbol beginning with 1
        private static final int DIVIDER_POSITION = DIVIDER_MODULO - 1; // means divider position is every 4th symbol beginning with 0
        private static final char DIVIDER = '-';

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // noop
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // noop
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!isInputCorrect(s, TOTAL_SYMBOLS, DIVIDER_MODULO, DIVIDER)) {
                s.replace(0, s.length(), buildCorrecntString(getDigitArray(s, TOTAL_DIGITS), DIVIDER_POSITION, DIVIDER));
            }
        }

        private boolean isInputCorrect(Editable s, int totalSymbols, int dividerModulo, char divider) {
            boolean isCorrect = s.length() <= totalSymbols; // check size of entered string
            for (int i = 0; i < s.length(); i++) { // chech that every element is right
                if (i > 0 && (i + 1) % dividerModulo == 0) {
                    isCorrect &= divider == s.charAt(i);
                } else {
                    isCorrect &= Character.isDigit(s.charAt(i));
                }
            }
            return isCorrect;
        }

        private String buildCorrecntString(char[] digits, int dividerPosition, char divider) {
            final StringBuilder formatted = new StringBuilder();

            for (int i = 0; i < digits.length; i++) {
                if (digits[i] != 0) {
                    formatted.append(digits[i]);
                    if ((i > 0) && (i < (digits.length - 1)) && (((i + 1) % dividerPosition) == 0)) {
                        formatted.append(divider);
                    }
                }
            }

            return formatted.toString();
        }

        private char[] getDigitArray(final Editable s, final int size) {
            char[] digits = new char[size];
            int index = 0;
            for (int i = 0; i < s.length() && index < size; i++) {
                char current = s.charAt(i);
                if (Character.isDigit(current)) {
                    digits[index] = current;
                    index++;
                }
            }
            return digits;
        }
    }
}
