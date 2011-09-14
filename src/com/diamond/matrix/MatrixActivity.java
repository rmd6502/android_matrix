package com.diamond.matrix;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;

public class MatrixActivity extends Activity {
    /** Called when the activity is first created. */
	GridView gridViewA;
	MatrixAdaptor matrixA;
	
	static final String LOG_TAG="MatrixActivity";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        gridViewA = (GridView) findViewById(R.id.gridView1);
        if (savedInstanceState != null && savedInstanceState.containsKey("matrixA")) {
        	matrixA = new MatrixAdaptor(savedInstanceState.getSerializable("matrixA"), savedInstanceState.getInt("columnsA"));
        } else {
        	matrixA = new MatrixAdaptor();
        }
        gridViewA.setAdapter(matrixA);
        gridViewA.setNumColumns(matrixA.columns);
        Log.i(LOG_TAG, "columns in gridview "+gridViewA.getNumColumns());
        findViewById(R.id.addColumnButton).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				matrixA.addColumn();
			}
		});
        findViewById(R.id.addRowButton).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				matrixA.addRow();
			}
		});
        findViewById(R.id.solveButton).setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View v) {
				
			}
		});
        findViewById(R.id.transposeButton).setOnClickListener(new OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				
			}
		});
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	outState.putSerializable("matrixA", matrixA.getDoubleArray());
    	outState.putInt("columnsA", matrixA.columns);
    }

	public void handleContextMenuClick(MenuItem item, int _pos) {
		Log.i(LOG_TAG, "item "+item.toString()+" pos "+_pos+" cols "+matrixA.columns);
		if (item.getTitle().equals("Delete Column")) {
			matrixA.delColumn(_pos % matrixA.columns);
		} else if (item.getTitle().equals("Delete Row")) {
			matrixA.delRow(_pos / matrixA.columns);
		} else if (item.getTitle().equals("Add Row")) {
			matrixA.addRow(_pos / matrixA.columns);
		} else if (item.getTitle().equals("Add Column")) {
			matrixA.addColumn(_pos % matrixA.columns);
		}
	}
}