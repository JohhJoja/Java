import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FileChooserFrame extends JFrame {
    private JTextField filePathField;
    private JRadioButton mode1, mode2;
    private ButtonGroup modeGroup;
    private File selectedFile;
    int counter = 0;

    public FileChooserFrame() {
        setTitle("Выбор файла и режима");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setVisible(true);
        setResizable(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        /// Кнопка выбора файла
        JButton selectFileButton = new JButton("Выбрать файл");
        filePathField = new JTextField();
        filePathField.setEditable(false);
        filePathField.setPreferredSize(new Dimension(200, 25));

        selectFileButton.addActionListener(e -> chooseFile());

        gbc.gridx = gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(selectFileButton, gbc);

        gbc.gridy = 1;
        add(filePathField, gbc);

        // Радиокнопки выбора режима
        mode1 = new JRadioButton("Добавить", true);
        mode2 = new JRadioButton("Перезаписать");
        modeGroup = new ButtonGroup();
        modeGroup.add(mode1);
        modeGroup.add(mode2);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(mode1, gbc);

        gbc.gridx = 1;
        add(mode2, gbc);

        // Кнопка подтверждения
        JButton confirmButton = new JButton("Подтверждение");
        confirmButton.addActionListener(e -> {
            try {
                confirmSelection();
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(confirmButton, gbc);

        ///  Решение проблемы отображения всех элементов
        SwingUtilities.invokeLater(this::revalidate);
        SwingUtilities.invokeLater(this::repaint);
    }

    private void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            filePathField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void confirmSelection() throws IOException, ClassNotFoundException {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "Файл не выбран!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String mode = mode1.isSelected() ? "Режим 1" : "Режим 2";
        boolean wMODE = mode1.isSelected();
        JOptionPane.showMessageDialog(this, "Выбран файл: " + selectedFile.getName() + "\nРежим: " + mode, "Информация", JOptionPane.INFORMATION_MESSAGE);

        new NameQualifare(selectedFile.getAbsolutePath(), wMODE);

        counter = NameQualifare.WordCounter;
        System.out.println(counter);
        filePathField.setText("Добавлено "+counter+" новых слов");
    }
}
