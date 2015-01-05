package org.dean.fragmenttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class FragmentChangeActivity extends android.support.v4.app.FragmentActivity implements View.OnClickListener {
    private static final String FRAGMENT_TAG1 = "FRAGMENT_TAG1";
    private static final String FRAGMENT_TAG2 = "FRAGMENT_TAG2";
    private static final String FRAGMENT_TAG3 = "FRAGMENT_TAG3";
    private static final String FRAGMENT_NAME = "FRAGMENT_NAME";
    private Button btn1, btn2, btn3;
    private ExampleFragment fragment1, fragment2, fragment3;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment1 = ExampleFragment.newInstance(FRAGMENT_TAG1);
        fragment2 = ExampleFragment.newInstance(FRAGMENT_TAG2);
        fragment3 = ExampleFragment.newInstance(FRAGMENT_TAG3);
        fragmentTransaction.add(R.id.example_fragment, fragment1, FRAGMENT_TAG1);
        fragmentTransaction.commit();
    }

    //使用attach或者detach的话,fragment只是会重构UI，内存中得变量值是不会被清空的
    @Override
    public void onClick(View v) {
        int id = v.getId();
        ExampleFragment fragment;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (id) {
            case R.id.btn1:
                fragment = (ExampleFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG1);
                if (fragment == null) {
                    fragmentTransaction.add(R.id.example_fragment, fragment1, FRAGMENT_TAG1);
                }

                if (!fragment2.isDetached()) {
                    fragmentTransaction.detach(fragment2);
                }
                if (!fragment3.isDetached()) {
                    fragmentTransaction.detach(fragment3);
                }
                fragmentTransaction.attach(fragment1);
                fragmentTransaction.commit();
                fragment2.setName("Dean");//可以修改变量的值，因为fragment的实例还存在在内存中
                fragment3.setName("Livia");
                break;
            case R.id.btn2:
                fragment = (ExampleFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG2);
                if (fragment == null) {
                    System.out.println("创建Fragment2");
                    fragmentTransaction.add(R.id.example_fragment, fragment2, FRAGMENT_TAG2);
                }

                if (!fragment1.isDetached()) {
                    fragmentTransaction.detach(fragment1);
                }
                if (!fragment3.isDetached()) {
                    fragmentTransaction.detach(fragment3);
                }
                fragmentTransaction.attach(fragment2);
                fragmentTransaction.commit();
                break;
            case R.id.btn3:
                fragment = (ExampleFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG3);
                if (fragment == null) {
                    System.out.println("创建Fragment3");
                    fragmentTransaction.add(R.id.example_fragment, fragment3, FRAGMENT_TAG3);
                }

                if (!fragment1.isDetached()) {
                    fragmentTransaction.detach(fragment1);
                }
                if (!fragment2.isDetached()) {
                    fragmentTransaction.detach(fragment2);
                }
                fragmentTransaction.attach(fragment3);
                fragmentTransaction.commit();
                break;
        }
    }

    public static class ExampleFragment extends Fragment {

        private String name;
        private TextView tv1;

        public static ExampleFragment newInstance(String fragmentName) {
            ExampleFragment f = new ExampleFragment();
            Bundle args = new Bundle();
            args.putString(FRAGMENT_NAME, fragmentName);
            f.setArguments(args);
            f.setName(fragmentName);
            f.setRetainInstance(true);//旋转屏幕的时候内存变量还会被保留着
            return f;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFragmentName() {
            return getArguments().getString(FRAGMENT_NAME);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.example_fragment, container, false);
            tv1 = (TextView) view.findViewById(R.id.tv1);
            System.out.println("======>"+getName());//如果不设置setRetainInstance那么旋转屏幕的时候，变量的值会丢失
            tv1.setText(getName());
            return view;
        }
    }
}
