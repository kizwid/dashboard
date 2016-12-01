package sandkev.dash;

import com.google.common.hash.Hashing;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * Created by kevin on 01/12/2016.
 */
public class Service2Test {


    public interface TaskMarker{
        Callable<String> call() throws Exception;
    }

    public interface Service<T>{
        CompletableFuture<Void> perform(TaskMarker task) throws Exception;
        void cancel(TaskMarker task); //who has responsibility to cancel the task(service or task)
    }


    @Test
    public void canMonitorTaskToCompletion() throws Exception {
        long start = System.currentTimeMillis();

        //Callable<Integer> foo;
        final ExecutorService executorService = Executors.newFixedThreadPool(3);
        final CompletionService<String> completionService = new ExecutorCompletionService(executorService);

        Service<String> service = new Service<String>() {
            @Override
            public CompletableFuture<Void> perform(TaskMarker task) throws Exception{
                Supplier<Collection<File>> listSupplier = () -> {
                    File rootDir = new File("/Users/kevin/Google Drive/photos");

                    Collection<File> files = FileUtils.listFiles(
                            rootDir,
                            new WildcardFileFilter("*.JPG", IOCase.INSENSITIVE),
                            TrueFileFilter.INSTANCE
                    );
                    return files;
                };

                CompletableFuture<Collection<File>> listReceiver
                        = CompletableFuture.supplyAsync(listSupplier);

                return listReceiver.thenAccept(files -> {
                    for (File file1 : files) {
                        try {
                            String hash = hashFile(file1);
                            System.out.println(hash + " -> " + file1);
                            //return hash;
                        } catch (IOException e) {
                            throw new IllegalStateException(e);
                        }
                    }
                });

            }

            @Override
            public void cancel(TaskMarker task) {

            }
        };

        CompletableFuture<Void> completableFuture = service.perform(null);
        completableFuture.join();

        System.out.println("time taken " + (System.currentTimeMillis() - start));
    }


    private String hashFile(File file) throws IOException {
        return Hashing.murmur3_128().hashBytes(FileUtils.readFileToByteArray(file)).toString();
    }



}