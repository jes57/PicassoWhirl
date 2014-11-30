package com.basementkittehs.www.simplegraphics;

import android.opengl.GLSurfaceView;
import android.content.Context;

class MyGLSurfaceView extends GLSurfaceView {

    public MyGLSurfaceView(Context context){
        super(context);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(new MyGLRenderer());
    }
}