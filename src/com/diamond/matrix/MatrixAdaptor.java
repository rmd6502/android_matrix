package com.diamond.matrix;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Vector;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class MatrixAdaptor extends BaseAdapter {
	Vector<Double> theMatrix;
	int columns;
	NumberFormat nf;
	LayoutInflater infl;
	static final String LOG_TAG = "MatrixAdapter (no Keanu)";
	
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
	
	public int delRow(int rownum) {
		if (rownum < 0) {
			return -1;
		}
		int location = rownum * columns;
		if (location >= theMatrix.size()) {
			return -1;
		}
		for (int i=0; i < columns; ++i) {
			theMatrix.removeElementAt(location);
		}
		notifyDataSetChanged();
		return 0;
	}
	
	public int delColumn(int colnum) {
		if (colnum < 0 || colnum >= columns) {
			return -1;
		}
		--columns;
		for (int i=colnum+1; i <= theMatrix.size(); i += columns) {
			theMatrix.removeElementAt(i);
		}
		notifyDataSetChanged();
		return 0;
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
		final MatrixActivity _context = (MatrixActivity)parent.getContext();
		final int _pos = position;
		
		if (ret == null) {
			if (infl == null) {
				infl = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}
			ret = (EditText) infl.inflate(R.layout.grid_text_view, null);
		}
		final EditText _ret = ret;
		ret.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				PopupMenu pm = new PopupMenu(_context, _ret);
				pm.getMenuInflater().inflate(R.menu.popup, pm.getMenu());
				pm.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						_context.handleContextMenuClick(item, _pos);
						return true;
					}
				});
				pm.show();
				return true;
			}
		});
		
		ret.setText(nf.format(theMatrix.get(position)));
		
		ret.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				Number val = null;
				try {
					val = (Number) nf.parse(s.toString());
					theMatrix.set(_pos, val.doubleValue());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		return ret;
	}

}
