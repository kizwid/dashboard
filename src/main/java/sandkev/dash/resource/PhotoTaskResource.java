package sandkev.dash.resource;

import com.google.common.hash.Hashing;
import com.mongodb.MongoClient;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.io.input.ReaderInputStream;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import sandkev.dash.pojo.PhotoTask;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/photo")
@Produces(APPLICATION_JSON)
public class PhotoTaskResource {
    protected final static char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private Datastore datastore;

    public PhotoTaskResource(MongoClient mongoClient) {
        //datastore = new Morphia().createDatastore(mongoClient, "Cafelito");
    }

    @Path("notify/{task}")
    @POST()
    @Consumes(MediaType.APPLICATION_JSON)
    public Response runTask(@PathParam("task") String task) throws IOException {

        PhotoTask photoTask = new PhotoTask(task);
        processTask(photoTask);

        return Response.created(URI.create("1"))
                       .entity(photoTask)
                       .build();
    }

    private void processTask(PhotoTask photoTask) throws IOException {
        File rootDir = new File("/Users/kevin/Google Drive/photos");

        Collection<File> files = FileUtils.listFiles(
                rootDir,
                new WildcardFileFilter("*.JPG", IOCase.INSENSITIVE),
                TrueFileFilter.INSTANCE
        );

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletionService<String> completionService = new ExecutorCompletionService(executorService);

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

        Map<String, Set<File>> filesByHex = new HashMap<>();
        for (Map.Entry<File, String> ent : file2Hex.entrySet()) {
            File file = ent.getKey();
            String key = ent.getValue();
            Set<File> filesForHex = filesByHex.get(key);
            if (filesForHex == null) {
                filesByHex.put(key, filesForHex = new HashSet<>());
            }
            filesForHex.add(file);
        }
        Map<String, Set<File>> dups = new HashMap();
        for (Map.Entry<String, Set<File>> entry : filesByHex.entrySet()) {
            String key = entry.getKey();
            Set<File> fileSet = entry.getValue();
            if (fileSet.size() > 1) {
                dups.put(key, fileSet);
            }
        }


        System.out.println("found " + files.size() + " files " + (System.currentTimeMillis() - start));
        for (Map.Entry<String, Set<File>> entry : dups.entrySet()) {
            System.out.println(entry);
        }
        System.out.println(dups.size()+" duplicates found");

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


    public static String checksum(Reader reader) throws IOException {
        InputStream fis = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA1");

            fis = new ReaderInputStream(reader);
            byte[] data = new byte[1024 * 4];
            int read;
            while ((read = fis.read(data)) != -1) {
                digest.update(data, 0, read);
            }
            return Hex.encodeHexString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IOException(e);
        } finally {
            if(fis!=null)fis.close();
        }
    }


}
