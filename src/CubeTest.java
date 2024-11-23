import java.awt.Component;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.util.Arrays;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2ES1;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.fixedfunc.GLLightingFunc;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.math.*;

import com.jogamp.opengl.util.Animator;

/**
 * ported to JOGL 2.0 by Julien Gouesse (http://tuer.sourceforge.net)
 */
/**
 * modified by me!!!! :3
 */
public class CubeTest implements GLEventListener, KeyListener, MouseListener {
    private Vec2f rotateT = new Vec2f(0.0f, 0.0f);
    private Vec2f rotateV = new Vec2f(0.0f, 0.0f);
    private Vec2f mouseNorm = new Vec2f(0.0f, 0.0f);
    private float mouseSpeed = 1.0f;
    
    static GLU glu = new GLU();
    
    static GLCanvas canvas = new GLCanvas();
    
    static Frame frame = new Frame("Jogl Quad drawing");
    
    static Animator animator = new Animator(canvas);
    
    public void display(GLAutoDrawable gLDrawable) {
	final GL2 gl = gLDrawable.getGL().getGL2();
	gl.glClear(GL.GL_COLOR_BUFFER_BIT);
	gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
	gl.glLoadIdentity();
	gl.glTranslatef(0.0f, 0.0f, -5.0f);
	
	// rotate on the three axis
	gl.glRotatef(rotateT.y(), 1.0f, 0.0f, 0.0f);
	gl.glRotatef(rotateT.x(), 0.0f, 1.0f, 0.0f);
	
	// Draw A Quad
	gl.glBegin(GL2.GL_QUADS);//WHITE
	gl.glColor3f(1.0f, 1.0f, 1.0f); // set the color of the quad
	gl.glVertex3f(1.0f, 1.0f, 1.0f); // Top Left
	gl.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Right
	gl.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Right
	gl.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Left
	
	gl.glBegin(GL2.GL_QUADS);//YELLOW
	gl.glColor3f(1.0f, 1.0f, 0.0f); // set the color of the quad
	gl.glVertex3f(1.0f, 1.0f, -1.0f); // Top Left
	gl.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Right
	gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Right
	gl.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Left
	
	gl.glBegin(GL2.GL_QUADS);//BLUE
	gl.glColor3f(0.0f, 0.0f, 1.0f); // set the color of the quad
	gl.glVertex3f(1.0f, 1.0f, -1.0f); // Top Left
	gl.glVertex3f(1.0f, 1.0f, 1.0f); // Top Right
	gl.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Right
	gl.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Left
	
	gl.glBegin(GL2.GL_QUADS);//GREEN
	gl.glColor3f(0.0f, 1.0f, 0.0f); // set the color of the quad
	gl.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Left
	gl.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Right
	gl.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Right
	gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Left
	
	gl.glBegin(GL2.GL_QUADS);//ORANGE
	gl.glColor3f(1.0f, 0.5f, 0.0f); // set the color of the quad
	gl.glVertex3f(1.0f, 1.0f, 1.0f); // Top Left
	gl.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right
	gl.glVertex3f(-1.0f, 1.0f, -1.0f); // Bottom Right
	gl.glVertex3f(-1.0f, 1.0f, 1.0f); // Bottom Left
	
	gl.glBegin(GL2.GL_QUADS);//RED
	gl.glColor3f(1.0f, 0.0f, 0.0f); // set the color of the quad
	gl.glVertex3f(1.0f, -1.0f, 1.0f); // Top Left
	gl.glVertex3f(1.0f, -1.0f, -1.0f); // Top Right
	gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Right
	gl.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Left
	// Done Drawing The Quad
	gl.glEnd();

	rotateT = rotateT.add(rotateV);
    }
    
    public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged,
			       boolean deviceChanged) {
    }
    
    public void init(GLAutoDrawable gLDrawable) {
	GL2 gl = gLDrawable.getGL().getGL2();
	gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
	gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	gl.glClearDepth(1.0f);
	gl.glEnable(GL.GL_DEPTH_TEST);
	gl.glDepthFunc(GL.GL_LEQUAL);
	gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
	((Component) gLDrawable).addKeyListener(this);
	((Component) gLDrawable).addMouseListener(this);
    }
    
    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width,
			int height) {
	GL2 gl = gLDrawable.getGL().getGL2();
	if (height <= 0) {
	    height = 1;
	}
	float h = (float) width / (float) height;
	gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
	gl.glLoadIdentity();
	glu.gluPerspective(50.0f, h, 1.0, 1000.0);
	gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
	gl.glLoadIdentity();
    }
    
    public void keyPressed(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	    exit();
	}
    }
    
    public void keyReleased(KeyEvent e) {
    }
    
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e){
	
    }

    @Override
    public void mouseEntered(MouseEvent e){
	
    }

    @Override
    public void mouseReleased(MouseEvent e){
	Vec2f releaseNorm = new Vec2f( (float)e.getX() / (float)canvas.getWidth(), (float)e.getY() / (float)canvas.getHeight() );
	Vec2f mouseDiff = releaseNorm.minus(mouseNorm);
	rotateV = mouseDiff.isZero() ? new Vec2f(0.0f, 0.0f) : rotateV.add(mouseDiff.mul(mouseSpeed));
    }

    @Override
    public void mousePressed(MouseEvent e){
	mouseNorm = new Vec2f( (float)e.getX() / (float)canvas.getWidth(), (float)e.getY() / (float)canvas.getHeight() );
    }

    @Override
    public void mouseClicked(MouseEvent e){
    }
    
    public static void exit() {
	animator.stop();
	frame.dispose();
	System.exit(0);
    }
    
    public static void main(String[] args) throws SecurityException,
						  NoSuchFieldException, IllegalArgumentException,
						  IllegalAccessException {
	
	canvas.addGLEventListener(new CubeTest());
	frame.add(canvas);
	frame.setSize(640, 480);
	frame.setUndecorated(true);
	frame.setExtendedState(Frame.MAXIMIZED_BOTH);
	frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    exit();
		}
	    });
	frame.setVisible(true);
	animator.start();
	canvas.requestFocus();
    }
    
    public void dispose(GLAutoDrawable gLDrawable) {
	// do nothing
    }
}
