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
