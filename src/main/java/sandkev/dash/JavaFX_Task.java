package sandkev.dash;

import com.google.common.hash.Hashing;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/**
 * @web http://java-buddy.blogspot.com/
 */
public class JavaFX_Task extends Application {

    @Override
    public void start(Stage primaryStage) {

        final AtomicReference<Task<Void>> task = new AtomicReference();

        final ProgressBar progressBar = new ProgressBar();

        Label labelCount = new Label();

        final Label labelState = new Label();

        Button startButton = new Button("Start Task");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {

                Task<Void> localTask = getTask("/Users/kevin/Google Drive/photos", 10);
                task.set(localTask);
                //progressBar.setProgress(0);
                progressBar.progressProperty().bind(localTask.progressProperty());
                labelCount.textProperty().bind(localTask.messageProperty());
                new Thread(localTask).start();
                labelState.setText(localTask.getState().toString());
            }
        });

        Button cancelButton = new Button("Cancel Task");
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Task<Void> localTask = task.get();
                localTask.cancel();
            }
        });

        Button btnReadTaskState = new Button("Read Task State");
        btnReadTaskState.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                labelState.setText(task.get().getState().toString());
            }
        });


        VBox vBox = new VBox();
        vBox.setPadding(new Insets(5, 5, 5, 5));
        vBox.setSpacing(5);
        vBox.getChildren().addAll(
                progressBar,
                labelCount,
                startButton,
                cancelButton,
                btnReadTaskState,
                labelState);

        StackPane root = new StackPane();
        root.getChildren().add(vBox);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("java-buddy.blogspot.com");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private Task<Void> getTask(final String pathname, final int numThreads) {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                final ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
                final CompletionService<Map.Entry> completionService = new ExecutorCompletionService(executorService);

                File rootDir = new File(pathname);

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
                                    new Callable<Map.Entry>() {
                                        @Override
                                        public Map.Entry call() throws Exception {
                                            if (isCancelled()) {
                                                return null;
                                            }
                                            Map.Entry entry = new AbstractMap.SimpleEntry(file, hashFile(file));
                                            return entry;
                                        }
                                    }
                            );
                        }
                        double n = 0;
                        double max = files.size();
                        for (File file : files) {
                            try {
                                n++;
                                double percentage = (n / max) * 100;
                                if (isCancelled()) {
                                    updateMessage("cancelled at " + ((int)percentage) + "% complete");
                                    break;
                                }
                                String message = "done " + (int) n + " of " + (int) max + " (" + (int) percentage + "%)";
                                updateProgress(n, max);
                                updateMessage(message);
                                System.out.print("\r" + message);
                                //}
                                Map.Entry<File, String> entry = completionService.take().get();
                                hashByFile.put(entry.getKey(), entry.getValue());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }



                        return hashByFile;
                    }
                };

                CompletableFuture<Map<File, String>> supplyAsync = CompletableFuture.supplyAsync(supplier, executorService);
                //return CompletableFuture.supplyAsync(supplier);

                supplyAsync.join();


/*
                int max = 50;
                for (int i = 1; i <= max; i++) {
                    if (isCancelled()) {
                        break;
                    }
                    updateProgress(i, max);
                    updateMessage(String.valueOf(i));

                    Thread.sleep(100);
                }
*/
                return null;
            }
            private String hashFile(File file) throws IOException {
                return Hashing.murmur3_128().hashBytes(FileUtils.readFileToByteArray(file)).toString();
            }
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}


