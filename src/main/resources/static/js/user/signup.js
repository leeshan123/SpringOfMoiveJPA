window.addEventListener("load", function() {
    const usernameInput = document.querySelector("#user-id");
    const statusSpan = document.querySelector(".status");

    usernameInput.addEventListener("input", function () {
        const username = usernameInput.value;

        // 아이디가 최소 길이에 도달했을 때만 중복 확인을 시작
        if (username.length >= 4) {
            checkUsername(username);
        } else {
            statusSpan.textContent = "4글자 이상 입력해주세요";
            statusSpan.classList.add("font-color-invalid");
            statusSpan.classList.remove("font-color-valid");
        }
    })



    function checkUsername(username) {
        // 서버에 아이디 중복 확인 요청을 보냅니다.
        fetch(`/api/member/check-username?name=${username}`)
            .then(response => response.json())
            .then(data => {
                // setCustomValidity은 input에 대한 유효성 상태를 설정해줌
                // ""을 제외한 모든 문자열은 유효하지 않은 상태임.
                if (data.isDuplicate) {
                    statusSpan.textContent = "아이디가 중복됩니다.";
                    statusSpan.classList.add("font-color-invalid");
                    statusSpan.classList.remove("font-color-valid");
                    usernameInput.setCustomValidity("아이디가 중복됩니다.");
                    
                    
                } else {
                    statusSpan.textContent = "사용 가능한 아이디입니다.";
                    statusSpan.classList.add("font-color-valid");
                    statusSpan.classList.remove("font-color-invalid");
                    usernameInput.setCustomValidity("");
                }
            })
            .catch(error => {
                console.error('Error checking username:', error);
                statusSpan.textContent = "오류가 발생했습니다.";
            });
    }

})