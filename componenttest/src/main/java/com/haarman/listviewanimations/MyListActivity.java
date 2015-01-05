/*
 * Copyright 2014 Niek Haarman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.haarman.listviewanimations;

import com.zhejiangnews.demo.R;

import android.os.Bundle;
import android.widget.ListView;

public class MyListActivity extends BaseActivity {

    private ListView mListView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylist);
        mListView = (ListView) findViewById(R.id.activity_mylist_listview);
    }

    public ListView getListView() {
        return mListView;
    }

    protected MyListAdapter createListAdapter() {
        return new MyListAdapter(this);
    }
}
