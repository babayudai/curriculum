class Taiyaki{

    constructor(taste){
        this.taste = taste;
    }
        nakami (){
            console.log("中身は" + this.taste +"です");
        }

}
let anko = new Taiyaki("あんこ");
let cheese = new Taiyaki("チーズ");
let cream = new Taiyaki("クリーム");

anko.nakami();
cheese.nakami();
cream.nakami();