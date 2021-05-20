import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Load {
    public static void main(String[] args) {
        openZip("C:\\Games\\savegames\\saves.zip", "C:\\Games\\savegames\\");
        openProgress("C:\\Games\\savegames\\save0.dat");
        openProgress("C:\\Games\\savegames\\save1.dat");
        openProgress("C:\\Games\\savegames\\save2.dat");
    }

    public static void openZip(String addressIn, String addressOut) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(addressIn))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(addressOut + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void openProgress(String address) {
        try (FileInputStream fos = new FileInputStream(address);
                 ObjectInputStream oos = new ObjectInputStream(fos)) {
            GameProgress gameProgress = (GameProgress) oos.readObject();
            System.out.println(gameProgress);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
