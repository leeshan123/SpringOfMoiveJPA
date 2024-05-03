window.addEventListener('load', function () {
    const btnNick = document.getElementById('btn-nick');
    const btnEmail = document.getElementById('btn-email');
    const btnPwd = document.getElementById('btn-pwd');
    const closeBtnNick = document.getElementById('close-btn-nick');
    const closeBtnEmail = document.getElementById('close-btn-email');
    const closeBtnPwd = document.getElementById('close-btn-pwd');
    const modalBackdrop = document.getElementById('modal-backdrop');
    const modalChgNic = document.getElementById('modal-chg-nick');
    const modalChgEmail = document.getElementById('modal-chg-email');
    const modalChgPwd = document.getElementById('modal-chg-pwd');
  
    btnNick.addEventListener('click', function () {
        modalChgNic.classList.remove('d:none');
        modalBackdrop.classList.remove('d:none');
        modalChgNic.classList.add('modal-fade-in');
    });
    btnEmail.addEventListener('click', function () {
        modalChgEmail.classList.remove('d:none');
        modalBackdrop.classList.remove('d:none');
        modalChgEmail.classList.add('modal-fade-in');
    });
    btnPwd.addEventListener('click', function () {
        modalChgPwd.classList.remove('d:none');
        modalBackdrop.classList.remove('d:none');
        modalChgPwd.classList.add('modal-fade-in');
    });
  
    closeBtnNick.addEventListener('click', function () {
        modalChgNic.classList.replace('modal-fade-in', 'modal-fade-out');
  
        setTimeout(() => {
            modalChgNic.classList.add('d:none');
            modalBackdrop.classList.add('d:none');
            modalChgNic.classList.remove('modal-fade-out');
        }, 130);
    });
    closeBtnEmail.addEventListener('click', function () {
        modalChgEmail.classList.replace('modal-fade-in', 'modal-fade-out');
  
        setTimeout(() => {
            modalChgEmail.classList.add('d:none');
            modalBackdrop.classList.add('d:none');
            modalChgEmail.classList.remove('modal-fade-out');
        }, 130);
    });
    closeBtnPwd.addEventListener('click', function () {
        modalChgPwd.classList.replace('modal-fade-in', 'modal-fade-out');
  
        setTimeout(() => {
            modalChgPwd.classList.add('d:none');
            modalBackdrop.classList.add('d:none');
            modalChgPwd.classList.remove('modal-fade-out');
        }, 130);
    });
  });