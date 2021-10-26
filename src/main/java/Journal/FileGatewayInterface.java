package Journal;

import java.io.File;
import java.time.LocalDate;

public interface FileGatewayInterface {

    public File addFile(String title,  String content, LocalDate date, String[] tags);

    public String[] getInfo(String title,  String content, LocalDate date, String[] tags);

    public void deleteFile(File fileToDelete);



}
