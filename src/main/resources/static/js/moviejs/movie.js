// const selectedMovie = document.querySelector(".childSelect");
// const inputBtn = document.querySelector(".input-btn");

// window.addEventListener("load",function(){
//     inputBtn.addEventListener("click",function(){
//         let xhr = new XMLHttpRequest();
//         xhr.open();

//     });
// })

document.addEventListener("DOMContentLoaded", function() {
    var parentSelect = document.getElementById("parentSelect");
    var childSelect = document.getElementById("childSelect");
    var submitButton = document.getElementById("submitButton");
    var movieListContainer = document.getElementById("movieListContainer");

    submitButton.addEventListener("click", function(e) {
        e.preventDefault(e);

        // 부모 선택값과 자식 선택값 가져오기
        var parentSelectValue = parentSelect.value;
        var childSelectValue = childSelect.value;
        
        // AJAX 요청 보내기
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/api/movie/vote-list?pS=" + parentSelectValue + "&cS=" + childSelectValue, true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var movieWeeks = JSON.parse(xhr.responseText);
                // 받은 데이터를 화면에 출력
                renderMovieList(movieWeeks);
            } else {
                // 에러 처리
                console.error(xhr.responseText);
            }
        };
        xhr.send();
    });

    // 영화 목록을 화면에 출력하는 함수
    function renderMovieList(movieWeeks) {
        // 이전에 출력된 내용 지우기
        movieListContainer.innerHTML = "";

        movieWeeks.forEach(function(movie) {
            var movieItem = document.createElement("div");
            movieItem.innerHTML = `
                <h3>${movie.kor_name}</h3>
                <p>TMDB ID: ${movie.tmdb_id}</p>
                <p>KOBIS ID: ${movie.kobis_id}</p>
                <p>영문 이름: ${movie.eng_name}</p>
                <p>출시일: ${movie.release_date}</p>
                <p>러닝 타임: ${movie.running_time}</p>
                <p>시청 등급: ${movie.watch_grade}</p>
                <p>영화 소개: ${movie.movie_intro}</p>
                <p>장르: ${movie.genre}</p>
                <img src="${movie.poster_url}" alt="포스터 이미지">
                <img src="${movie.logo_url}" alt="로고 이미지">
                <img src="${movie.main_img_url}" alt="메인 이미지">
                <p>출시 국가: ${movie.release_nation}</p>
            `;
            movieListContainer.appendChild(movieItem);
        });
    }
});
