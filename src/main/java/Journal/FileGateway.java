package Journal;

import java.io.File;
import java.time.LocalDate;

public interface FileGateway {

    public File createFile(String title, String content, LocalDate date, String tags);

    public String[] getEntryInfo(File fileWithInfo);

    public void deleteFile(File fileToDelete);



}
