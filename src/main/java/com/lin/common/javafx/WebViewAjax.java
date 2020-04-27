/**
 * 
 */
package com.lin.common.javafx;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * @author michael
 *
 */
public class WebViewAjax extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		WebView webView = new WebView();
		webView.getEngine().load("http://www.jquerysample.com/#BasicAJAX");
		webView.getEngine().setOnAlert((event)->{
			System.out.println(event.getData());
		});

		final Scene scene = new Scene(webView);
		stage.setScene(scene);
		stage.show();
	}
}