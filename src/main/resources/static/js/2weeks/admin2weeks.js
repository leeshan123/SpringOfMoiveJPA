// document.addEventListener('DOMContentLoaded', function () {
//     const parentSelect = document.getElementById('parentSelect');
//     const childSelect = document.getElementById('childSelect');
//     const searchButton = document.getElementById('search-btn');
//     const resultList = document.querySelector(".result-list");

//     // 부모 select 요소가 변경될 때마다 자식 select 요소를 업데이트
//     parentSelect.addEventListener('change', function () {
//         childSelect.innerHTML = '';

//         const selectedValue = parentSelect.value;

//         switch (selectedValue) {
//             case '1':
//                 childSelect.add(new Option('2018', '2018'));
//                 childSelect.add(new Option('2019', '2019'));
//                 childSelect.add(new Option('2020', '2020'));
//                 childSelect.add(new Option('2021', '2021'));
//                 childSelect.add(new Option('2022', '2022'));
//                 childSelect.add(new Option('2023', '2023'));
//                 childSelect.add(new Option('2024', '2024'));
//                 break;
//             case '2':
//                 childSelect.add(new Option('SF', 'SF'));
//                 childSelect.add(new Option('가족', '가족'));
//                 childSelect.add(new Option('공연', '공연'));
//                 childSelect.add(new Option('공포(호러)', '공포(호러)'));
//                 childSelect.add(new Option('기타', '기타'));
//                 childSelect.add(new Option('다큐멘터리', '다큐멘터리'));
//                 childSelect.add(new Option('드라마', '드라마'));
//                 childSelect.add(new Option('멜로/로맨스', '멜로/로맨스'));
//                 childSelect.add(new Option('뮤지컬', '뮤지컬'));
//                 childSelect.add(new Option('미스터리', '미스터리'));
//                 childSelect.add(new Option('범죄', '범죄'));
//                 childSelect.add(new Option('사극', '사극'));
//                 childSelect.add(new Option('서부극(웨스턴)', '서부극(웨스턴)'));
//                 childSelect.add(new Option('스릴러', '스릴러'));
//                 childSelect.add(new Option('애니메이션', '애니메이션'));
//                 childSelect.add(new Option('액션', '액션'));
//                 childSelect.add(new Option('어드벤처', '어드벤처'));
//                 childSelect.add(new Option('전쟁', '전쟁'));
//                 childSelect.add(new Option('코미디', '코미디'));
//                 childSelect.add(new Option('판타지', '판타지'));
//                 break;
//             case '3':
//                 childSelect.add(new Option('롯데시네마', '롯데시네마'));
//                 childSelect.add(new Option('CGV', 'CGV'));
//                 childSelect.add(new Option('메가박스', '메가박스'));
//                 break;
//             default:
//                 break;
//         }
//     });

//     // 검색 버튼 클릭 이벤트 리스너
//     searchButton.addEventListener('click', function (e) {
//         e.preventDefault();

//         if (resultList) {
//             resultList.innerHTML = "";
//         }

//         console.log("SearchButton 클릭");

//         const parentValue = parentSelect.value;
//         const childValue = childSelect.value;

//         console.log("parentValue: " + parentValue);
//         console.log("childValue: " + childValue);

//         if (parentValue && childValue) {
//             let xhr = new XMLHttpRequest();
//             xhr.open('GET', `http://localhost/admin/playground/search?pS=${parentValue}&cS=${encodeURIComponent(childValue)}`, true);

//             xhr.onload = function () {
//                 if (xhr.status === 200) {
//                     console.log("성공");
//                     // console.log(xhr.responseText);
//                     try {
//                         let mlist = JSON.parse(xhr.responseText);
//                         console.log(mlist);

//                         let sectionHTML = '';  // sectionHTML 초기화

//                         for (let movie of mlist) {
//                             let releaseYear = new Date(movie.releaseDate).getFullYear();

//                             sectionHTML += `
//                                 <div class="movie d-flex jc-center ai-center gap-3 fl-dir-column">
//                                     <div>
//                                         <img class="bd-radius-1" src="${movie.posterUrl != null ? movie.posterUrl : '/image/no_image_poster.png'}" alt="/" width="100">
//                                     </div>
//                                     <div class="fw-bold">
//                                         <span class="korName">${movie.korName}</span>
//                                         <span>(${releaseYear})</span>
//                                         <span class="movieId d-none">${movie.id}</span>
//                                     </div>
//                                 </div>
//                             `;
//                         }

//                         resultList.insertAdjacentHTML("beforeend", sectionHTML);

//                         // .movie 클래스를 가진 요소를 선택하여 클릭 이벤트 리스너 추가
//                         const movies = document.querySelectorAll('.movie');
//                         movies.forEach(movie => {
//                             movie.addEventListener('click', function () {
//                                 const korName = this.querySelector('.korName').textContent;
//                                 const movieId = this.querySelector('.movieId').textContent;

//                                 const movieQuery = document.getElementById('movie_query');
//                                 movieQuery.value = `${korName} (${movieId})`;
//                             });
//                         });

//                     } catch (e) {
//                         console.error("JSON 파싱 오류: ", e);
//                     }
//                 } else {
//                     console.log(xhr.status);
//                 }
//             };

//             xhr.onerror = function () {
//                 console.error("AJAX 요청 오류");
//             };

//             xhr.send(); // XMLHttpRequest 보내기
//         } else {
//             console.log("parentValue 또는 childValue가 비어 있습니다.");
//         }
//     });
// });

// function handleButtonClick(url) {
//     window.location.href = url;
// }
