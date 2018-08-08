package com.marcel.browser.controller;

import java.util.ArrayList;

import com.marcel.browser.BrowserMain;
import com.marcel.browser.util.Constants;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;

public class RootController {

	@FXML
	private TextField searchBar;
	
	@FXML
	private WebView webView;
	
	private WebEngine webEngine;

	private ArrayList<String> history;
	private int currentWebsite = 0;
	
	private BrowserMain main;
	
	public void initBrowserMain(BrowserMain main) {
		this.main = main;
	}
	
	public RootController() {
		history = new ArrayList<String>();
	}
	
	@FXML
	void initialize() {
		searchBar.setPromptText("SEARCH URL: ");
		webEngine = webView.getEngine();
		
		webEngine.load(Constants.HOME);
		webEngine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
			
			@Override
			public void handle(WebEvent<String> event) {
				searchBar.setText(webEngine.getLocation());
				
				if(!history.contains(webEngine.getLocation()))
					history.add(webEngine.getLocation());
			}
		});
	}
	
	@FXML
	void onPreviousAction() {
		int index = history.size() - 1 + currentWebsite;
		if(index >= 0) {
			webEngine.load(history.get(index));
			currentWebsite--;
		}
	}
	
	@FXML
	void onNextAction() {
		int index = history.size() + 1 + currentWebsite;
		if(index <= history.size() - 1) {
			webEngine.load(history.get(index));
			currentWebsite++;
		}
	}
	
	@FXML
	void onSearchAction() {
		webEngine.load(searchBar.getText());
	}
	
	@FXML
	void onHomeAction() {
		webEngine.load(Constants.HOME);
	}
	
	@FXML
	void onRefreshAction() {
		webEngine.reload();
	}
	
	@FXML
	void onMinimizeAction() {
		main.getStage().setIconified(true);
	}
	
	@FXML
	void onCloseAction() {
		Platform.exit();
	}
}
