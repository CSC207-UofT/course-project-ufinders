package User;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class MakeDir {
    // The path of the file we created
    private File dir;

    /**
     * Creates a file and stores the path of the new directory in dir.
     * @param path the place where the new directory will be created.
     */


    public MakeDir(String path){
        this.dir = new File(path);
        createDir();
    }
    /**
     * Makes the new directory dir.
     */
    public void createDir(){
        this.dir.mkdir(); // folder with that name could exist so must check that
    }

    /**
     * Gets the path of dir and returns it.
     * @return  the path of dir.
     */
    public String getPath(){
        return this.dir.getAbsolutePath();
    }

    /**
     * Gets the file that dir stores.
     * @return  the file that dir stores
     */
    public File getDir(){
        return this.dir;
    }
}
