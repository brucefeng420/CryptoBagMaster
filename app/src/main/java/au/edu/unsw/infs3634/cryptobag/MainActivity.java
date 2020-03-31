package au.edu.unsw.infs3634.cryptobag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "au.edu.unsw.infs3634.beers.MESSAGE";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = findViewById(R.id.rvCoins);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mTwoPane = findViewById(R.id.scrollView1) != null;

        CoinAdapter.RecyclerViewClickListener listener = new CoinAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                System.out.println(position);

                if(mTwoPane){
                    System.out.println("TWO PANE DETECTEDDD");
                    final FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    Bundle arguements = new Bundle();
                    arguements.putInt("position",position);
                    CoinFragment fragment = new CoinFragment();
                    fragment.setArguments(arguements);
                    transaction.replace(R.id.scrollView1, fragment);
                    transaction.commit();

                } else {
                    launchDetailFragment(position);
                }

            }
        };
        mAdapter = new CoinAdapter(Coin.getCoins(), listener);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void launchDetailFragment(int position) {

        Intent intent = new Intent (this, DetailActivity.class);
        intent.putExtra(EXTRA_MESSAGE, position);
        startActivity(intent);

    }
}
