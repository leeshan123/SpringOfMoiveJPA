const { createApp } = Vue;

createApp({
    data() {
        return {
            list: [],
            page: 1, // 초기 페이지 설정
            category: '' // 초기 카테고리 설정
        }
    },
    methods: {
        async categoryClickHandler(category) {
            this.category = category; // 클릭된 카테고리 업데이트
            console.log("클릭", category);

            try {
                const response = await fetch(`http://localhost/api/community-boards/${category}?p=${this.page}`);
                const data = await response.json();
                this.list = data.list; // API 응답에서 리스트 데이터 추출하여 할당
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        }
    },
}).mount("main");
