package kr.co.moviespring.web.controller.api;

import kr.co.moviespring.web.config.security.CustomUserDetails;
import kr.co.moviespring.web.entity.PlayGroundBoard;
import kr.co.moviespring.web.repository.PlayGroundRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/playground")
public class PlayGroundService {

    @Autowired
    PlayGroundRepository repository;


    //로그인 상태 체크
    @GetMapping("/checkLogin")
    public boolean checkLogin(@AuthenticationPrincipal CustomUserDetails userDetails) {

        if (userDetails == null)
            return false;
        else
            return true;

    }

    @PostMapping("/betting-possible")
    public ResponseEntity<String> bettingPossible(@RequestBody BettingRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long a = request.getPbgId();
        System.out.println("PBG ID: " + request.getPbgId());
        System.out.println("Betting Amount: " + request.getBettingAmount());

        // 사용자 정보 사용 예시
        System.out.println("User ID: " + userDetails.getId());
        System.out.println("Username: " + userDetails.getUsername());
        System.out.println("UserPoint: " + userDetails.getPoint());

        int bettingPoint = request.getBettingAmount();
        int userPoint = userDetails.getPoint();

        if (bettingPoint <= userPoint && bettingPoint !=0) {
            return ResponseEntity.ok("투자 가능.");
        } else {
            return ResponseEntity.ok("투자할 돈이 없습니다.");
        }


    }

    @PostMapping("/betting")
    public ResponseEntity<String> betting(@RequestBody BettingRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {

        int bettingPoint = request.getBettingAmount();
        int userPoint = userDetails.getPoint();
        int userChoice = request.getSelectedBettingValue();

        System.out.println(request.getPbgId());


        PlayGroundBoard playGroundBoard = repository.findById(request.getPbgId());

        int leftBettingPoint = playGroundBoard.getLeftBettingPoint();
        int rightBettingPoint = playGroundBoard.getRightBettingPoint();
//        double leftDividend = playGroundBoard.getLeftDividend();
//        double rightDividend = playGroundBoard.getRightDiviend();





        if (bettingPoint <= userPoint && bettingPoint !=0) {

            if(userChoice == 0){
                System.out.println("왼쪽이 실행");

                leftBettingPoint += bettingPoint;
                double leftDividend = (double)leftBettingPoint/(leftBettingPoint+rightBettingPoint);
                leftDividend = leftDividend * 0.038;
                System.out.println("leftDividend: "+ leftDividend);

                double rightDividend = (double)rightBettingPoint/(leftBettingPoint+rightBettingPoint);
                rightDividend = rightDividend * 0.038;
                System.out.println("rightDividend: "+ rightDividend);



            } else {
                System.out.println("오른쪽이 실행");

                rightBettingPoint += bettingPoint;
                double leftDividend = (double)leftBettingPoint/(leftBettingPoint+rightBettingPoint);
                leftDividend = leftDividend * 0.038;
                System.out.println("leftDividend: "+ leftDividend);

                double rightDividend = (double)rightBettingPoint/(leftBettingPoint+rightBettingPoint);
                rightDividend = rightDividend * 0.038;
                System.out.println("rightDividend: "+ rightDividend);

            }







            return ResponseEntity.ok("투자 성공.");
        } else {
            return ResponseEntity.ok("투자할 돈이 없습니다.");
        }


    }


    @NoArgsConstructor
    public static class BettingRequest {
        private Long pbgId; // pbgId 필드
        private int bettingAmount; // bettingAmount 필드

        private int selectedBettingValue;

        // Getter와 Setter 메서드
        public Long getPbgId() {
            return pbgId;
        }

        public void setPbgId(Long pbgId) {
            this.pbgId = pbgId;
        }

        public int getSelectedBettingValue() {
            return selectedBettingValue;
        }

        public void setSelectedBettingValue(int selectedBettingValue) {
            this.selectedBettingValue = selectedBettingValue;
        }

        public int getBettingAmount() {
            return bettingAmount;
        }

        public void setBettingAmount(int bettingAmount) {
            this.bettingAmount = bettingAmount;
        }
    }

}

