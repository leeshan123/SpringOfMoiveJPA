package kr.co.moviespring.web.movieapi;


import kr.co.moviespring.web.entity.Movie2;
import kr.co.moviespring.web.repository.MovieInsertRepository;
import kr.co.moviespring.web.service.MovieInsertService;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//@Component
//누적관객수를 넣기 위해서 엑셀을 읽어오는 자바파일(AOI이용 앵간하면 사용 안할듯)
public class AudiAccInsert {

//    @Autowired
    MovieInsertService movieInsertService;

    public void processExcelData() {

        List<Movie2> movie2List = new ArrayList<>();

        // 엑셀 파일 경로
        try (FileInputStream file = new FileInputStream("C:/Users/leedw/Desktop/audience.xlsx")) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            //몇번째 시트부터 시작할거냐
            XSSFSheet sheet = workbook.getSheetAt(0);

            // 3행부터 시작하곘다
            int startRow = 2;

            //Movie2 리스트 생성


            // 마지막행까지 for문 돌리기
            for (int rowNum = startRow; rowNum < sheet.getLastRowNum() + 1; rowNum++) {
                // 시트의 행 가져오기
                XSSFRow row = sheet.getRow(rowNum);

                // 영화명
                // 영화명이 String이면 스트링으로 받고 NUMBERIC이면(int)로 강제 형변환하고 String으로 바꿔줌.
                String movieTitle = "";
                CellType cellType = row.getCell(0).getCellType();
                if(cellType == CellType.STRING)
                    movieTitle = row.getCell(0).getStringCellValue();
                else if (cellType == CellType.NUMERIC)
                    movieTitle = String.valueOf((int)row.getCell(0).getNumericCellValue());
                else
                    System.out.println("이거 나오면 걍 망했다고 생각하자.");

                // 개봉일
                // 개봉일을 String으로 해놨으니까 Date 형식을 string으로 넣어줌.
                Date releaseDate = row.getCell(1).getDateCellValue();
                String stringDate = "";
                if(releaseDate != null) {
                    String dateFormatPattern = "yyyyMMdd";
                    SimpleDateFormat formatter = new SimpleDateFormat(dateFormatPattern);
                    stringDate = formatter.format(releaseDate);
                } else {
                    stringDate = null;
                }

                // 전국 관객수
                //double로 주기 때문에 int로 형변환함.
                int audienceCount = (int)row.getCell(2).getNumericCellValue();

                Movie2 movie2 = new Movie2();
                movie2.setMovieNm(movieTitle);
                movie2.setOpenDt(stringDate);
                movie2.setAudiAcc(audienceCount);
                movie2List.add(movie2);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        System.out.println(movie2List.size()+"시작");

        movieInsertService.AduienceAccInsert(movie2List);

        System.out.println("끝");


    }

}
