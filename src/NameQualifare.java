import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class NameQualifare {

    /// Глобальные переменные для чтения, извлечения отдельных имен и создания списка имен
    FileReader reader;
    String NAME = "";
    ArrayList<String> LIST = new ArrayList<>();

    boolean MODE = true;

    static int WordCounter = 0;

    /// Конструктор класса: инициализация ридера, выполнение логики чтения, выделения и записи отдельных имен
    public NameQualifare(String SETTINGFILE, boolean MODE) throws IOException, ClassNotFoundException {

        /// File reader logic
        reader = new FileReader(SETTINGFILE);
        int data = reader.read();

        while (data!=-1){
            char i = (char)data;
            if (data!=10 && data!=13) NAME += i;
            if (data==13){
                LIST.add(NAME);
                NAME="";
            }
            data = reader.read();
        }
        reader.close();

        /// Сортировка имен по первым двум буквам и выполнение функции Write
        for (String x : LIST){
            String first_lay = String.valueOf(x.charAt(0));
            String second_lay = String.valueOf(x.charAt(1));
            Write("src\\"+first_lay, second_lay, x);
        }
    }

    /// Основная логика записи в файл. Принимает путь к директории и имя файла, а также имя, которое нужно добавить
    void Write(String filePath, String fileName, String name) throws IOException, ClassNotFoundException {
        Path DirPath = Paths.get(filePath);
        Path FilePath = Paths.get(filePath+"\\"+fileName+".dat");

    /// Создание файлов и директорий в случае, если таковых нет + учет выбранного Мода
        if (!MODE){
            if (!Files.exists(DirPath)) Files.createDirectories(DirPath);
            Files.createFile(FilePath);
        } else {
            if (!Files.exists(DirPath)) Files.createDirectories(DirPath);
            if (!Files.exists(FilePath)) Files.createFile(FilePath);
        }

    /// Запись в файл
        ArrayList<String> words = new ArrayList<>();

        File file = new File(String.valueOf(FilePath));
        if (file.exists()){
            try (ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(file))){
                words = (ArrayList<String>) OIS.readObject();
            } catch (Exception ignored){}
        }
        WordCounter++;
        words.add(name);

        try (ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(file))){OOS.writeObject(words);
        } catch (IOException e){
            e.printStackTrace();
        }

      //  READ(String.valueOf(FilePath));
    }

/// Чтение из файла
    void READ(String filepath){
        try (ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(filepath))) {
            ArrayList<String> words = (ArrayList<String>) OIS.readObject();
            for (String word : words) {
              System.out.println(word);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
