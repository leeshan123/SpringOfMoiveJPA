window.addEventListener('load', function () {
    const openButton = document.getElementById('modal-btn');
    const closeButton = document.getElementById('close-btn');
    const modal = document.getElementById('modal');
    const modalBackdrop = document.getElementById('modal-backdrop');
  
    openButton.addEventListener('click', function () {
      modal.classList.remove('d:none');
      modalBackdrop.classList.remove('d:none');
      modal.classList.add('modal-fade-in');
    });
  
    closeButton.addEventListener('click', function () {
      modal.classList.replace('modal-fade-in', 'modal-fade-out');
  
      setTimeout(() => {
        modal.classList.add('d:none');
        modalBackdrop.classList.add('d:none');
        modal.classList.remove('modal-fade-out');
      }, 130);
    });
  });