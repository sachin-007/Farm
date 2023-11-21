package com.example.farm;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.farm.adapter.PlotAdapter;
import com.example.farm.controllers.ApiService;
import com.example.farm.model.PlotModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlotsFragment extends Fragment {

    private Call<List<PlotModel>> apiCall;


    private static final String TAG = "PlotsFragment";

    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;

    private RecyclerView recyclerView;
    private PlotAdapter plotAdapter;

    public PlotsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plots, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        progressBar = view.findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Set up swipe-to-refresh listener
        swipeRefreshLayout.setOnRefreshListener(this::refreshData);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize SharedPreferencesManager here
        SharedPreferencesManager sh = new SharedPreferencesManager(requireContext());
        String userId = sh.getUserId();
        Log.d(TAG, "onViewCreated: useridisthemain" + userId);

        // Replace with the actual API call to get farm plots
        getFarmsFromApi(userId);
    }

    private void refreshData() {
        SharedPreferencesManager sh = new SharedPreferencesManager(requireContext());
        String userId = sh.getUserId();
        getFarmsFromApi(userId);
    }

    private void getFarmsFromApi(String userId) {

        if (!isAdded() || !isVisible()) {
            return;
        }

        // Cancel previous API call if it exists
        if (apiCall != null && !apiCall.isCanceled()) {
            apiCall.cancel();
        }


        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.cofastudio.com/my_farm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<PlotModel>> call = apiService.getPlotsApiCall(userId);

        call.enqueue(new Callback<List<PlotModel>>() {
            @Override
            public void onResponse(Call<List<PlotModel>> call, Response<List<PlotModel>> response) {
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful()) {
                    List<PlotModel> plotList = response.body();
                    updateRecyclerView(plotList);
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<List<PlotModel>> call, Throwable t) {
                // Hide the loader and swipe-to-refresh indicator
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);

                // Handle failure
            }
        });
    }

    private void updateRecyclerView(List<PlotModel> plotList) {

        if (!isAdded() || !isVisible()) {
            return;
        }

        if (plotAdapter == null) {
            plotAdapter = new PlotAdapter(requireContext(), plotList);
            recyclerView.setAdapter(plotAdapter);
        } else {
            // Update the existing adapter's data
            plotAdapter.setData(plotList);
            plotAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Cancel the API call when the view is destroyed
        if (apiCall != null && !apiCall.isCanceled()) {
            apiCall.cancel();
        }
    }
}
