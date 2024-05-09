window.addEventListener('load', function () {
    const openButtons = document.querySelectorAll('.modal-btn');
    const closeButtons = document.querySelectorAll('.close-btn');

    openButtons.forEach(openButton => {
        openButton.addEventListener('click', function () {
            const modal = this.nextElementSibling;
            const modalBackdrop = modal.nextElementSibling;
            modal.classList.remove('d:none');
            modalBackdrop.classList.remove('d:none');
            modal.classList.add('modal-fade-in');
        });
    });

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
});
