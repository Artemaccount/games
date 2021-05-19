import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws Exception {

        //Дата для логов файла temp.txt
        Date currentDate = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyy hh:mm:ss a");

        stringBuilder.append(formatDate.format(currentDate) + "\n");

        //В папке Games создайте несколько директорий: src, res, savegames, temp.
        File src = new File("C:\\Games\\src");
        File res = new File("C:\\Games\\res");
        File savegames = new File("C:\\Games\\savegames");
        File temp = new File("C:\\Games\\temp");

        //В каталоге src создайте две директории: main, test.
        File main = new File("C:\\Games\\src\\main");
        File test = new File("C:\\Games\\src\\test");

        //В подкаталоге main создайте два файла: Main.java, Utils.java.
        File mainJava = new File("C:\\Games\\src\\main\\Main.java");
        File utilsJava = new File("C:\\Games\\src\\main\\Utils.java");

        //В каталог res создайте три директории: drawables, vectors, icons.
        File drawables = new File("C:\\Games\\res\\drawables");
        File vectors = new File("C:\\Games\\res\\vectors");
        File icons = new File("C:\\Games\\res\\icons");

        //В директории temp создайте файл temp.txt.
        File tempFile = new File("C:\\Games\\temp\\temp.txt");
        try {
            temp.mkdir();
            tempFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Ошибка создания директория или файла temp:");
            System.out.println(e.getMessage());
        }

        //Волшебство создания директорий и файлов с записью в стрингбилдер :)
        try (FileWriter writer = new FileWriter(tempFile, true)) {
            makeDir(src);
            makeDir(res);
            makeDir(savegames);
            makeDir(main);
            makeDir(test);
            makeDir(drawables);
            makeDir(vectors);
            makeDir(icons);
            createFile(mainJava);
            createFile(utilsJava);
            writer.append(stringBuilder.toString());
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        GameProgress user1 = new GameProgress(100, 2, 10, 244.32);
        GameProgress user2 = new GameProgress(87, 10, 4, 255.51);
        GameProgress user3 = new GameProgress(84, 4, 24, 335.31);

        saveGame("C:\\Games\\savegames\\save1.dat", user1);
        saveGame("C:\\Games\\savegames\\save2.dat", user2);
        saveGame("C:\\Games\\savegames\\save3.dat", user3);

        List<String> files = Arrays.asList("C:\\Games\\savegames\\save1.dat",
                "C:\\Games\\savegames\\save2.dat",
                "C:\\Games\\savegames\\save3.dat");

        zipFiles("C:\\Games\\savegames\\saves.zip", files);
        deleteFile("C:\\Games\\savegames\\save1.dat");
        deleteFile("C:\\Games\\savegames\\save2.dat");
        deleteFile("C:\\Games\\savegames\\save3.dat");

    }

    public static void saveGame(String address, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(address);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteFile(String address) {
        File save = new File(address);
        save.delete();
    }

    public static void makeDir(File file) {
        if (file.mkdir()) {
            stringBuilder.append("Каталог " + file.getName() + " успешно создан!");
        } else {
            stringBuilder.append("Каталог " + file.getName() + " не создан!");
        }
        stringBuilder.append('\n');
    }

    public static void createFile(File file) throws Exception {
        if (file.createNewFile()) {
            stringBuilder.append("Файл " + file.getName() + " успешно создан!");
        } else {
            stringBuilder.append("Файл " + file.getName() + " не создан!");
        }
        stringBuilder.append('\n');
    }

    public static void zipFiles(String address, List<String> files) throws Exception {
        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(address));
        for (int i = 0; i < files.size(); i++) {
            FileInputStream fis = new FileInputStream(files.get(i));
            ZipEntry entry = new ZipEntry("Save" + i + ".dat");
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
            fis.close();
        }
        zout.close();
    }
}