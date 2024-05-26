const{ createApp } = Vue

createApp({

    data(){
    
        return{
            pwd1:'',
            pwd2:'',
            misMatch:false
        };
    },
    methods:{

        check(){
            if(this.pwd1!==this.pwd2)
            misMatch=true;
            console.log("비밀번호가 틀려용")
        }
    }
}).mount('.password2')