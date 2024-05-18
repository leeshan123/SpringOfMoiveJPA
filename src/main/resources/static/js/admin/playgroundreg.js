window.addEventListener('load', function () {
    const backButton = document.getElementById('back-btn');
    const SearchButton = document.getElementById('search-btn');
    const resultList = document.querySelector(".result-list");

    backButton.addEventListener('click', () => handleButtonClick('http://localhost/admin/playground/main'));

    SearchButton.addEventListener('click', function(e) {

        e.preventDefault();

        if (resultList) {
            resultList.innerHTML = "";
        }

        console.log("SearchButton클릭");
        const movieQuery = document.getElementById('movie_query').value;

        console.log("movieQuery: " + movieQuery);

        if (movieQuery) {
            let xhr = new XMLHttpRequest();
            xhr.open('GET', 'http://localhost/admin/playground/search?movie_query=' + encodeURIComponent(movieQuery), true);

            xhr.onload = function() {
                if (xhr.status === 200) {
                    console.log("성공");
                    // console.log(xhr.responseText);
                try {
                    let mlist = JSON.parse(xhr.responseText);
                    console.log(mlist);

                    let sectionHTML = '';  // sectionHTML 초기화

                   for(let movie of mlist) {

                       let releaseYear = new Date(movie.releaseDate).getFullYear();

                       sectionHTML += `
                       <div class="movie d:flex jc:center ai:center gap:3 fl-dir:column" >
                           <div>
                           <img
                           class="bd-radius:1" src= "${movie.posterUrl != null ? movie.posterUrl : '/image/no_image_poster.png'}" alt="/"
                           width="100">
                           </div>
                           <div class = "fw:bold">
                           <span class = "korNmae">${movie.korName}</span>
                           <span>(${releaseYear})</span>
                           <span class="movieId d:none">${movie.id}</span>
                           </div>
                       </div>
                            
                       `;
                   }

                    resultList.insertAdjacentHTML("beforeend",sectionHTML);

                    // .movie 클래스를 가진 요소를 선택하여 클릭 이벤트 리스너 추가
                    const movies = document.querySelectorAll('.movie');
                    movies.forEach(movie => {
                        movie.addEventListener('click', function() {
                            const korName = this.querySelector('.korNmae').textContent;
                            const movieId = this.querySelector('.movieId').textContent;

                            const movieQuery = document.getElementById('movie_query');
                            movieQuery.value = `${korName} (${movieId})`;
                        });
                    });



                } catch(e) {
                    console.error("JSON 파싱 오류: ", e);
                }
                } else {
                    console.log(xhr.status);
                }
            };

            xhr.onerror = function() {
                console.error("AJAX 요청 오류");
            };

            xhr.send(); // XMLHttpRequest 보내기
        } else {
            console.log("movieQuery가 비어 있습니다.");
        }
    });







});

function handleButtonClick(url) {
    window.location.href = url;
}




