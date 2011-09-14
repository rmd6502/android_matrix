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
        matrixA = new MatrixAdaptor();
        gridViewA.setAdapter(matrixA);
        Log.i(LOG_TAG, "columns in gridview "+gridViewA.getNumColumns());
        findViewById(R.id.addColumnButton).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				gridViewA.setNumColumns(gridViewA.getNumColumns()+1);
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

	public void handleContextMenuClick(MenuItem item, int _pos, int _cols) {
		Log.i(LOG_TAG, "item "+item.toString()+" pos "+_pos+" cols "+_cols);	
	}
}