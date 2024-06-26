package kr.co.moviespring.web.controller.api;

import kr.co.moviespring.web.config.security.CustomUserDetails;
import kr.co.moviespring.web.entity.Betting;
import kr.co.moviespring.web.entity.Member;
import kr.co.moviespring.web.entity.PlayGroundBoard;
import kr.co.moviespring.web.repository.BettingRepository;
import kr.co.moviespring.web.repository.MemberRepository;
import kr.co.moviespring.web.repository.PlayGroundRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RestController("ApiPlayGroundController")
@RequestMapping("api/playground")
public class PlayGroundController {

    @Autowired
    PlayGroundRepository pgRepository;

    @Autowired
    MemberRepository mbRepository;

    @Autowired
    BettingRepository btRepository;


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

    //베팅 로직
    @PostMapping("/betting")
    public ResponseEntity<String> betting(@RequestBody BettingRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {

        int bettingPoint = request.getBettingAmount();
        int userChoice = request.getSelectedBettingValue();

        System.out.println(request.getPbgId());


        PlayGroundBoard playGroundBoard = pgRepository.findById(request.getPbgId());
        Member member = mbRepository.findByMembername(userDetails.getUsername());
        int userPoint = member.getPoint();

        int leftBettingPoint = playGroundBoard.getLeftBettingPoint();
        int rightBettingPoint = playGroundBoard.getRightBettingPoint();
        double leftDividend = playGroundBoard.getLeftDividend();
        double rightDividend = playGroundBoard.getRightDividend();

        System.out.println(leftDividend);

        System.out.println(rightDividend);





        if (bettingPoint <= userPoint && bettingPoint !=0) {

            if(userChoice == 0){
                System.out.println("왼쪽에 베팅");

                //베팅 테이블에 데이터 만들어주기
                Betting betting = Betting.builder()
                        .bettingPoint(bettingPoint)
                        .successPoint((int)(bettingPoint*leftDividend))
                        .memberId(userDetails.getId())
                        .bettingBoardId(request.getPbgId())
                        .choose(0)
                        .build();



                leftBettingPoint += bettingPoint;
                rightDividend = (double)leftBettingPoint/(leftBettingPoint+rightBettingPoint);
                rightDividend =  roundToThree(rightDividend * 0.038) * 100;
                System.out.println("rightDividend: "+ rightDividend);

                leftDividend = (double)rightBettingPoint/(leftBettingPoint+rightBettingPoint);
                leftDividend =  roundToThree(leftDividend * 0.038) * 100;
                System.out.println("leftDividend: "+ leftDividend);

                //베팅 포인트와 배당률 변경점 업데이트해주기
                playGroundBoard.setLeftBettingPoint(leftBettingPoint);
                playGroundBoard.setRightBettingPoint(rightBettingPoint);
                playGroundBoard.setLeftDividend(leftDividend);
                playGroundBoard.setRightDividend(rightDividend);

                //유저 포인트 변경점 업데이트해주기
                userPoint = userPoint - bettingPoint;
                member.setPoint(userPoint);
                userDetails.setPoint(userPoint);



                System.out.println(userPoint);



                //DB에 변경점 저장
                pgRepository.update(playGroundBoard);
                mbRepository.updatePoint(member);
                pgRepository.saveBetting(betting);











            } else {
                System.out.println("오른쪽에 베팅");

                //베팅 테이블에 데이터 만들어주기
                Betting betting = Betting.builder()
                        .bettingPoint(bettingPoint)
                        .successPoint((int)(bettingPoint*rightDividend))
                        .memberId(userDetails.getId())
                        .bettingBoardId(request.getPbgId())
                        .choose(1)
                        .build();

                rightBettingPoint += bettingPoint;
                rightDividend = (double)leftBettingPoint/(leftBettingPoint+rightBettingPoint);
                rightDividend =  roundToThree(rightDividend * 0.038)* 100;

                System.out.println("rightDividend: "+ rightDividend);

                leftDividend = (double)rightBettingPoint/(leftBettingPoint+rightBettingPoint);
                leftDividend =  roundToThree(leftDividend * 0.038)* 100;
                System.out.println("leftDividend: "+ leftDividend);


                //베팅 포인트와 배당률 변경점 업데이트해주기
                playGroundBoard.setLeftBettingPoint(leftBettingPoint);
                playGroundBoard.setRightBettingPoint(rightBettingPoint);
                playGroundBoard.setLeftDividend(leftDividend);
                playGroundBoard.setRightDividend(rightDividend);

                //유저 포인트 변경점 업데이트해주기
                userPoint = userPoint - bettingPoint;
                userDetails.setPoint(userPoint);
                member.setPoint(userPoint);

                System.out.println(userPoint);

                //DB에 변경점 저장
                pgRepository.update(playGroundBoard);
                mbRepository.updatePoint(member);
                pgRepository.saveBetting(betting);

            }



            return ResponseEntity.ok("투자 성공.");
        } else {
            return ResponseEntity.ok("투자할 돈이 없습니다.");
        }


    }

    @PostMapping("/findboardbyid")
    public PlayGroundBoard findboardbyid(@RequestBody Long pgbId) {

        return pgRepository.findById(pgbId);

    }


    @PostMapping("/givepoint")
    public void givePoint(@RequestBody BettingRequest request){

        Long boardId = request.getPbgId();
        int choose = request.getSelectedBettingValue();

        System.out.println("id: "+ request.getPbgId());
        System.out.println("selectedBettingValue: "+ request.getSelectedBettingValue());

        List<Betting> SuccessUsers = btRepository.finaAllSuccessUser(boardId, choose);

        for(Betting bettingUser : SuccessUsers){
            System.out.println(bettingUser.getSuccessPoint());


            btRepository.givePointUser(bettingUser);
            btRepository.changePaymentStatus(bettingUser);

        }

        System.out.println("베팅 지급 완료!");

//        btrRepository.getPoint(boardId, choose);
//        btrRepository.deletegetPoint(boardId, choose);


    }


    public static double roundToThree(double num) {
        BigDecimal bd = new BigDecimal(Double.toString(num));
        bd = bd.setScale(3, RoundingMode.HALF_UP);
        return bd.doubleValue();
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

