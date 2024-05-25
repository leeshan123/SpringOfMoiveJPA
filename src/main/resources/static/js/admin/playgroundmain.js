window.addEventListener('load', function () {
    const  openButton = document.getElementById('reg-btn');
    const deleteButtons = document.querySelectorAll('.delete-btn');
    const editButtons = document.querySelectorAll('.edit-btn');
    const closeButton = document.getElementById('close-btn');
    const modal = document.getElementById('modal');
    const modalBackdrop = document.getElementById('modal-backdrop');
    const realDeleteButton = document.getElementById('real-delete-btn');
    const pointButtons = document.querySelectorAll('.point-btn');
    const pointModal = document.getElementById('point-modal');
    const pointcloseButton = document.getElementById('point-close-btn');
    const givepointButton = document.getElementById('give-point-btn');

    const checkBoxes = document.querySelectorAll('.betting-checkbox');









    //오픈버튼
    openButton.addEventListener('click', () => handleButtonClick('http://localhost/admin/playground/reg'));

    //삭제버튼
    deleteButtons.forEach(button => {
        button.addEventListener('click', function () {
            const id = this.getAttribute('data-id');
            realDeleteButton.setAttribute('data-id', id);
            modal.classList.remove('d:none');
            modalBackdrop.classList.remove('d:none');
            modal.classList.add('modal-fade-in');
        });


    });


    //수정버튼
    editButtons.forEach(button => {
        button.addEventListener('click', function () {
            const id = this.getAttribute('data-id');
            window.location.href = `http://localhost/admin/playground/edit?id=${id}`;

        });
    });

    // 포인트 지급 버튼
    pointButtons.forEach(button => {
        button.addEventListener('click', function () {
            const id = this.getAttribute('data-id');
            givepointButton.setAttribute('data-id', id);
            console.log("id: " + id);
            realDeleteButton.setAttribute('data-id', id);
            pointModal.classList.remove('d:none');
            modalBackdrop.classList.remove('d:none');
            pointModal.classList.add('modal-fade-in');
        });


    });


    //삭제 모달 닫기 버튼
    closeButton.addEventListener('click', function () {
        modal.classList.replace('modal-fade-in', 'modal-fade-out');
        setTimeout(() => {
            modal.classList.add('d:none');
            modalBackdrop.classList.add('d:none');
            modal.classList.remove('modal-fade-out');
        }, 130);
    });

    //포인트 모달 닫기 버튼
    pointcloseButton.addEventListener('click', function () {
        pointModal.classList.replace('modal-fade-in', 'modal-fade-out');

        setTimeout(() => {
            pointModal.classList.add('d:none');
            modalBackdrop.classList.add('d:none');
            pointModal.classList.remove('modal-fade-out');
        }, 130);
    });

    //진짜 삭제하는 로직
    realDeleteButton.addEventListener('click', function() {
        const id = this.getAttribute('data-id');
        if (id) {
            window.location.href = `http://localhost/admin/playground/delete?id=${id}`;
            alert("게시글이 정상적으로 삭제되었습니다!");
        } else {
            console.error('게시글 삭제 중 오류가 발생되었습니다.');
        }
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

    givepointButton.addEventListener('click', function() {
        const id = this.getAttribute('data-id');
        let leftBettingCheckBox = document.querySelector('.left-betting-checkbox');
        let rightBettingCheckBox = document.querySelector('.right-betting-checkbox');
        let selectedBettingValue = null;

        //체크가 안됏으면 실행 안되게
        if(leftBettingCheckBox.checked || rightBettingCheckBox.checked) {
            if (leftBettingCheckBox.checked) {
                selectedBettingValue = leftBettingCheckBox.value;
            } else if (rightBettingCheckBox.checked) {
                selectedBettingValue = rightBettingCheckBox.value;
            }

            console.log("selectedBettingValue:" + selectedBettingValue);

            let data = {
                id: id,
                selectedBettingValue: parseInt(selectedBettingValue)
            };

            // API 호출
            fetch('/api/playground/givepoint', {
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
                    console.log('포인트 지급 성공:', data);
                    // 포인트 지급 성공 시 버튼 삭제 및 콘솔에 메시지 출력
                    // givepointButton.remove();
                    alert('포인트 지급 완료!');
                })
                .catch(error => {
                    console.error('포인트 지급 실패:', error);
                    // 실패 시 콘솔에 에러 메시지 출력
                });

        }else{
            alert('베팅 체크해야됨!');
        }




    });









});

function handleButtonClick(url) {
    window.location.href = url;

}




