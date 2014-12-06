package com.garufa.picassowhirl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.Matrix;

import java.util.concurrent.TransferQueue;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {


    static float triangleCoords[] = {   // in counterclockwise order:
            0.0f,   -1.0f,   0.0f, // top
            -1.0f, 0.5f,  0.0f, // bottom left
            1.0f,   0.5f,  0.0f  // bottom right
    };
    static float rgbaVals[] = {   // in counterclockwise order:
            0, 1, 1, 1,       // top
            0, 1, 1, 1,   // bottom left
            0, 1, 1, 1f         // bottom right
    };
    private Triangle triangle1, triangle2, triangle3, triangle4, triangle5;
    private Triangle mTriangle;
    Square mSquare;

    // mMVPMatrix is an abbreviation for "Model View Projection Matrix"
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] mRotationMatrix = new float[16];

    public MyGLRenderer() {
        mTriangle = new Triangle();
        triangle1 = new Triangle(triangleCoords, rgbaVals);
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Improve performance
        unused.glDisable(GL10.GL_DITHER);
        unused.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

        // Set the background frame color
        GLES20.glClearColor(.8f, 0f, .2f, 1f);
        GLES20.glClearDepthf(1f);
        // initialize a triangle
//        mTriangle = new Triangle();
        // initialize a square
//        mSquare = new Square();
    }

    public void onDrawFrame(GL10 g1) {
        float[] scratch = new float[16];

        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

//        g1.glMatrixMode(GL10.GL_MODELVIEW);
//        g1.glLoadIdentity();
//        GLU.gluLookAt(g1, 0, 0, 2, 0, 0, 0, 0, 2f, 0);

        // Draw triangle
        mTriangle.draw(g1);
        triangle1.draw(g1);

    }

    public void onSurfaceChanged(GL10 g1, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        // Set ratio to account for portrait or landscape mode
//        float ratio = (float) width/height;
//        g1.glMatrixMode(GL10.GL_PROJECTION);
//        g1.glLoadIdentity();
//        // How far in the z direction we want the object to be seen
//        g1.glFrustumf(-ratio, ratio, -1, 1, 1, 25);
    }

}