package com.jic.basegol.adapter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;


public class KeyValueAdapter
    extends BaseAdapter
    implements SpinnerAdapter
{

    private static String TAG = "KeyValueAdapter";

    private final Context _context;
    private final HashMap<String, String> _data;
    private final String[] _keys;
    private final String[] _values;
    
    private Typeface mTypeface;
    private Float mSize;
    private boolean mIsBold;

    
    public KeyValueAdapter(Context context, int textViewResourceId, LinkedHashMap<String, String> objects)
    {
    	
    	this( context,  textViewResourceId, objects, null, null, false);
    }

    public KeyValueAdapter(Context context, int textViewResourceId, LinkedHashMap<String, String> objects, Typeface typeface, Float textSize, boolean isBold)
    {
    	mIsBold = isBold;
    	mSize = textSize;
    	mTypeface = typeface;
        _context = context;
        _data = objects;
        _values = null;

        //get positions
        int i = 0;
        _keys = new String[_data.size()];

        Iterator<String> iter = _data.keySet().iterator();
        while(iter.hasNext()) {
            String key = iter.next();
            _keys[i] = key;
            i++;
        }
        
        /*
        for (final String e = (String) _data.keySet(); e.hasMoreElements();)
        {
            _keys[i] = e.nextElement().toString();
            i++;
        }*/
    }


    public int getCount()
    {
        return _data.size();
    }


    public int getPositionFromKey(String searchKey)
    {
        for (int i = 0; i < _keys.length; i++)
        {
            if (_keys[i].equals(searchKey))
                return i;
        }
        return -1;
    }


    public Object getItem(int position)
    {
        return _data.get(_keys[position]);
    }


    public long getItemId(int position)
    {

        if (position >= _keys.length || position < 0)
        {
            return -1;
        }

        //return Long.valueOf(_data.get(_keys[position]));
        return Long.valueOf(_keys[position]);
    }


    public View getView(int position, View view, ViewGroup parent)
    {
        //Set the text of the view to what you want it to display.
        //final int id = Integer.parseInt((_data.get(_keys[position])));
        String texto = (_data.get(_keys[position]));

        final TextView text = new TextView(_context);
        //text.setTextColor(Color.BLACK);
        text.setText(texto);
        if (mTypeface!=null){text.setTypeface(mTypeface);};
        if (mSize!=null){text.setTextSize(mSize);};
        //text.setGravity(Gravity.CENTER);
        if(mIsBold)
        {
        	text.setTypeface(null, Typeface.BOLD);
        }

        return text;
    }

}

