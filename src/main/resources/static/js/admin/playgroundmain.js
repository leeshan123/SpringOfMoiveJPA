window.addEventListener('load', function () {
    const  openButton = document.getElementById('reg-btn');


    openButton.addEventListener('click', () => handleButtonClick('http://localhost/admin/playground/reg'));



});

function handleButtonClick(url) {
    window.location.href = url;
}




