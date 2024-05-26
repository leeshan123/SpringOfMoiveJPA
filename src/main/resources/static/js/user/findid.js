window.addEventListener('load', function(){
    const findBtn = this.document.querySelector(".find-btn");
    window.memberId;


    findBtn.onclick = function(e){
        // 현재 클릭되는 태그를 반환해줌
        console.log(e.target);

        //버튼 태그가 아닌건 실행되지 않게
        if(e.target.tagName != "BUTTON")
            return;

        let email = document.querySelector(".email-input").value;
        let name = document.querySelector(".name-input").value;
        

        if (email && name) {
            // 이메일 전송 전에 이름이랑 이메일이 있는지 확인
            const url = "/api/email/verify-user";
            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ email: email, name: name })
            })
            .then(response => response.json())
            .then(data => {
                if (data.id) {
                    // 반환받은 아이디가 있으면 저장
                    window.memberId = data.id;
                    // 이메일 전송
                    sendVerificationEmail(email);
                } else {
                    alert("존재하지 않습니다. 이름과 이메일을 다시 확인해주세요.");
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert("확인 중 오류가 발생했습니다.");
            });

        } else {
            alert("이름, 이메일 주소 모두 입력해주세요.");
        }
    }


    function sendVerificationEmail(email) {
        // AJAX를 사용하여 서버로 이메일 주소 전송
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "/api/email/sendVerificationEmail", true);
        xhr.setRequestHeader("Content-Type", "application/json");

        
        // CSRF 토큰 가져오기
        // let csrfToken = document.querySelector(".token").getAttribute("value");
        // xhr.setRequestHeader("X-CSRF-TOKEN", csrfToken); // CSRF 토큰 설정
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                // 요청이 완료되면 중복 요청 변수를 재설정합니다.
                isRequestInProgress = false;

                if (xhr.status === 200) {
                    // 서버에서 정상적인 응답을 받았을 때
                    let responseData = xhr.responseText;
                    alert("인증 이메일이 전송되었습니다.\n3분 안에 인증키를 입력해주세요.");

                    //버튼 박스 선택
                    const emailBtn = document.querySelector(".email_button");

                    // 새로운 div 엘리먼트 생성
                    const div = document.createElement("div");

                    // 오른쪽 정렬을 위해 스타일을 설정
                    div.style.textAlign = "right";

                    // 새로운 인풋 엘리먼트 생성
                    const input = document.createElement("input");
                    input.type = "text";
                    input.placeholder = "인증키 입력";

                    // 현재 시간을 보여줄 span 엘리먼트 생성
                    const timeSpan = document.createElement("span");

                    // 새로운 button 엘리먼트 생성
                    const button = document.createElement("button");
                    button.type = "button";
                    button.textContent = "확인";

                    // div 안에 input과 button을 추가
                    div.appendChild(timeSpan);
                    div.appendChild(input);
                    div.appendChild(button);

                    // 버튼에 클릭 이벤트 추가
                    button.addEventListener("click", function() {
                        if(input.value == responseData){
                            
                            // 유저 아이디를 받아옴
                            fetch('/api/member/' + window.memberId)
                            .then(response => {
                                if (!response.ok) {
                                    throw new Error('Network response was not ok');
                                }
                                return response.json();
                            })
                            .then(user => {
                                // 유저 정보 사용
                                console.log(user);
                                alert("회원님의 아이디는 [" + user.username + "] 입니다.");

                                // 새로운 페이지로 이동
                                window.location.href = 'findid';
                            })
                            .catch(error => {
                                console.error('There was a problem with the fetch operation:', error);
                            });


                            div.parentNode.removeChild(div);
                        }
                        else
                            alert("코드를 다시 한 번 확인해주세요.");
                    });

                    // email_button 요소의 다음 형제로 div 추가
                    // emailBtn.parentNode.insertBefore(div, emailBtn.nextSibling);

                    // email_button 요소에 새로운 div를 자식으로 추가
                    emailBtn.appendChild(div);

                    // 3분 후에 타임아웃을 실행하여 인증을 완료하지 않은 경우 처리.
                    let time = 180;
                    startTimer(time, timeSpan, div); //초와 초가 나올 노드와, 삭제할 노드
                    
                
                } else {
                    alert("이메일 전송에 실패했습니다.");
                }
            }
        };
        xhr.send(JSON.stringify({ email: email }));

    }

    // 타이머 설정 인자값으로 초, 노드를 실행
    function startTimer(sec, span, delDiv) {
        let timeLeft = sec;

        const timerInterval = setInterval(() => {
            const minutes = Math.floor(timeLeft / 60);
            const seconds = timeLeft % 60;
            const formattedTime = `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
            span.textContent = formattedTime;

            // 시간이 다 되면 타이머 중지 및 div 제거
            if (timeLeft === 0) {
                clearInterval(timerInterval);
                delDiv.parentNode.removeChild(delDiv);
            }

            timeLeft--;
        }, 1000);
    }

})