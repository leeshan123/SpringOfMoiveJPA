
//스크롤 감지센서 부착
// const scrollAmount = 980;

// // 스크롤 컨텐츠, 버튼 배열
// const scrollContents = [
//   { content: document.querySelector(".scrable-content1"), leftBtn: document.querySelector(".scr-btn-left1"), rightBtn: document.querySelector(".scr-btn-right1") },
//   { content: document.querySelector(".scrable-content2"), leftBtn: document.querySelector(".scr-btn-left2"), rightBtn: document.querySelector(".scr-btn-right2") },
//   { content: document.querySelector(".scrable-content3"), leftBtn: document.querySelector(".scr-btn-left3"), rightBtn: document.querySelector(".scr-btn-right3") }
// ];

// // 좌측 부드럽게 스크롤
// function smoothScrollLeft(content, leftBtn, rightBtn) {
//   content.scrollBy({
//     left: -scrollAmount,
//     behavior: 'smooth'
//   });
//   updateButtonState(content, leftBtn, rightBtn);
// }

// // 우측 부드럽게 스크롤
// function smoothScrollRight(content, leftBtn, rightBtn) {
//   content.scrollBy({
//     left: scrollAmount,
//     behavior: 'smooth'
//   });
//   updateButtonState(content, leftBtn, rightBtn);
// }

// // 버튼 이벤트 리스너
// scrollContents.forEach(item => {
//   item.leftBtn.addEventListener("click", () => smoothScrollLeft(item.content, item.leftBtn, item.rightBtn));
//   item.rightBtn.addEventListener("click", () => smoothScrollRight(item.content, item.leftBtn, item.rightBtn));
//   item.content.addEventListener("scroll", () => updateButtonState(item.content, item.leftBtn, item.rightBtn));
// });

// // 스크롤 상태 업데이트 함수
// function updateButtonState(content, leftBtn, rightBtn) {
//   const maxScrollLeft = content.scrollWidth - content.clientWidth;
//   const scrollLeft = content.scrollLeft;

//   // 왼쪽 버튼 숨기기/보이기
//   if (scrollLeft === 0) {
//     leftBtn.style.display = 'none';
//   } else {
//     leftBtn.style.display = 'block';
//   }

//   // 오른쪽 버튼 숨기기/보이기
//   if (scrollLeft >= maxScrollLeft) {
//     rightBtn.style.display = 'none';
//   } else {
//     rightBtn.style.display = 'block';
//   }
// }



//스크롤감지 센서 + 스크롤 가능한 컨텐츠에만 버튼표시

const scrollAmount = 980;

// 스크롤 컨텐츠, 버튼 배열
const scrollContents = [
  { content: document.querySelector(".scrable-content1"), leftBtn: document.querySelector(".scr-btn-left1"), rightBtn: document.querySelector(".scr-btn-right1") },
  { content: document.querySelector(".scrable-content2"), leftBtn: document.querySelector(".scr-btn-left2"), rightBtn: document.querySelector(".scr-btn-right2") },
  { content: document.querySelector(".scrable-content3"), leftBtn: document.querySelector(".scr-btn-left3"), rightBtn: document.querySelector(".scr-btn-right3") }
];

// 좌측 부드럽게 스크롤
function smoothScrollLeft(content, leftBtn, rightBtn) {
  content.scrollBy({
    left: -scrollAmount,
    behavior: 'smooth'
  });
  updateButtonState(content, leftBtn, rightBtn);
}

// 우측 부드럽게 스크롤
function smoothScrollRight(content, leftBtn, rightBtn) {
  content.scrollBy({
    left: scrollAmount,
    behavior: 'smooth'
  });
  updateButtonState(content, leftBtn, rightBtn);
}

// 버튼 이벤트 리스너
scrollContents.forEach(item => {
  item.leftBtn.addEventListener("click", () => smoothScrollLeft(item.content, item.leftBtn, item.rightBtn));
  item.rightBtn.addEventListener("click", () => smoothScrollRight(item.content, item.leftBtn, item.rightBtn));
  item.content.addEventListener("scroll", () => updateButtonState(item.content, item.leftBtn, item.rightBtn));

  // 버튼 초기 숨김
  item.leftBtn.style.display = 'none';
  item.rightBtn.style.display = 'none';
});

// 스크롤 상태 업데이트 함수
function updateButtonState(content, leftBtn, rightBtn) {
  const maxScrollLeft = content.scrollWidth - content.clientWidth;
  const scrollLeft = content.scrollLeft;

  // 컨텐츠 너비가 클 때만 버튼 표시
  if (content.scrollWidth > content.clientWidth) {
    leftBtn.style.display = 'block';
    rightBtn.style.display = 'block';
  } else {
    leftBtn.style.display = 'none';
    rightBtn.style.display = 'none';
  }

  // 왼쪽 버튼 숨기기/보이기
  if (scrollLeft === 0) {
    leftBtn.style.display = 'none';
  } else {
    leftBtn.style.display = 'block';
  }

  // 오른쪽 버튼 숨기기/보이기
  if (scrollLeft >= maxScrollLeft) {
    rightBtn.style.display = 'none';
  } else {
    rightBtn.style.display = 'block';
  }
}

// 페이지 로드 시 버튼 상태 업데이트
window.addEventListener('load', () => {
  scrollContents.forEach(item => {
    updateButtonState(item.content, item.leftBtn, item.rightBtn);
  });
});
