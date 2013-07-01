package com.xiao.bsgen;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 2 level menu that lets you view the word list used to generate BS.
 * Created by Xiao on 6/30/13.
 */
public class WordListActivity extends Activity {
    private BSGenerator generator;
    private ListView listView;

    private List<String> menuStrList;

    private static final int ADVERBS_POS = 0;
    private static final int ADJECTIVES_POS = 1;
    private static final int VERBS_POS = 2;
    private static final int NOUNS_POS = 3;

    private int menuLevel = 0;

    AdapterView.OnItemClickListener menuListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch(i)
            {
                case ADJECTIVES_POS:
                    navigateTo(generator.getAdjectives(), null, true);
                    break;
                case ADVERBS_POS:
                    navigateTo(generator.getAdverbs(), null, true);
                    break;
                case NOUNS_POS:
                    navigateTo(generator.getNouns(), null, true);
                    break;
                case VERBS_POS:
                    navigateTo(generator.getVerbs(), null, true);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordlist);
        generator = BSGenerator.getInstance();

        loadResources(getResources());

        listView = (ListView) findViewById(R.id.listview);
        listView.setTextFilterEnabled(true);

        populate(menuStrList,menuListener);
    }

    private void loadResources(Resources r){
        menuStrList = new ArrayList<String>();
        menuStrList.add(ADVERBS_POS, r.getString(R.string.adverbs_label));
        menuStrList.add(ADJECTIVES_POS, r.getString(R.string.adjectives_label));
        menuStrList.add(VERBS_POS, r.getString(R.string.verbs_label));
        menuStrList.add(NOUNS_POS, r.getString(R.string.nouns_label));
    }

    private void populate(List<String> items, AdapterView.OnItemClickListener listener){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setOnItemClickListener(listener);
        listView.setAdapter(adapter);
    }
    private void navigateTo(List<String> items, AdapterView.OnItemClickListener listener, boolean goingDown)
    {
        populate(items,listener);
        if(goingDown)
        {
            menuLevel ++;
        }
        else
        {
            menuLevel --;
        }
    }

    //Override back button to navigate menu layers
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if ( keyCode == KeyEvent.KEYCODE_BACK && menuLevel!=0) {
            navigateTo(menuStrList, menuListener, false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
