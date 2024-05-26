window.addEventListener('scroll', function() {
    // 스크롤 위치가 100px 이상이면 버튼을 나타냅니다
    if (window.scrollY > 100) {
        document.querySelector('.top-nav-btn').classList.add('show');
    } else {
        document.querySelector('.top-nav-btn').classList.remove('show');
    }
});

function scrollToTop() {
    window.scrollTo({
        top: 0,
        behavior: 'smooth' // 부드럽게 스크롤
    });
}