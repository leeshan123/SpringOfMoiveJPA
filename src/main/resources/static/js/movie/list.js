// const scrableContent = document.querySelector(".scrable-content");
// const scrBtnLeft = document.querySelector(".scr-btn-left");
// const scrBtnRight = document.querySelector(".scr-btn-right");

// function scrollUp() {
//     // scrableContent.scrollTop += 50;
//     document.querySelector(".scr-btn") += 50;
// }

// // 아래로 스크롤하는 함수
// function scrollDown() {
//     // scrableContent.scrollTop -= 50;
//     document.querySelector(".scrable-content").scrollTop -= 50; // 50 픽셀씩 아래로 이동
//     // scrableContent.scrollTop -= 50;
// }

// // 위로 스크롤 버튼에 onclick 이벤트 추가
// document.querySelector(".scr-btn-left").onclick = scrollUp;

// // 아래로 스크롤 버튼에 onclick 이벤트 추가
// document.querySelector(".scr-btn-right").onclick = scrollDown;

// const scrableContent = document.querySelector(".scrable-content");
// const scrBtnLeft = document.querySelector(".scr-btn-left");
// const scrBtnRight = document.querySelector(".scr-btn-right");

// function scrollUp() {
//     scrableContent.scrollLeft -= 50; // 50 픽셀씩 위로 이동
// }

// function scrollDown() {
//     scrableContent.scrollRight += 50; // 50 픽셀씩 아래로 이동
// }

// // 위로 스크롤 버튼에 onclick 이벤트 추가
// scrBtnLeft.onclick = scrollUp;

// // 아래로 스크롤 버튼에 onclick 이벤트 추가
// scrBtnRight.onclick = scrollDown;

// const scrableContent = document.querySelector(".scrable-content");
// const scrBtnLeft = document.querySelector(".scr-btn-left");
// const scrBtnRight = document.querySelector(".scr-btn-right");

// function scrollLeft() {
//     scrableContent.scrollLeft -= 50; // 50 픽셀씩 왼쪽으로 이동
// }

// function scrollRight() {
//     scrableContent.scrollLeft += 50; // 50 픽셀씩 오른쪽으로 이동
// }

// // 왼쪽으로 스크롤 버튼에 onclick 이벤트 추가
// scrBtnLeft.onclick = scrollLeft;

// // 오른쪽으로 스크롤 버튼에 onclick 이벤트 추가
// scrBtnRight.onclick = scrollRight;

// const scrableContent = document.querySelector(".scrable-content");
// const scrBtnLeft = document.querySelector(".scr-btn-left");
// const scrBtnRight = document.querySelector(".scr-btn-right");

// function smoothScrollLeft() {
//     const currentPosition = scrableContent.scrollLeft;
//     const newPosition = currentPosition - 50; // 왼쪽으로 50픽셀 스크롤
//     scrableContent.scrollTo({
//         left: newPosition,
//         behavior: 'smooth' // 부드러운 스크롤 애니메이션 적용
//     });
// }

// function smoothScrollRight() {
//     const currentPosition = scrableContent.scrollLeft;
//     const newPosition = currentPosition + 50; // 오른쪽으로 50픽셀 스크롤
//     scrableContent.scrollTo({
//         left: newPosition,
//         behavior: 'smooth' // 부드러운 스크롤 애니메이션 적용
//     });
// }

// // 왼쪽으로 스크롤 버튼에 onclick 이벤트 추가
// scrBtnLeft.addEventListener("click", smoothScrollLeft);

// // 오른쪽으로 스크롤 버튼에 onclick 이벤트 추가
// scrBtnRight.addEventListener("click", smoothScrollRight);


const scrableContent = document.querySelector(".scrable-content");
const scrBtnLeft = document.querySelector(".scr-btn-left");
const scrBtnRight = document.querySelector(".scr-btn-right");

const scrollAmount = 800; 
// 이벤트 실행1 회당 스크롤되는 양 (픽셀 단위임)

function smoothScrollLeft() {
    scrableContent.scrollBy({
        left: -scrollAmount,
        behavior: 'smooth' 
        // 부드러운 스크롤 애니메이션
    });
}

function smoothScrollRight() {
    scrableContent.scrollBy({
        left: scrollAmount,
        behavior: 'smooth'
         // 부드러운 스크롤 애니메이션 적용
    });
}

scrBtnLeft.addEventListener("click", smoothScrollLeft);

scrBtnRight.addEventListener("click", smoothScrollRight);


