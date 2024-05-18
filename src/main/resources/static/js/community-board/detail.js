// 버튼 요소 가져오기
let voteBox = document.querySelector("#vote-box");
let likeButtonBox = voteBox.querySelector(".like-box");
let disLikeButtonBox = voteBox.querySelector(".dislike-box");

let likeCount = likeButtonBox.querySelector(".like-box > span:first-child");
let disLikeCount = disLikeButtonBox.querySelector(".dislike-box > span:first-child");
// let data = {};
let boardId = Number(voteBox.dataset.boardid);
let url = '/api/community-board-likes/'+boardId;

voteBox.onclick = function(e) {
    e.preventDefault();

    if (likeButtonBox.classList.contains('bd-color:accent-1')) {
        alert("이미 '좋아요' 하신 게시글 입니다.");
        return;
    } else if (disLikeButtonBox.classList.contains('bd-color:sub-1')) {
        alert("이미 '싫어요' 하신 게시글 입니다.");
        return;
    }
    // 좋아요 클릭
    if (likeButtonBox.contains(e.target)) {

        async function like() {
            const response = await fetch(url+'/like',
                {
                    method: 'POST',
                    // headers: {
                    //     'Content-Type': 'text/plain'
                    // },
                    // body: boardId
                });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            const resultText = await response.text(); //결과값을 문자열로 받음
            const result = parseInt(resultText, 10); //int로 형변환, 두번째 매개변수는 변환할 진법/ 10진수,2,8,16...

            switch (result) {
                case 100:
                    alert("로그인후 이용할수 있습니다")
                    break;
                case 1:
                    likeButtonBox.classList.add('bd-color:accent-1');
                    // 현재 값 가져오기
                    let currentLikeCount = Number(likeCount.innerText);
                    // 값 증가
                    let addLikeCount = currentLikeCount + 1;
                    // 버튼 텍스트 업데이트
                    likeCount.innerText = addLikeCount;
                    break;
                default:
                    alert('예기치못한 오류가 발생했습니다, 잠시후 다시 시도해주세요');
                    break;
            }
        }

        like();
    }

    // 싫어요 클릭
    if (disLikeButtonBox.contains(e.target)) {
        // e.target.closest(".dislike-box") 이렇게도 쓸수있음

        async function disLike() {
            const response = await fetch(url+'/dislike',
                {
                    method: 'POST',
                    // headers: {
                    //     'Content-Type': 'text/plain'
                    // },
                    // body: boardId
                });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            const resultText = await response.text(); //결과값을 문자열로 받음
            const result = parseInt(resultText, 10); //int로 형변환, 두번째 매개변수는 변환할 진법/ 10진수,2,8,16...

            switch (result) {
                case 100:
                    alert("로그인후 이용할수 있습니다")
                    break;
                case -1:
                    disLikeButtonBox.classList.add('bd-color:sub-1');
                    // 현재 값 가져오기
                    let currentDisLikeCount = Number(disLikeCount.innerText);
                    // 값 증가
                    let addDisLikeCount = currentDisLikeCount + 1;
                    // 버튼 텍스트 업데이트
                    disLikeCount.innerText = addDisLikeCount;
                    break;
                default:
                    alert('예기치못한 오류가 발생했습니다, 잠시후 다시 시도해주세요');
                    break;
            }
        }

        disLike();

    }
};


// // 클릭 이벤트 핸들러 등록
// likeButtonBox.addEventListener("click", function(e) {
//
//     e.preventDefault();
//
//     // 현재 값 가져오기
//     let currentLikeCount = Number(likeCount.innerText);
//     console.log("현재 좋아요 수",currentLikeCount);
//     // 값 증가
//     let addLikeCount = currentLikeCount + 1;
//     console.log(addLikeCount);
//
//     // 버튼 텍스트 업데이트
//     likeCount.innerText = addLikeCount;
//     // like = "Click to Increment: " + newValue;
// });
//
// // 클릭 이벤트 핸들러 등록
// disLikeButtonBox.addEventListener("click", function(e) {
//     e.preventDefault();
//     // 현재 값 가져오기
//     let currentDisLikeCount = Number(disLikeCount.innerText);
//     console.log("현재 싫어요 수",currentDisLikeCount);
//     // 값 증가
//     let addDisLikeCount = currentDisLikeCount + 1;
//     console.log(addDisLikeCount);
//
//     // 버튼 텍스트 업데이트
//     disLikeCount.innerText = addDisLikeCount;
//     // like = "Click to Increment: " + newValue;
// });