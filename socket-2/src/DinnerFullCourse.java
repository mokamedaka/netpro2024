public class DinnerFullCourse {

	private Dish[] list = new Dish[5];// [0]-[4]の計5個

	public static void main(String[] args) {

		DinnerFullCourse fullcourse = new DinnerFullCourse();
		fullcourse.eatAll();
	}

	DinnerFullCourse() {

		list[0] = new Dish();
		list[0].setName("麦ごはん");
		list[0].setValune(10);
		list[1] = new Dish();
		list[1].setName("牛乳");
		list[1].setValune(20);
		list[2] = new Dish();
		list[2].setName("海藻サラダ");
		list[2].setValune(50);
		list[3] = new Dish();
		list[3].setName("ABCスープ");
		list[3].setValune(1);
		list[4] = new Dish();
		list[4].setName("鶏肉のレモン煮");
		list[4].setValune(20);
	}// DinnerFullCourse()コンストラクターエンド

	void eatAll() {
		StringBuilder str = new StringBuilder();

        for (Dish element : list) {
            str.append(element.getName()).append("=").append(element.getValune()).append(">");
        }


		System.out.println("今日の献立は" + str + "です。今日も頑張って勉強しましょう");
	}

}