class Renshu {

    // xを2倍にして返す関数
    public int doubleValue(int x) {
        return x * 2;
    }

    public int sumUpToN(int n) {
        int sum = 0;
        for(int i = 1;i <= n; i++){
            sum += i;
        }
    return sum;
    }

    public int sumFromPtoQ(int p, int q){
        if(p <= q){
            int sum2 = 0;
            for(int i = p;i <= q; i++){
                sum2 += i;
            }
            return sum2;
        }
        else{
            return -1;
        }
    }
    public int sumFromArrayIndex(int[] a, int index){
        if(index < 0 || index >= a.length){
            return -1;
        }
        else {
            int sum3 = 0;
            for(int i = index; i < a.length; i++){
                sum3 += a[i];
            }
            return sum3;
        }
    }

    public int selectMaxValue(int[] a){
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }
    
    int selectMaxIndex(int[] a){
        int max2 = a[0];
        int min2 = a[0];
        int maxIndex = 0;
        int minIndex = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max2) {
                max2 = a[i];
                maxIndex = i;
            } 
    
            else if (a[i] == max2 && i < maxIndex) {
                if(a[i] < min2){
                    min2 = a[i];
                    minIndex = i;
               }
            }
        }
        return maxIndex;
        return minIndex;
    }
    
    //ここに続きを実装していく。
