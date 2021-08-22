class Student {
    String name;

    public double calculateAvg(double[] data){
    double sum = 0;
    for( int i=0; i < data.length; i++){
        //配列の要素数を数えるメソッド→length
        sum += data[i];
             }
    double avg = sum/data.length;
    return avg;
    }
    public String judge(double avg) {
        String result;
        if( avg >= 40 ) {
            result ="passed";
        }else{
            result = "failed";
        }
        return result;
    }

public static void main(String [] args){
        Student a001 = new Student();
        a001.name="kishino";

        double[] data = { 60,50,40,20,90};

        double avg = a001.calculateAvg(data);
        String result = a001.judge(avg);

        System.out.println(avg);
        System.out.println(a001.name + "  " + result);
    }
}