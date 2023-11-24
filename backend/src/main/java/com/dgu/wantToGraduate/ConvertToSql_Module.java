package com.dgu.wantToGraduate;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class ConvertToSql_Module {

    public static void main(String[] args) {
        String filePath = "C://Project_files//2023-2-OSSP1-WantToGraduateTeam-8//backend//src//main//java//com//dgu//wantToGraduate/매장크롤링DATA.txt"; // 파일 경로 수정
        List<String> insertQueries = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath), Charset.forName("UTF-8"));

            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    categorySet(parts[1]);// 카테고리 분류 집합(확인용)


                    String storeName = parts[0].trim();
                    String sql = "CALL InsertBrand('" + storeName + "', '" + enumNameof(parts[1]) + "');";
                    insertQueries.add(sql);
                }
            }

            String finalSql = insertQueries.stream().collect(Collectors.joining("\n"));
            System.out.println(finalSql);
            System.out.println("카테고리명 집합: " + set);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 간단한 키워드 기반 카테고리 분류
    static HashSet<String> set = new HashSet<>();
    private static HashSet<String> categorySet(String data) {
        set.add(data);
        return set;
    }

    static String enumNameof(String categoryName){
        switch(categoryName){
            case "커피점/카페": return "CAFE";
            case "양식": return "WESTERN";
            case "중식": return "CHINESE";
            case "제과제빵떡케익": return "BAKERY";
            case "닭/오리요리": return "CHICKEN";
            case "일식/수산물": return "JAPANESE";
            case "한식": return "KOREAN";
            case "별식/퓨전요리": return "FUSION";
            case "패스트푸드": return "FASTFOOD";
            case "분식": return "SNACK";
            case "유흥주점": return "PUB";
            default: return new Exception("잘못된 카테고리명입니다.").getMessage();
        }
    }
}