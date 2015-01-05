package org.dean.fragmenttest;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.Button;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;

import org.dean.fragmenttest.volley.CacheRequest;

import java.io.UnsupportedEncodingException;

/**
 * Created by Dean on 14-12-25.
 */
public class VolleyActivity extends FragmentActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<String> {
    private Button btn1, btn2, btn3;
    private RequestQueue queue;
    private JobManager jobManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jobManager = BaseApplication.getInstance().getJobManager();
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        queue = Volley.newRequestQueue(this);
        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn1:
                // Instantiate the RequestQueue.
                final String url = "http://www.baidu.com";

                // Request a string response from the provided URL.
                // 60秒缓存过期
                CacheRequest stringRequest = new CacheRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                System.out.println(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                        System.out.println("That didn't work!");
                    }
                });
                Cache.Entry cacheEntry = new Cache.Entry();
                long softExpire = System.currentTimeMillis() + 60 * 1000;
                cacheEntry.softTtl = softExpire;
                cacheEntry.ttl = cacheEntry.softTtl;
                stringRequest.setCacheEntry(cacheEntry);//这样设置entry也是没有效果的，必须在response里面设置entry才有效果，
                // 因为 NetworkDispatcher里面判断 是否缓存数据是用 if (request.shouldCache() && response.cacheEntry != null)
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
                break;
            case R.id.btn2:
                new Thread(new GetCacheRunnable()).start();
                break;
            case R.id.btn3:
                final long startTime = System.currentTimeMillis();
                netOrCache("http://www.baidu.com", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        long endTime = System.currentTimeMillis();
                        System.out.println(endTime - startTime);
                        System.out.println("网路上的数据" + response);
                    }
                }, null);
                break;
        }
    }

    public static class GetCacheAsyncTaskLoader extends AsyncTaskLoader<String> {
        private String result;

        public GetCacheAsyncTaskLoader(Context context) {
            super(context);
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();
            if (result != null) {
                deliverResult(result);
            } else {
                forceLoad();
            }
        }

        @Override
        public void deliverResult(String data) {
            result = data;
            if (isStarted())
                super.deliverResult(data);
        }

        @Override
        public String loadInBackground() {
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = "Done";
            return result;
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new GetCacheAsyncTaskLoader(VolleyActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        btn1.setText(data);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    public class GetCacheTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            try {
                Thread.sleep(9 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            System.out.println("GetCacheTask finished!!!!");
        }
    }

    public class GetCacheJob extends Job {
        public static final int PRIORITY = 1;

        public GetCacheJob() {
            super(new Params(PRIORITY));
        }

        @Override
        public void onAdded() {

        }

        @Override
        public void onRun() throws Throwable {
            getDateFromCache();
        }

        @Override
        protected void onCancel() {

        }

        @Override
        protected boolean shouldReRunOnThrowable(Throwable throwable) {
            return false;
        }
    }

    private class GetCacheRunnable implements Runnable {

        @Override
        public void run() {
            getDateFromCache();

        }


    }

    boolean hasNet = true;

    // 通用方法，如果有网络从网络上获取数据，如果没有网络从缓存中获取数据
    private void netOrCache(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        //new Thread(new GetCachaTask(queue)).start();
        //jobManager.addJobInBackground(new GetCacheJob());
        new GetCacheTask().execute();
        if (hasNet) {
            CacheRequest stringRequest = new CacheRequest(Request.Method.GET, url,
                    listener, errorListener);
            // Add the request to the RequestQueue.
            queue.add(stringRequest);
        }
    }

    private void getDateFromCache() {
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 一旦缓存，缓存文件会永久存在
        long startTime = System.currentTimeMillis();
        Cache.Entry entry = queue.getCache().get("http://www.baidu.com");
        String cache = null;
        try {
            cache = new String(entry.data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        System.out.println(cache);
        System.out.println("缓存是否过期:" + entry.isExpired());//60秒后会过期
    }
}

