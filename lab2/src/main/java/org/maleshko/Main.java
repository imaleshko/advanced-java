package org.maleshko;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть шлях до директорії:");
        Path dir = Path.of(scanner.nextLine());

        if (!Files.isDirectory(dir)) {
            System.out.println("Помилка! Папка не існує");
            return;
        }

        System.out.println("Введіть розширення через кому:");
        String inputExtensions = scanner.nextLine();
        String[] inputExtensionsList = inputExtensions.split(",");

        List<String> targetExtensions = new ArrayList<>();

        ExtensionFormatter formatter = inputExtension -> {
            inputExtension = inputExtension.trim();
            targetExtensions.add(inputExtension.startsWith(".") ? inputExtension : "." + inputExtension);
        };
        for (String inputExtension : inputExtensionsList) {
            formatter.formatAndAdd(inputExtension);
        }

        ExtensionExtractor extractor = path -> {
            String fileName = path.getFileName().toString();
            int dotIndex = fileName.lastIndexOf('.');
            return dotIndex == -1 ? "" : fileName.substring(dotIndex);
        };

        FileFilter filter = path -> targetExtensions.contains(extractor.extract(path));

        HashMap<String, Integer> statistic = new HashMap<>();

        try (Stream<Path> paths = Files.walk(dir)) {
            paths.forEach(path -> {
                if (Files.isRegularFile(path) && filter.match(path)) {
                    System.out.println(path.getFileName());
                    statistic.put(extractor.extract(path), statistic.getOrDefault(extractor.extract(path), 0) + 1);
                }
            });
        } catch (Exception _) {
            System.out.println("Помилка! Неможливо прочитати вміст папки!");
        }

        System.out.println("\nСтатистика:");
        statistic.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
