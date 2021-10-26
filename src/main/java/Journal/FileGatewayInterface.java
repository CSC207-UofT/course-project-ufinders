package Journal;

import java.io.File;
import java.time.LocalDate;

public interface FileGatewayInterface {

    public File addFile(String title,  String content, LocalDate date, String[] tags);

    public String[] getInfo(File fileWithInfo);

    public void deleteFile(File fileToDelete);



}
