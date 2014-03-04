package test;

import chambresPhytotroniques.vue.error.JFrameError;

public class TestGraphiqueError {

	public static void main(String[] args) {
		JFrameError jFrameError = new JFrameError();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		jFrameError
				.getScrollError()
				.setText(
						"a\nb\nc\nd\ne\nf\ng\nh\ni\nj\nk\nl\nm\nn\no\np\nq\nr\ns\nta\nb\nc\nd\ne\nf\ng\nh\ni\nj\nk\nl\nm\nn\no\np\nq\nr\ns\nta\nb\nc\nd\ne\nf\ng\nh\ni\nj\nk\nl\nm\nn\no\np\nq\nr\ns\nt");
	}

}
