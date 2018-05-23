package com.example.mcs.mcscc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.mcs.mcscc.adapter.AnswerAdapter;
import com.example.mcs.mcscc.data.model.remote.model1.SOAnswersResponse;
import com.example.mcs.mcscc.data.model.remote.remote1.ApiUtils;
import com.example.mcs.mcscc.data.model.remote.remote1.SOService;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private AnswerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SOService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService = ApiUtils.getSOService();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_answers);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        loadAnswers();
    }

    public void loadAnswers() {
        mService.getAnswers().enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call, retrofit2.Response<SOAnswersResponse> response) {
                if (response.isSuccessful())
                {
                    mAdapter.updateAnswers(response.body().getAuthor());

                }


            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {

                showErrorMessage();

            }


        });
    }

    public void showErrorMessage(){
        Toast.makeText(this, "Error loading posts", Toast.LENGTH_SHORT).show();
    }
}
