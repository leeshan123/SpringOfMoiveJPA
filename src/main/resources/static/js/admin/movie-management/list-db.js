window.addEventListener('load', function() {
    const deleteBtn = this.document.querySelector(".delete-btn");
    const checkMovie = this.document.querySelectorAll(".check-movie");
    // 체크된 체크박스의 값을 저장할 배열 생성.
    
    deleteBtn.onclick = function() {
        const apiUrl = `/api/movie/delete`;
        const checkedItems = []; 
        Array.from(checkMovie).forEach((checkbox) => { // NodeList를 배열로 변환
            if (checkbox.checked) {
                checkedItems.push(checkbox.value);
            }
        });
        console.log(checkedItems);
        
        if(checkedItems.length == 0){
            alert("선택된 영화가 없습니다.");
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
                // 페이지 리다이렉트하기
                
            })
            .catch(error => console.error('Error:', error));
        }
    }

})