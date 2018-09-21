
package SoundSystem;


public interface Music {

	/**
	 * Play this Music and loop if specified.
	 * @param loop if this Music should loop
	 */
	public void play(boolean loop);
	
	/**
	 * Play this Music at the specified volume and loop if specified.
	 * @param loop if this Music should loop
	 * @param volume the volume to play the this Music
	 */
	public void play(boolean loop, double volume);
	
	/**
	 * Play this Music at the specified volume and pan, and loop if specified.
	 * @param loop if this Music should loop
	 * @param volume the volume to play the this Music
	 * @param pan the pan at which to play this Music [-1.0,1.0], values outside
	 * the valid range will be ignored
	 */
	public void play(boolean loop, double volume, double pan);
	
	/**
	 * Stop playing this Music and set its position to the beginning.
	 */
	public void stop();
	
	/**
	 * Stop playing this Music and keep its current position.
	 */
	public void pause();
	
	/**
	 * Play this Music from its current position.
	 */
	public void resume();
	
	/**
	 * Set this Music's position to the beginning.
	 */
	public void rewind();
	
	/**
	 * Set this Music's position to the loop position.
	 */
	public void rewindToLoopPosition();
	
	/**
	 * Determine if this Music is playing.
	 * @return true if this Music is playing
	 */
	public boolean playing();
	
	/**
	 * Determine if this Music has reached its end and is done playing.
	 * @return true if this Music is at the end and is done playing
	 */
	public boolean done();
	
	/**
	 * Determine if this Music will loop.
	 * @return true if this Music will loop
	 */
	public boolean loop();
	
	/**
	 * Set whether this Music will loop.
	 * @param loop whether this Music will loop
	 */
	public void setLoop(boolean loop);
	
	/**
	 * Get the loop position of this Music by sample frame.
	 * @return loop position by sample frame
	 */
	public int getLoopPositionByFrame();
	
	/**
	 * Get the loop position of this Music by seconds.
	 * @return loop position by seconds
	 */
	public double getLoopPositionBySeconds();
	
	/**
	 * Set the loop position of this Music by sample frame.
	 * @param frameIndex sample frame loop position to set
	 */
	public void setLoopPositionByFrame(int frameIndex);
	
	/**
	 * Set the loop position of this Music by seconds.
	 * @param seconds loop position to set by seconds
	 */
	public void setLoopPositionBySeconds(double seconds);
	
	/**
	 * Get the volume of this Music.
	 * @return volume of this Music
	 */
	public double getVolume();
	
	/**
	 * Set the volume of this Music.
	 * @param volume the desired volume of this Music
	 */
	public void setVolume(double volume);
	
	/**
	 * Get the pan of this Music.
	 * @return pan of this Music
	 */
	public double getPan();
	
	/**
	 * Set the pan of this Music.  Must be between -1.0 (full pan left) and 1.0
	 * (full pan right).  Values outside the valid range will be ignored.
	 * @param pan the desired pan of this Music
	 */
	public void setPan(double pan);
	
	/**
	 * Unload this Music from the system.  Attempts to use this Music after
	 * unloading will result in error.
	 */
	public void unload();
	
}
