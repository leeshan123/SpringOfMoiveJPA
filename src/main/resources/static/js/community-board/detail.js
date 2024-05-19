{
// 게시글 버튼 요소 가져오기
    let voteBox = document.querySelector("#vote-box");
    let likeButtonBox = voteBox.querySelector(".like-box");
    let disLikeButtonBox = voteBox.querySelector(".dislike-box");

    let likeCount = likeButtonBox.querySelector(".like-box > span:first-child");
    let disLikeCount = disLikeButtonBox.querySelector(".dislike-box > span:first-child");
// let data = {};
//     let boardId = Number(voteBox.dataset.boardid);
    let boardId = Number(voteBox.dataset.boardid);
    let url = '/api/community-board-likes/board/' + boardId;

    voteBox.onclick = function (e) {
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
                const response = await fetch(url + '/like',
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
                const response = await fetch(url + '/dislike',
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
}

// =========================================================================================================

{
// 댓글 버튼 요소 가져오기
    //반복문으로 생성된 여러개를 전체 선택 / 배열에 담김
    let commentVoteBoxes = document.querySelectorAll(".comment-vote-box");
    //그 배열을 하나씩 순회하면서 값을 사용함
    commentVoteBoxes.forEach(function(commentVoteBox) {
        let likeButtonBox = commentVoteBox.querySelector(".icon\\:thumbs_up");
        let disLikeButtonBox = commentVoteBox.querySelector(".icon\\:thumbs_down");

        let likeCount = likeButtonBox;
        let disLikeCount = disLikeButtonBox;
        let commentId = Number(commentVoteBox.dataset.commentid);
        let url = '/api/community-board-likes/comment/' + commentId;

        commentVoteBox.onclick = function (e) {
            e.preventDefault();

            if (likeButtonBox.classList.contains('icon-color:accent-1')) {
                alert("이미 '좋아요' 하신 댓글 입니다.");
                return;
            } else if (disLikeButtonBox.classList.contains('icon-color:sub-1')) {
                alert("이미 '싫어요' 하신 댓글 입니다.");
                return;
            }
            // 좋아요 클릭
            if (likeButtonBox.contains(e.target)) {

                async function like() {
                    const response = await fetch(url + '/like',
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
                            likeButtonBox.classList.add('icon-color:accent-1');
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
                    const response = await fetch(url + '/dislike',
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
                            disLikeButtonBox.classList.add('icon-color:sub-1');
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
        }
    });
}