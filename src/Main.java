import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        //Дата для логов файла temp.txt
        Date currentDate = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyy hh:mm:ss a");

        StringBuilder stringBuilder = new StringBuilder();
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
        } catch(IOException e) {
            System.out.println("Ошибка создания директория или файла temp:");
            System.out.println(e.getMessage());
        }

        //Волшебство создания директорий и файлов с записью в стрингбилдер :)
        try (FileWriter writer = new FileWriter(tempFile, true)) {
            if (src.mkdir()) {
                stringBuilder.append("Каталог src успешно создан!");
            } else {
                stringBuilder.append("Каталог src не создан!");
            }
            stringBuilder.append('\n');

            if (res.mkdir()) {
                stringBuilder.append("Каталог res успешно создан!");
            } else {
                stringBuilder.append("Каталог res не создан!");
            }
            stringBuilder.append('\n');

            if (savegames.mkdir()) {
                stringBuilder.append("Каталог savegames успешно создан!");
            } else {
                stringBuilder.append("Каталог savegames не создан!");
            }
            stringBuilder.append('\n');

            if (main.mkdir()) {
                stringBuilder.append("Каталог main успешно создан!");
            } else {
                stringBuilder.append("Каталог main не создан!");
            }
            stringBuilder.append('\n');

            if (test.mkdir()) {
                stringBuilder.append("Каталог test успешно создан!");
            } else {
                stringBuilder.append("Каталог test не создан!");
            }
            stringBuilder.append('\n');

            if (mainJava.createNewFile()) {
                stringBuilder.append("Файл Main.java успешно создан!");
            } else {
                stringBuilder.append("Файл Main.java не создан!");
            }
            stringBuilder.append('\n');

            if (utilsJava.createNewFile()) {
                stringBuilder.append("Файл Test.java успешно создан!");
            } else {
                stringBuilder.append("Файл Test.java не создан!");
            }
            stringBuilder.append('\n');

            if (drawables.mkdir()) {
                stringBuilder.append("Каталог drawables успешно создан!");
            } else {
                stringBuilder.append("Каталог drawables не создан!");
            }
            stringBuilder.append('\n');

            if (vectors.mkdir()) {
                stringBuilder.append("Каталог vectors успешно создан!");
            } else {
                stringBuilder.append("Каталог vectors не создан!");
            }
            stringBuilder.append('\n');

            if (icons.mkdir()) {
                stringBuilder.append("Каталог icons успешно создан!");
            } else {
                stringBuilder.append("Каталог icons не создан!");
            }
            stringBuilder.append('\n');
            writer.append(stringBuilder.toString());
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
