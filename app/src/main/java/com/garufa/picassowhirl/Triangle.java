package com.garufa.picassowhirl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLES20;

public class Triangle {
    private FloatBuffer vertexBuffer, colorBuff;
    private ByteBuffer indexBuffer;    // Buffer for index-array

    static float triangleCoords[] = {   // in counterclockwise order:
            0.0f,   1.0f,   0.0f, // top
            -1.0f, -0.5f,  0.0f, // bottom left
            1.0f,   -0.5f,  0.0f  // bottom right
    };
    static float rgbaVals[] = {   // in counterclockwise order:
            0, .8f, 0, .75f,        // top
            0, .3f, 0, .75f,        // bottom left
            0, .4f, 0, .75f         // bottom right
    };

    private byte[] indices = { 0, 1, 2 }; // Indices to above vertices (in CCW)

    // Constructor to take in variable coordinates and colors
    public Triangle(float[] triCoords, float[] rgbaVals){
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                triCoords.length * 4);
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(triCoords);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);

        // Setup index-array buffer. Indices in byte.
        indexBuffer = ByteBuffer.allocateDirect(indices.length);
        indexBuffer.order(ByteOrder.nativeOrder());
        indexBuffer.put(indices);
        indexBuffer.position(0);

        // Setup color-array buffer. Indices in byte.
        ByteBuffer cBuff = ByteBuffer.allocateDirect(rgbaVals.length * 4);
        cBuff.order(ByteOrder.nativeOrder());
        colorBuff = cBuff.asFloatBuffer();
        colorBuff.put(rgbaVals);
        colorBuff.position(0);
    }

    public Triangle() {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                triangleCoords.length * 4);
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(triangleCoords);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);

        // Setup index-array buffer. Indices in byte.
        indexBuffer = ByteBuffer.allocateDirect(indices.length);
        indexBuffer.order(ByteOrder.nativeOrder());
        indexBuffer.put(indices);
        indexBuffer.position(0);

        // Setup color-array buffer. Indices in byte.
        ByteBuffer cBuff = ByteBuffer.allocateDirect(rgbaVals.length * 4);
        cBuff.order(ByteOrder.nativeOrder());
        colorBuff = cBuff.asFloatBuffer();
        colorBuff.put(rgbaVals);
        colorBuff.position(0);
    }

    public void draw(GL10 gl) {
        // Enable vertex-array and define the buffers
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuff);

        // Draw the primitives via index-array
        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE, indexBuffer);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}
