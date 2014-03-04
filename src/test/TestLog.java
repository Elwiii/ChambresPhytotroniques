package test;

import chambresPhytotroniques.outils.Error;

public class TestLog {

	public static void main(String[] args) {

		Error.getError().error("Class", "Method", "Message",
				new Throwable("Throwable"));

	}
}
