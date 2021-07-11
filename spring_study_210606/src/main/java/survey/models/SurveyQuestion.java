package survey.models;

import java.util.List;

public class SurveyQuestion {
	private String title;
	private List<String> options;
	
	public SurveyQuestion(String title) {
		this.title = title;
	}
	
	public SurveyQuestion(String title, List<String> options) {
		this.title = title;
		this.options = options;
	}

	public String getTitle() {
		return title;
	}

	public List<String> getOptions() {
		return options;
	}
	
	public boolean isChoice() {
		return options != null && !options.isEmpty();
	}
}
