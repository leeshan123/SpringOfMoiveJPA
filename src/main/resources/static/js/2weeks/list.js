window.addEventListener('load', function () {
    const openButtons = document.querySelectorAll('.modal-btn');
    const closeButtons = document.querySelectorAll('.close-btn');
    const submitButtons = document.querySelectorAll('.submit-btn');

  //모달 열기버튼
    openButtons.forEach(openButton => {
        openButton.addEventListener('click', function () {
            const modal = this.nextElementSibling;
            const modalBackdrop = modal.nextElementSibling;
            modal.classList.remove('d:none');
            modalBackdrop.classList.remove('d:none');
            modal.classList.add('modal-fade-in');
        });
    });
    //모달 닫기버튼
    closeButtons.forEach(closeButton => {
        closeButton.addEventListener('click', function () {
            const modal = this.closest('.modal');
            const modalBackdrop = modal.nextElementSibling;
            modal.classList.replace('modal-fade-in', 'modal-fade-out');

            setTimeout(() => {
                modal.classList.add('d:none');
                modalBackdrop.classList.add('d:none');
                modal.classList.remove('modal-fade-out');
            }, 130);
        });
    });
    //모달 내부 요청버튼
    submitButtons.forEach(submitButton => {
      submitButton.addEventListener('click', function () {
          const modal = this.closest('.modal');
          const modalBackdrop = modal.nextElementSibling;
          modal.classList.replace('modal-fade-in', 'modal-fade-out');

          setTimeout(() => {
              modal.classList.add('d:none');
              modalBackdrop.classList.add('d:none');
              modal.classList.remove('modal-fade-out');
          }, 130);
      });
  });
});



//버튼입력시 조건처리로 alert실행하기
// const votebutton =document.querySelector('.vote-btn');
// let boardId =  votebutton.dataset.movieid;
// console.log(boardId)
// let url = '/2weeks/list/' + boardId;
// votebutton.onclick = function(){
//     e.preventDefault()
//     async function like() {
//         const response = await fetch(url,
//             {
//                 method: 'POST',
//                 // headers: {
//                 //     'Content-Type': 'text/plain'
//                 // },
//                 // body: boardId
//             });

//         if (!response.ok) {
//             throw new Error('Network response was not ok');
//         }

//         const resultText = await response.text(); //결과값을 문자열로 받음
//         const result = parseInt(resultText, 10); //int로 형변환, 두번째 매개변수는 변환할 진법/ 10진수,2,8,16...

//         switch (result) {
//             case 100:
//                 Swal.fire("로그인후 이용할수 있습니다");
//                 break;
//             case 1:
//                 alert("test")
//                 break;
//             default:
//                 Swal.fire("예기치못한 오류가 발생했습니다, 잠시후 다시 시도해주세요");
//                 break;
//         }
//     }

//     like();

// }