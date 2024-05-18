window.addEventListener('load', function () {
    const  openButton = document.getElementById('reg-btn');


    openButton.addEventListener('click', () => handleButtonClick('http://localhost/admin/playground/reg'));

    const deleteButtons = document.querySelectorAll('.delete-btn');
    const closeButton = document.getElementById('close-btn');
    const modal = document.getElementById('modal');
    const modalBackdrop = document.getElementById('modal-backdrop');
    const realDeleteButton = document.getElementById('real-delete-btn');

    deleteButtons.forEach(button => {
        button.addEventListener('click', function () {
            const id = this.getAttribute('data-id');
            realDeleteButton.setAttribute('data-id', id);
            modal.classList.remove('d:none');
            modalBackdrop.classList.remove('d:none');
            modal.classList.add('modal-fade-in');
        });
    });

    closeButton.addEventListener('click', function () {
        modal.classList.replace('modal-fade-in', 'modal-fade-out');

        setTimeout(() => {
            modal.classList.add('d:none');
            modalBackdrop.classList.add('d:none');
            modal.classList.remove('modal-fade-out');
        }, 130);
    });

    realDeleteButton.addEventListener('click', function() {
        const id = this.getAttribute('data-id');
        if (id) {
            window.location.href = `http://localhost/admin/playground/delete?id=${id}`;
        } else {
            console.error('No id found for deletion');
        }
    });


});

function handleButtonClick(url) {
    window.location.href = url;

}




