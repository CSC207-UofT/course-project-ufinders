package Journal;

import java.io.File;
import java.time.LocalDate;

public interface FileGatewayInterface {

    public boolean addFile(String title,  String content, LocalDate date, String[] tags);



}
