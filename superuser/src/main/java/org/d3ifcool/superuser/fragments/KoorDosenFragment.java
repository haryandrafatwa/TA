package org.d3ifcool.superuser.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.service.interfaces.lists.DosenListView;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.presenters.DosenPresenter;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.editors.KoorDosenTambahActivity;
import org.d3ifcool.superuser.adapters.KoorDosenViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorDosenFragment extends Fragment implements DosenListView {

    private RecyclerView recyclerView;
    private ArrayList<Dosen> arrayList = new ArrayList<>();
    private FloatingActionButton floatingActionButton;
    private KoorDosenViewAdapter adapter;
    private ProgressDialog progressDialog;
    private DosenPresenter presenter;
    private SwipeRefreshLayout refreshLayout;
    private View empty_view;


    public KoorDosenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_koor_dosen, container, false);

        presenter = new DosenPresenter(this);
        refreshLayout = view.findViewById(R.id.refresh);
        recyclerView = view.findViewById(R.id.frg_koor_dosen_home_recyclerview);
        floatingActionButton = view.findViewById(R.id.frg_koor_dosen_home_fab);
        empty_view = view.findViewById(R.id.view_emptyview);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));
        presenter.getDosen();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KoorDosenTambahActivity.class);
                startActivity(intent);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getDosen();
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getDosen();
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void onGetListDosen(List<Dosen> dosen) {
        adapter = new KoorDosenViewAdapter(getContext());
        arrayList.clear();
        arrayList.addAll(dosen);
        adapter.setDosens(arrayList);
        adapter.setLayoutType(R.layout.content_item_koor_dosen);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        refreshLayout.setRefreshing(false);

        if (arrayList.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
        }

    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
