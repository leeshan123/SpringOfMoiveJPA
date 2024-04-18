const scrableContent = document.querySelector(".scrable-content");
const scrBtnLeft = document.querySelector(".scr-btn-left");
const scrBtnRight = document.querySelector(".scr-btn-right");

const scrollAmount =980; 
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


