package org.pong.ping;
import java.util.logging.Handler;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
	private GameThread _thread;
	public GameView(Context context, AttributeSet attrs){
	super(context,attrs);
	
	//so we can listen for events....
	SurfaceHolder holder = getHolder();
	holder.addCallback(this);
	setFocusable(true);
	
	// and instatiate the thread
	_thread = new GameThread(holder, context, new Handler());
	
}

@Override
public boolean onKeyDown(int keyCode, KeyEvent msg){
	return _thread.getGameState().keyPressed(keyCode,msg);
}
//Implemented as part of the SurfaceHolder.Callback interface
@Override
public void surfaceChanged(SurfaceHolder holder,int format,int width,int height){
//mandatory	
}
@Override
public void surfaceCreated(SurfaceHolder holder){
	_thread.start();
}

@Override
public void surfaceDestroyed(SurfaceHolder holder){
	_thread.stop();
}


}

//
