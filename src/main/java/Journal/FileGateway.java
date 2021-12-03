package Journal;

import java.io.File;
import java.time.LocalDate;

public interface FileGateway {

    File createFile(String title, String content, LocalDate date, String tags);

     String[] getEntryInfo(File fileWithInfo);

     void deleteFile(File fileToDelete);



}
