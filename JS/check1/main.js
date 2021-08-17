let numbers = [2, 5, 12, 13, 15, 18, 22];
//ここに答えを実装してください。↓↓↓
for(let i = 0;i < numbers.length; i++){
    if(numbers[i] % 2 === 0){
        console.log(numbers[i]);
    
        let num = numbers[i];

        function isEven() {
            console.log(num + 'は偶数です');
        }
    }
    isEven();
}
    
class car {
    constructor(gas,num){
        this.gas = gas;
        this.num = num;
    }
}
let getNumGas = new car("〇〇","△△");
console.log("ガソリンは"+ getNumGas.gas + "です。ナンバーは"+ getNumGas.num + "です");
