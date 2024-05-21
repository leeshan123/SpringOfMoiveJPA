
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

// =========================================================================================================

{
    //댓글 수정
    //댓글 수 만큼 코멘트 박스 찾기
    let commentBoxes = document.querySelectorAll(".comment-box");
    // 현재 URL의 쿼리 문자열 가져오기
    let params = new URLSearchParams(window.location.search);
    // 특정 파라미터 값 가져오기 (예: 'c' 파라미터 값)
    let categoryEngName = params.get('c');
    let boardId = params.get('id');
    let url = '/api/community-board/comments/';
    commentBoxes.forEach(function(commentBox) {
        //댓글 수 만큼 반복문 돌리는데 그중에 내가 쓴 댓글이라서 수정,삭제 버튼이 존재하는 경우에만 함수 추가,
        //이렇게 안하면 내가 작성하지 않은(수정,삭제 없는) 코멘트박스에서 버튼 돔객체를 찾지 못해 null값이 저장되어서 오류발생
        if (commentBox.querySelector(".del-btn") != null){
            let deleteButton = commentBox.querySelector(".del-btn");
            let editButton = commentBox.querySelector(".edit-btn");
            let myComment = commentBox.querySelector("p");
            let currentText = myComment.textContent;
            let commentId = Number(deleteButton.dataset.commentid);

            deleteButton.onclick = async function (e) {
                const choice = await Swal.fire({
                    title: "정말 삭제하시겠습니까?",
                    text: "삭제한 후에는 복구가 불가능합니다 🥲",
                    // icon: "warning",
                    showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
                    confirmButtonColor: "#d33", //빨간색
                    cancelButtonColor: "#3085d6", //파란색
                    confirmButtonText: "삭제",
                    cancelButtonText: "취소",
                    reverseButtons: false // 버튼 순서 거꾸로
                });

                if (choice.isDismissed) {
                    return;
                }

                const response = await fetch(url + commentId,
                    {
                        method: 'DELETE',
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
                        alert("로그인후 이용할수 있습니다");
                        break;
                    case 1:
                        await Swal.fire({
                            title : "댓글이 삭제되었습니다",
                            // icon  : "success",
                            closeOnClickOutside : true,
                            confirmButtonColor: "#3085d6",
                            });
                        window.location.href = `http://localhost/community/board/detail?c=${categoryEngName}&id=${boardId}`;
                        break;
                    default:
                        alert('예기치못한 오류가 발생했습니다, 잠시후 다시 시도해주세요');
                        break;
                }
            };

            editButton.onclick = async function (e) {

                // 수정버튼 누를시 텍스트 에이리어 돔 객체를 만듦
                let textArea = document.createElement("textarea");
                textArea.classList.add("mx:7","mb:2","n-textbox","my-comment");
                textArea.value = currentText;

                // 내 댓글창을 텍스트에이리어 창으로 바꾼다
                myComment.replaceWith(textArea);

                let cancelButton = document.createElement("button");
                cancelButton.textContent = "취소";

                // Add classes to save button
                cancelButton.classList.add("mr:3", "p:1" ,"bd" ,"bd-radius:3" ,"bg-color:base-1" ,"box-shadow:2");

                // Add save button after edit button
                deleteButton.replaceWith(cancelButton);

                cancelButton.onclick = async function (e) {
                    textArea.replaceWith(myComment);
                    cancelButton.replaceWith(deleteButton);
                }

                // const response = await fetch(url + commentId,
                //     {
                //         method: 'PUT',
                //         // headers: {
                //         //     'Content-Type': 'text/plain'
                //         // },
                //         // body: boardId
                //     });
                //
                // if (!response.ok) {
                //     throw new Error('Network response was not ok');
                // }
                //
                // const resultText = await response.text(); //결과값을 문자열로 받음
                // const result = parseInt(resultText, 10); //int로 형변환, 두번째 매개변수는 변환할 진법/ 10진수,2,8,16...
                //
                // switch (result) {
                //     case 100:
                //         alert("로그인후 이용할수 있습니다");
                //         break;
                //     case 1:
                //
                //         window.location.href = 'http://localhost/community/board/detail?c=review&id=81';
                //         break;
                //     default:
                //         alert('예기치못한 오류가 발생했습니다, 잠시후 다시 시도해주세요');
                //         break;
                // }
            };
        }
    });
}

// =========================================================================================================

{
    //댓글 삭제
}


