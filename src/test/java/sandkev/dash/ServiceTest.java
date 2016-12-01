package sandkev.dash;

import com.google.common.hash.Hashing;
import com.google.common.util.concurrent.AtomicDouble;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.junit.Test;
import sandkev.dash.pojo.PhotoTask;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

import static org.junit.Assert.*;

/**
 * Created by kevin on 01/12/2016.
 */
public class ServiceTest {

    public interface Monitor{
        void updateProgress(double workDone, double max);
        void updateState(Task.State state);
    }

    public interface MonitorListener{
/*
        void onReady(Task task);
        void onScheduled(Task task);
        void onRunning(Task task);
        void onSucceeded(Task task);
        void onFailed(Task task);
        void onCancelled(Task task);
        void onProgressUpdate(Task task, double percentage);
*/
        void onStatusUpdate(Task.State state, double progress);
    }

    public interface Service<T>{
        T perform(Task task);
        void cancel(Task task); //who has responsibility to cancel the task(service or task)
    }


    @Test
    public void canMonitorTaskToCompletion(){

        //Callable<Integer> foo;
        final ExecutorService executorService = Executors.newFixedThreadPool(3);
        final CompletionService<String> completionService = new ExecutorCompletionService(executorService);

        Service<Map<File,String>> photoService = new Service<Map<File,String>>() {
            @Override public Map<File,String> perform(Task task) {
                File rootDir = new File("/Users/kevin/Google Drive/photos");

                Collection<File> files = FileUtils.listFiles(
                        rootDir,
                        new WildcardFileFilter("*.JPG", IOCase.INSENSITIVE),
                        TrueFileFilter.INSTANCE
                );
                long start = System.currentTimeMillis();
                for (File file : files) {
                    completionService.submit(() -> {
                        String hex = hashFile(file);
                        System.out.println(hex + " " +  file.getAbsoluteFile());
                        return hex;
                    });
                }

                System.out.println("found " + files.size() + " files " + (System.currentTimeMillis() - start));
                Map<File,String> file2Hex = new HashMap<>();
                for (File file : files) {
                    try {
                        String hex = completionService.take().get();
                        file2Hex.put(file, hex);

                    } catch (InterruptedException e) {
                        Thread.interrupted();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
                return file2Hex;
            }

            @Override
            public void cancel(Task task) {

            }
        };

        Task<Boolean> task = new Task<Boolean>() {
            @Override
            public Boolean call() {
                return null;
            }

            @Override
            public void cancel() {

            }

            @Override
            public void updateProgress(double workDone, double max) {

            }

            @Override
            public void updateState(double workDone, double max) {

            }
        };


        final MonitorListener monitorListener = new MonitorListener() {
            @Override
            public void onStatusUpdate(Task.State state, double progress) {
                System.out.println("state: " + state + " progress " + progress);
            }
        };

        final AtomicDouble progress = new AtomicDouble();
        Monitor monitor = new Monitor() {
            @Override
            public void updateProgress(double workDone, double max) {
                progress.getAndSet(workDone/max);
            }

            @Override
            public void updateState(Task.State state) {
                monitorListener.onStatusUpdate(state, progress.get());
            }
        };
        photoService.perform(task);





    }


    private String hashFile(File file) throws IOException {
        //found 12260 files 129899  *.JPG <-- 1
        //found 12260 files 76215 <-- 3
        //found 12260 files 63327
        //found 13340 files 61248
        //found 1085 files 3380 <-- 1
        //found 1085 files 1614 <-- 3
        //found 1085 files 1613 <-- 5
        //found 1085 files 1393 <-- 10

        return Hashing.murmur3_128().hashBytes(FileUtils.readFileToByteArray(file)).toString();

        //found 1085 files 67508 <-- 3
        //return checksum(new FileReader(file));

        //return DigestUtils.sha1Hex(FileUtils.readFileToByteArray(file));

    }



}