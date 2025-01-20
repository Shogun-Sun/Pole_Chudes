package org.example.pole_chudes.gamePageClasses;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.*;

import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordDefinitionManager {
    private String word;
    private String definition;

    public WordDefinitionManager() {
        try {
//            // Получение случайного слова
//            String randomWordApiUrl = "https://random-word-api.vercel.app/api?words=1";
//            URL randomWordUrl = new URL(randomWordApiUrl);
//            HttpURLConnection randomWordConnection = (HttpURLConnection) randomWordUrl.openConnection();
//            randomWordConnection.setRequestMethod("GET");
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(randomWordConnection.getInputStream()));
//            StringBuilder randomWordResponse = new StringBuilder();
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                randomWordResponse.append(inputLine);
//            }
//            in.close();
//
//            // Парсинг ответа для получения случайного слова
//            String randomWord = randomWordResponse.toString().replace("[", "").replace("]", "").replace("\"", "");
//            System.out.println("Случайное слово: " + randomWord);
//
//            String testString = "apple";
//            String url = String.format("https://dictionary.cambridge.org/ru/словарь/англо-русский/%s", testString);  //randomWord
//
//            // Получаем HTML-документ страницы
//            Document doc = Jsoup.connect(url).get();

//            Element definitionElement = doc.selectFirst(".def.ddef_d.db");
//
//            if (definitionElement != null) {
//                String definition = definitionElement.text();
//                System.out.println("Определение: " + definition);
//            } else {
//                System.out.println("Определение не найдено.");
//            }

//        } catch (Exception e) {
//            e.printStackTrace();
//        }
            String filePath = "src/main/resources/org/example/xdxf/dict.xdxf";
            //Первый вариант
//        try {
//            StringBuilder xmlContent = new StringBuilder();
//            BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                xmlContent.append(line).append("\n");
//            }
//            reader.close();
//
//            Document doc = Jsoup.parse(xmlContent.toString(), "", org.jsoup.parser.Parser.xmlParser());
//
//            Elements entries = doc.select("ar");
//
//            if (!entries.isEmpty()) {
//                List<Element> entryList = new ArrayList<>(entries);
//                Random random = new Random();
//
//                int randomIndex = random.nextInt(entryList.size());
//                Element randomEntry = entryList.get(randomIndex);
//
//                String word = randomEntry.select("k").text();
//                word = word.replaceAll("^\\*", "").trim().toLowerCase();
//
//                String definition = randomEntry.text().replaceFirst(word, "");
//                definition = definition.replaceAll("^[^\\-]*-\\s*", "").trim().toLowerCase();
//
//                System.out.println("Word: " + word);
//                System.out.println("Definition: " + definition);
//            } else {
//                System.out.println("Элементы <ar> не найдены.");
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
            //Второй вариант
            try {
                StringBuilder xmlContent = new StringBuilder();
                BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
                String line;
                while ((line = reader.readLine()) != null) {
                    xmlContent.append(line).append("\n");
                }
                reader.close();

                Document doc = Jsoup.parse(xmlContent.toString(), "", org.jsoup.parser.Parser.xmlParser());

                Elements entries = doc.select("ar");

                List<Element> validEntries = new ArrayList<>();

                for (Element entry : entries) {
                    String potentialWord = entry.select("k").text().trim().toLowerCase();
                    String potentialDefinition = entry.text().trim();

                    if (potentialWord.split("\\s+").length == 1 &&
                        !potentialWord.contains("см. ") &&
                            potentialWord.length() <= 7 &&
                            potentialDefinition.length() <= 300
                    ) {
                            validEntries.add(entry);
                    }
                }
                if (!validEntries.isEmpty()) {
                    Random random = new Random();
                    Element randomEntry = validEntries.get(random.nextInt(validEntries.size()));

                    word = randomEntry.select("k").text().trim().toLowerCase();
                    definition = randomEntry.text()
                            .replaceAll(word, "")
                            .replaceAll(word.toUpperCase(), "")

                            .replaceAll("(?i)" + word.substring(0, word.length() - 1) + "[а-яА-Я]?[а-яА-Я]*", "***")
                            .replaceAll("(?i)" + word + "(?:[а-яА-Я]+)?", "***")

                            .replaceAll("[^\\- ]*-\\s*", "")
//                            .replaceAll("\\b\\d{1,2}[^\\s]*|[^\\s]*\\d{1,2}\\b", "")
                            .replaceAll("\\s*\\([^)]*\\)", "")
                            .replaceAll("\\s*\\((?:(?!\\b\\d{3,4}\\b)[^)]*)\\)", "")
                            .replaceAll("[a-zA-Z]", "")
                            .trim();


                } else {
                    System.out.println("Нету нужных записей");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }
}
