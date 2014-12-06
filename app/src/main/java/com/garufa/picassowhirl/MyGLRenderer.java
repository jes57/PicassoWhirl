package com.garufa.picassowhirl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import java.lang.Math;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {

    static float triangleCoords[] = {   // in counterclockwise order:
         // x,      y,          z
            0.0f,   -1.0f,      0.0f, // top
            -1.0f,  0.5f,       0.0f, // bottom left
            1.0f,   0.5f,       0.0f  // bottom right
    };
    static float rgbaVals[] = {   // in counterclockwise order:
            0, .2f, 0, .75f,        // top
            0, .6f, 0, .75f,        // bottom left
            0, .4f, 0, .75f         // bottom right
    };
    static float trunkCoords[] = {   // in counterclockwise order:
            // x,      y,   z
            0.0f,   .8f,    0.0f,   // top
            -.15f,  -.9f,    0,      // bottom left
            .15f,   -.9f,     0.0f    // bottom right
    };
    static float trunkColor[] = {   // in counterclockwise order:
            .53f, .30f, .21f, .75f,        // top
            .53f, .30f, .21f, .75f,        // bottom left
            .35f, .18f, .14f, .75f         // bottom right
    };
    static float snowCoords[] = {   // in counterclockwise order:
            // x,      y,   z
            0f,   -5f,    0f,   // top
            -100f,  0f,    0,      // bottom left
            100f,   0f,     0f    // bottom right
    };
    static float snowColor[] = {   // in counterclockwise order:
            1f, 1f, 1f, 1f,        // top
            1f, 1f, 1f, 1f,        // bottom left
            1f, 1f, 1f, 1f         // bottom right
    };
    static float skyCoords[] = {   // in counterclockwise order:
            // x,      y,   z
           0f,   1.5f,    0f,   // top
            -100f,  0f,    0,      // bottom left
            100f,   0f,     0f    // bottom right
    };
    static float skyColor[] = {   // in counterclockwise order:
            0f, .6f, .8f, 1f,        // top
            .53f, .8f, 1f, 1f,        // bottom left
            .53f, .8f, 1f, 1f         // bottom right
    };
    private Triangle triangle1, triangle2, triangle3, triangle4, triangle5, triangle6,
            triangle7, snow, sky;

    public MyGLRenderer() {
        snow = new Triangle(snowCoords, snowColor);
        sky = new Triangle(skyCoords, skyColor);
        triangle7 = new Triangle(trunkCoords, trunkColor);
        triangleCoords = changeCoords(.2f, 1f);
        triangle1 = new Triangle(triangleCoords, rgbaVals);
        triangleCoords = changeCoords(.4f, .8f);
        triangle2 = new Triangle(triangleCoords, rgbaVals);
        triangleCoords = changeCoords(.6f, .6f);
        triangle3 = new Triangle(triangleCoords, rgbaVals);
        triangleCoords = changeCoords(.8f, .4f);
        triangle4 = new Triangle(triangleCoords, rgbaVals);
        triangleCoords = changeCoords(.975f, .15f);
        rgbaVals = changeColor(.8f, .8f, 0, 0);
        triangle5 = new Triangle(triangleCoords, rgbaVals);
        triangleCoords = changeCoordsFlipped(.8f, .15f);
        triangle6 = new Triangle(triangleCoords, rgbaVals);

    }

    public float[] changeColor(float r, float g, float b, float a){
        float newColor[] = rgbaVals;

        newColor[0] = r;
        newColor[1] = g;
        newColor[2] = b;
        newColor[3] = a;
        newColor[4] = r;
        newColor[5] = g;
        newColor[6] = b;
        newColor[7] = a;
        newColor[8] = r;
        newColor[9] = g;
        newColor[10] = b;
        newColor[11] = a;

        return newColor;
    }


    public float[] changeCoords(float top, float z){
        float newCoords[] = triangleCoords;
        float height, topX, topY, leftX, leftY, rightX, rightY;

        height = (float) Math.sqrt( ( (z*z)- ((z/2)*(z/2)) ));
        topX = 0;
        topY = top;

        leftX = -z/2;
        leftY = top - height;

        rightX = z/2;
        rightY = top - height;

        newCoords[0] = topX;
        newCoords[1] = topY;
        newCoords[3] = leftX;
        newCoords[4] = leftY;
        newCoords[6] = rightX;
        newCoords[7] = rightY;

        return newCoords;
    }

    public float[] changeCoordsFlipped(float bottom, float z){
        float newCoords[] = triangleCoords;
        float height, topX, topY, leftX, leftY, rightX, rightY;

        height = (float) Math.sqrt( ( (z*z)- ((z/2)*(z/2)) ));
        topX = 0;
        topY = bottom;

        leftX = -z/2;
        leftY = bottom + height;

        rightX = z/2;
        rightY = bottom + height;

        newCoords[0] = topX;
        newCoords[1] = topY;
        newCoords[3] = leftX;
        newCoords[4] = leftY;
        newCoords[6] = rightX;
        newCoords[7] = rightY;

        return newCoords;
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Improve performance
        unused.glDisable(GL10.GL_DITHER);
        unused.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

        // Set the background frame color
        GLES20.glClearColor(.8f, 0f, .2f, .2f);
        GLES20.glClearDepthf(1f);
    }

    public void onDrawFrame(GL10 g1) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        g1.glMatrixMode(GL10.GL_MODELVIEW);
        g1.glLoadIdentity();
        GLU.gluLookAt(g1, 0, 0, 1, 0, 0, 0, 0, 2f, 0);

        // Draw triangle
        snow.draw(g1);
        sky.draw(g1);
        triangle7.draw(g1);
        triangle1.draw(g1);
        triangle2.draw(g1);
        triangle3.draw(g1);
        triangle4.draw(g1);
        triangle5.draw(g1);
        triangle6.draw(g1);

    }

    public void onSurfaceChanged(GL10 g1, int width, int height) {
        g1.glViewport(0, 0, width, height);

        // Set ratio to account for portrait or landscape mode
        // Should take care of equilateral triangles being distorted
        float ratio = (float) width/height;
        g1.glMatrixMode(GL10.GL_PROJECTION);
        g1.glLoadIdentity();
        // How far in the z direction we want the object to be seen
        // The -1 and 1 tell OpenGL that the top and bottom are 1 and -1 repectively
        g1.glFrustumf(-ratio, ratio, -1, 1, 1, 25);
    }

}