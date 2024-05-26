window.addEventListener('load', function () {

    const btnDelete = document.querySelector(".delete");
    const userId = this.document.querySelector("#user-id");
    
    btnDelete.onclick = function(){

        const apiUrl = `/api/member/delete`;

        if (confirm("정말 삭제하시겠습니까?")){
            fetch(apiUrl, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userId.value)
            })
            .then(response => response.text())
            // 리턴되는 데이터
            .then(data => {
                alert(data);
                // 페이지를 리다이렉트
                window.location.href = "/";
            })
            .catch(error => {
                console.error('Error fetching data:', error);
                alert('삭제 중 오류가 발생했습니다.');
            });
        }
    }

    const modals = {
        'nick': {
            'btn': document.getElementById('btn-nick'),
            'modal': document.getElementById('modal-chg-nick'),
            'closeBtn': document.getElementById('close-btn-nick')
        },
        'email': {
            'btn': document.getElementById('btn-email'),
            'modal': document.getElementById('modal-chg-email'),
            'closeBtn': document.getElementById('close-btn-email')
        },
        'pwd': {
            'btn': document.getElementById('btn-pwd'),
            'modal': document.getElementById('modal-chg-pwd'),
            'closeBtn': document.getElementById('close-btn-pwd')
        }
    };
    const modalBackdrop = document.getElementById('modal-backdrop');
  
    function showModal(modalId) {
        const modal = modals[modalId].modal;
        modal.classList.remove('d:none');
        modalBackdrop.classList.remove('d:none');
        modal.classList.add('modal-fade-in');
    }
  
    function hideModal(modalId) {
        const modal = modals[modalId].modal;
        modal.classList.replace('modal-fade-in', 'modal-fade-out');
  
        setTimeout(() => {
            modal.classList.add('d:none');
            modalBackdrop.classList.add('d:none');
            modal.classList.remove('modal-fade-out');
        }, 130);
    }
  
    for (const key in modals) {
        if (modals.hasOwnProperty(key)) {
            const modal = modals[key];
  
            modal.btn.addEventListener('click', function () {
                showModal(key);
            });
  
            modal.closeBtn.addEventListener('click', function () {
                hideModal(key);
            });
        }
    }
    // const btnNick = document.getElementById('btn-nick');
    // const btnEmail = document.getElementById('btn-email');
    // const btnPwd = document.getElementById('btn-pwd');
    // const closeBtnNick = document.getElementById('close-btn-nick');
    // const closeBtnEmail = document.getElementById('close-btn-email');
    // const closeBtnPwd = document.getElementById('close-btn-pwd');
    // const modalBackdrop = document.getElementById('modal-backdrop');
    // const modalChgNic = document.getElementById('modal-chg-nick');
    // const modalChgEmail = document.getElementById('modal-chg-email');
    // const modalChgPwd = document.getElementById('modal-chg-pwd');
  
    // btnNick.addEventListener('click', function () {
    //     modalChgNic.classList.remove('d:none');
    //     modalBackdrop.classList.remove('d:none');
    //     modalChgNic.classList.add('modal-fade-in');
    // });
    // btnEmail.addEventListener('click', function () {
    //     modalChgEmail.classList.remove('d:none');
    //     modalBackdrop.classList.remove('d:none');
    //     modalChgEmail.classList.add('modal-fade-in');
    // });
    // btnPwd.addEventListener('click', function () {
    //     modalChgPwd.classList.remove('d:none');
    //     modalBackdrop.classList.remove('d:none');
    //     modalChgPwd.classList.add('modal-fade-in');
    // });
  
    // closeBtnNick.addEventListener('click', function () {
    //     modalChgNic.classList.replace('modal-fade-in', 'modal-fade-out');
  
    //     setTimeout(() => {
    //         modalChgNic.classList.add('d:none');
    //         modalBackdrop.classList.add('d:none');
    //         modalChgNic.classList.remove('modal-fade-out');
    //     }, 130);
    // });
    // closeBtnEmail.addEventListener('click', function () {
    //     modalChgEmail.classList.replace('modal-fade-in', 'modal-fade-out');
  
    //     setTimeout(() => {
    //         modalChgEmail.classList.add('d:none');
    //         modalBackdrop.classList.add('d:none');
    //         modalChgEmail.classList.remove('modal-fade-out');
    //     }, 130);
    // });
    // closeBtnPwd.addEventListener('click', function () {
    //     modalChgPwd.classList.replace('modal-fade-in', 'modal-fade-out');
  
    //     setTimeout(() => {
    //         modalChgPwd.classList.add('d:none');
    //         modalBackdrop.classList.add('d:none');
    //         modalChgPwd.classList.remove('modal-fade-out');
    //     }, 130);
    // });
  });