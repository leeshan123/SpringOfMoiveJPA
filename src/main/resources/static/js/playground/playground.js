window.addEventListener('load', function () {
    //베팅하기 누루는 버튼
    const openButtons = document.querySelectorAll('.betting-btn');

    //베팅 모달
    const closeBettingButton = document.getElementById('close-btn');
    const bettingModal = document.getElementById('betting-modal');
    const modalBackdrop = document.getElementById('modal-backdrop');
    const submitButton = document.getElementById('submit-btn');
    const bettingInput = document.getElementById('betting-input');

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

    //제출 버튼을 눌렀을때 그 게시판의 id와 input value 값을 가져옴
    submitButton.addEventListener('click',function (e){
        e.preventDefault();

        const pbgId = submitButton.getAttribute('data-pbg-id');
        console.log("PBG ID on submit:", pbgId);
        console.log("Betting amount:", bettingInput.value);

        const data = {
            pbgId: pbgId,
            bettingAmount: parseInt(bettingInput.value)
        };

        //AJAX로 POST 요청 해보기
        fetch(url+'betting-possible', {
            //포스트 요청
            method: 'POST',
            //JSON 형식으로 데이터 보내기
            headers: {
                'Content-Type': 'application/json'
            },
            // 데이터를 JSON 문자열로 변화하여 보냄.
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data=>{
                //서버로부터 받은 응답처리
                console.log(data);
            })
            .catch(error=>{
                //오류 처리
                console.error("Error:", error);
            });

        bettingInput.classList.add('n-textbox-status:warning');
        });

});
