window.addEventListener('load', function () {
    //베팅하기 누루는 버튼
    const openButtons = document.querySelectorAll('.betting-btn');

    //베팅 모달
    const closeBettingButton = document.getElementById('close-btn');
    const bettingModal = document.getElementById('betting-modal');
    const modalBackdrop = document.getElementById('modal-backdrop');
    const submitButton = document.getElementById('submit-btn');
    const bettingInput = document.getElementById('betting-input');

    //DIV위에 데이터를 넣기 위해 사용
    const btnDiv = document.getElementById('btn-div');

    //베팅이 한번 눌렸을때 가능한지 여부를 알아보기 위한 것.
    let isBettingPossible = false;


    //로그인 모달
    const loginModal = document.getElementById('login-modal');
    const loginButton = document.getElementById('login-btn');
    const closeLoginButton = document.getElementById('close-login-btn');


    //API URL
    const url = '/api/playground/';


    //베팅 버튼을 눌렀을때
    openButtons.forEach(button => {
        button.addEventListener('click', function () {
            // 서버에 로그인 상태를 요청
            fetch(url+'checkLogin')
                .then(response => response.json())
                .then(isLoggedIn=>{
                    if(isLoggedIn){
                        //로그인 상태일 때 모달을 열기
                        console.log("betting-btn");
                        bettingModal.classList.remove('d:none');
                        modalBackdrop.classList.remove('d:none');
                        bettingModal.classList.add('modal-fade-in');

                        //데이터의 id를 가져오기 위한 작업
                        const pbgId = button.getAttribute('data-pbg-id');
                        submitButton.setAttribute('data-pbg-id', pbgId);
                        console.log(pbgId);

                    } else {
                        loginModal.classList.remove('d:none');
                        modalBackdrop.classList.remove('d:none');
                        loginModal.classList.add('modal-fade-in');
                    }
                })
                .catch(error =>{
                   console.error("Error", error);
                   alert('로그인 상태를 확인할 수 없습니다. 다시 시도해주세요.');
                });


        });
    });

    //베팅 버튼에서 뒤로 가기를 눌렀을때
    closeBettingButton.addEventListener('click', function () {
        bettingModal.classList.replace('modal-fade-in', 'modal-fade-out');

        setTimeout(() => {
            bettingModal.classList.add('d:none');
            modalBackdrop.classList.add('d:none');
            bettingModal.classList.remove('modal-fade-out');
        }, 130);
    });

    //로그인 버튼에서 뒤로 가기를 눌렀을때
    closeLoginButton.addEventListener('click', function () {
        loginModal.classList.replace('modal-fade-in', 'modal-fade-out');

        setTimeout(() => {
            loginModal.classList.add('d:none');
            modalBackdrop.classList.add('d:none');
            loginModal.classList.remove('modal-fade-out');
        }, 130);
    });

    //로그인 버튼을 눌렀을때 로그인 페이지로 이동
    loginButton.addEventListener('click',function (){
        location.href = '/user/signin';
    });

    // 제출 버튼을 눌렀을 때 그 게시판의 id와 input value 값을 가져옴
    submitButton.addEventListener('click', function (e) {
        e.preventDefault();

        const pbgId = submitButton.getAttribute('data-pbg-id');
        console.log("PBG ID on submit:", pbgId);
        console.log("Betting amount:", bettingInput.value);

        if (!isBettingPossible) {
            const data = {
                pbgId: pbgId,
                bettingAmount: parseInt(bettingInput.value)
            };

            // AJAX로 POST 요청 해보기
            fetch(url + 'betting-possible', {
                // POST 요청
                method: 'POST',
                // JSON 형식으로 데이터 보내기
                headers: {
                    'Content-Type': 'application/json'
                },
                // 데이터를 JSON 문자열로 변화하여 보냄.
                body: JSON.stringify(data)
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                })
                .then(data => {
                    // 서버로부터 받은 응답 처리
                    console.log("Response from server:", data);
                    // alert(data); // 서버에서 온 메시지 출력
                    if (data === "베팅 가능합니다.") {
                        bettingInput.classList.remove('n-textbox-status:warning');
                        bettingInput.classList.add('n-textbox-status:success');

                        //베팅 안되는거 문구 없애기
                        const bettingImpossible = document.querySelector('.betting-impossible-msg');
                        if (bettingImpossible) {
                            bettingImpossible.remove();
                        }

                        // 배팅 가능 문구 추가
                        if (!document.querySelector('.betting-possible-msg')) {
                            let sectionHTML = '<p class="betting-possible-msg ">배팅이 가능합니다. 베팅 하시려면 한번 더 눌러주세요!</p>';
                            btnDiv.insertAdjacentHTML("beforebegin", sectionHTML);
                        }
                        isBettingPossible = true;

                    } else {
                        bettingInput.classList.remove('n-textbox-status:success');
                        bettingInput.classList.add('n-textbox-status:warning');

                        //베팅 되는거 문구 없애기
                        const bettingPossible = document.querySelector('.betting-possible-msg');

                        if (bettingPossible) {
                            bettingPossible.remove();
                        }

                        // 배팅 불가능 문구 추가
                        if (!document.querySelector('.betting-impossible-msg')) {
                            let sectionHTML = '<p class="betting-impossible-msg ">배팅이 불가능합니다.보유 포인트를 확인해 주세요!</p>';
                            btnDiv.insertAdjacentHTML("beforebegin", sectionHTML);
                        }

                        isBettingPossible = false;
                    }

                })
                .catch(error => {
                    // 오류 처리
                    console.error("Error:", error);
                });
        } else {
            // 두 번째 클릭: 실제 배팅 요청
            const data = {
                pbgId: pbgId,
                bettingAmount: parseInt(bettingInput.value)
            };
            fetch(url + 'betting', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                })
                .then(data => {
                    if (data === "베팅 성공.") {
                        console.log("Betting response from server:", data);
                        alert("베팅이 성공적으로 처리되었습니다!");
                        // 배팅 완료 후 상태 초기화
                        isBettingPossible = false;
                        bettingInput.classList.remove('n-textbox-status:success');
                        const bettingPossibleMsg = document.querySelector('.betting-possible-msg');
                        if (bettingPossibleMsg) {
                            bettingPossibleMsg.remove();
                        }
                    } else {
                        bettingInput.classList.remove('n-textbox-status:success');
                        bettingInput.classList.add('n-textbox-status:warning');

                        //베팅 되는거 문구 없애기
                        const bettingPossible = document.querySelector('.betting-possible-msg');

                        if (bettingPossible) {
                            bettingPossible.remove();
                        }

                        // 배팅 불가능 문구 추가
                        if (!document.querySelector('.betting-impossible-msg')) {
                            let sectionHTML = '<p class="betting-impossible-msg ">배팅이 불가능합니다.보유 포인트를 확인해 주세요!</p>';
                            btnDiv.insertAdjacentHTML("beforebegin", sectionHTML);
                        }

                        isBettingPossible = false;

                    }
                })
                .catch(error => {
                    console.error("Error:", error);
                    alert("베팅 처리 중 오류가 발생했습니다.");
                });
        }
    });
});
