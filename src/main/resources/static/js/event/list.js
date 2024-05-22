Vue.createApp({
    data(){
        return{
            list:[],
            activeButton: null // 클릭된 버튼을 추적할 변수 추가
        }
    },
    methods:{
        ongoingEventHandler(){
            this.fetchGoingData();
            if (this.activeButton) {
                this.activeButton.classList.remove('font-bold'); // 이전에 활성화된 버튼에서 font-bold 클래스 제거
            }
            this.activeButton = event.target; // 클릭된 버튼을 추적
            this.activeButton.classList.add('font-bold'); // font-bold 클래스 추가
        },
        endedEventHandler(){
            this.fetchEndedData();
            if (this.activeButton) {
                this.activeButton.classList.remove('font-bold'); // 이전에 활성화된 버튼에서 font-bold 클래스 제거
            }
            this.activeButton = event.target; // 클릭된 버튼을 추적
            this.activeButton.classList.add('font-bold'); // font-bold 클래스 추가
        },
        formatDate(date) {
            return `${date.getFullYear()}.${String(date.getMonth() + 1).padStart(2, '0')}.${String(date.getDate()).padStart(2, '0')}`;
        },
        fetchGoingData() {
            // Fetch API를 사용하여 데이터를 가져오는 요청 보내기
            fetch('/api/event/ongoing')
                .then(response => {
                    // 응답을 JSON 형식으로 변환하여 처리
                    return response.json();
                })
                .then(data => {
                    // 날짜 형식 변환
                    data.forEach(item => {
                        item.startDate = new Date(item.startDate);
                        item.endDate = new Date(item.endDate);
                    });
                    // 서버로부터 받은 데이터를 list에 할당
                    this.list = data;
                })
                .catch(error => {
                    console.error('데이터를 가져오는 중 오류 발생:', error);
                });
        },
        fetchEndedData() {
            // Fetch API를 사용하여 데이터를 가져오는 요청 보내기
            fetch('/api/event/ended')
                .then(response => {
                    // 응답을 JSON 형식으로 변환하여 처리
                    return response.json();
                })
                .then(data => {
                    // 날짜 형식 변환
                    data.forEach(item => {
                        item.startDate = new Date(item.startDate);
                        item.endDate = new Date(item.endDate);
                    });
                    // 서버로부터 받은 데이터를 list에 할당
                    this.list = data;
                })
                .catch(error => {
                    console.error('데이터를 가져오는 중 오류 발생:', error);
                });
        }
    },
    mounted() {
        this.fetchGoingData(); // Vue 앱이 마운트될 때 데이터를 가져오도록 호출
    },
    template:`
    <div class="d:flex jc:end">
        <nav class="nav-contents mt:4 mb:10">
            <ul class="d:flex col-gap:1 jc:flex-end">
                <li>
                    <button type="button" @click.prevent="ongoingEventHandler">진행중인 이벤트</button> |
                </li>
                <li>
                    <button type="button" @click.prevent="endedEventHandler">종료된 이벤트</button>
                </li>
            </ul>
        </nav>
    </div>
    <div class="content">
        <section v-for="menu in list" class="event-card">
        <a :href="'detail?id=' + menu.id">
            <div class="p:4">
                <h1>{{ menu.title }}</h1>
                <div class="event-card-period">
                    <span>{{ formatDate(menu.startDate) }}</span>
                    ~
                    <span>{{ formatDate(menu.endDate) }}</span>
                </div>
            </div>
            <div class="event-card-img_block">
                <img src="/image/제목 없음.png" alt="이미지">
            </div>
        </a>
        </section>
    </div>
    `
}).mount('.list1');
