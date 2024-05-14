window.addEventListener('load', function () {
    const  backButton = document.getElementById('back-btn');
    backButton.addEventListener('click', () => handleButtonClick('http://localhost/admin/playground/main'));

    document.getElementById('search-btn').addEventListener('click', function() {
        var movieQuery = document.getElementById('movie_query').value;
        if(movieQuery) {
            window.location.href = '/admin/playground/search?movie_query=' + encodeURIComponent(movieQuery);
        }
    });

});

function handleButtonClick(url) {
    window.location.href = url;
}




