package comrr.example.starlingsoftwares.fragmentsdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnFirst,btnSecond;
    public static MainActivity mInstance;

    public static MainActivity getInstace() {
        return mInstance;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          mInstance=this;
        btnFirst=findViewById(R.id.btn_first);
        btnSecond=findViewById(R.id.btn_second);


        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment fragment = getFragment();
                setTitleFromFragment(fragment);
            }
        });


        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new FirstFragment(), FirstFragment.class.getSimpleName());

            }
        });

        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new SecondFragment(), SecondFragment.class.getSimpleName());

            }
        });
    }

    private Fragment getFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.container);
    }
    private void replaceFragment(Fragment newFragment, String transactionTag) {
        if (newFragment != null) {
            FragmentManager frgManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = frgManager.beginTransaction();

            fragmentTransaction.setReorderingAllowed(false);
            fragmentTransaction.addToBackStack(transactionTag);
            fragmentTransaction.replace(R.id.container, newFragment).commitAllowingStateLoss();
            frgManager.executePendingTransactions();
        }
    }
    private void setTitleFromFragment(Fragment fragment) {
        if (fragment instanceof FirstFragment) {

        }
        if (fragment instanceof SecondFragment) {

        }

    }
    public  void takeToFirst() {
        clearBackStack();
        replaceFragment(new FirstFragment(), FirstFragment.class.getSimpleName());

    }

    private void clearBackStack() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStackImmediate(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

    }

}
