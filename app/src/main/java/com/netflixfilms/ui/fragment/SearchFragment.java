package com.netflixfilms.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.netflixfilms.R;
import com.netflixfilms.service.NetflixService;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchFragment extends Fragment {


    @Bind(R.id.search_recycler)
    RecyclerView recyclerView;
    @Bind(R.id.search)
    EditText searchField;

    private boolean isTitleSearch;
    public static final String ARG_SEARCH_TYPE = "search_type";


    public SearchFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.search_btn)
    void searchMovie() {
        if (!TextUtils.isEmpty(searchField.getText().toString())) {
            String searchQuery = searchField.getText().toString().trim();
            if (isTitleSearch) {
                NetflixService.searchByTitle(getActivity(), searchQuery);
            } else {
                NetflixService.searchByDirector(getActivity(), searchQuery);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        isTitleSearch = this.getArguments().getBoolean(ARG_SEARCH_TYPE);

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

}
