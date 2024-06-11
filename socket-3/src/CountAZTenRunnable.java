//package thread;

public class CountAZTenRunnable implements Runnable {
    private char character;

    // コンストラクタでカウントする文字を指定
    public CountAZTenRunnable(char character) {
        this.character = character;
    }

    // メインメソッド
    public static void main(String[] args) {
        // 'a' から 'z' までのスレッドを作成して開始
        for (char c = 'a'; c <= 'z'; c++) {
            CountAZTenRunnable runnable = new CountAZTenRunnable(c);
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }

    // スレッドが実行するコード
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(character + ": " + i);
                Thread.sleep(1000);  // 1000ミリ秒（1秒）間スリープ
            }
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }
}
