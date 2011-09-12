package com.diamond.matrix;

import java.text.NumberFormat;
import java.util.Vector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

public class MatrixAdaptor extends BaseAdapter {
	Vector<Double> theMatrix;
	int columns;
	NumberFormat nf;
	LayoutInflater infl;
	
	public MatrixAdaptor() {
		nf = NumberFormat.getNumberInstance();
		
		columns = 3;
		theMatrix = new Vector<Double>();
		addRow();
	}
	
	public int addRow() {
		for (int i=0; i < columns; ++i) {
			theMatrix.add(0.0);
		}
		notifyDataSetChanged();
		return theMatrix.size()/columns;
	}
	
	public int addColumn() {
		int newSize = theMatrix.size()/columns * (columns + 1);
		for (int i=columns; i <= newSize; i += columns) {
			theMatrix.add(i, 0.0);
			++i;
		}
		++columns;
		notifyDataSetChanged();
		return columns;
	}
	
	@Override
	public int getCount() {
		return theMatrix.size();
	}

	@Override
	public Object getItem(int arg0) {
		return theMatrix.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		EditText ret = (EditText)convertView;
		if (ret == null) {
			if (infl == null) {
				infl = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}
			ret = (EditText) infl.inflate(R.layout.grid_text_view, null);
		}
		ret.setText(nf.format(theMatrix.get(position)));
		
		return ret;
	}

}
