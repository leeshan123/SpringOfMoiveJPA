window.addEventListener('load', function () {
    //베팅하기 누루는 버튼
    const openButtons = document.querySelectorAll('.betting-btn');


    //베팅 모달
    const closeBettingButton = document.getElementById('close-btn');
    const bettingModal = document.getElementById('betting-modal');
    const modalBackdrop = document.getElementById('modal-backdrop');
    const submitButton = document.getElementById('submit-btn');
    const bettingInput = document.getElementById('betting-input');
    const bettingCheckButton = document.getElementById('betting-check-btn');



    //DIV위에 데이터를 넣기 위해 사용
    const btnDiv = document.getElementById('btn-div');


    //로그인 모달
    const loginModal = document.getElementById('login-modal');
    const loginButton = document.getElementById('login-btn');
    const closeLoginButton = document.getElementById('close-login-btn');

    //베팅 완료 모달
    const finishModal = document.getElementById('finish-modal');
    const finishButton = document.getElementById('finish-btn');

    //체크박스
    const checkBoxes = document.querySelectorAll('.betting-checkbox');

    //체크박스 모달
    const checkboxModal = document.getElementById('checkbox-modal');
    const closeCheckboxButton = document.getElementById('close-checkbox-btn');
    //값을 가져오기 위해서 어쩔수없이 var 씀
    var selectedBettingValue = null;
    var pbgId = null;

    //현재 날짜
    const now = new Date();








    //API URL
    const url = '/api/playground/';


    //베팅 버튼을 눌렀을때
    openButtons.forEach(button => {
        button.addEventListener('click', function () {

            let bettingWrapper = button.closest('.betting_wrap');
            let leftBettingCheckBox = bettingWrapper.querySelector('#left-betting-checkbox');
            let rightBettingCheckBox = bettingWrapper.querySelector('#right-betting-checkbox');

            //체크가 안됏으면 실행 안되게
            if(leftBettingCheckBox.checked || rightBettingCheckBox.checked) {

                //selectedBettingValue 값 얻기

                if (leftBettingCheckBox.checked) {
                    selectedBettingValue = leftBettingCheckBox.value;
                } else if (rightBettingCheckBox.checked) {
                    selectedBettingValue = rightBettingCheckBox.value;
                }



                // 서버에 로그인 상태를 요청
                fetch(url + 'checkLogin')
                    .then(response => response.json())
                    .then(isLoggedIn => {
                        if (isLoggedIn) {
                            //로그인 상태일 때 모달을 열기
                            console.log("betting-btn");
                            bettingModal.classList.remove('d:none');
                            modalBackdrop.classList.remove('d:none');
                            bettingModal.classList.add('modal-fade-in');

                            //데이터의 id를 가져오기 위한 작업
                            pbgId = button.getAttribute('data-pbg-id');
                            submitButton.setAttribute('data-pbg-id', pbgId);
                            console.log(pbgId);

                        }
                        else {
                            loginModal.classList.remove('d:none');
                            modalBackdrop.classList.remove('d:none');
                            loginModal.classList.add('modal-fade-in');
                        }
                    })
                    .catch(error => {
                        console.error("Error", error);
                        alert('로그인 상태를 확인할 수 없습니다. 다시 시도해주세요.');
                    });
            }
            //체크 해달라는 모달창 띄우기
            else{

                checkboxModal.classList.remove('d:none');
                modalBackdrop.classList.remove('d:none');
                checkboxModal.classList.add('modal-fade-in');

            }


        });
    });

    //체크박스 안됏을떄 모달창 끌때
    closeCheckboxButton.addEventListener('click', function () {
        checkboxModal.classList.replace('modal-fade-in', 'modal-fade-out');

        setTimeout(() => {
            checkboxModal.classList.add('d:none');
            modalBackdrop.classList.add('d:none');
            checkboxModal.classList.remove('modal-fade-out');
        }, 130);

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

    //베팅 체크 버튼
    bettingCheckButton.addEventListener('click', function (e) {
        e.preventDefault();


        console.log("PBG ID on submit:", pbgId);
        console.log("Betting amount:", bettingInput.value);

            let data = {
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
                    if (data === "투자 가능.") {
                        bettingInput.classList.remove('n-textbox-status:warning');
                        bettingInput.classList.add('n-textbox-status:focus');

                        //베팅 안되는거 문구 없애기
                        const bettingImpossible = document.querySelector('.betting-impossible-msg');
                        if (bettingImpossible) {
                            bettingImpossible.remove();
                        }

                        // 배팅 가능 문구 추가
                        if (!document.querySelector('.betting-possible-msg')) {
                            let sectionHTML = '<p class="betting-possible-msg color:sub-1">투자가 가능합니다.</p>';
                            btnDiv.insertAdjacentHTML("beforebegin", sectionHTML);
                        }
                        } else {
                        bettingInput.classList.remove('n-textbox-status:focus');
                        bettingInput.classList.add('n-textbox-status:warning');

                        //베팅 되는거 문구 없애기
                        const bettingPossible = document.querySelector('.betting-possible-msg');

                        if (bettingPossible) {
                            bettingPossible.remove();
                        }

                        // 배팅 불가능 문구 추가
                        if (!document.querySelector('.betting-impossible-msg')) {
                            let sectionHTML = '<p class="betting-impossible-msg color:accent-1">투자가 불가능합니다.보유 포인트를 확인해 주세요!</p>';
                            btnDiv.insertAdjacentHTML("beforebegin", sectionHTML);
                        }
                    }

                    })
                    .catch(error => {
                        // 오류 처리
                        console.error("Error:", error);
                    });
    });

    // 제출 버튼을 눌렀을 때 그 게시판의 id와 input value 값을 가져옴
    submitButton.addEventListener('click', function (e) {
            e.preventDefault();

        console.log("Selected Betting Value:", selectedBettingValue);


            console.log("PBG ID on submit:", pbgId);
            console.log("Betting amount:", bettingInput.value);

            let data = {
                pbgId: pbgId,
                bettingAmount: parseInt(bettingInput.value),
                selectedBettingValue: parseInt(selectedBettingValue)
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
                    if (data === "투자 성공.") {
                        console.log("Betting response from server:", data);


                        bettingInput.classList.remove('n-textbox-status:focus');
                        const bettingPossibleMsg = document.querySelector('.betting-possible-msg');
                        if (bettingPossibleMsg) {
                            bettingPossibleMsg.remove();
                        }

                        bettingModal.classList.replace('modal-fade-in', 'modal-fade-out');

                        setTimeout(() => {
                            //베팅 모달 끝
                            bettingModal.classList.add('d:none');
                            bettingModal.classList.remove('modal-fade-out');

                            //finish 모달 시작
                            finishModal.classList.remove('d:none');
                            finishModal.classList.add('modal-fade-in');
                        }, 130);

                        // finish 버튼 누르면 다시 메인 페이지로 이동
                        finishButton.addEventListener('click', () => {
                            modalBackdrop.classList.add('d:none');
                            window.location.href = 'http://localhost/playground/main';
                        });

                    } else {
                        bettingInput.classList.remove('n-textbox-status:focus');
                        bettingInput.classList.add('n-textbox-status:warning');

                        //베팅 되는거 문구 없애기
                        const bettingPossible = document.querySelector('.betting-possible-msg');

                        if (bettingPossible) {
                            bettingPossible.remove();
                        }

                        // 배팅 불가능 문구 추가
                        if (!document.querySelector('.betting-impossible-msg')) {
                            let sectionHTML = '<p class="betting-impossible-msg color:accent-1">투자가 불가능합니다.보유 포인트를 확인해 주세요!</p>';
                            btnDiv.insertAdjacentHTML("beforebegin", sectionHTML);
                        }



                    }
                })
                .catch(error => {
                    console.error("Error:", error);
                    alert("투자 처리 중 오류가 발생했습니다.");
                });

    });

    checkBoxes.forEach(checkbox => {
        checkbox.addEventListener('change', () => {
            if (checkbox.checked) {
                checkBoxes.forEach(otherCheckbox => {
                    if (otherCheckbox !== checkbox) {
                        otherCheckbox.checked = false;
                    }
                });
            }
        });
    });

    const boardButton = document.getElementById('board-btn');

    boardButton.addEventListener('click',function (){
        location.href = '/community/board/list?c=betting';
    });











});
