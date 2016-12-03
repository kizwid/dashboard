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
        CompletableFuture<Map<File,String>> perform(TaskMarker task) throws Exception;
        void cancel(TaskMarker task); //who has responsibility to cancel the task(service or task)
    }

    public interface FileListener{
        void onFile(File file);
    }

    @Test
    public void canMonitorTaskToCompletion() throws Exception {
        long start = System.currentTimeMillis();

        //Callable<Integer> foo;
        final ExecutorService executorService = Executors.newFixedThreadPool(7);
        final CompletionService<AbstractMap.SimpleEntry> completionService = new ExecutorCompletionService(executorService);

        Service<Map<File,String>> service = new Service<Map<File,String>>() {
            @Override
            public CompletableFuture<Map<File,String>> perform(TaskMarker task) throws Exception{
                File rootDir = new File("/Users/kevin/Google Drive/photos");

                Supplier<Map<File,String>> supplier = new Supplier<Map<File, String>>() {
                    @Override
                    public Map<File, String> get() {

                        Map<File,String> hashByFile = new HashMap<>();
                        WildcardFileFilter fileFilter = new WildcardFileFilter("*.JPG", IOCase.INSENSITIVE);
                        Collection<File> files = FileUtils.listFiles(
                                rootDir,
                                fileFilter,
                                TrueFileFilter.INSTANCE
                        );


                        for (File file : files) {
                            completionService.submit(
                                    new Callable<AbstractMap.SimpleEntry>() {
                                        @Override
                                        public AbstractMap.SimpleEntry call() throws Exception {
                                            AbstractMap.SimpleEntry entry = new AbstractMap.SimpleEntry(file, hashFile(file));
                                            System.out.println(entry);
                                            return entry;
                                            //return hashFile(file);
                                        }
                                    }
                            );
                        }
                        for (File file : files) {
                            try {
                                AbstractMap.SimpleEntry<File, String> entry = completionService.take().get();
                                hashByFile.put(entry.getKey(), entry.getValue());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }



                        return hashByFile;
                    }
                };

                return CompletableFuture.supplyAsync(supplier);

            }

            @Override
            public void cancel(TaskMarker task) {

            }
        };

        CompletableFuture<Map<File,String>> completableFuture = service.perform(null);
        completableFuture.join();

        System.out.println("time taken " + (System.currentTimeMillis() - start));

        //3=76218, 5=67
    }


    private String hashFile(File file) throws IOException {
        return Hashing.murmur3_128().hashBytes(FileUtils.readFileToByteArray(file)).toString();
    }



}