window.addEventListener('load', function() {
    const deleteBtn = this.document.querySelector(".delete-btn");
    // 체크된 체크박스의 값을 저장할 배열 생성.
    const checkMovie = this.document.querySelectorAll(".check-content");
    const checkAll = document.querySelector("#check-all");

    // 전체 선택
    checkAll.addEventListener('change', function() {
        checkMovie.forEach(function(checkbox) {
            checkbox.checked = checkAll.checked;
        });
    });

    deleteBtn.onclick = function() {
        const apiUrl = `http://localhost/api/community-board/delete-board`;
        const checkedItems = [];
        Array.from(checkMovie).forEach((checkbox) => { // NodeList를 배열로 변환
            if (checkbox.checked) {
                checkedItems.push(checkbox.value);
            }
        });
        console.log(checkedItems);

        if(checkedItems.length == 0){
            alert("선택된 게시글이 없습니다.");
            return;
        }

        if (confirm("정말 삭제하시겠습니까?")){
            fetch(apiUrl, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(checkedItems)
            })
            .then(response => response.text())
            .then(data => {
                alert(data);
                // 페이지를 리다이렉트
                window.location.href = "/admin/community-board/boardlist";
            })
            .catch(error => console.error('Error:', error));
        }
    }

})