package gr9.qlsv.utils;

import gr9.qlsv.entity.Monhoc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectUtils {
    public static List<Monhoc> readMonhocFromFile(String filePath) {
        List<Monhoc> monhoc = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0];
                    String name = parts[1];
                    String teacher = parts[2];
                    String address = parts[3];
                    monhoc.add(new Monhoc(id, name, teacher, address));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return monhoc;
    }

    public static void writeMonhocToFile(String filePath, List<Monhoc> monhoc) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Monhoc subject : monhoc) {
                bw.write(subject.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}