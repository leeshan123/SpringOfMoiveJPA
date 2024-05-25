window.addEventListener("load", function() {

    const btnRestore = document.querySelectorAll(".restore");

    
    btnRestore.forEach(button => {
        button.onclick = function() {
            // 클릭한 버튼에서 가장 가까운 tr 요소를 찾음
            const userRow = this.closest('tr');
            const userId = userRow.querySelector(".user-id");

            const apiUrl = `http://localhost/api/member/restore`;

            if (confirm("해당 유저를 복구하시겠습니까?")){
                fetch(apiUrl, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: userId.textContent
                })
                .then(response => response.text())
                // 리턴되는 데이터
                .then(data => {
                    alert(data);
                    // 페이지를 리다이렉트
                    window.location.href = "/admin/user-management/ban-list";
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                    alert('복구 중 오류가 발생했습니다.');
                });
            }
        }
    })
    
});