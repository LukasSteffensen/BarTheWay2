package com.p3.bartheway.Browse;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.p3.bartheway.Database.Loan;
import com.p3.bartheway.Database.Student;
import com.p3.bartheway.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PreviousLoansFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PreviousLoansFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreviousLoansFragment extends Fragment implements LoanRecyclerAdapter.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_STUDENT_NAMES = "studentNames";
    private static final String ARG_TITLE = "title";
    private static final String ARG_TIMESTAMP_BORROW = "timestampBorrow";
    private static final String ARG_TIMESTAMP_RETURN = "timestampReturn";

    private List<Loan> loanList;
    private List<Student> studentList;

    ArrayList<Loan> loanArrayList = new ArrayList<>();
    ArrayList<Student> studentArrayList = new ArrayList<>();

    ArrayList<String> studentNames = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> timestampBorrow = new ArrayList<>();
    ArrayList<String> timestampReturn = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private LoanRecyclerAdapter loanRecyclerAdapter;

    private OnFragmentInteractionListener mListener;

    public PreviousLoansFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PreviousLoansFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PreviousLoansFragment newInstance(ArrayList<String> studentNames,
                                                    ArrayList<String> title,
                                                    ArrayList<String> timestampBorrow,
                                                    ArrayList<String> timestampReturn) {
        PreviousLoansFragment fragment = new PreviousLoansFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_STUDENT_NAMES, studentNames);
        args.putStringArrayList(ARG_TITLE, title);
        args.putStringArrayList(ARG_TIMESTAMP_BORROW, timestampBorrow);
        args.putStringArrayList(ARG_TIMESTAMP_RETURN, timestampReturn);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            studentNames = getArguments().getStringArrayList(ARG_STUDENT_NAMES);
            title = getArguments().getStringArrayList(ARG_TITLE);
            timestampBorrow = getArguments().getStringArrayList(ARG_TIMESTAMP_BORROW);
            timestampReturn = getArguments().getStringArrayList(ARG_TIMESTAMP_RETURN);
            for (int i = 0; i < timestampBorrow.size(); i++) {
                Loan loan = new Loan();
                loan.setTitle(title.get(i));
                loan.setTimestampBorrow(timestampBorrow.get(i));
                loan.setTimestampReturn(timestampReturn.get(i));
                loanArrayList.add(loan);
                Student student = new Student();
                student.setStudentName(studentNames.get(i));
                studentArrayList.add(student);
            }
            loanList = loanArrayList;
            Log.i("previous fragment", loanList.size() + "");
            studentList = studentArrayList;
            Log.i("previous fragment", studentList.size() + "");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_previous_loans, container, false);

        mRecyclerView = view.findViewById(R.id.previousBorrowersRecycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.setHasFixedSize(true);
        loanRecyclerAdapter = new LoanRecyclerAdapter(loanList, studentList, this);
        mRecyclerView.setAdapter(loanRecyclerAdapter);
        // Inflate the layout for this fragment
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
    public void onItemClick(int position) {

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
