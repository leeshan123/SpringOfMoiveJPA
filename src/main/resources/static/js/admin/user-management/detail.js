Vue.createApp({
    data() {
        return {
            communityBoards: [],
            communityBoardComments: [],
            onelineReviews: [],
            // 초기 활성화된 버튼
            activeButton: 'board-list', 
            // 선택한 유저의 Id가 필요함
            id: null, 
            list: []
        };
    },
    methods: {
        fetchData(type) {
            let url = `/api/member/${type}?id=${this.id}`;
            fetch(url)
                .then(response => response.json())
                .then(data => {
                    if (type === 'board-list') {
                        this.communityBoards = data;
                        this.list = this.communityBoards;
                    } else if (type === 'comment-list') {
                        this.communityBoardComments = data;
                        this.list = this.communityBoardComments;
                    } else if (type === 'review-list') {
                        this.onelineReviews = data;
                        this.list = this.onelineReviews;
                    }
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                    alert('데이터를 가져오는 중 오류가 발생했습니다.');
                });
        },
        handleButtonClick(type) {
            this.activeButton = type;
            this.fetchData(type);
        },
        getIdFromUrl() {
            const params = new URLSearchParams(window.location.search);
            this.id = params.get('id');
        }
    },
    mounted() {
        // URL에서 id 값을 추출
        this.getIdFromUrl();
        // 초기 리스트를 communityBoards로 설정
        this.handleButtonClick('board-list');
    },
    template: `
    <div class="d:flex jc:end">
        <nav class="nav-contents mt:4 mb:10">
            <ul class="d:flex col-gap:1 jc:flex-end">
                <li>
                    <button type="button" :class="{ 'font-bold': activeButton === 'board-list' }" @click.prevent="handleButtonClick('board-list')">커뮤니티 게시판</button> |
                </li>
                <li>
                    <button type="button" :class="{ 'font-bold': activeButton === 'comment-list' }" @click.prevent="handleButtonClick('comment-list')">커뮤니티 댓글</button> |
                </li>
                <li>
                    <button type="button" :class="{ 'font-bold': activeButton === 'review-list' }" @click.prevent="handleButtonClick('review-list')">한줄 리뷰</button>
                </li>
            </ul>
        </nav>
    </div>
    <div class="content">
        <section v-if="activeButton === 'board-list'" v-for="item in list" :key="item.id" class="board-list bd-bottom border-width:2 pb:2 mb:2 ai:center">
            <h1>번호:{{ item.id }} </h1>
            <span class="fs:4">제목:{{ item.title }}</span>
            <span class="fs:2 color:base-6"> 날짜:{{ new Date(item.regDate).toLocaleDateString() }}</span>
        </section>
        <section v-if="activeButton === 'comment-list'" v-for="item in list" :key="item.id" class="comment-list bd-bottom border-width:2 pb:2 mb:2 ai:center">
            <h1>번호:{{ item.id }}</h1>
            <p>내용: {{ item.contents }}</p>
            <span class="fs:2 color:base-6">{{ new Date(item.regDate).toLocaleString() }}</span>
        </section>
        <section v-if="activeButton === 'review-list'" v-for="item in list" :key="item.id" class="review-list bd-bottom border-width:2 pb:2 mb:2 ai:center">
            <h1>번호:{{ item.id }}</h1>
            <p>내용: {{ item.comments }}</p>
            <div>
                <span class="fs:2 color:base-6">날짜:{{ new Date(item.regDate).toLocaleDateString() }}</span>
                <p>평점: {{ item.memberRate }}</p>
            </div>
        </section>
    </div>
    `
}).mount('.user-list');