// const scrableContent = document.querySelector(".scrable-content");
// const scrBtnLeft = document.querySelector(".scr-btn-left");
// const scrBtnRight = document.querySelector(".scr-btn-right");

// const scrollAmount =980; 
// // 이벤트 실행1 회당 스크롤되는 양 (픽셀 단위임)

// function smoothScrollLeft() {
//     scrableContent.scrollBy({
//         left: -scrollAmount,
//         behavior: 'smooth' 
//     });
// }

// function smoothScrollRight() {
//     scrableContent.scrollBy({
//         left: scrollAmount,
//         behavior: 'smooth'
//     });
// }

// scrBtnLeft.addEventListener("click", smoothScrollLeft);

// scrBtnRight.addEventListener("click", smoothScrollRight);

//스크롤버튼 한번 클릭당 이동하는 범위(픽셀단위)
const scrollAmount = 980;

// 스크롤 컨텐츠,버튼 배열
const scrollContents = [
    { content: document.querySelector(".scrable-content1"), leftBtn: document.querySelector(".scr-btn-left1"), rightBtn: document.querySelector(".scr-btn-right1") },
    { content: document.querySelector(".scrable-content2"), leftBtn: document.querySelector(".scr-btn-left2"), rightBtn: document.querySelector(".scr-btn-right2") },
    { content: document.querySelector(".scrable-content3"), leftBtn: document.querySelector(".scr-btn-left3"), rightBtn: document.querySelector(".scr-btn-right3") }
];

// 좌측 부드럽게 스크롤
function smoothScrollLeft(content) {
    content.scrollBy({
        left: -scrollAmount,
        behavior: 'smooth'
    });
}

// 우측 부드럽게 스크롤
function smoothScrollRight(content) {
    content.scrollBy({
        left: scrollAmount,
        behavior: 'smooth'
    });
}

// 버튼 이벤트 리스너
scrollContents.forEach(item => {
    item.leftBtn.addEventListener("click", () => smoothScrollLeft(item.content));
    item.rightBtn.addEventListener("click", () => smoothScrollRight(item.content));
});
