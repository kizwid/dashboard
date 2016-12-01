package sandkev.dash;

/**
 * Created by kevin on 01/12/2016.
 */
public interface Task<T> {
    enum State {
        /**
         * Indicates that the Worker has not yet been executed and is ready
         * to be executed, or that it has been reinitialized. This is the
         * default initial state of the Worker.
         */
        READY,
        /**
         * Indicates that the Worker has been scheduled for execution, but
         * that it is not currently running. This might be because the
         * Worker is waiting for a thread in a thread pool to become
         * available before it can start running.
         */
        SCHEDULED,
        /**
         * Indicates that this Worker is running. This is set just immediately
         * prior to the Worker actually doing its first bit of work.
         */
        RUNNING,
        /**
         * Indicates that this Worker has completed successfully, and that there
         * is a valid result ready to be read from the <code>value</code> property.
         */
        SUCCEEDED,
        /**
         * Indicates that this Worker has been cancelled via the {@link #cancel()}
         * method.
         */
        CANCELLED,
        /**
         * Indicates that this Worker has failed, usually due to some unexpected
         * condition having occurred. The exception can be retrieved from the
         * <code>exception</code> property.
         */
        FAILED
    }
    T call();
    void cancel();
    void updateProgress(double workDone, double max);
    void updateState(double workDone, double max);
}
