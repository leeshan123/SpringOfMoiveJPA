window.addEventListener('load', function () {
    const  openButton = document.getElementById('reg-btn');
    const  backButton = document.getElementById('back-btn');

    openButton.addEventListener('click', () => handleButtonClick('http://localhost/admin/playground/reg'));
    backButton.addEventListener('click', () => handleButtonClick('http://localhost/admin/playground/main'));


});

function handleButtonClick(url) {
    window.location.href = url;
}




